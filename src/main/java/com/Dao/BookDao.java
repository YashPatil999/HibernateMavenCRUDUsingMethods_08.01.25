package com.Dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entity.Book;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class BookDao {

	public void InsertData() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Book.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		Book b1 = new Book();
		System.out.println("Table is created Successfully!!!");

		b1.setBookName("Middle of the Night");
		b1.setBookAuthor("Riley Sager");
		b1.setBookPrice(900);

		ss.persist(b1);
		System.out.println(b1);
		tr.commit();
		ss.clear();
		System.out.println("Data Added Successfully!!!!");
	}

	public void UpdateData() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Book.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		int bookid = 1;
		Book b1 = ss.get(Book.class, bookid);
		b1.setBookName("GameOfThrones");
		b1.setBookAuthor("Riche Rich");
		b1.setBookPrice(650);
		ss.merge(b1);
		tr.commit();
		ss.close();
		System.out.println("Data Updated Successfully!!!");
	}

	public void DeleteData() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Book.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		int bookid = 3;
		Book b1 = ss.get(Book.class, bookid);
		ss.remove(b1);
		tr.commit();
		ss.close();
		System.out.println("Data Deleted Successfully!!!");
	}

	public void GetSingleRecord() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Book.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		int bookid = 1;
		Book b1 = ss.get(Book.class, bookid);
		System.out.println(b1);
		tr.commit();
		ss.close();
	}

	public void GetAllRecords() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Book.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		CriteriaBuilder hcb = ss.getCriteriaBuilder();
		CriteriaQuery<Object> cq = hcb.createQuery();
		Root<Book> root = cq.from(Book.class);
		cq.select(root);

		Query query = ss.createQuery(cq);
		List<Book> list = query.getResultList();

		Iterator<Book> iterator = list.iterator();

		while (iterator.hasNext()) {
			Book item = iterator.next();
			System.out.println(item);
		}
	}
}
