package com.jane.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jane.one2one.foreign.Department;
import com.jane.one2one.foreign.Manager;

public class HibernateTest5 {
	/**
	 * 双向一对一基于外键
	 */

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
	public void testGet2(){
		//在查询没有外键的实体对象时, 使用的左外连接查询, 一并查询出其关联的对象
		//并已经进行初始化. 
		Manager mgr = (Manager) session.get(Manager.class, 1);
		System.out.println(mgr.getMgrName()); 
		System.out.println(mgr.getDept().getDeptName()); 
	}
	
	@Test
	public void testGet(){
		//1. 默认情况下对关联属性使用懒加载
		Department dept = (Department) session.get(Department.class, 1);
		System.out.println(dept.getDeptName()); 
		
		//2. 所以会出现懒加载异常的问题. 
//		session.close();
//		Manager mgr = dept.getMgr();
//		System.out.println(mgr.getClass()); 
//		System.out.println(mgr.getMgrName()); 
		
		//3. 查询 Manager 对象的连接条件应该是 dept.manager_id = mgr.manager_id
		//而不应该是 dept.dept_id = mgr.manager_id
		Manager mgr = dept.getMgr();
		System.out.println(mgr.getMgrName()); 
		
	}
	
	@Test
	public void testSave(){
		
		Department department = new Department();
		department.setDeptName("DEPT-BB");
		
		Manager manager = new Manager();
		manager.setMgrName("MGR-BB");
		
		//设定关联关系
		department.setMgr(manager);
		manager.setDept(department);
		
		//保存操作
		//建议先保存没有外键列的那个对象. 这样会减少 UPDATE 语句
		session.save(department);
		session.save(manager);
		
	}
	
	

}
