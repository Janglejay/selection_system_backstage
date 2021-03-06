package com.janglejay.selection_system_backstage.repository;

import com.janglejay.selection_system_backstage.entity.Course;
import com.janglejay.selection_system_backstage.entity.StudentCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentCourseRepository extends BaseRepository<StudentCourse,Integer> {
    void deleteAllByCourse_Id(int id);
    List<StudentCourse> findAllByCourse(Course course);


    @Query("SELECT sc FROM StudentCourse sc WHERE sc.course.id=:cid")
    Optional<StudentCourse> getSC(@Param("cid")int cid);

    @Query("SELECT sc FROM StudentCourse sc WHERE sc.student.user.id=:sid")
    List<StudentCourse> findStudent(@Param("sid")int sid);
}
