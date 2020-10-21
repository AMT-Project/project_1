package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.domain.IEntity;
import ch.heigvd.amt.stack.domain.IRepository;
import ch.heigvd.amt.stack.domain.Id;

public abstract class JdbcRepository<ENTITY extends IEntity<ENTITY, ID>, ID extends Id> implements IRepository<ENTITY, ID> {
}
