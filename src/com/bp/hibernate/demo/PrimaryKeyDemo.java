package com.bp.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bp.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// Create a session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// Create a session
		Session session = factory.getCurrentSession();

		try {
			// Use the session object to save the object
			System.out.println("Creating 3 student objects....");
			Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com", null);
			Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com", null);
			Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com", null);

			// start a transaction
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the students....");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done, saved successfully!");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
