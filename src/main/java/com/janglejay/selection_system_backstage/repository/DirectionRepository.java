package com.janglejay.selection_system_backstage.repository;

import com.janglejay.selection_system_backstage.entity.Direction;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends BaseRepository<Direction,Integer> {
//    Optional<List<Direction>> findAllByStudentId(int sid);
}
