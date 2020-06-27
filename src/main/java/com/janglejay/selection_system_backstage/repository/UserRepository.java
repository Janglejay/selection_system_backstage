package com.janglejay.selection_system_backstage.repository;

import com.janglejay.selection_system_backstage.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.number=:number")
    User getUser(@Param("number") int number);

    User findById(int id);
    @Modifying
    @Query("update User u set u.password=:password where u.number=:number")
    int updatePassword(@Param("number")int number,@Param("password") String password);
}
