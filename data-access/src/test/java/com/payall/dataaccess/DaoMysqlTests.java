package com.payall.dataaccess;

import com.payall.dataaccess.Dao.DaoMysql;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DaoMysqlTests {

    @Test
    public void MysqlConnectionTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        DaoMysql daoMysql = new DaoMysql(
                "manageOsticket","mysql","localhost", "3306","osticket_db"
        );
        daoMysql.createConnection();
        assertNotNull(daoMysql.getConnection());
    }

    @Test
    public void getAllRowsOfTableTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        DaoMysql daoMysql = new DaoMysql(
                "manageOsticket","mysql","localhost", "3306","osticket_db"
        );
        ResultSet result = daoMysql.getAllRowsOfTable("osticket_user");
        assertNotNull( result);
    }
}
