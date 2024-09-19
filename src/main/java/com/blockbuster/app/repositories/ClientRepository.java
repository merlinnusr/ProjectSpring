package com.blockbuster.app.repositories;

import com.blockbuster.app.models.Client;
import com.blockbuster.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<Client, Long> {
}
