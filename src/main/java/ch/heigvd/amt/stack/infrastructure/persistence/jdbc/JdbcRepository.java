package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.domain.IEntity;
import ch.heigvd.amt.stack.domain.IRepository;
import ch.heigvd.amt.stack.domain.Id;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.Optional;

public abstract class JdbcRepository<ENTITY extends IEntity<ENTITY, ID>, ID extends Id> implements IRepository<ENTITY, ID> {

    @Resource(lookup = "jdbc/StackDS")
    DataSource dataSource;

    public void update(PreparedStatement saveStatement) throws SQLIntegrityConstraintViolationException {
        try {
            Connection conn = dataSource.getConnection();
            saveStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected ResultSet getSet(PreparedStatement query) {
        try {
            return query.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
