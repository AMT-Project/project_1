package ch.heigvd.amt.stack.infrastructure.persistence.memory;

import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.infrastructure.persistence.exception.DataCorruptionException;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Named("InMemoryPersonRepository")
public class InMemoryPersonRepository extends InMemoryRepository<Person, PersonId> implements IPersonRepository {

    @Resource(lookup = "StackOverflowOS")
    DataSource dataSource;

    public InMemoryPersonRepository(){

    }

    public InMemoryPersonRepository(DataSource dataSource) {this.dataSource = dataSource;}

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
