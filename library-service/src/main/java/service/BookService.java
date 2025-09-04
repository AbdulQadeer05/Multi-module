package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Book;
import repository.BookRepository;

@Service
public class BookService {

	
	private BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public Book addBook(Book book) {
        return repo.save(book);
    }

    public List<Book> getAllBooks() {
        return repo.findAll();
    }
    
    public Book updateBook(Long id, String name, String title, String author) {
        Optional<Book> opt = repo.findById(id);
        if (opt.isPresent()) {
            Book b = opt.get();
            b.setName(name);
            b.setTitle(title);
            b.setAuthor(author);
            return repo.save(b);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
        
    }
    
    public void deleteBook(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }
}
