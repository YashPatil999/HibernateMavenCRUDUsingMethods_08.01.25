package com.Dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entity.Vehicle;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class VehicleDao {

	public void InsertData() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Vehicle.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		Vehicle v1 = new Vehicle();
		System.out.println("Table is Created Successfully!!!");

		v1.setCarNumber("YU 68 GY 8485");
		v1.setCarMake("Kia");
		v1.setCarModel("Seltos");
		v1.setCarManufacturingYear(2021);

		ss.persist(v1);
		tr.commit();
		ss.close();
		System.out.println("Data Added Successfully!!!");
	}

	public void UpdateData() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Vehicle.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		int carSRno = 1;
		Vehicle v1 = ss.get(Vehicle.class, carSRno);
		v1.setCarMake("Suzuki");
		v1.setCarModel("Alto k10");
		v1.setCarManufacturingYear(2015);
		v1.setCarNumber("GJ 04 HG 7965");
		ss.merge(v1);
		tr.commit();
		ss.close();
		System.out.println("Data Updated Successfully!!!");
	}

	public void DeleteData() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Vehicle.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		int carSRno = 1;
		Vehicle v1 = ss.get(Vehicle.class, carSRno);
		ss.remove(v1);
		tr.commit();
		ss.close();
		System.out.println("Data Deleted Successfully!!!");
	}

	public void GetSingleRecord() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Vehicle.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		int carSRno = 2;
		Vehicle v1 = ss.get(Vehicle.class, carSRno);
		System.out.println(v1);
		tr.commit();
		ss.close();

	}

	public void GetAllRecords() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Vehicle.class);

		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction tr = ss.beginTransaction();

		CriteriaBuilder hcb = ss.getCriteriaBuilder();
		CriteriaQuery<Object> cq = hcb.createQuery();
		Root<Vehicle> root = cq.from(Vehicle.class);
		cq.select(root);

		Query query = ss.createQuery(cq);
		List<Vehicle> list = query.getResultList();

		Iterator<Vehicle> iterator = list.iterator();

		while (iterator.hasNext()) {
			Vehicle item = iterator.next();
			System.out.println(item);
		}
	}
}
