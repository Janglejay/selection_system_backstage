package com.janglejay.selection_system_backstage.repository;

import com.janglejay.selection_system_backstage.entity.Tutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends BaseRepository<Tutor,Integer>{

//    //修改指定教师的最大学生数宇与最大范围数
//    @Modifying
//    @Query("update Tutor t set t.maxStuNum=:maxStuNum,t.scopeStuNum=:scopeStuNum where t.id=:id")
//    int updateMaxStuNum(@Param("id")long id,@Param("maxStuNum") int maxStuNum,@Param("scopeStuNum")int scopeStuNum);


}
