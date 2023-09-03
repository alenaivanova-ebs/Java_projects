package com.runner.dao.model;

import com.runner.dao.DAOManager;

import java.util.Optional;

public interface PersonDAO  extends DAOManager<Person> {
    Optional<Person> find(String name);

}