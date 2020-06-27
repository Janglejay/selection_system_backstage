package com.janglejay.selection_system_backstage.service;

import com.janglejay.selection_system_backstage.entity.Course;
import com.janglejay.selection_system_backstage.repository.CourseRepository;
import com.janglejay.selection_system_backstage.repository.DirectionRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    //添加课程
    public void addCourse(Course course){
        courseRepository.save(course);
    }

}