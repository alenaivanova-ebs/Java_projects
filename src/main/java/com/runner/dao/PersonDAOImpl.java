package com.runner.dao;

import com.runner.dao.model.Person;
import com.runner.dao.model.PersonDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class PersonDAOImpl implements PersonDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Optional<Person> find(String name) {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = criteriaBuilder.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);
        criteria.where(criteriaBuilder.equal(root.get("username"), name)).distinct(true);
        return Optional.ofNullable(em.createQuery(criteria).getSingleResult());

    }

    @Override
    public Optional<Person> get(Long id) {
        return Optional.empty();
    }

    @Override
    public Long create(Person entity) {
        em.persist(entity);
        return (Long) (long) entity.getId();
    }
}