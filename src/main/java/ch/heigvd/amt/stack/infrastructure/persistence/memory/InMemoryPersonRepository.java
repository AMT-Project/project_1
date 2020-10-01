package ch.heigvd.amt.stack.infrastructure.persistence.memory;

import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.infrastructure.persistence.exception.DataCorruptionException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryPersonRepository extends InMemoryRepository<Person, PersonId> implements IPersonRepository {

    public void save(Person entity) throws SQLIntegrityConstraintViolationException {
        synchronized(entity.getUsername()) {
            if(findByUsername(entity.getUsername()).isPresent()) {
                throw new SQLIntegrityConstraintViolationException("Cannot save/udpate person. Integrity constraint violation");
            }
            super.save(entity);
        }
    }

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
}
