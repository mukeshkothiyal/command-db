package com.muk.fkp.cmddb.service;

import com.muk.fkp.cmddb.repository.SpringJdbcRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@NoArgsConstructor
public class DbService {

  private SpringJdbcRepo repository;

  @Autowired
  public DbService(SpringJdbcRepo repository) {
    this.repository = repository;
  }

  public void create(String query) {
    this.repository.create(query);
  }

  public List<Map<String, Object>> fetchData(String query) {
    return this.repository.fetchData(query);
  }

}
