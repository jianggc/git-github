package com.jane.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jane.subclass.Person;
import com.jane.subclass.Student;

public class HibernateTest8 {

	
	/**
	 * subclass 测试
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
	
	/**
	 * 缺点:
	 * 1. 使用了辨别者列.
	 * 2. 子类独有的字段不能添加非空约束.
	 * 3. 若继承层次较深, 则数据表的字段也会较多. 
	 */
	
	/**
	 * 查询:
	 * 1. 查询父类记录, 只需要查询一张数据表
	 * 2. 对于子类记录, 也只需要查询一张数据表
	 */
	@Test
	public void testQuery(){
		List<Person> persons = session.createQuery("FROM Person").list();
		System.out.println(persons.size()); 
		
		List<Student> stus = session.createQuery("FROM Student").list();
		System.out.println(stus.size()); 
	}
	
	/**
	 * 插入操作: 
	 * 1. 对于子类对象只需把记录插入到一张数据表中.
	 * 2. 辨别者列由 Hibernate 自动维护. 
	 */
	@Test
	public void testSave(){
		
		Person person = new Person();
		person.setAge(11);
		person.setName("AA");
		
		session.save(person);
		
		Student stu = new Student();
		stu.setAge(22);
		stu.setName("BB");
		stu.setSchool("JANE");
		
		session.save(stu);
		
	}

}
