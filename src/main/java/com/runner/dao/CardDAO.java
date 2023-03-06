package com.runner.dao;

import com.runner.dao.model.Card;
import com.runner.dao.model.Product;

public interface CardDAO  extends DAOManager<Card> {
    Card find(String name) ;
}
