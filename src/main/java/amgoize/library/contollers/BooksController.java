package amgoize.library.contollers;

import amgoize.library.dao.BookDAO;
import amgoize.library.dao.PersonDAO;
import amgoize.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String showBooks(Model model){
        model.addAttribute("books", bookDAO.showBooks());
        return "books/showBooks";
    }
    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.showBook(id));
        model.addAttribute("isBookAvailable", bookDAO.isBookAvailable(id));
        model.addAttribute("people", personDAO.showPeople());
        return "books/showBook";
    }
    @PostMapping("/assign/{id}")
    public String assignBookToPerson(@PathVariable("id") int id, @RequestParam("personId") int personId) {
        bookDAO.assignBookToPerson(id, personId);
        return "redirect:/books";
    }
    @PostMapping("/release/{id}")
    public String releaseBook(@PathVariable("id") int id) {
        bookDAO.releaseBook(id);
        return "redirect:/books/" + id; // или другой URL в зависимости от логики приложения
    }
    @GetMapping("/new")
    public String createBook(Model model){
        model.addAttribute("book", new Book());
        return "books/newBook";
    }
    @PostMapping()
    public String saveBook(@ModelAttribute("book") @Valid Book Book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/newBook";
        }
        bookDAO.saveBook(Book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id ,Model model){
        model.addAttribute("book", bookDAO.showBook(id));
        return "books/editBook";
    }
    @PostMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book Book,
                               BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "books/editBook";
        }
        bookDAO.updateBook(Book, id);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }

}
