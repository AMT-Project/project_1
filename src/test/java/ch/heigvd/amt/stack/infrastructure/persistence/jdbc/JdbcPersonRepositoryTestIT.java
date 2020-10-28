package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.infrastructure.persistence.jdbc.helper.DataSourceProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JdbcPersonRepositoryTestIT {

    static JdbcPersonRepository repository;
    static int i = 0;

    public static Person getPerson(){
        i++;
        Person person = Person.builder()
                .username("toto" + i)
                .lastName("Dupont")
                .firstName("Dupont")
                .clearTextPassword("1234")
                .email("toto" + i + "@example.com").build();
        return person;
    }

    @BeforeAll
    public static void setup(){
        repository = new JdbcPersonRepository(DataSourceProvider.getDataSource());
    }

    @Test
    public void findByIdReturnsNullWhenNoPersonIsFound(){
        Person nonExistingPerson = repository.findById(new PersonId()).orElse(null);
        assertNull(nonExistingPerson);
    }

    @Test
    public void addingPersonIncreasesCount(){
        int n = repository.count();
        repository.save(getPerson());
        assertEquals(n + 1, repository.count());
    }

    @Test
    public void removingPersonDecreasesCount(){
        Person p = getPerson();
        repository.save(p);
        int n = repository.count();
        repository.remove(p.getUuid());
        assertEquals(n - 1, repository.count());
    }
}