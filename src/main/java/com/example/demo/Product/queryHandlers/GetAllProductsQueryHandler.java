package com.example.demo.Product.queryHandlers;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.ProductDTO;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsQueryHandler implements Query<Void, List<ProductDTO>> {

    @Autowired
    private ProductRepository productRepository;

    // Actual Business Logic is implemented here so that Controller is independent of Business Logic
    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void input) {

//        List<ProductDTO> productDTOs = productRepository
//                .findAll()
//                .stream()
//                .map(ProductDTO::new)
//                .toList();

        List<ProductDTO> productDTOs = productRepository.getAllProductDTOs();

        return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
    }
}
