package com.jane.test;


import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.jdbc.Work;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jane.model.News;
import com.jane.model.Pay;
import com.jane.model.Worker;

public class HibernateTest {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init(){
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
		sessionFactory = new MetadataSources(ssr).buildMetadata().buildSessionFactory();
		
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@After
	public void destroy(){
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	@Test
	public void testComponent(){
		Worker worker = new Worker();
		Pay pay = new Pay();
		
		pay.setMonthlyPay(1000);
		pay.setYearPay(80000); 
		pay.setVocationWithPay(5);
		
		worker.setAge(30);
		worker.setName("ABCD");
		worker.setPay(pay);
		
		session.save(worker);
		System.out.println(worker);
	}
	@Test
	public void testBlob() throws Exception{
		News news = new News();
		news.setAuthor("cc");
		news.setContent("CONTENT");
		news.setDate(new Date());
		news.setDescrible("DESC");
		news.setTitle("CC");
		
		InputStream stream = new FileInputStream("1.jpg");
		Blob image = Hibernate.getLobCreator(session)
				              .createBlob(stream, stream.available());
		news.setPicture(image);
		
		session.save(news);
		System.out.println(news);
//		
//		News news2 = (News) session.get(News.class, news.getId());
//		Blob image2 = news2.getPicture();
//		
//		InputStream in = image2.getBinaryStream();
//		System.out.println(in.available()); 
	}
	
	@Test
	public void testPropertyUpdate(){
		News news = (News) session.get(News.class, 1);
		news.setTitle("aaaa"); 
		
		System.out.println(news.getDescrible());
		System.out.println(news.getDate().getClass()); 
	}
	
	@Test
	public void testIdGenerator() throws InterruptedException{
		News news = new News("AA", "aa", new java.sql.Date(new Date().getTime()));
		session.save(news); 
		
//		Thread.sleep(5000); 
	}
	
	@Test
	public void testDynamicUpdate(){
		News news = (News) session.get(News.class, 1);
		news.setAuthor("AA");
		
	}
	
	@Test
	public void testDoWork(){
		session.doWork(new Work() {
			
			public void execute(Connection connection) throws SQLException {
				System.out.println(connection); 
				String sql = "{call proc1(?)}";
				//调用存储过程. 
				CallableStatement prepareCall = connection.prepareCall(sql);
				//设置返回值
				prepareCall.registerOutParameter(1, java.sql.Types.INTEGER);
				//执行语句
				boolean executeUpdate = prepareCall.execute();
				int result = prepareCall.getInt(1);//得到返回值
				System.out.println(" result: "+result);
			}

		});
	}
	
	/**
	 * evict: 从 session 缓存中把指定的持久化对象移除
	 */
	@Test
	public void testEvict(){
		News news1 = (News) session.get(News.class, 1);
		News news2 = (News) session.get(News.class, 2);
		
		news1.setTitle("CC");
		news2.setTitle("DD");
		
		session.evict(news1); 
	}
	
	/**
	 * delete: 执行删除操作. 只要 OID 和数据表中一条记录对应, 就会准备执行 delete 操作
	 * 若 OID 在数据表中没有对应的记录, 则抛出异常
	 * 
	 * 可以通过设置 hibernate 配置文件 hibernate.use_identifier_rollback 为 true,
	 * 使删除对象后, 把其 OID 置为  null
	 */
	@Test
	public void testDelete(){
//		News news = new News();
//		news.setId(11);
		
		News news = (News) session.get(News.class, 2);
		session.delete(news); 
		
		System.out.println(news);
	}
	/**
	 * 注意:
	 * 1. 若 OID 不为 null, 但数据表中还没有和其对应的记录. 会抛出一个异常. 
	 * 2. 了解: OID 值等于 id 的 unsaved-value 属性值的对象, 也被认为是一个游离对象
	 */
	@Test
	public void testSaveOrUpdate(){
		News news = new News("FFF", "fff", new Date());
		news.setId(11);
		
		session.saveOrUpdate(news); 
	}
	
	/**
	 * update:
	 * 1. 若更新一个持久化对象, 不需要显示的调用 update 方法. 因为在调用 Transaction
	 * 的 commit() 方法时, 会先执行 session 的 flush 方法.
	 * 2. 更新一个游离对象, 需要显式的调用 session 的 update 方法. 可以把一个游离对象
	 * 变为持久化对象
	 * 
	 * 需要注意的:
	 * 1. 无论要更新的游离对象和数据表的记录是否一致, 都会发送 UPDATE 语句. 
	 *    如何能让 updat 方法不再盲目的出发 update 语句呢 ? 在 .hbm.xml 文件的 class 节点设置
	 *    select-before-update=true (默认为 false). 但通常不需要设置该属性. 
	 * 
	 * 2. 若数据表中没有对应的记录, 但还调用了 update 方法, 会抛出异常
	 * 
	 * 3. 当 update() 方法关联一个游离对象时, 
	 * 如果在 Session 的缓存中已经存在相同 OID 的持久化对象, 会抛出异常. 因为在 Session 缓存中
	 * 不能有两个 OID 相同的对象!
	 *    
	 */
	@Test
	public void testUpdate(){
		News news = (News) session.get(News.class, 1);
		
		transaction.commit();
		session.close();
		
//		news.setId(100);

		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
//		news.setAuthor("SUN"); 
		
//		News news2 = (News) session.get(News.class, 1);
		session.update(news);
	}
	
	/**
	 * get VS load:
	 * 
	 * 1. 执行 get 方法: 会立即加载对象. 
	 *    执行 load 方法, 若不使用该对象, 则不会立即执行查询操作, 而返回一个代理对象
	 *    
	 *    get 是 立即检索, load 是延迟检索. 
	 * 
	 * 2. load 方法可能会抛出 LazyInitializationException 异常: 在需要初始化
	 * 代理对象之前已经关闭了 Session
	 * 
	 * 3. 若数据表中没有对应的记录, Session 也没有被关闭.  
	 *    get 返回 null
	 *    load 若不使用该对象的任何属性, 没问题; 若需要初始化了, 抛出异常.  
	 */
	@Test
	public void testLoad(){
		
		News news = (News) session.load(News.class, 10);
		System.out.println(news.getClass().getName()); 
//		System.out.println("*************************");
//		session.close();
		System.out.println(news); 
	}
	
	@Test
	public void testGet(){
		News news = (News) session.get(News.class, 10);
//		System.out.println(news.getClass().getName()); 
//		session.close();
		System.out.println("****************");
		System.out.println(news); 
	}
	
	/**
	 * persist(): 也会执行 INSERT 操作
	 * 
	 * 和 save() 的区别 : 
	 * 在调用 persist 方法之前, 若对象已经有 id 了, 则不会执行 INSERT, 而抛出异常
	 */
	@Test
	public void testPersist(){
		News news = new News();
		news.setTitle("EE");
		news.setAuthor("ee");
		news.setDate(new Date());
		news.setId(200); 
		
		session.persist(news); 
	}
	
	/**
	 * 1. save() 方法
	 * 1). 使一个临时对象变为持久化对象
	 * 2). 为对象分配 ID.
	 * 3). 在 flush 缓存时会发送一条 INSERT 语句.
	 * 4). 在 save 方法之前的 id 是无效的
	 * 5). 持久化对象的 ID 是不能被修改的!
	 */
	@Test
	public void testSave(){
		News news = new News();
		news.setTitle("CC");
		news.setAuthor("cc");
		news.setDate(new Date());
		news.setId(100); //这里设置id无效
		
		System.out.println(news);
		
//		session.save(news);//为对象分配OID
		session.persist(news);

		System.out.println(news);
		news.setId(101); //不允许修改id
	}
	
	/**
	 * clear(): 清理缓存
	 */
	@Test
	public void testClear(){
		News news1 = (News) session.get(News.class, 1);
		
		session.clear();
		
		News news2 = (News) session.get(News.class, 1);
	}
	
	/**
	 * refresh(): 会强制发送 SELECT 语句, 以使 Session 缓存中对象的状态和数据表中对应的记录保持一致!
	 */
	@Test
	public void testRefresh(){
		News news = (News) session.get(News.class, 1);
		System.out.println(news);
		
		session.refresh(news); 
		System.out.println(news); 
	}
	
	@Test
	public void testSessionFlush2(){
		News news = new News("JAVA", "SUN", "orm", new Date(new java.util.Date().getTime()), null, null);
		session.save(news);
	}
	
	@Test
	public void testSessionFlush(){
		News news = (News) session.get(News.class, 1);
		news.setAuthor("Oracle");
		
//		session.flush();
//		System.out.println("flush");
		
		News news2 = (News) session.createCriteria(News.class).list().get(0);
		System.out.println(news2);
	}
	
	@Test
	public void test1(){
		News news = (News) session.get(News.class, 1);
		System.out.println(news); 
		
		News news2 = (News) session.get(News.class, 1);
		System.out.println(news2);
	}	

	@Test
	public void test() {
		
		System.out.println("test...");
		
		//1. 创建一个 SessionFactory 对象
		SessionFactory sessionFactory = null;
		
		//2. 创建 Configuration 对象: 对应 hibernate 的基本配置信息和 对象关系映射信息
//		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		
		//4.0 之前这样创建
//		sessionFactory = configuration.buildSessionFactory();
		
		//3. 创建一个 ServiceRegistry 对象: hibernate 4.x 新添加的对象
		//hibernate 的任何配置和服务都需要在该对象中注册后才能有效.
//		ServiceRegistry serviceRegistry = 
//				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
//				                            .buildServiceRegistry();
		
//		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		//4.创建一个 SessionFactory 对象
//		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
		sessionFactory = new MetadataSources(ssr).buildMetadata().buildSessionFactory();
		
		//5. 创建一个 Session 对象
		Session session = sessionFactory.openSession();
		
		//6. 开启事务
		Transaction transaction = session.beginTransaction();
		
		//7. 执行保存操作
		News news =new News("hibernate", "jane", "orm", new Date(new java.util.Date().getTime()), null, null);
		System.out.println(news);
		session.save(news);
		
		//8. 提交事务 
		transaction.commit();
		
		//9. 关闭 Session
		session.close();
		
		//10. 关闭 SessionFactory 对象
		sessionFactory.close();
	}
	
}
