package ch.heigvd.amt.stack.infrastructure.persistence.memory;

import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.infrastructure.persistence.exception.DataCorruptionException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Named("InMemoryPersonRepository")
public class InMemoryPersonRepository extends InMemoryRepository<Person, PersonId> implements IPersonRepository {

    @Override
    public Optional<Person> findByUsername(String username) {
        List<Person> matchingEntities = findAll().stream()
            .filter(p -> p.getUsername().equals(username))
            .collect(Collectors.toList());

        if(matchingEntities.size() < 1) {
            return Optional.empty();
        }
        if(matchingEntities.size() > 1) {
            throw new DataCorruptionException("Your data store is corrupted");
        }

        return Optional.of(matchingEntities.get(0).deepClone());
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public int update(Person user) {
        return 0;
    }

    @Override
    public int count() {
        return findAll().size();
    }
}
