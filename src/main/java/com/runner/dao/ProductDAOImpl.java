package com.runner.dao;

import com.runner.dao.model.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product get(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public Long create(Product entity) {
        entityManager.persist(entity);
        String name = entity.getName();
        return find(name).getId();
    }

    @Override
    public Product find(String name) {
        return (Product) entityManager.createQuery("SELECT u from Product  u WHERE u.name = :productname").setParameter("productname", name).getSingleResult();
    }

    @Override
    public List<Product> getAllProducts() {
        return entityManager
                .createQuery("Select a from Product a", Product.class)
                .getResultList();
    }
}