package com.formacion.bosonit.backendfront.ticket.controller;

import com.formacion.bosonit.backendfront.ticket.controller.dto.ClienteDTO;
import com.formacion.bosonit.backendfront.ticket.controller.dto.ViajeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://backend-service:8081", name = "TicketFeign")
public interface TicketFeign {
    @GetMapping("/passenger/{cliente_id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable int cliente_id);

    @GetMapping("/trip/{viaje_id}")
    public ResponseEntity<ViajeDTO> getViajeById(@PathVariable int viaje_id);
}
