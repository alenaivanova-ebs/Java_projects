package com.runner.service;

import com.runner.dao.CardDAO;
import com.runner.dao.ProductDAO;
import com.runner.dao.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Long create(Card entity) {
        return null;
    }

    @Override
    public Card get(Long name) {
        return null;
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
