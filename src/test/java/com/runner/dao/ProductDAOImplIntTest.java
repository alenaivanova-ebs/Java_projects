package com.runner.dao;
import com.runner.dao.model.Product;
import com.runner.dao.model.Discount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {InitTestDataBaseConfig.class})
@Transactional
@Sql({"/init.sql", "/populate.sql"})
public class ProductDAOImplIntTest {
    @Autowired
    private ProductDAO productDAO;

    @Test
    public void testGet() {
        Optional<Product> prExpected = Optional.ofNullable(getProduct());
        Optional<Product> product = productDAO.get(1L);
        assertEquals(prExpected, product);
    }

    private Product getProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("test");
        product.setPrice(1.0);
        Discount discount = new Discount();
        discount.setId(1L);
        discount.setDiscountType("test");
        discount.setDiscountPercent(1.0);
        product.setDiscount(discount);
        return product;
    }
}
