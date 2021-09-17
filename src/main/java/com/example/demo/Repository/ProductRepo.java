package com.example.demo.Repository;

import com.example.demo.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
//
//    @Query("Select p from Product p")
//    Page<Product> listProduct(Pageable pageable)
;}
