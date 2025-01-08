package com.Dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entity.Student;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class StudentDao {

	public void InsertData() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Student.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		Student s1 = new Student();
		System.out.println("Table is Added Successfully!!!");

		s1.setsName("Uemesh");
		s1.setsAge(19);
		s1.setsMarks(488);

		ss.persist(s1);
		tr.commit();
		ss.close();
		System.out.println("Data Added Successfully!!!");

	}

	public void UpdateData() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Student.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		int stuid = 1;
		Student s1 = ss.get(Student.class, stuid);
		s1.setsName("Manish");
		s1.setsAge(22);
		s1.setsMarks(450);
		ss.merge(s1);
		tr.commit();
		ss.close();
		System.out.println("Data Updated Successfully!!!");

	}

	public void DeleteData() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Student.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		int stuid = 2;
		Student s1 = ss.get(Student.class, stuid);
		ss.remove(s1);
		tr.commit();
		ss.close();
		System.out.println("Data Deleted Successfully!!!");

	}

	public void GetSingleRecord() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Student.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		int stuid = 1;
		Student s1 = ss.get(Student.class, stuid);
		System.out.println(s1);
		tr.commit();
		ss.close();

	}

	public void GetAllRecords() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Student.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		CriteriaBuilder hcb = ss.getCriteriaBuilder();
		CriteriaQuery<Object> cq = hcb.createQuery();
		Root<Student> root = cq.from(Student.class);
		cq.select(root);

		Query query = ss.createQuery(cq);
		List<Student> list = query.getResultList();

		Iterator<Student> iterator = list.iterator();

		while (iterator.hasNext()) {
			Student item = iterator.next();
			System.out.println(item);
		}
	}
}
