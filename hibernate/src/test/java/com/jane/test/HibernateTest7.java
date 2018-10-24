package com.jane.test;

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

import com.jane.n2n.Category;
import com.jane.n2n.Item;

public class HibernateTest7 {

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
	public void testGet(){
		Category category = (Category) session.get(Category.class, 1);
		System.out.println(category.getName()); 
		
		//需要连接中间表
		Set<Item> items = category.getItems();
		System.out.println(items.size()); 
	}
	
	@Test
	public void testSave(){
		Category category1 = new Category();
		category1.setName("C-AA");

		Category category2 = new Category();
		category2.setName("C-BB");
		
		Item item1 = new Item();
		item1.setName("I-AA");
		
		Item item2 = new Item();
		item2.setName("I-BB");
		
		//设定关联关系
		category1.getItems().add(item1);
		category1.getItems().add(item2);
		
		category2.getItems().add(item1);
		category2.getItems().add(item2);
		
		item1.getCategories().add(category1);
		item1.getCategories().add(category2);
		
		item2.getCategories().add(category1);
		item2.getCategories().add(category2);
		
		//执行保存操作
		session.save(category1);
		session.save(category2);
		
		session.save(item1);
		session.save(item2);
	}

}
