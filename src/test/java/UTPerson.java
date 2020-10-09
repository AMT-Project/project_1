import ch.heigvd.amt.stack.domain.person.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UTPerson {

    private static final String USER = "wasadigi";
    private static final String FIRSTNAME = "Olivier";
    private static final String LASTNAME = "Liechti";
    private static final String EMAIL = "oliechti@heigvd.ch";
    private static final String PASSWORD = "pwd";

    @Test
    public void mandatoryPassword(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Person p = Person.builder()
                    .username(USER)
                    .firstName(FIRSTNAME)
                    .lastName(LASTNAME)
                    .email(EMAIL)
                    .build();
        });

        exception = assertThrows(IllegalArgumentException.class, () -> {
            Person p = Person.builder()
                    .clearTextPassword("")
                    .username(USER)
                    .firstName(FIRSTNAME)
                    .lastName(LASTNAME)
                    .email(EMAIL)
                    .build();
        });


    }

    @Test
    public void mandatoryUsername(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        Person p = Person.builder()
                .clearTextPassword(PASSWORD)
//                .username(USER)
                .firstName(FIRSTNAME)
                .lastName(LASTNAME)
                .email(EMAIL)
                .build();
        });
    }

    @Test
    public void mandatoryFirstname(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Person p = Person.builder()
                    .clearTextPassword(PASSWORD)
                .username(USER)
//                    .firstName(FIRSTNAME)
                    .lastName(LASTNAME)
                    .email(EMAIL)
                    .build();
        });
    }

    @Test
    public void mandatoryLastname(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Person p = Person.builder()
                    .clearTextPassword(PASSWORD)
                .username(USER)
                    .firstName(FIRSTNAME)
//                    .lastName(LASTNAME)
                    .email(EMAIL)
                    .build();

        });
    }

    @Test
    public void mandatoryEmail(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Person p = Person.builder()
                    .clearTextPassword(PASSWORD)
                    .username(USER)
                    .firstName(FIRSTNAME)
                    .lastName(LASTNAME)
//                    .email(EMAIL)
                    .build();

        });
    }

    @Test
    public void emailFormatting(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Person p = Person.builder()
                    .clearTextPassword(PASSWORD)
                    .username(USER)
                    .firstName(FIRSTNAME)
                    .lastName(LASTNAME)
                    .email("notAnEmail")
                    .build();

        });
    }

    @Test
    public void validCreation(){
        assertDoesNotThrow(() -> {
            Person p = Person.builder()
                    .clearTextPassword(PASSWORD)
                    .username(USER)
                    .firstName(FIRSTNAME)
                    .lastName(LASTNAME)
                    .email(EMAIL)
                    .build();

        });
    }


}
