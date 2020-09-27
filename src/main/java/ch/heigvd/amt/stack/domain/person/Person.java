package ch.heigvd.amt.stack.domain.person;

import ch.heigvd.amt.stack.domain.IEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Person implements IEntity<Person, PersonId> {

    private PersonId id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;

    @EqualsAndHashCode.Exclude
    private String encryptedPassword;

    public boolean authenticate(String clearTextPassword){
        return clearTextPassword.toUpperCase().equals(encryptedPassword);
    }


    @Override
    public PersonId getId() {
        return id;
    }

    @Override
    public Person deepClone() {
        return this.toBuilder().
                id(new PersonId(id.asString())).
                build();


    }

    public static class PersonBuilder{
        public PersonBuilder clearTextPassword(String clearTextPassword){

            if(clearTextPassword == null || clearTextPassword.isEmpty()){
                throw new java.lang.IllegalArgumentException("Password is mandatory");
            }

            encryptedPassword = clearTextPassword.toUpperCase();
            return this;
        }

        public Person build(){
            if(id == null){
                id = new PersonId();
            }

            //TODO ADD CONDITIONS

            Person newPerson = new Person(id, userName, email, firstName, lastName, encryptedPassword);
            return newPerson;
        }
    }

}
