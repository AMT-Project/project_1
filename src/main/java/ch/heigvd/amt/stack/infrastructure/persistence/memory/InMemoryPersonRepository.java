package ch.heigvd.amt.stack.infrastructure.persistence.memory;

import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.person.PersonId;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;


public class InMemoryPersonRepository extends InMemoryRepository<Person, PersonId> implements IPersonRepository {

    public void save(Person entity) throws SQLIntegrityConstraintViolationException {
        synchronized (entity.getUserName()){
            if(!findByUsername(entity.getUserName()).isEmpty()){
                throw new SQLIntegrityConstraintViolationException("Cannot save/udpate person. Integrity constraint violation");
            }
            super.save(entity);
        }
    }


    @Override
    public Optional<Person> findByUsername(String username) {
        return Optional.empty();

        //Todo ADD THE FUNCTION

    }
}
