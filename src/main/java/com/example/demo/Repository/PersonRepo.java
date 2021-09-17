package com.example.demo.Repository;

import com.example.demo.Entity.PerSon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PersonRepo extends JpaRepository<PerSon,Integer> {
//    @Query("SELECT p FROM PerSon p WHERE puserName = :name")
//    PerSon findUserByUserName(@Param("name") String userName)  ;
//        @Query("SELECT u FROM PerSon u WHERE u.username = :uname")
//        PerSon getByUsername(@Param("uname") String username);

}
