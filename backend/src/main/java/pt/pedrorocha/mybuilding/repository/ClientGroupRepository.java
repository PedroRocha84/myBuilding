package pt.pedrorocha.mybuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.pedrorocha.mybuilding.model.ClientGroup;

public interface ClientGroupRepository extends JpaRepository<ClientGroup, Long> {

}
