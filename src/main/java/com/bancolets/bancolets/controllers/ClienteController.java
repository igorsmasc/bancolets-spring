package com.bancolets.bancolets.controllers;

import com.bancolets.bancolets.model.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ClienteController {

    /**
     * CRUD
     *
     * CREATE - POST
     * READ - GET
     * UPDATE - PUT
     * DELETE - DELETE
     *
     */


    private ArrayList<Cliente> clientesDB = new ArrayList<>();
    private int count = 0;

    @PostMapping("/cliente")
    public Cliente criarCliente(@RequestBody Cliente novoCliente) {
        count++;
        novoCliente.setId(count);
        clientesDB.add(novoCliente);

        return novoCliente;
    }

    @GetMapping("/clientes")
    public ArrayList<Cliente> retornaTodosOsClientes() {
        return clientesDB;
    }

    @PutMapping("/cliente")
    public Cliente atualizaCliente(@RequestBody Cliente clienteAtualizado) {
        /**
         *
         * lista clientesDB - size 3
         *  [
         *  {1, pedro, 22}, 0
         *  {2, maria, 19}, 1
         *  {3, ana, 25}    2
         *  ]
         *
         */
                // i = 2; i < 3; i++
        for(int i = 0; i < clientesDB.size(); i++) {
            if(clientesDB.get(i).getId() == clienteAtualizado.getId()) {
                Cliente clienteDadosAntigos = clientesDB.get(i);

                clientesDB.remove(i);
                clientesDB.add(new Cliente(clienteDadosAntigos.getId(), clienteAtualizado.getNome(), clienteAtualizado.getIdade()));
                break;
            }
        }

        return clienteAtualizado;
    }

    @DeleteMapping("/cliente")
    public String deletaCliente(@RequestParam(value = "id") int id) {
        for(int i = 0; i < clientesDB.size(); i++) {
            if(clientesDB.get(i).getId() == id) {
                clientesDB.remove(i);
                break;
            }
        }

        return "Usuario deletado com sucesso!";
    }

}
