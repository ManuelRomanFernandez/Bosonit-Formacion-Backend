package com.formacion.bosonit.backend.viaje.controller;

import com.formacion.bosonit.backend.viaje.application.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/trip")
public class ViajeController {
    @Autowired
    ViajeService service;
    @GetMapping("/{viaje_id}")
    public ResponseEntity<ViajeDTO> getViajeById(@PathVariable int viaje_id){
        return ResponseEntity.ok().body(service.getViajeById(viaje_id));
    }

    @GetMapping
    public ResponseEntity<List<ViajeDTO>> getAllViajes(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize
    ){
        return ResponseEntity.ok().body(service.getAllViajes(pageNumber, pageSize));
    }

    @GetMapping("/count/{viaje_id}")
    public ResponseEntity<Integer> getNumberPassengerOnTripById(@PathVariable int viaje_id){
        return ResponseEntity.ok().body(service.getNumberPassengersOnTripById(viaje_id));
    }

    @GetMapping("/verify/{viaje_id}")
    public ResponseEntity<String> getTripStatusById(@PathVariable int viaje_id){
        return ResponseEntity.ok().body(service.getTripStatusById(viaje_id));
    }


    @PostMapping
    public ResponseEntity<ViajeDTO> addViaje(@RequestBody ViajeDTO dto){
        URI location = URI.create("/trip");
        return ResponseEntity.created(location).body(service.addViaje(dto));
    }

    @PostMapping("/{viaje_id}/{cliente_id}")
    public void addOnePassengerToTrip(
            @PathVariable int viaje_id,
            @PathVariable int cliente_id
    ){
        service.addOnePassengerToTrip(viaje_id, cliente_id);
    }

    @PutMapping("/{viaje_id}")
    public ResponseEntity<ViajeDTO> updateViajeById(
            @PathVariable int viaje_id,
            @RequestBody ViajeDTO dto
    ){
        return ResponseEntity.ok().body(service.updateViajeById(viaje_id, dto));
    }

    @PutMapping("/{viaje_id}/{status}")
    public ResponseEntity<ViajeDTO> updateViajeStatusById(
            @PathVariable int viaje_id,
            @PathVariable String status
    ){
        return ResponseEntity.ok().body(service.updateViajeStatusById(viaje_id, status));
    }

    @DeleteMapping("/{viaje_id}")
    public void deleteViajeById(@PathVariable int viaje_id){
        service.deleteViajeById(viaje_id);
    }
}
