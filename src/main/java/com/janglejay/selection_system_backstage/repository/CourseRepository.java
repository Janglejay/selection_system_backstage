package com.janglejay.selection_system_backstage.repository;

import com.janglejay.selection_system_backstage.entity.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends BaseRepository<Course,Integer>{
//    Optional<List<Course>> findAllByTutorId(int tid);
}
