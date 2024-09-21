package com.blockbuster.app.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class ClientDto {
    private String email;
    private String password;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private LocalDateTime registerAt;
}
