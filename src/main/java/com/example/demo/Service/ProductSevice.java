package com.example.demo.Service;

import com.example.demo.Entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductSevice {

    List<Product> getAllProduct();

    Product add(Product productDTO);

    void deleteProduct(int id);

    Page<Product> findPaginated(int pageNo,int pageSize, String sortField, String sortDirection);

  //  List<ProductDTO> getAll(int page,int sizePage);

}

