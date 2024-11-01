package com.example.demo.Product;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.price <= :maxPrice")
    List<Product> findProductsWithPriceLessThan(@Param("maxPrice") double maxPrice);

    @Query("SELECT new com.example.demo.Product.Model.ProductDTO(p.name,p.description,p.price) FROM Product p")
    List<ProductDTO> getAllProductDTOs();
}
