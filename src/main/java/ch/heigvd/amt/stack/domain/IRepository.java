package ch.heigvd.amt.stack.domain;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.Optional;

public interface IRepository<ENTITY extends IEntity, ID extends Id> {

    public void save(ENTITY entity) throws SQLIntegrityConstraintViolationException;
    public void remove(ID id);
    public Optional<ENTITY> findById(ID id);
    public Collection<ENTITY> findAll();
}
