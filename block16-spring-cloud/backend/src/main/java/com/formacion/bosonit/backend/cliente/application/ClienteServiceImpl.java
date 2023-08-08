package com.formacion.bosonit.backend.cliente.application;

import com.formacion.bosonit.backend.cliente.controller.ClienteDTO;
import com.formacion.bosonit.backend.cliente.domain.Cliente;
import com.formacion.bosonit.backend.cliente.repository.ClienteRepository;
import com.formacion.bosonit.backend.exception.EntityNotFoundException;
import com.formacion.bosonit.backend.viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ViajeRepository viajeRepository;
    private static final String CLIENT_ID_ERROR = "No existe el cliente con el id indicado";
    @Override
    public ClienteDTO getClienteById(int cliente_id) {
        return new ClienteDTO(clienteRepository.findById(cliente_id)
                .orElseThrow(() -> new EntityNotFoundException(CLIENT_ID_ERROR)));
    }

    @Override
    public List<ClienteDTO> getAllClientes(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return clienteRepository.findAll(pageRequest).getContent()
                .stream().map(ClienteDTO::new).toList();
    }

    @Override
    public ClienteDTO addCliente(ClienteDTO dto) {
        Cliente cliente = clienteRepository.save(dto.clienteDtoToCliente());
        return new ClienteDTO(cliente);
    }

    @Override
    public ClienteDTO updateClienteById(int cliente_id, ClienteDTO dto) {
        clienteRepository.findById(cliente_id)
                .orElseThrow(() -> new EntityNotFoundException(CLIENT_ID_ERROR));

        Cliente updateCliente = dto.clienteDtoToCliente();
        updateCliente.setCliente_id(cliente_id);

        return new ClienteDTO(clienteRepository.save(updateCliente));
    }

    @Override
    public void deleteClienteById(int cliente_id) {
        Cliente deleteCliente = clienteRepository.findById(cliente_id)
                .orElseThrow(() -> new EntityNotFoundException(CLIENT_ID_ERROR));

        viajeRepository.findAll().forEach(viaje -> {
            viaje.getPassenger().remove(deleteCliente);
        });

        clienteRepository.deleteById(cliente_id);
    }
}
