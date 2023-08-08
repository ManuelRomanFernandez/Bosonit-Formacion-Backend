package com.formacion.bosonit.backendfront.ticket.controller;

import com.formacion.bosonit.backendfront.ticket.application.TicketService;
import com.formacion.bosonit.backendfront.ticket.controller.dto.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
    @Autowired
    TicketService service;

    @PostMapping("/generateTicket/{cliente_id}/{viaje_id}")
    public ResponseEntity<TicketDTO> addTicket(
            @PathVariable int cliente_id,
            @PathVariable int viaje_id
    ){
        return ResponseEntity.ok().body(service.addTicket(cliente_id, viaje_id));
    }
}
