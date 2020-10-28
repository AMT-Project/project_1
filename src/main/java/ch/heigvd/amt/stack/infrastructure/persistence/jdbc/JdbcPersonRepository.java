package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.person.PersonId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@ApplicationScoped
@Named("JdbcPersonRepository")
public class JdbcPersonRepository extends JdbcRepository<Person, PersonId> implements IPersonRepository {
    @Resource(lookup = "jdbc/StackDS")
    DataSource dataSource;

    public JdbcPersonRepository() {
    }

    public JdbcPersonRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Person person) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataSource.getConnection().prepareStatement(
                "INSERT INTO Person (uuid, username, email, firstname, lastname, password) " +
                    "VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, person.getUuid().asString());
            preparedStatement.setString(2, person.getUsername());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getFirstName());
            preparedStatement.setString(5, person.getLastName());
            preparedStatement.setString(6, person.getEncryptedPassword());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        ;
    }

    @Override
    public void remove(PersonId uuid) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataSource.getConnection().prepareStatement(
                "DELETE FROM Person WHERE uuid=?");
            preparedStatement.setString(1, uuid.asString());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        ;
    }

    @Override
    public Optional<Person> findById(PersonId uuid) {
        // TODO : verify implementation
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().
                prepareStatement("SELECT * FROM Person WHERE uuid=?");
            preparedStatement.setString(1, uuid.asString());
            ResultSet rs = preparedStatement.executeQuery();

            LinkedList<Person> matches = new LinkedList<>();

            while(rs.next()) {
                Person person = Person.builder()
                    .uuid(uuid)
                    .username(rs.getString("username"))
                    .firstName(rs.getString("firstname"))
                    .lastName(rs.getString("lastname"))
                    .email(rs.getString("email"))
                    .encryptedPassword(rs.getString("password"))
                    .build();
                matches.add(person);
            }

            if(matches.size() != 1) {
                return Optional.empty();
            }

            return Optional.of(matches.get(0));
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Person> findAll() {
        // TODO : verify implementation
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().
                prepareStatement("SELECT * FROM Person");
            ResultSet rs = preparedStatement.executeQuery();

            LinkedList<Person> persons = new LinkedList<>();
            while(rs.next()) {
                Person person = Person.builder()
                    .uuid(new PersonId(rs.getString("uuid")))
                    .username(rs.getString("username"))
                    .firstName(rs.getString("firstname"))
                    .lastName(rs.getString("lastname"))
                    .email(rs.getString("email"))
                    .encryptedPassword(rs.getString("password"))
                    .build();
                persons.add(person);
            }

            return persons;
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int count() {
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("SELECT COUNT(*) AS nbUsers FROM Person");
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getInt("nbUsers");
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        // TODO : verify implementation
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().
                prepareStatement("SELECT * FROM Person WHERE username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            LinkedList<Person> persons = new LinkedList<>();

            while(rs.next()) {
                Person person = Person.builder()
                    .uuid(new PersonId(rs.getString("uuid")))
                    .username(rs.getString("username"))
                    .firstName(rs.getString("firstname"))
                    .lastName(rs.getString("lastname"))
                    .email(rs.getString("email"))
                    .encryptedPassword(rs.getString("password"))
                    .build();
                persons.add(person);
            }

            if(persons.size() != 1) {
                return Optional.empty();
            }

            return Optional.of(persons.get(0));
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
