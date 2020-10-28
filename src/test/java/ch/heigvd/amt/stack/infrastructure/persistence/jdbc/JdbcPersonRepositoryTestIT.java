package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.infrastructure.persistence.jdbc.helper.DataSourceProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class JdbcPersonRepositoryTestIT {

    static JdbcPersonRepository repository;

    @BeforeAll
    public static void setup(){
        repository = new JdbcPersonRepository(DataSourceProvider.getDataSource());
    }

    @Test
    public void findByIdReturnsNullWhenNoPersonIsFound(){
        Person nonExistingPerson = repository.findById(new PersonId()).orElse(null);
        assertNull(nonExistingPerson);
    }
}