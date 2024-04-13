package amgoize.library.dao;

import amgoize.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final RowMapper<Person> personRowMapper = ((rs, rowNum) -> new Person(rs.getInt("id"), rs.getString("name"),
            rs.getString("surname"), rs.getString("patronymic"), rs.getInt("year_of_birth")));
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> showPeople() {
        return jdbcTemplate.query("SELECT * FROM person", personRowMapper);
    }

    public Person showPerson(int id) {
        return (Person) jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
                        personRowMapper)
                .stream().findFirst().orElse(null);
    }

    public void savePerson(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, surname, patronymic, year_of_birth) VALUES(?, ?, ?, ?)",
                person.getName(), person.getSurname(), person.getPatronymic(), person.getYearOfBirth());

    }

    public void updatePerson(Person updatedPerson, int id) {
        jdbcTemplate.update("UPDATE Person SET name = ?, surname = ?, patronymic = ?, year_of_birth = ? where id=?",
                updatedPerson.getName(), updatedPerson.getSurname(), updatedPerson.getPatronymic(), updatedPerson.getYearOfBirth(), id);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM person where id=?", id);
    }
}
