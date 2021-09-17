package com.example.demo.Service.implement;

import com.example.demo.Entity.Product;
import com.example.demo.Repository.ProductRepo;
import com.example.demo.Service.ProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductImp implements ProductSevice {


    @Autowired
    ProductRepo productRepo;


    @Override
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public Product add(Product productDTO) {

//        Product product = new Product();
       // String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        if(fileName.contains("..")){
//            System.out.println("not a valid file");
//        }
//        try{
//                product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
//        }catch (Exception e){
//
//        }
//        product.setName(productDTO.getName()) ;
//        product.setPrice(productDTO.getPrice());
//        product.setImage(productDTO.getImage());
//        product.setCategory(productDTO.getCategory());
//        product.setDecription(productDTO.getDecription());
       return productRepo.save(productDTO);



    }

//    @Override
//    public ProductDTO get(int id) {
//        Product product = productRepo.getById(id);
//        if(product != null){
//            ProductDTO productDTO = new ProductDTO();
//            productDTO.setId(product.getId());
//            productDTO.setName(product.getName());
//
//            return productDTO;
//        }
//        return null;
//    }

    @Override
    public void deleteProduct(int id) {
       productRepo.deleteById(id);
    }

    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo-1,pageSize,sort);

        return this.productRepo.findAll(pageable);
    }

//    @Override
//    public Page<Product> findPaginated(int pageNo, int pageSize, String sortFied, String sortDirection) {
//
//        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortFied).ascending():
//                Sort.by(sortFied).descending();
//        Pageable pageable = PageRequest.of(pageNo - 1,pageSize,sort);
//        return this.productRepo.findAll(pageable);
//    }

//    @Override
//    public List<ProductDTO> getAll(int page, int sizePage, String sortField, String sortDirection) {
//
//        List<ProductDTO> productDTOS = new ArrayList<ProductDTO>();
//
//        Pageable pageable = PageRequest.of(page , sizePage, Sort.by("id").descending());
//
//        Page<Product> productPage = productRepo.findAll(pageable);
//
//        for(Product p : productPage)    {
//            ProductDTO productDTO = new ProductDTO();
//            productDTO.setId(p.getId());
//            productDTO.setName(p.getName());
//            productDTO.setCategory(p.getCategory().getName());
//            productDTO.setPrice(p.getPrice());
//
//            productDTOS.add(productDTO);
//        }
//
//
//        return productDTOS;
//    }


}
