package com.bp.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bp.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		//Create a session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		//Create a session
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction
			session.beginTransaction();
			
			//query the students
			List<Student> theStudents = session.createQuery("from Student").list();
//			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			//display the students
			System.out.println("Displaying all the students...");
			displayStudents(theStudents);
			
			//query students: last name='Doe'
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			
			//display the students
			System.out.println("\n\nStudent who have last name of Doe");
			displayStudents(theStudents);
			
			//query students: last name='Doe' or first name = 'Paul'
			theStudents = session.createQuery("from Student s where s.lastName='Doe' or s.firstName='Paul'").getResultList();
			
			//display the students
			System.out.println("\n\nStudent who have last name of Doe OR first name of Paul");
			displayStudents(theStudents);
			
			//query students: email ends with 'luv2code.com'
			theStudents = session.createQuery("from Student s where s.email like '%luv2code.com'").getResultList();
			
			//display the students
			System.out.println("\n\nStudent whoes email ends with luv2code.com");
			displayStudents(theStudents);
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done, retrieved successfully!");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
	
	private static void displayStudents(List<Student> theStudents) {
		theStudents.forEach(student -> System.out.println("student: " + student));
	}

}
