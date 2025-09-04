package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import entity.Publisher;
import repository.PublisherRepository;

@Service
public class PublisherService {

    private final PublisherRepository repo;

    public PublisherService(PublisherRepository repo) {
        this.repo = repo;
    }

    public Publisher addPublisher(Publisher publisher) {
        return repo.save(publisher);
    }

    public List<Publisher> getAllPublishers() {
        return repo.findAll();
    }

    public Publisher updatePublisher(Long id, String name, String address) {
        Optional<Publisher> opt = repo.findById(id);
        if (opt.isPresent()) {
            Publisher p = opt.get();
            p.setName(name);
            p.setAddress(address);
            return repo.save(p);
        } else {
            throw new RuntimeException("Publisher not found with id: " + id);
        }
    }

    public void deletePublisher(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Publisher not found with id: " + id);
        }
    }
}
