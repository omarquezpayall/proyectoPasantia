package com.payall.Requests;

public class MigrateRequest {

    private DatabaseRequest mysqlDatabase;
    private DatabaseRequest mongoDatabase;
    private String tableToMigrate;

    public MigrateRequest(DatabaseRequest mysqlDatabase, DatabaseRequest mongoDatabase, String tableToMigrate) {
        this.mysqlDatabase = mysqlDatabase;
        this.mongoDatabase = mongoDatabase;
        this.tableToMigrate = tableToMigrate;
    }

    public DatabaseRequest getMysqlDatabase() {
        return mysqlDatabase;
    }

    public void setMysqlDatabase(DatabaseRequest mysqlDatabase) {
        this.mysqlDatabase = mysqlDatabase;
    }

    public DatabaseRequest getMongoDatabase() {
        return mongoDatabase;
    }

    public void setMongoDatabase(DatabaseRequest mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    public String getTableToMigrate() {
        return tableToMigrate;
    }

    public void setTableToMigrate(String tableToMigrate) {
        this.tableToMigrate = tableToMigrate;
    }
}
