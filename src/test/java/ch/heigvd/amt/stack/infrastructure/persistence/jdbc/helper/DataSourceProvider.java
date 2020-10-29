package ch.heigvd.amt.stack.infrastructure.persistence.jdbc.helper;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {
    public static DataSource getDataSource(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:9906/AMT-db");
        dataSource.setUser("stackusr");
        dataSource.setPassword("stackpwd");
        return dataSource;
    }
}
