package com.payall.logic.Command;

import com.payall.Requests.DatabaseRequest;
import com.payall.Serializers.ResponseSerializer;
import com.payall.dataaccess.Dao.DaoMongo;
import com.payall.dataaccess.Dao.DaoMysql;
import com.payall.exceptions.CustomException;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MigrateCommand implements GeneralCommand{
    private static final Logger LOGGER= LoggerFactory.getLogger(MigrateCommand.class);
    private DatabaseRequest mongoDatabase;
    private DatabaseRequest mysqlDatabase;
    private String tableToMigrate;
    private ResponseSerializer response = new ResponseSerializer();

    public MigrateCommand() {
    }

    @Override
    public void execute() {
        int columnCount = -1;
        int count = 0;
        try {
            DaoMongo daoMongo = new DaoMongo(
                    this.mongoDatabase.getUsername(),
                    this.mongoDatabase.getPassword(),
                    this.mongoDatabase.getHostname(),
                    this.mongoDatabase.getPort(),
                    this.mongoDatabase.getDatabase()
            );
            DaoMysql daoMysql = new DaoMysql(
                    this.mysqlDatabase.getUsername(),
                    this.mysqlDatabase.getPassword(),
                    this.mysqlDatabase.getHostname(),
                    this.mysqlDatabase.getPort(),
                    this.mysqlDatabase.getDatabase()
            );
            List<Document> documents = new ArrayList<Document>();
            ResultSet dataToMigrate = daoMysql.getAllRowsOfTable( this.tableToMigrate);
            if ( Objects.nonNull(dataToMigrate)) {
                while (dataToMigrate.next()) {
                    if (columnCount == -1) {
                        columnCount = dataToMigrate.getMetaData().getColumnCount();
                    }
                    Document documentToInsert = new Document();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = dataToMigrate.getMetaData().getColumnName(i);
                        Object value = dataToMigrate.getObject(i);
                        documentToInsert.append(columnName, value);
                    }
                    documents.add(documentToInsert);
                    count++;

                }
                daoMongo.insertDocumentsInCollection(this.tableToMigrate, documents);
                this.response.setRowsCount(count);
                this.response.setStatus(200);
                this.response.setTableMigrated(this.tableToMigrate);
                this.response.setMessage("Se ha completado la migracion con exito");
            }
        }catch (CustomException customException) {
            LOGGER.error( customException.toString());
            this.response.setMessage(customException.getMessage());
            this.response.setStatus( 400);
        }catch(Exception e){
            LOGGER.error( e.toString());
            this.response.setMessage(
                    "Ha ocurrido un error durante la migracion, revisar que los datos enviados sean correctos"
            );
            this.response.setStatus( 404);
        }
    }

    public DatabaseRequest getMongoDatabase() {
        return mongoDatabase;
    }

    public void setMongoDatabase(DatabaseRequest mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    public DatabaseRequest getMysqlDatabase() {
        return mysqlDatabase;
    }

    public void setMysqlDatabase(DatabaseRequest mysqlDatabase) {
        this.mysqlDatabase = mysqlDatabase;
    }

    public String getTableToMigrate() {
        return tableToMigrate;
    }

    public void setTableToMigrate(String tableToMigrate) {
        this.tableToMigrate = tableToMigrate;
    }
    public ResponseSerializer getResponse() {
        return response;
    }

    public void setResponse(ResponseSerializer response) {
        this.response = response;
    }
}
