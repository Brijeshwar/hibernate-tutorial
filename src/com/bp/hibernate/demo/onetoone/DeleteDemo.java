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
			//begin the transaction
			session.beginTransaction();

			//get instructor by primary key id
			int theId =3;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Found instructor: " + tempInstructor);
			
			//delete the instructor
			if (tempInstructor != null) {
				System.out.println("Deleting: " + tempInstructor);
				
				//Note: will also delete the associated "details" objects
				//because of CasecadeType.ALL
				session.delete(tempInstructor);
			}

			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
