package com.runner.dao;

import com.runner.dao.model.Card;

public interface CardDAO  extends DAOManager<Card> {
    Card find(String name) ;
}
