package com.example.demo.Controller;

import com.example.demo.Entity.Product;
import com.example.demo.PerSonDto.CategoryDTO;
import com.example.demo.Repository.ProductRepo;
import com.example.demo.Service.CategorySevice;
import com.example.demo.Service.ProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    CategorySevice categorySevice;

    @Autowired
    ProductSevice productSevice;

    @Autowired
    ProductRepo productRepo;
//
//    @GetMapping("/list-product")
//    public String ListProduct(Model model,@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
//                              @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
//                              @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
//
//        Sort sortable = null;
//        if (sort.equals("ASC")) {
//            sortable = Sort.by("id").ascending();
//        }
//        if (sort.equals("DESC")) {
//            sortable = Sort.by("id").descending();
//        }
//        Pageable pageable = PageRequest.of(page, size, sortable);
//
//        model.addAttribute("listProduct", productRepo.listProduct(pageable));
//
//        return "ProductList";
//    }

//    @GetMapping("/")
//    public String viewHomePage(Model model) {
//        return findPaginated(1, "firstName", "asc", model);
//    }

    @GetMapping("/list/product")
    public String listPro(Model model){
        return findPaginated(1, "name", "asc", model);
    }




//    @GetMapping("/list/product")
//    public String listProduct(Model model ,@RequestParam(name = "page",required = false) Integer page,@RequestParam(name = "size",required = false) Integer size){
//        if (page == null)
//            page = 0;
//
//        if (size == null)
//            size = 5;
//        long count = productRepo.count();
//
//        long pageTotal = (long) Math.ceil((double)count/size);
//
//        List<ProductDTO> dtoList = productSevice.getAll(page,size);
//
//        model.addAttribute("listProduct",dtoList);
//
//        model.addAttribute("page",page);
//        model.addAttribute("size",size);
//        model.addAttribute("pageTotal",pageTotal);
//        System.out.println(dtoList);
//        return "ProductList";
//    }
    

    @GetMapping("/product/add")
    public String addProduct(Model model) {
        List<CategoryDTO> categoryDTOS = categorySevice.getALL();
        model.addAttribute("product", new Product());
        model.addAttribute("cateList", categoryDTOS);

        return "add-product";
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute Product productDTO,
                             @RequestParam("fileImage")MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        productDTO.setImage(fileName);
        Product savedProduct = productSevice.add(productDTO);

        String uploadDir = "/image" + savedProduct.getId();

        Path uploadPath = Paths.get(uploadDir);
        //FileInputStream

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);

        }
        try(InputStream inputStream = multipartFile.getInputStream()){

            Path filePath = uploadPath.resolve(fileName);
            System.out.println(filePath.toFile().getAbsolutePath());
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);


        }catch (Exception e){
            throw new IOException("Could not upload file");
        }
        String FOLDER_SAVE = "E:\\DoAn\\";
        File outputStream = new File(FOLDER_SAVE+fileName);

        multipartFile.transferTo(outputStream);
        return "redirect:/list/product";
    }
    @GetMapping("/dowload")//?filename=abcc.jpg
    public void dowloadFile(@RequestParam("filename") String fileName, HttpServletResponse resp )throws IOException {
        String FOLDER_SAVE = "E:\\DoAn\\";
        File intputFile = new File(FOLDER_SAVE+ fileName);
        if(intputFile.exists()){
            Files.copy(intputFile.toPath(),resp.getOutputStream());//copy trar veef respon
        }
    }
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("id") int id, Model model) {
        productSevice.deleteProduct(id);
        return "redirect:/list/product";
    }

    @GetMapping("/edit")
    public String updateProduct(@RequestParam("id") int id, Model model) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        List<CategoryDTO> categoryDTOS = categorySevice.getALL();
        model.addAttribute("cateList", categoryDTOS);
        optionalProduct.ifPresent(product -> model.addAttribute("update", optionalProduct));
        return "editProduct";
    }

    @GetMapping("/page/{pageNo}")
    public String  findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                 @RequestParam("sortField") String sortField,
                                 @RequestParam("sortDir") String sortDir,Model model){
        int pageSize = 5;

        Page<Product> page = productSevice.findPaginated(pageNo,pageSize, sortField, sortDir);
        List<Product> productList = page.getContent();


        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listProduct", productList);
        return "list-product";

    }
//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
//                                @RequestParam("sortField") String sortField,
//                                @RequestParam("sortDir") String sortDir,
//                                Model model) {
//        int pageSize = 5;
//
//        Page<Product> page = productSevice.findPaginated(pageNo, pageSize, sortField, sortDir);
//        List<Product> listProduct = page.getContent();
//
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//
//        model.addAttribute("listProduct", listProduct);
//        return "ProductList";
//    }
}