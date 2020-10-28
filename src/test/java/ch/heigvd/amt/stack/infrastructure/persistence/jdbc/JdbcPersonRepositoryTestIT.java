package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.infrastructure.persistence.jdbc.helper.DataSourceProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class JdbcPersonRepositoryTestIT {

    static JdbcPersonRepository repository;

    public Person getPerson(String name){
        Person person = null;

        name = name + (repository.count() + 1);

        person = Person.builder()
                .username(name)
                .lastName("Dupont")
                .firstName("Dupont")
                .clearTextPassword("1234")
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


    // FIXME https://github.com/AMT-Project/project_1/issues/42
//    @Test
//    public void findAll(){
//        LinkedList<Person> persons = new LinkedList();
//        for(int i = 0; i < 10; i++){
//            Person p = getPerson("findAll" + i);
//            persons.add(p);
//            repository.save(p);
//        }
//
//        Person p1 = getPerson("oijj");
//        Person p2 = p1.deepClone();
//        if (!p1.equals(p2)){
//            fail();
//        }
//
//        Collection<Person> allPersons = repository.findAll();
//        System.out.println(allPersons);
//        System.out.println(persons);
//
//        for(Person p: persons) {
//            boolean found = false;
//            for(Person pA: allPersons){
//               if (found = p.equals(pA)){
//                   break;
//               }
//            }
//            if (!found){
//                fail();
//            }
//        }
//    }
}