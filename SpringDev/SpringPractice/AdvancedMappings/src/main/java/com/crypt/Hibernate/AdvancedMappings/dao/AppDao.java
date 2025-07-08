package com.crypt.Hibernate.AdvancedMappings.dao;

import com.crypt.Hibernate.AdvancedMappings.Entity.Course;
import com.crypt.Hibernate.AdvancedMappings.Entity.Instructor;
import com.crypt.Hibernate.AdvancedMappings.Entity.InstructorDetail;

import java.util.List;

public interface AppDao {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    List<Course> findCoursesByInstructorById(int theId);
}
