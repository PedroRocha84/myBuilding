package pt.pedrorocha.mybuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.pedrorocha.mybuilding.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
