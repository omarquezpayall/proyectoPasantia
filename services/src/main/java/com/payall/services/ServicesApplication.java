package com.payall.services;

import com.payall.Requests.MigrateRequest;
import com.payall.Serializers.ResponseSerializer;
import com.payall.logic.Command.MigrateCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(
		scanBasePackages = {
				"com.payall.Requets",
				"com.payall.logic",
				"com.payall.Serializers",
				"com.payall.dataaccess",
				"com.payall.exceptions"
		}
)
@RestController
public class ServicesApplication {

	private static final Logger LOGGER= LoggerFactory.getLogger( ServicesApplication.class);

	@PostMapping("/migrate")
	public ResponseSerializer migrateMySqlToMongo(@RequestBody MigrateRequest migrateRequest) {
		ResponseSerializer result = null;
		try{
			MigrateCommand migrateCommand = new MigrateCommand();
			migrateCommand.setMongoDatabase( migrateRequest.getMongoDatabase());
			migrateCommand.setMysqlDatabase( migrateRequest.getMysqlDatabase());
			migrateCommand.setTableToMigrate( migrateRequest.getTableToMigrate());
			migrateCommand.execute();
			result = migrateCommand.getResponse();
		}catch (Exception e){
			LOGGER.error( e.toString());
		}
		return result;
	}

	public static void main(String[] args) {
		SpringApplication.run(ServicesApplication.class, args);
	}
}
