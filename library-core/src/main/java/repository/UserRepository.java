package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import entity.User;

@Service
public interface UserRepository extends JpaRepository<User, Long> {

}
