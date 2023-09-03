package com.runner.service;

import com.runner.dao.model.Person;
import com.runner.dao.model.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final PersonDAO personDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationServiceImpl(PersonDAO personDAO, PasswordEncoder passwordEncoder) {
        this.personDAO = personDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long create(Person entity) {
        return null;
    }

    @Override
    public Optional<Person> get(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Person entity) {
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    @Transactional
    public void register(Person person) {
        String encodedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encodedPassword);
        person.setRole("ROLE_USER");
        personDAO.create(person);
    }
}
