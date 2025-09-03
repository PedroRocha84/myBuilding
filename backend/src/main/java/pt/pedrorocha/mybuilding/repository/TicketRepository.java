package pt.pedrorocha.mybuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.pedrorocha.mybuilding.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    boolean getTicketById(Integer id);
}
