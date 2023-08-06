package com.runner.dao;

import com.runner.dao.model.Product;
import java.util.List;

public interface ProductDAO extends DAOManager<Product> {
    Product find(String name);

    List<Product> getAllProducts();


}

