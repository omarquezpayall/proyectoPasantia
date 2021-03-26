package com.payall.services;

import com.payall.Requests.DatabaseRequest;
import com.payall.Requests.MigrateRequest;
import com.payall.Serializers.ResponseSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServicesApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void migrateTest(){
		DatabaseRequest mysqlRequest = new DatabaseRequest(
				"manageOsticket",
				"localhost",
				"3306",
				"osticket_db",
				"mysql"
		);
		DatabaseRequest mongoRequest = new DatabaseRequest(
				"oscar",
				"localhost",
				"27017",
				"test",
				"mongo"
		);
		MigrateRequest migrateRequest = new MigrateRequest(
				mysqlRequest,
				mongoRequest,
				"osticket_user"
		);

		ServicesApplication servicesApplication = new ServicesApplication();
		ResponseSerializer response = servicesApplication.migrateMySqlToMongo( migrateRequest);
		assertEquals(
				"osticket_user",
				response.getTableMigrated()
		);
	}

}
