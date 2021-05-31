package com.muk.fkp.cmddb.service;

import com.muk.fkp.cmddb.repository.PgDbRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;

@Service
public class PgDbService {

  @Autowired
  PgDbRepo repository;

  public ResultSet fetchData(String query) {
    return repository.fetchData(query);
  }

}
