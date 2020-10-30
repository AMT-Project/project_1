package ch.heigvd.amt.stack.domain.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    private static final String USER = "wasadigi";
    private static final String FIRSTNAME = "Olivier";
    private static final String LASTNAME = "Liechti";
    private static final String EMAIL = "oliechti@heigvd.ch";
    private static final String PASSWORD = "pwd234XXX";

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

        exception = assertThrows(IllegalArgumentException.class, () -> {
            Person p = Person.builder()
                    .clearTextPassword(PASSWORD)
                    .username(USER)
                    .firstName(FIRSTNAME)
                    .lastName(LASTNAME)
                    .email("notAnEmail@email")
                    .build();

        });

        exception = assertThrows(IllegalArgumentException.class, () -> {
            Person p = Person.builder()
                    .clearTextPassword(PASSWORD)
                    .username(USER)
                    .firstName(FIRSTNAME)
                    .lastName(LASTNAME)
                    .email("notAnEmail@.ch")
                    .build();

        });

        exception = assertThrows(IllegalArgumentException.class, () -> {
            Person p = Person.builder()
                    .clearTextPassword(PASSWORD)
                    .username(USER)
                    .firstName(FIRSTNAME)
                    .lastName(LASTNAME)
                    .email("@nope.ch")
                    .build();

        });
    }

    @Test
    public void pwdFormatting(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Person p = Person.builder()
                    .clearTextPassword("1Passwd")
                    .username(USER)
                    .firstName(FIRSTNAME)
                    .lastName(LASTNAME)
                    .email(EMAIL)
                    .build();

        });

        exception = assertThrows(IllegalArgumentException.class, () -> {
            Person p = Person.builder()
                    .clearTextPassword("password1")
                    .username(USER)
                    .firstName(FIRSTNAME)
                    .lastName(LASTNAME)
                    .email(EMAIL)
                    .build();

        });

        exception = assertThrows(IllegalArgumentException.class, () -> {
            Person p = Person.builder()
                    .clearTextPassword("PASSWORD1")
                    .username(USER)
                    .firstName(FIRSTNAME)
                    .lastName(LASTNAME)
                    .email(EMAIL)
                    .build();

        });

        exception = assertThrows(IllegalArgumentException.class, () -> {
            Person p = Person.builder()
                    .clearTextPassword("Password")
                    .username(USER)
                    .firstName(FIRSTNAME)
                    .lastName(LASTNAME)
                    .email(EMAIL)
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

    @Test
    public void authentication(){
        Person p = Person.builder()
                .clearTextPassword(PASSWORD)
                .username(USER)
                .firstName(FIRSTNAME)
                .lastName(LASTNAME)
                .email(EMAIL)
                .build();
        assertTrue(p.authenticate(PASSWORD));
        assertFalse(p.authenticate(PASSWORD + 1));

    }

    @Test
    public void deepCloning(){
        Person p = Person.builder()
                .clearTextPassword(PASSWORD)
                .username(USER)
                .firstName(FIRSTNAME)
                .lastName(LASTNAME)
                .email(EMAIL)
                .build();

        Person clone = p.deepClone();

        assertEquals(p.getUsername(), clone.getUsername());
        assertEquals(p.getFirstName(),clone.getFirstName());
        assertEquals(p.getLastName(),clone.getLastName());
        assertEquals(p.getEmail(),clone.getEmail());
        assertEquals(p.getEncryptedPassword(),clone.getEncryptedPassword());

        assertNotEquals(p.getUuid(), clone.getUuid());
    }
}
