package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import entity.User;
import repository.UserRepository;

@Service
public class UserService {

	private UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }
    
    public User addUser(User user) {
        return repo.save(user);
    }

    
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    
    public User updateUser(Long id, User newUser) {
        Optional<User> optional = repo.findById(id);
        if (optional.isPresent()) {
            User u = optional.get();
            u.setName(newUser.getName());
            u.setEmail(newUser.getEmail());
            return repo.save(u);
        }
        return null;
    }

    
    public boolean deleteUser(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
