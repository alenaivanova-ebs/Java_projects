package com.runner.controller;

import com.runner.controller.response.InfoResponse;
import com.runner.controller.validator.ProductValidator;
import com.runner.dao.model.Product;
import com.runner.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductService productService;

    private MessageSource messageSource;

    @Autowired
    public ProductController(ProductService productService, MessageSource messageSource) {
        this.productService = productService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public List<Product> getProduct() {
        return new ArrayList<>(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public HttpEntity<Product> getProduct(@PathVariable Long productId, Locale locale) {
        ProductValidator.validateIfExists(productId, locale);
        return ResponseEntity.ok(productService.get(productId));
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        Long productId = productService.create(product);
        return productService.get(productId);
    }

    @DeleteMapping("/{productId}")
    public InfoResponse deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
        String message = "product was deleted";
        return new InfoResponse(
                HttpStatus.NO_CONTENT.value(),
                message + ":" + productId,
                HttpStatus.NO_CONTENT.toString());
    }
}
