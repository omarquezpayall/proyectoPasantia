package com.payall.dataaccess.Dao;

import com.payall.exceptions.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DaoMysql {

    private static final Logger LOGGER= LoggerFactory.getLogger(DaoMysql.class);
    private String username;
    private String hostname;
    private String port;
    private String database;
    private String password;
    private Connection connection;

    public DaoMysql(String username, String password, String hostname, String port, String database){
        this.username = username;
        this.port = port;
        this.database = database;
        this.password = password;
        this.hostname = hostname;
    }

    public void createConnection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:mysql://"+ hostname+ ":"+port+"/"+database+"?" +"user="+username+"&password="+password);
        } catch (SQLException e) {
            throw new CustomException( e.toString());
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException sqlex) {
                throw new CustomException( sqlex.toString());
            }
        }
    }

    public ResultSet getAllRowsOfTable( String tableName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Statement statement = null;
        ResultSet result = null;
        try {
            createConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM " + tableName);
        } catch (SQLException e) {
            // LOGGER.error( e.toString());
            throw new CustomException( e.toString());
        }finally {
            closeConnection();
        }
        return result;
    }

    public Connection getConnection(){
        return this.connection;
    }

}
