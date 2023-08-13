package com.runner.dao;

import com.runner.dao.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductDAO extends DAOManager<Product> {
    Optional<Product> find(String name);

    List<Product> getAllProducts();


}

