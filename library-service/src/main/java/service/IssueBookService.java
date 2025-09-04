package service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import entity.Book;
import entity.User;
import entity.IssueBook;
import repository.BookRepository;
import repository.UserRepository;
import repository.IssueBookRepository;

@Service
public class IssueBookService {

    private final IssueBookRepository issueRepo;
    private final BookRepository bookRepo;
    private final UserRepository userRepo;

    public IssueBookService(IssueBookRepository issueRepo, BookRepository bookRepo, UserRepository userRepo) {
        this.issueRepo = issueRepo;
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    
    public IssueBook issueBook(Long bookId, Long userId) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + bookId));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + userId));

       
        if (issueRepo.findAll().stream().anyMatch(i -> i.getBook().getId().equals(bookId))) {
            throw new RuntimeException("Book is already issued to someone else!");
        }

        IssueBook issue = new IssueBook();
        issue.setBook(book);
        issue.setUser(user);
        issue.setIssueDate(LocalDate.now());
        issue.setReturnDate(null);

        return issueRepo.save(issue);
    }

    
    public IssueBook returnBook(Long issueId) {
        IssueBook issue = issueRepo.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue record not found"));

        issue.setReturnDate(LocalDate.now());
        return issueRepo.save(issue);
    }

    
    public List<IssueBook> getAllIssues() {
        return issueRepo.findAll();
    }
}
