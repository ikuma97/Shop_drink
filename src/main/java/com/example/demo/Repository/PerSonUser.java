package com.example.demo.Repository;

import com.example.demo.Entity.PerSon;

public interface PerSonUser {
    PerSon getByUsername(String username);
}
