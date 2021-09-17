package com.example.demo.Service.implement;

import com.example.demo.Entity.Category;
import com.example.demo.PerSonDto.CategoryDTO;
import com.example.demo.Repository.CatagoryRepo;
import com.example.demo.Service.CategorySevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CategoryImp implements CategorySevice {

    @Autowired
    CatagoryRepo catagoryRepo;

    @Override
    public List<CategoryDTO> getALL() {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        List<Category> list = catagoryRepo.findAll();

        list.forEach(category -> {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());

            categoryDTOList.add(categoryDTO);
        });

        return categoryDTOList;
    }

    @Override
    public void insert(Category categoryDTO) {
//        Category category = new Category();
//        category.setName(categoryDTO.getName());
        catagoryRepo.save(categoryDTO);
       // categoryDTO.setId(category.getId());
    }
}
