package ch.heigvd.amt.stack.domain;

import java.util.UUID;

public abstract class Id {

    private UUID id;

    public Id() {
        id = UUID.randomUUID();
    }

    public Id(String id) {
        this.id = UUID.fromString(id);
    }

    public Id(UUID id) {
        if(id == null) {
            throw new NullPointerException();
        }
        this.id = id;
    }

    public String asString() {
        return id.toString();
    }
}
