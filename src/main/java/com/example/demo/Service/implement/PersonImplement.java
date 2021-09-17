package com.example.demo.Service.implement;

import com.example.demo.Entity.PerSon;
import com.example.demo.Repository.PersonRepo;
import com.example.demo.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Service
public class PersonImplement implements PersonService {
    @Autowired
    PersonRepo personRepo;

    @Autowired
    EntityManager entityManager ;


    @Override
    public List<PerSon> getAllUser() {
        List<PerSon> list = personRepo.findAll();
        return list;
    }

    @Override
    public void savePerSon(PerSon perSon) {


        personRepo.save(perSon);

    }

    @Override
    public void delete(int id) {
        personRepo.deleteById(id);
    }

    @Override
    public Optional<PerSon> findPersonById(int id) {
        Optional<PerSon> perSon = personRepo.findById(id);
        return perSon;
    }




}
