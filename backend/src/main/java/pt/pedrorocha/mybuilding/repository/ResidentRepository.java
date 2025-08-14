package pt.pedrorocha.mybuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.pedrorocha.mybuilding.model.Resident;

public interface ResidentRepository extends JpaRepository<Resident, Long> {

}
