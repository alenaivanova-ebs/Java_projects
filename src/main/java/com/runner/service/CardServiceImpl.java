package com.runner.service;

import com.runner.dao.CardDAO;
import com.runner.dao.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardServiceImpl implements CardService{

    private CardDAO cardDAO;

    @Autowired
    public CardServiceImpl( CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    @Override
    public Card find(String name) {
        return cardDAO.find(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Long create(Card entity) {
        return cardDAO.create(entity);
    }

    @Override
    public Card get(Long id) {
        return cardDAO.get(id);
    }

    @Override
    public boolean doExistsById(Long Id) {
        return false;
    }

    @Override
    public void update(Card entity) {
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
