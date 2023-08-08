package com.formacion.bosonit.backend.cliente.application;

import com.formacion.bosonit.backend.cliente.controller.ClienteDTO;

import java.util.List;

public interface ClienteService {
    ClienteDTO getClienteById(int cliente_id);
    List<ClienteDTO> getAllClientes(int pageNumber, int pageSize);
    ClienteDTO addCliente(ClienteDTO dto);
    ClienteDTO updateClienteById(int cliente_id, ClienteDTO dto);
    void deleteClienteById(int cliente_id);
}
