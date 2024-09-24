package com.blockbuster.app.controllers;

import com.blockbuster.app.dtos.ClientDto;
import com.blockbuster.app.models.Client;
import com.blockbuster.app.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService userService;
    @PostMapping
    public Client createClient(@RequestBody ClientDto clientDto) {
        return userService.create(clientDto);
    }
    @GetMapping
    public List<Client> getClients() {
        return userService.all();
    }
    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return userService.findUser(id);
    }
    @PutMapping("/{id}")
    public Client updateClient(@RequestBody ClientDto clientDto, @PathVariable Long id) {
        return userService.updateUser(clientDto, id);
    }
}
