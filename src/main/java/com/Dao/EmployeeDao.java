package com.Dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entity.Employee;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class EmployeeDao {

	public void InsertData() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Employee.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		Employee e1 = new Employee();
		System.out.println("Table is Created Successfully!!!");

		e1.setEmpName("Yash Patil");
		e1.setEmpAge(25);
		e1.setEmpSalary(150000);

		ss.persist(e1);
		tr.commit();
		ss.close();
		System.out.println("Data Added Successfully!!!");
	}

	public void UpdateData() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Employee.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		int empid = 1;
		Employee e1 = ss.get(Employee.class, empid);
		e1.setEmpName("Girish");
		e1.setEmpAge(35);
		e1.setEmpSalary(60000);
		ss.merge(e1);
		tr.commit();
		ss.close();
		System.out.println("Data Updated Successfully!!!!");
	}

	public void DeleteData() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Employee.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		int empid = 2;
		Employee e1 = ss.get(Employee.class, empid);
		ss.remove(e1);
		tr.commit();
		ss.close();
		System.out.println("Data Deleted Successfully!!!!");

	}

	public void GetSingleRecord() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Employee.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		int empid = 1;
		Employee e1 = ss.get(Employee.class, empid);
		System.out.println(e1);
		tr.commit();
		ss.close();

	}

	public void GetAllRecords() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Employee.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		CriteriaBuilder hcb = ss.getCriteriaBuilder();
		CriteriaQuery<Object> cq = hcb.createQuery();
		Root<Employee> root = cq.from(Employee.class);
		cq.select(root);

		Query query = ss.createQuery(cq);
		List<Employee> list = query.getResultList();

		Iterator<Employee> iterator = list.iterator();

		while (iterator.hasNext()) {
			Employee item = iterator.next();
			System.out.println(item);
		}

	}
}
