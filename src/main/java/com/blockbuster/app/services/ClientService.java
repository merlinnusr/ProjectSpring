package com.blockbuster.app.services;

import com.blockbuster.app.dtos.ClientDto;
import com.blockbuster.app.models.Client;
import com.blockbuster.app.models.User;
import com.blockbuster.app.repositories.ClientRepository;
import com.blockbuster.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private UserRepository userRepository;
    public Client create(ClientDto clientDto){
        User user = new User();
        user.setEmail(clientDto.getEmail());
        user.setPassword(clientDto.getPassword());
        userRepository.save(user);

        Client client = new Client();
        client.setUser(user);
        client.setName(clientDto.getName());
        client.setLastName(clientDto.getLastName());
        client.setAddress(clientDto.getAddress());
        client.setPhone(clientDto.getPhone());
        client.setRegisterAt(clientDto.getRegisterAt());
        clientRepository.save(client);
        return client;
    }

}
