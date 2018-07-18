package com.bp.hibernate.demo.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bp.hibernate.demo.entity.onetomany.Course;
import com.bp.hibernate.demo.entity.onetomany.Instructor;
import com.bp.hibernate.demo.entity.onetomany.InstructorDetail;
import com.bp.hibernate.demo.entity.onetomany.Review;

public class CreateCoursesDemo {

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
			
			//create a course
			Course course = new Course("Pacman - How To Score One Million Points");
			
			//add some reviews 
			Review review1 = new Review("Grate course... loved it!");
			Review review2 = new Review("Cool course, job well done");
			Review review3 = new Review("What a dumb course, you are an idiot!");
			course.add(review1);
			course.add(review2);
			course.add(review3);
			
			//save the course... and leverage the casecase all:-)
			System.out.println("Saving the course: " + course);
			System.out.println(course.getReviews());
			
			session.save(course);
			
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
