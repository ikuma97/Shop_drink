package com.example.demo.Controller;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.PerSon;
import com.example.demo.PerSonDto.CategoryDTO;
import com.example.demo.Repository.CatagoryRepo;
import com.example.demo.Service.CategorySevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CategoryController {
    @Autowired
    CategorySevice categorySevice;

    @Autowired
    CatagoryRepo catagoryRepo;

    @GetMapping("/list-category")
    public String listCategory(Model model){
        model.addAttribute("listCate",catagoryRepo.findAll());

        return "list-category";

    }
    @GetMapping("/add-category")
    public String addCategory(Model model){

        model.addAttribute("category",new Category());

        return "add-category";
    }

    @PostMapping("/add-category")
    public String saveCategory(Category categoryDTO){
        categorySevice.insert(categoryDTO);
        return "redirect:/list-category";
    }

    @GetMapping("/delete-category")
    public String deleteCategory(@RequestParam("id") int id){
        catagoryRepo.deleteById(id);
        return "redirect:/list-category";
    }
    @GetMapping("/update-category")
    public String updateCategory(@RequestParam("id") int id,Model model){
        Optional<Category> category = catagoryRepo.findById(id);
        category.ifPresent(product -> model.addAttribute("update",category));
        return "edit-category";
    }

}
