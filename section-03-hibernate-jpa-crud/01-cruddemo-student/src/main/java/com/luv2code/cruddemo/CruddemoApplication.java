package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
	return runner -> {

		// createStudent(studentDAO);

		createMultipleStudents(studentDAO);

		// readStudents(studentDAO);

		// queryForStudents(studentDAO);

		// queryForStudentsByLastName(studentDAO);

		// updateStudent(studentDAO);

		// deleteStudent(studentDAO);

		// deleteAllStudents(studentDAO);
	    };
    }

	private void deleteAllStudents(StudentDAO studentDAO) {

		System.out.println("Deleting all students");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row count: " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Deleting student id: " + studentId);
		studentDAO.delete(studentId);

	}

	private void updateStudent(StudentDAO studentDAO) {

		// retrieve student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id: "  + studentId);
		Student myStudent = studentDAO.findById(studentId);

		// change first name to John
		System.out.println("Updating student...");
		myStudent.setFirstName("John");

		// update the student
		studentDAO.update(myStudent);

		// display the updated student
		System.out.println("Upload student: " + myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {

		// get list of students
		List<Student> theStudents = studentDAO.findByLastName("Doe");

		// display that list pf students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {

		// get a list of students
		List<Student> theStudent = studentDAO.findAll();
		//display that list of students
		for (Student tempStudent : theStudent) {
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
	}

	private void readStudents(StudentDAO studentDAO) {
		// create a student object
		System.out.println("reating new student object");
		Student tempStudent = new Student ("Daffy", "Duck", "daffy@luv2code.com");

		//save the student
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the saved students
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		//retrieve studentd based on id
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		//display student
		System.out.println("Found the student: "+ myStudent);

	}


	private void createMultipleStudents(StudentDAO studentDAO) {

		//create multiple students
		System.out.println("Creating 3 Student object...");
		Student tempStudent1 = new Student("John","Doe",  "johnluv2code.com");
		Student tempStudent2 = new Student("Mary","public",  "maryluv2code.com");
		Student tempStudent3 = new Student("Bonita","Applebum",  "bonitaluv2code.com");

		// save the students objects
		System.out.println("Saving the students ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

	}

	private void createStudent(StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new Student object...");
		Student tempStudent = new Student("Paul","Doe",  "paulluv2code.com");

	    // save he student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		//display id of the saved student
		System.out.println("Saved student. generated ID: " + tempStudent.getId());
	}
}