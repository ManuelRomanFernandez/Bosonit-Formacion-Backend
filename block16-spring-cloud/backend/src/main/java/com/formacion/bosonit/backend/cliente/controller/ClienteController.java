package com.formacion.bosonit.backend.cliente.controller;

import com.formacion.bosonit.backend.cliente.application.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/passenger")
public class ClienteController {
    @Autowired
    ClienteService service;

    @GetMapping("/{cliente_id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable int cliente_id) {
        return ResponseEntity.ok().body(service.getClienteById(cliente_id));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize)
    {
        return ResponseEntity.ok().body(service.getAllClientes(pageNumber, pageSize));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> addCliente(@RequestBody ClienteDTO dto){
        URI location = URI.create("/passenger");
        return ResponseEntity.created(location).body(service.addCliente(dto));
    }

    @PutMapping("/{cliente_id}")
    public ResponseEntity<ClienteDTO> updateClienteById(
            @PathVariable int cliente_id,
            @RequestBody ClienteDTO dto)
    {
        return ResponseEntity.ok().body(service.updateClienteById(cliente_id, dto));
    }

    @DeleteMapping("/{cliente_id}")
    public void deleteClienteById(@PathVariable int cliente_id){
        service.deleteClienteById(cliente_id);
    }
}
