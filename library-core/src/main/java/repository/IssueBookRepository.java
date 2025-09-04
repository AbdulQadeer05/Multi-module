package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import entity.IssueBook;

@Repository
public interface IssueBookRepository extends JpaRepository<IssueBook, Long> {
}
