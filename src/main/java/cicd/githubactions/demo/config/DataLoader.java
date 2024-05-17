package cicd.githubactions.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cicd.githubactions.demo.entity.Book;
import cicd.githubactions.demo.repository.BookRepository;

@Component
public class DataLoader implements CommandLineRunner {

    
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        // Add more books as needed
    }
}
