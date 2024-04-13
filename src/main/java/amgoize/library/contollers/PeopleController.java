package amgoize.library.contollers;

import amgoize.library.dao.PersonDAO;
import amgoize.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String showPeople(Model model){
        model.addAttribute("people", personDAO.showPeople());
        return "people/showPeople";
    }
    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.showPerson(id));
        return "people/showPerson";
    }
    @GetMapping("/new")
    public String createPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/newPerson";
    }
    @PostMapping()
    public String savePerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "people/newPerson";
        }
        personDAO.savePerson(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id ,Model model){
        Person person = personDAO.showPerson(id);
        System.out.println("Loaded person: " + person);
        System.out.println("meow");
        model.addAttribute("person", person);
        return "people/editPerson";
    }
    @PostMapping("/{id}")
    public String updatePerson( @PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "people/editPerson";
        }
        personDAO.updatePerson(person, id);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personDAO.deletePerson(id);
        return "redirect:/people";
    }


}

