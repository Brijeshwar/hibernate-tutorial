package com.bp.hibernate.demo.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bp.hibernate.demo.entity.onetomany.Course;
import com.bp.hibernate.demo.entity.onetomany.Instructor;
import com.bp.hibernate.demo.entity.onetomany.InstructorDetail;
import com.bp.hibernate.demo.entity.onetomany.Review;

public class DeleteCoursesAndReviewsDemo {

	public static void main(String[] args) {
		
		//Create a session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .addAnnotatedClass(Review.class)
								 .buildSessionFactory();
		
		//Create a session
		Session session = factory.getCurrentSession();
		
		try {
			//begin the transaction
			session.beginTransaction();
			
			//get the instructor from db
			int theId = 3;
			Course tempCourse = session.get(Course.class, theId);
			
			//delete the course
			System.out.println("Deleting the course: " + tempCourse);
			
			//print the course reviews
			System.out.println("Course reviews: " + tempCourse.getReviews());
			
			//delete the course
			session.delete(tempCourse);
			
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
