package com.muk.fkp.cmddb.repository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@NoArgsConstructor
public class SpringJdbcRepo {
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public SpringJdbcRepo(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void create(String query) {
    this.jdbcTemplate.execute(query);
  }

  public List<Map<String, Object>> fetchData(String query) {
    List<Map<String, Object>> maps = this.jdbcTemplate.queryForList(query);
    return maps;
  }

}
