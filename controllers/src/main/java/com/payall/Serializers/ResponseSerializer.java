package com.payall.Serializers;

public class ResponseSerializer {

    private String tableMigrated;
    private int rowsCount;
    private int status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public ResponseSerializer(String tableMigrated, int rowsCount, int status) {
        this.tableMigrated = tableMigrated;
        this.rowsCount = rowsCount;
        this.status = status;
    }

    public ResponseSerializer() {
    }

    public String getTableMigrated() {
        return tableMigrated;
    }

    public void setTableMigrated(String tableMigrated) {
        this.tableMigrated = tableMigrated;
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public void setRowsCount(int rowsCount) {
        this.rowsCount = rowsCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
