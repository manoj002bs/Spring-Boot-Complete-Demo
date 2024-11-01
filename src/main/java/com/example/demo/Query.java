package com.example.demo;

import com.example.demo.Product.Model.Product;
import org.springframework.http.ResponseEntity;

public interface Query<I,O> {
    ResponseEntity<O> execute(I input);
}
