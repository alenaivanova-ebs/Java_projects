package com.runner.service;


import com.runner.dao.model.Person;

public interface RegistrationService extends ManagementService<Person> {
void register(Person person);
}

