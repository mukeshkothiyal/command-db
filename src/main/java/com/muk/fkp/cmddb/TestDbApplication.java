package com.muk.fkp.cmddb;

import com.muk.fkp.cmddb.service.PgDbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootApplication
public class TestDbApplication implements CommandLineRunner {

  private static final List<Integer> UNSUPPORTED_DATA_TYPES = Arrays.asList(Types.BINARY, Types.BLOB);

  @Value("${custom.record.limit}")
  String rsLimit;

  @Autowired
  PgDbService dbService;

  public static void main(String[] args) {
    SpringApplication.run(TestDbApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    if (args.length != 2) {
      log.error("Invalid arguments passed");
    } else {
      String type = args[0];
      switch (type) {
        case "sql":
          String query = args[1] + " limit " + rsLimit;
          System.out.println("Query created is: " + query);
          ResultSet rs = dbService.fetchData(query);

          // add header for query
          try {
            int i = 1;
            while (true) {
              System.out.print(rs.getMetaData().getColumnName(i));
              System.out.print("(" + rs.getMetaData().getColumnTypeName(i).toUpperCase() + ")");
              System.out.print(",");
              i++;
            }
          } catch (Exception e) {
            System.out.println();
          }

          // add content for query
          while (rs.next()) {
            try {
              int i = 1;
              while (true) {
                if (UNSUPPORTED_DATA_TYPES.contains(rs.getMetaData().getColumnType(i))) {
                  System.out.print(rs.getMetaData().getColumnTypeName(i).toUpperCase() + " NOT SUPPORTED");
                } else {
                  System.out.print(rs.getObject(i));
                }
                System.out.print(",");
                i++;
              }
            } catch (Exception e) {
              System.out.println();
            }
          }
          break;
        case "misc":
          log.warn("Not implemented yet!!!");
          break;
      }
    }
  }
}
