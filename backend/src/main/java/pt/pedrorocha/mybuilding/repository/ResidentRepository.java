package pt.pedrorocha.mybuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.pedrorocha.mybuilding.entity.Resident;

import java.util.Collection;

public interface ResidentRepository extends JpaRepository<Resident, Long> {

    Long id(Integer id);

    Long buildingId(Integer findByBuildingId);

    Collection<Object> findByBuildingId(Long buildingId);
}
