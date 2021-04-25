package com.tyr.autentication.service.token.service;


import com.tyr.persistence.PersistenceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchTesting {

    @Autowired
    PersistenceTest persistenceTest;

    public String searchInformation(){

        return persistenceTest.methodTesting();
    }


}
