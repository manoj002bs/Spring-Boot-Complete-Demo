package com.example.demo.Product.commandHandlers;

import com.example.demo.Command;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.UpdateProductCommand;
import com.example.demo.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductCommandHandler implements Command<UpdateProductCommand, ResponseEntity> {

    @Autowired
    private ProductRepository productRepository;
    @Override
    @CacheEvict(value = "productCache",key = "#command.getId()")
    public ResponseEntity<ResponseEntity> execute(UpdateProductCommand command) {
        Product product = command.getProduct();
        product.setId(command.getId());
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.OK).build();    }
}
