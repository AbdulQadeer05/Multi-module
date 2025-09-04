package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import entity.Author;
import repository.AuthorRepository;

@Service
public class AuthorService {

    private final AuthorRepository repo;

    public AuthorService(AuthorRepository repo) {
        this.repo = repo;
    }

    public Author addAuthor(Author author) {
        return repo.save(author);
    }

    public List<Author> getAllAuthors() {
        return repo.findAll();
    }

    public Author updateAuthor(Long id, String name, String email) {
        Optional<Author> opt = repo.findById(id);
        if (opt.isPresent()) {
            Author a = opt.get();
            a.setName(name);
            a.setEmail(email);
            return repo.save(a);
        } else {
            throw new RuntimeException("Author not found with id: " + id);
        }
    }

    public void deleteAuthor(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Author not found with id: " + id);
        }
    }
}
