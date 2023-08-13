package com.runner.dao;

import com.runner.dao.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDAOImpl implements ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Product> get(Long id) {
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

    @Override
    public Long create(Product entity) {
        entityManager.persist(entity);
        String name = entity.getName();
        return find(name).get().getId();
    }

    @Override
    public Optional<Product> find(String name) {
        return Optional.ofNullable((Product) entityManager.createQuery("SELECT u from Product  u WHERE u.name = :productname").setParameter("productname", name).getSingleResult());
    }

    @Override
    public List<Product> getAllProducts() {
        return entityManager.createQuery("Select a from Product a", Product.class).getResultList();
    }
}