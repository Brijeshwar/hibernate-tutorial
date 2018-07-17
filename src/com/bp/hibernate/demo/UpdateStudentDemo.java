package com.bp.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bp.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		//Create a session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		//Create a session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 4;
			
			// start a transaction
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("Getting student with id: " + studentId);
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("Deleting student....");
			session.delete(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			
			//NEW Code
			//Create a session and begin the transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//delete the student
			System.out.println("delete student whoes id is 2");
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done, deleted successfully!");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
