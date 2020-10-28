package ch.heigvd.amt.stack.domain;

public interface IEntity<ENTITY extends IEntity, ID extends Id> {
    ID getUuid();
    ENTITY deepClone();
}
