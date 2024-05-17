package cicd.githubactions.demo.repository;

import cicd.githubactions.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
