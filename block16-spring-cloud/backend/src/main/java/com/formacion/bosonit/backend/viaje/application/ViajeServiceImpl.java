package com.formacion.bosonit.backend.viaje.application;

import com.formacion.bosonit.backend.cliente.domain.Cliente;
import com.formacion.bosonit.backend.cliente.repository.ClienteRepository;
import com.formacion.bosonit.backend.exception.EntityNotFoundException;
import com.formacion.bosonit.backend.viaje.controller.ViajeDTO;
import com.formacion.bosonit.backend.viaje.domain.Viaje;
import com.formacion.bosonit.backend.viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViajeServiceImpl implements ViajeService{
    @Autowired
    ViajeRepository viajeRepository;
    @Autowired
    ClienteRepository clienteRepository;
    private static final String TRIP_ID_ERROR = "No existe el viaje con el id indicado";
    private static final String CLIENT_ID_ERROR = "No existe el cliente con el id indicado";
    @Override
    public ViajeDTO getViajeById(int viaje_id) {
        return new ViajeDTO(viajeRepository.findById(viaje_id)
                .orElseThrow(() -> new EntityNotFoundException(TRIP_ID_ERROR)));
    }

    @Override
    public List<ViajeDTO> getAllViajes(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return viajeRepository.findAll(pageRequest).getContent()
                .stream().map(ViajeDTO::new).toList();
    }

    @Override
    public int getNumberPassengersOnTripById(int viaje_id) {
        return viajeRepository.findById(viaje_id)
                .orElseThrow(() -> new EntityNotFoundException(TRIP_ID_ERROR))
                .getPassenger()
                .size();
    }

    @Override
    public String getTripStatusById(int viaje_id) {
        Viaje viaje = viajeRepository.findById(viaje_id)
                .orElseThrow(() -> new EntityNotFoundException(TRIP_ID_ERROR));

        return viaje.getStatus();
    }

    @Override
    public ViajeDTO addViaje(ViajeDTO dto) {
        Viaje viaje = viajeRepository.save(dto.viajeDtoToViaje());
        return new ViajeDTO(viaje);
    }

    @Override
    public void addOnePassengerToTrip(int viaje_id, int cliente_id) {
        Viaje updateViaje = viajeRepository.findById(viaje_id)
                .orElseThrow(() -> new EntityNotFoundException(TRIP_ID_ERROR));

        Cliente updateCliente = clienteRepository.findById(cliente_id)
                .orElseThrow(() -> new EntityNotFoundException(CLIENT_ID_ERROR));

        updateViaje.getPassenger().add(updateCliente);
        updateCliente.getViajes().add(updateViaje);

        clienteRepository.save(updateCliente);
        viajeRepository.save(updateViaje);
    }

    @Override
    public ViajeDTO updateViajeById(int viaje_id, ViajeDTO dto) {
        viajeRepository.findById(viaje_id)
                .orElseThrow(() -> new EntityNotFoundException(TRIP_ID_ERROR));

        Viaje updateViaje = dto.viajeDtoToViaje();
        updateViaje.setViaje_id(viaje_id);

        return new ViajeDTO(viajeRepository.save(updateViaje));
    }

    @Override
    public ViajeDTO updateViajeStatusById(int viaje_id, String status) {
        Viaje updateViaje = viajeRepository.findById(viaje_id)
                .orElseThrow(() -> new EntityNotFoundException(TRIP_ID_ERROR));

        updateViaje.setStatus(status);

        return new ViajeDTO(viajeRepository.save(updateViaje));
    }

    @Override
    public void deleteViajeById(int viaje_id) {
        Viaje deleteViaje = viajeRepository.findById(viaje_id)
                .orElseThrow(() -> new EntityNotFoundException(TRIP_ID_ERROR));

        clienteRepository.findAll().forEach(cliente -> {
            cliente.getViajes().remove(deleteViaje);
        });

        viajeRepository.deleteById(viaje_id);
    }
}
