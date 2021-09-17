package com.example.demo.PerSonDto;

import com.example.demo.Entity.Category;
import lombok.Data;

@Data
public class ProductDTO {

    private int id;

    private String name;

    private Double price;

    private String category;
}
