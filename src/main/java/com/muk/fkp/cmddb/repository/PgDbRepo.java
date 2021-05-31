package com.muk.fkp.cmddb.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
@Repository
public class PgDbRepo {

  @Value("${spring.datasource.url}")
  String connUrl;

  @Value("${spring.datasource.username}")
  String username;

  @Value("${spring.datasource.password}")
  String pwd;

  @Value("${spring.datasource.driver-class-name}")
  String dbDriver;

  public ResultSet fetchData(String query) {
    ResultSet rs = null;
    try {
      Class.forName(dbDriver);
      Connection con = DriverManager.getConnection(connUrl, username, pwd);
      Statement stmt = con.createStatement();
      long startTime = System.nanoTime();
      rs = stmt.executeQuery(query);
      long end = System.nanoTime();
      System.out.println("Total time taken : " + (end - startTime) / 1000000 + " ms");
//      rs.close();
//      stmt.close();
//      con.close();
      log.info("Command successfully executed");
    } catch (ClassNotFoundException e) {
      log.error("Class Not Found : " + e.getMessage());
    } catch (SQLException exp) {
      log.error("SQL Exception: " + exp.getMessage());
      log.error("SQL State:     " + exp.getSQLState());
      log.error("Vendor Error:  " + exp.getErrorCode());
    }
    return rs;
  }
}
