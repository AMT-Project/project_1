package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.person.PersonId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcPersonRepository")
public class JdbcPersonRepository implements IPersonRepository {

    @Resource(lookup = "StackOverflowOS")
    DataSource dataSource;

    public JdbcPersonRepository(){

    }

    public JdbcPersonRepository(DataSource dataSource) {this.dataSource = dataSource;}

    @Override
    public Optional<Person> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void save(Person entity) throws SQLIntegrityConstraintViolationException {

    }

    @Override
    public void remove(PersonId id) {

    }

    @Override
    public Optional<Person> findById(PersonId id) {
        return Optional.empty();
    }

    @Override
    public Collection<Person> findAll() {
        return null;
    }
}
