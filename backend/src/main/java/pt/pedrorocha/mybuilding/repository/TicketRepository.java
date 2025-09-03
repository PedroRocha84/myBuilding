package pt.pedrorocha.mybuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.pedrorocha.mybuilding.model.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    boolean getTicketById(Integer id);

    List<Ticket> findByBuildingId(Long buildingId);
    List<Ticket> findByResidentId(Long residentId);
}
