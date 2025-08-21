package pt.pedrorocha.mybuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.pedrorocha.mybuilding.model.Building;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    boolean existsByName(String name);
}
