package com.Dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entity.Product;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class ProductDao {

	public void InsertData() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Product.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		Product p1 = new Product();
		System.out.println("Table is Created Successfully!!!");

		p1.setpName("Iphone 16 Pro");
		p1.setpQty(2);
		p1.setpCost(55000);

		ss.persist(p1);
		tr.commit();
		ss.close();
		System.out.println("Data Added Successfully!!!");
	}

	public void UpdateData() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Product.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		int pid = 1;
		Product p1 = ss.get(Product.class, pid);
		p1.setpName("Iphone 16");
		p1.setpQty(4);
		p1.setpCost(80000);
		ss.merge(p1);
		tr.commit();
		ss.close();
		System.out.println("Data Deleted Successfully!!!");
	}

	public void DeleteData() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Product.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		int pid = 2;
		Product p1 = ss.get(Product.class, pid);
		ss.remove(p1);
		tr.commit();
		ss.close();
		System.out.println("Data Deleted Successfully!!!");
	}

	public void GetSingleRecord() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Product.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		int pid = 1;
		Product p1 = ss.get(Product.class, pid);
		System.out.println(p1);
		tr.commit();
		ss.close();

	}

	public void GetAllRecords() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Product.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		CriteriaBuilder hcb = ss.getCriteriaBuilder();
		CriteriaQuery<Object> cq = hcb.createQuery();
		Root<Product> root = cq.from(Product.class);
		cq.select(root);

		Query query = ss.createQuery(cq);
		List<Product> list = query.getResultList();

		Iterator<Product> iterator = list.iterator();

		while (iterator.hasNext()) {
			Product item = iterator.next();
			System.out.println(item);
		}

	}
}
