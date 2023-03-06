package com.runner.service;

import com.runner.dao.model.Card;


public interface CardService extends ManagementService<Card> {

    Card find(String name) ;

    Card get(Long name) ;

    boolean doExistsById(Long Id);


}
