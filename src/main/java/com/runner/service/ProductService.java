package com.runner.service;

import com.runner.dao.model.Card;
import com.runner.dao.model.Product;
import com.runner.service.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService extends ManagementService<Product> {

    Optional<Product> get(Long id);

    List<Product> getAllProducts();

    Double getTotal(Map<Long, Long> mapIdQty);

    Double getPriceWithDiscount(Double price, Long quantity, Double discount );

    boolean doExistsById(Long Id);

    void createReceipt(Map<Long, Long> mapIdQty, Card card) throws  ServiceException;

    Double getCardDiscount(Double total, Card card);
}
