package com.runner.dao;

import com.runner.dao.model.Card;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
@Transactional
public class CardDAOImpl implements CardDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Card find(String name) {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Card> criteria = criteriaBuilder.createQuery(Card.class);
        Root<Card> root = criteria.from(Card.class);
        criteria.where(criteriaBuilder.equal(root.get("card_name"), name))
                .distinct(true);
        return em.createQuery(criteria).getSingleResult();
    }

    @Override
    public Card get(Long id) {
        return em.find(Card.class, id);
    }

    @Override
    public Long create(Card entity) {
        return null;
    }
}
