package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.infrastructure.persistence.jdbc.helper.DataSourceProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JdbcPersonRepositoryTestIT {

    static JdbcPersonRepository repository;

    public Person getPerson(String name){
        Person person = null;

        name = name + (repository.count() + 1);

        person = Person.builder()
                .username(name)
                .lastName("Dupont")
                .firstName("Dupont")
                .clearTextPassword("Password1234")
                .email(name + "@example.com").build();

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
        repository.save(getPerson("addingPersonIncreasesCount"));
        assertEquals(n + 1, repository.count());
    }

    @Test
    public void removingPersonDecreasesCount(){
        Person p = getPerson("removingPersonDecreasesCount");
        repository.save(p);
        int n = repository.count();
        repository.remove(p.getUuid());
        assertEquals(n - 1, repository.count());
    }

    @Test
    public void findUserByName(){
        Person p = getPerson("findUserByName");
        repository.save(p);
        String expected = repository.findByUsername(p.getUsername()).get().getUsername();
        assertEquals(p.getUsername(), expected);
    }
}