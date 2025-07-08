package com.crypt.Hibernate.AdvancedMappings;

import com.crypt.Hibernate.AdvancedMappings.Entity.Course;
import com.crypt.Hibernate.AdvancedMappings.Entity.Instructor;
import com.crypt.Hibernate.AdvancedMappings.Entity.InstructorDetail;
import com.crypt.Hibernate.AdvancedMappings.dao.AppDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdvancedMappingsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdvancedMappingsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao) {
		return runner -> createInstructorWithCourses(appDao);
	}

	private void createInstructorWithCourses(AppDao appDao) {
		Instructor instructor=new Instructor("Rishi", "Ganguly", "rh@email.com");
		InstructorDetail instructorDetail = new InstructorDetail("insta.com", "Vg");
		instructor.setInstructorDetail(instructorDetail);
		Course c1 = new Course("Physics");
		Course c2 = new Course("Maths");
		instructor.addCourse(c1);
		instructor.addCourse(c2);
		appDao.save(instructor);
	}


	private void findInstructorDetail(AppDao appDao) {
		int id = 2;
		System.out.println("finding instructor by id 1");
		InstructorDetail instructorDetail = appDao.findInstructorDetailById(id);
		System.out.println(instructorDetail);
	}

	private void deleteInstructor(AppDao appDao) {
		System.out.println("Deleting id 1");
		appDao.deleteInstructorById(1);
		System.out.println("done");
	}

	private void findInstructor(AppDao appDao) {
		int id=1;
		Instructor instructor = appDao.findInstructorById(1);
		System.out.println("ID is" + id);
		System.out.println("instructor details" + instructor.toString());
		System.out.println("InstructorDetail details" + instructor.getInstructorDetail());
	}

	private void createInstructor(AppDao appDao) {
		Instructor instructor=new Instructor("Chad", "Darby", "j@email.com");
		InstructorDetail instructorDetail = new InstructorDetail("youtube.com", "lfdsaf");
		instructor.setInstructorDetail(instructorDetail);
		appDao.save(instructor);
	}
}
