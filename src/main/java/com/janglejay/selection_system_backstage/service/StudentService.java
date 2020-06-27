package com.janglejay.selection_system_backstage.service;

import com.janglejay.selection_system_backstage.entity.Student;
import com.janglejay.selection_system_backstage.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public void addStudent(Student student){
        studentRepository.save(student);
    }
}
