package com.runner.service;

import com.runner.dao.model.Card;

import java.util.Optional;

public interface CardService extends ManagementService<Card> {

    Card find(String name) ;

    Optional<Card> get(Long name) ;

    boolean doExistsById(Long Id);


}
