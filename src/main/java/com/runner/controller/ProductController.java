package com.runner.controller;

import com.runner.controller.exception.EntityNotFoundException;
import com.runner.controller.response.InfoResponse;
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
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;
    private final MessageSource messageSource;

    @Autowired
    public ProductController(ProductService productService, MessageSource messageSource) {
        this.productService = productService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public List<Product> getProducts() {
        return new ArrayList<>(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public HttpEntity<Product> getProduct(@PathVariable Long productId, Locale locale) {
        Optional<Product> product = productService.get(productId);
        if (product.isEmpty()) {
            String errorMessage = messageSource.getMessage("label.not.found.product", null, locale);
            throw new EntityNotFoundException(errorMessage + ": " + productId);
        }
        return ResponseEntity.ok(product.get());
    }

    @PostMapping
    public InfoResponse addProduct(@RequestBody Product product, Locale locale) {
        Long productId = productService.create(product);
        String message = messageSource.getMessage("label.product.created", null, locale);
        return new InfoResponse(
                HttpStatus.CREATED.value(),
                message + ":" + productId,
                HttpStatus.CREATED.toString());
    }

    @DeleteMapping("/{productId}")
    public InfoResponse deleteProduct(@PathVariable Long productId, Locale locale) {
        productService.delete(productId);
        String message = messageSource.getMessage("label.product.deleted", null, locale);
        return new InfoResponse(
                HttpStatus.NO_CONTENT.value(),
                message + ":" + productId,
                HttpStatus.NO_CONTENT.toString());
    }
}
