package com.bp.hibernate.demo.onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bp.hibernate.demo.entity.onetoone.Instructor;
import com.bp.hibernate.demo.entity.onetoone.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {
		
		//Create a session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .buildSessionFactory();
		
		//Create a session
		Session session = factory.getCurrentSession();
		
		try {
			//create the object
			Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "Guitar");
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//begin the transaction
			session.beginTransaction();
			
			//save the instructor
			session.save(tempInstructor);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			factory.close();
		}
	}

}
