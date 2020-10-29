package ch.heigvd.amt.stack.domain.person;

import ch.heigvd.amt.stack.domain.IRepository;

import java.util.Optional;

public interface IPersonRepository extends IRepository<Person, PersonId> {

    public Optional<Person> findByUsername(String username);

    Optional<Person> findByEmail(String email);

    public int update(Person user);
}
