package com.blockbuster.app.controllers;

import com.blockbuster.app.dtos.ClientDto;
import com.blockbuster.app.models.Client;
import com.blockbuster.app.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService userService;
    @PostMapping
    public Client createClient(@RequestBody ClientDto clientDto) {
        return userService.create(clientDto);
    }
}
