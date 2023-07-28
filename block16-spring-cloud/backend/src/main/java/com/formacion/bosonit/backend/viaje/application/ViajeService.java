package com.formacion.bosonit.backend.viaje.application;

import com.formacion.bosonit.backend.viaje.controller.ViajeDTO;

import java.util.List;

public interface ViajeService {
    ViajeDTO getViajeById(int viaje_id);
    List<ViajeDTO> getAllViajes(int pageNumber, int pageSize);
    int getNumberPassengersOnTripById(int viaje_id);
    String getTripStatusById(int viaje_id);
    ViajeDTO addViaje(ViajeDTO dto);
    void addOnePassengerToTrip(int viaje_id, int cliente_id);
    ViajeDTO updateViajeById(int viaje_id, ViajeDTO dto);
    ViajeDTO updateViajeStatusById(int viaje_id, String status);
    void deleteViajeById(int viaje_id);
}
