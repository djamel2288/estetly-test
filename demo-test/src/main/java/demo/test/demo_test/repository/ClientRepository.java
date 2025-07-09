package demo.test.demo_test.repository;

import demo.test.demo_test.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAll();

    List<Client> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
