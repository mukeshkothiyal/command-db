package com.muk.fkp.cmddb;

import com.muk.fkp.cmddb.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@SpringBootApplication
public class TestDbApplication implements CommandLineRunner {

  private static final List<Integer> UNSUPPORTED_DATA_TYPES = Arrays.asList(Types.BINARY, Types.BLOB);

  @Value("${custom.record.limit}")
  String rsLimit;

  private DbService dbService;

  @Autowired
  public TestDbApplication(DbService dbService) {
    this.dbService = dbService;
  }

  public static void main(String[] args) {
    SpringApplication.run(TestDbApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    if (args.length != 2) {
      log.error("Invalid arguments passed");
    } else {
      String type = args[0];
      String query;
      switch (type) {
        case "C":
          query = args[1];
          log.info("Create query is : " + query);
          this.dbService.create(query);
          break;
        case "R":
          query = args[1] + " limit " + rsLimit;
          log.info("Select query is : " + query);
          List<Map<String, Object>> rs = this.dbService.fetchData(query);
          AtomicBoolean isHeader = new AtomicBoolean(true);
          rs.stream().forEach(dataMap -> {
            if (isHeader.get()) {
              log.info(dataMap.keySet().toString());
            }
            log.info(dataMap.values().toString());
            isHeader.set(false);
          });

          break;
        default:
          log.warn("Please provide appropriate type in command line!!!");
          break;
      }
    }
  }
}
