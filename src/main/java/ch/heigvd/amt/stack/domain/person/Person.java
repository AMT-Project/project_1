package ch.heigvd.amt.stack.domain.person;

import ch.heigvd.amt.stack.domain.IEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Person implements IEntity<Person, PersonId> {
    private PersonId uuid;
    private String username;
    private String email;
    private String firstName;
    private String lastName;

    @EqualsAndHashCode.Exclude
    private String encryptedPassword;

    public boolean authenticate(String clearTextPassword) {
        return BCrypt.checkpw(clearTextPassword, this.encryptedPassword);
    }

    public PersonId getUuid() {
        return this.uuid;
    }

    @Override
    public Person deepClone() {
        return this.toBuilder().
                uuid(new PersonId(uuid.asString())).
                build();
    }

    public static class PersonBuilder {
        public PersonBuilder clearTextPassword(String clearTextPassword) {

            if (clearTextPassword == null || clearTextPassword.isEmpty()) {
                throw new java.lang.IllegalArgumentException("Password is mandatory");
            } else if (!clearTextPassword.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$")) {
                throw new java.lang.IllegalArgumentException("Password invalid (must be at least 8 characters, include an uppercase, a lowercase letter and a number)");
            }

            encryptedPassword = BCrypt.hashpw(clearTextPassword, BCrypt.gensalt());
            return this;
        }

        public Person build() {
            if (uuid == null) {
                uuid = new PersonId();
            }

            if (username == null || username.isEmpty()) {
                throw new java.lang.IllegalArgumentException("Username is mandatory");
            }
            if (encryptedPassword == null || encryptedPassword.isEmpty()) {
                throw new java.lang.IllegalArgumentException("Password is mandatory");
            }
            if (firstName == null || firstName.isEmpty()) {
                throw new java.lang.IllegalArgumentException("First name is mandatory");
            }
            if (lastName == null || lastName.isEmpty()) {
                throw new java.lang.IllegalArgumentException("Last name is mandatory");
            }
            if (email == null || email.isEmpty()) {
                throw new java.lang.IllegalArgumentException("Email is mandatory");
            } else if (!email.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")) {
                throw new java.lang.IllegalArgumentException("Email is misformatted");
            }

            return new Person(uuid, username, email, firstName, lastName, encryptedPassword);
        }
    }

}
