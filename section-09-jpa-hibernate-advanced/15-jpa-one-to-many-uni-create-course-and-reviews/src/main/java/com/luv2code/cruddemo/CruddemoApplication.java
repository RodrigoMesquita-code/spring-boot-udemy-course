package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {

			createCourseAndReviews(appDAO);

		};
	}

	private void createCourseAndReviews(AppDAO appDAO) {

		// create a curse
		Course tempCourse = new Course("Pacman - How to score 1 Million Points");

		// add some reviuews
		tempCourse.addReview(new Review("Great course! i loved it!"));
		tempCourse.addReview(new Review("Cool course! well done job!"));
		tempCourse.addReview(new Review("What a dumb course! You are terrible!"));

		// save the course adn leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReview());

		appDAO.save(tempCourse);

		System.out.println("DEU CERTO");
	}

	private void deleteCourse(AppDAO appDAO) {

		int theId = 12;

		System.out.println("Deleting course id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("DONE Oh YEAS");
	}

	private void updateCourse(AppDAO appDAO) {

		int theId = 12;

		// find the course
		System.out.println("Finding the course id: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);

		// update teh course
		System.out.println("Updating course iud: " + theId);
		tempCourse.setTitle("Enjoy the Simple Things");

		appDAO.update(tempCourse);

		System.out.println("DONE MY FRIENDDDD");


	}

	private void updateInstructor(AppDAO appDAO) {

		int theId = 1;

		// find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		// update the instructor
		System.out.println("Updating instructor id: " + theId);
		tempInstructor.setLastName("Pork");

		appDAO.update(tempInstructor);

		System.out.println("DONE!!!! HAHAHAHA");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int theId =1;

		// find the instruyctor
		System.out.println("Finding ionstructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("YESS");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);

		// find course for instructor
		System.out.println("Finding course for Instructor id: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		//associate the objects
		tempInstructor.setCourses(courses);
		
		System.out.println("The associated courses: " + tempInstructor.getCourses());

		System.out.println("OOOH YEEAAAAH");

	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done MANNN!!!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		// create the instructor
		Instructor tempInstructor = new Instructor("John", "Pork", "JohnP@luv2code.com");

		// create the Intructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com","Phone Calls");


		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses

		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("Pinball Masterclass");
		Course tempCourse3 = new Course("Canoagem - The Super Guide");

		// add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		tempInstructor.add(tempCourse3);
		// save the instructor
		//
		// NOTE: thius will ALSO save the courses
		// because of cascade type.persist
		//
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("DONE!!!!!!!!!!!!!!!!!!!!!!!! SAVED");

	}

	private void deleteInstructorDetail(AppDAO appDAO) {

		int theId=4;
		System.out.println("Deleting instructor detail id: " + theId);

		// para deletar tudo: appDAO.deleteInstructorById(theId);
		appDAO.deleteInstructorDetailById(theId);

		System.out.println("DONE!!!!");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		// get the instructor detail object
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		// print the associated instructor
		System.out.println("The associated instructor: " + tempInstructorDetail.getInstructor());

		System.out.println("DONE!!!!!");

	}

	private void deleteInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Deleting instructor id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("DONE! DELETED");
	}

	private void findInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("FindingInstructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		/*
		// create the instructor
		Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");

		// create the Intructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube","Luv2code!!");
		*/

		// create the instructor
		Instructor tempInstructor = new Instructor("Madhu", "Patel", "mahdu@luv2code.com");

		// create the Intructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube","Guitar");


		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the Instructor
		//
		// NOTE: this will also save the details object because of CascadeType.ALL
		//
		System.out.println("Saving Instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done");
	}

}
