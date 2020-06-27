package com.janglejay.selection_system_backstage;

import com.janglejay.selection_system_backstage.entity.Student;
import com.janglejay.selection_system_backstage.entity.User;
import com.janglejay.selection_system_backstage.repository.StudentRepository;
import com.janglejay.selection_system_backstage.service.StudentService;
import com.janglejay.selection_system_backstage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.Map;

@SpringBootTest
@Slf4j
@Rollback(value = false)
class SelectionSystemBackstageApplicationTests {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;

    @Test
    void test_password() {
        String root = encoder.encode("root");
        boolean two = encoder.matches("root", root);
        System.out.println(two);

    }

    @Test
    void insertStudent() {
        User user = new User();
        user.setName("test");
        user.setRole(User.Role.STUDENT);
        user.setNumber(1001);
        user.setPassword("10001");
        Student student = new Student();
        student.setUser(user);
        studentRepository.save(student);
    }

    @Test
    void getStudent() {
        Student one = studentRepository.find("test", 1001);
        System.out.println(one.getId());
    }

    @Test
    void testStudentService() {
        Student student = new Student();
        User user = new User();
        user.setName("test2");
        user.setRole(User.Role.STUDENT);
        user.setNumber(1002);
        user.setPassword("10001");
        student.setUser(user);
        studentService.addStudent(student);
    }

    @Test
    void testFindStudent() {
        Student student = userService.getStudent(2);
        Student student1 = userService.getStudent(1);
        if (student != null) {

            System.out.println(student.getUser().getName());
        }
        System.out.println(student1.getUser().getName());
    }
}
