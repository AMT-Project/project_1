package ch.heigvd.amt.stack.infrastructure.persistence.memory;

import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.person.PersonId;

import java.util.Optional;


public class InMemoryPersonRepository extends InMemoryRepository<Person, PersonId> implements IPersonRepository {




    @Override
    public Optional<Person> findByUsername(String username) {
        return Optional.empty();
    }
}
