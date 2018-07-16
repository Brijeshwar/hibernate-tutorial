package com.bp.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bp.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		//Create a session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		//Create a session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 1;
			
			// start a transaction
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("Getting student with id: " + studentId);
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating student....");
			tempStudent.setFirstName("Scooby");
			
			//commit transaction
			session.getTransaction().commit();
			
			
			//NEW Code
			//Create a session and begin the transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//update email for all student
			System.out.println("Update student for all students...");
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done, updated successfully!");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			factory.close();
		}
	}

}
