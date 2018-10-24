package com.jane.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jane.model.Customer;
import com.jane.model.Order;


public class HibernateTest3 {
	/**
	 * 单向一对多测试
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
	public void testOneToManyDel(){
		Customer customer = session.get(Customer.class,1);
		session.delete(customer);
	}
	
	@Test
	public void testUpdate(){
		Customer customer = session.get(Customer.class, 1);
		customer.setCustomerName("CC");
		System.out.println(customer.getCustomerName());
		System.out.println(customer);
		
//		customer.getOrders().iterator().next().setOrderName("O-XXX-10");
	}
	
	//默认对关联的多的一方使用懒加载的加载策略. 
	//可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
	@Test
	public void testOneToManyGet2(){
		Order order = session.get(Order.class, 1);
		System.out.println(order);
		
	}
	//默认对关联的多的一方使用懒加载的加载策略. 
	//可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
	@Test
	public void testOneToManyGet(){
		Customer customer = session.get(Customer.class, 1);
		System.out.println(customer);
		
		System.out.println(customer.getOrders().size());
	}
	
	//单向 1-n 关联关系执行保存时, 一定会多出 UPDATE 语句.
	//因为 n 的一端在插入时不会同时插入外键列. 
	@Test
	public void testOneToManySave(){
		Customer customer = new Customer();
		customer.setCustomerName("AAA");
		customer.setOrders(new HashSet<>());
		
		Order order1 = new Order();
		order1.setOrderName("O-JJ-1");
		
		Order order2 = new Order();
		order2.setOrderName("O-JJ-2");
		System.out.println(customer);
		
		//建立关联关系
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);
		
		session.save(customer);
		session.save(order1);
		session.save(order2);
		
	}

}
