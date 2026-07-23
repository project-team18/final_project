package com.careflow.serviceimplementation;

import java.util.List;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careflow.entity.Ticket;
import com.careflow.repository.TicketRepository;
import com.careflow.service.TicketService;

@Service
public class TicketServiceImplementation implements TicketService {

    private static final Logger logger = LoggerFactory.getLogger(TicketServiceImplementation.class);

    @Autowired
    private TicketRepository repo;

    @Override
    @Transactional
    public Ticket saveTicket(Ticket ticket) {
        logger.info("Ticket before save: title={}, category={}, priority={}", ticket.getTitle(), ticket.getCategory(), ticket.getPriority());
        Ticket saved = repo.save(ticket);
        logger.info("Ticket saved with id={} and status={}", saved.getId(), saved.getStatus());
        return saved;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return repo.findAll();
    }

    @Override
    public Ticket getTicketById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deleteTicket(Long id) {
        repo.deleteById(id);
    }
}