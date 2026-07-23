package com.careflow.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.careflow.entity.Ticket;
import com.careflow.service.TicketService;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})
public class TicketController {

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private TicketService service;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> saveTicket(@RequestBody Ticket ticket) {
        logger.info("Saving ticket: {}", ticket);
        try {
            Ticket saved = service.saveTicket(ticket);
            logger.info("Saved ticket id={}", saved.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            logger.error("Failed to save ticket", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to save ticket", "message", e.getMessage()));
        }
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return service.getAllTickets();
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return service.getTicketById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable Long id) {
        service.deleteTicket(id);
        return "Ticket Deleted Successfully";
    }
}