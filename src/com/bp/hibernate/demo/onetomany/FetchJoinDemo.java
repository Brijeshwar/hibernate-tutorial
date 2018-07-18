package com.bp.hibernate.demo.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.bp.hibernate.demo.entity.onetomany.Course;
import com.bp.hibernate.demo.entity.onetomany.Instructor;
import com.bp.hibernate.demo.entity.onetomany.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		
		//Create a session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .buildSessionFactory();
		
		//Create a session
		Session session = factory.getCurrentSession();
		
		try {
			//begin the transaction
			session.beginTransaction();
			
			//Option2: Hibernate query with HQL
			
			//get the instructor from db
			int theId = 1;
			Query<Instructor> query = session.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id =: theInstructorId", Instructor.class);
			
			//set parameter on a query
			query.setParameter("theInstructorId", theId);
			
			//execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();			
			System.out.println("Instructor: " + tempInstructor);
			
			//commit the transaction
			session.getTransaction().commit();
			
			//close the session
			session.close();
			
			//to avoid the lazy loading exception
			//Option1: Call getter method while session is open
			//get courses for the instructor
			System.out.println("Courses after session closed: " + tempInstructor.getCourses());
			
			System.out.println("Done!");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
