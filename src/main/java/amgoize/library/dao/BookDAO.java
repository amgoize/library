package amgoize.library.dao;

import amgoize.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final RowMapper<Book> bookRowMapper = ((rs, rowNum) -> new Book(rs.getInt("id"),
            rs.getString("book_name"), rs.getString("author"),
            rs.getInt("year_of_book"), rs.getObject("own", Integer.class)));
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showBooks(){
        return jdbcTemplate.query("SELECT * FROM book", bookRowMapper);
    }
    public Book showBook(int id) {
        return (Book) jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id},
                        bookRowMapper)
                .stream().findFirst().orElse(null);
    }
    public void saveBook(Book book) {
        jdbcTemplate.update("INSERT INTO book(book_name, author, year_of_book) VALUES(?, ?, ?)",
                book.getNameBook(), book.getAuthor(), book.getYearOfBook());

    }
    public void updateBook(Book updatedbook, int id) {
        jdbcTemplate.update("UPDATE book SET book_name = ?, author = ?, year_of_book = ? where id=?",
                updatedbook.getNameBook(), updatedbook.getAuthor(), updatedbook.getYearOfBook(), id);
    }
    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM book where id=?", id);
    }

    public boolean isBookAvailable(int id) {
        Integer own = jdbcTemplate.queryForObject(
                "SELECT own FROM book WHERE id=?",
                new Object[]{id},
                Integer.class
        );

        if (own == null) {
            return true;
        }
        else {
            return false;
        }
    }
    public void assignBookToPerson(int id, int personId) {
        jdbcTemplate.update("UPDATE book SET own = ? WHERE id = ?", personId, id);
    }
    public void releaseBook(int id) {
        jdbcTemplate.update("UPDATE book SET own = NULL WHERE id = ?", id);
    }

}
