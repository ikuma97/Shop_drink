package com.example.demo.Service;

import com.example.demo.Entity.Category;
import com.example.demo.PerSonDto.CategoryDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategorySevice {
    List<CategoryDTO> getALL();
    void insert(Category categoryDTO);

}


