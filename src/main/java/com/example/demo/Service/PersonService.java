package com.example.demo.Service;

import com.example.demo.Entity.PerSon;

import java.util.List;
import java.util.Optional;


public interface PersonService {
    List<PerSon> getAllUser();

    void savePerSon(PerSon perSon);

    void delete(int id);

    Optional<PerSon> findPersonById(int id);


}
