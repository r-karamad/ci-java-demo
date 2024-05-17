package cicd.githubactions.demo.controller;

import cicd.githubactions.demo.entity.Book;
import cicd.githubactions.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books"; // This refers to 'books.html' under the 'src/main/resources/templates' directory
    }


    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-new";
    }

    @PostMapping
    public String submitBook(Book book) {
        bookRepository.save(book); // saves the new book to the database
        return "redirect:/books"; // redirects to the listing page which needs to be created
    }

    @GetMapping("/{id}")
    public String getBookDetails(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found with id " + id));
        model.addAttribute("book", book);
        return "book-details"; 
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books"; 
    }

}