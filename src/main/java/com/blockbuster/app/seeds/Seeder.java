package com.blockbuster.app.seeds;

import com.blockbuster.app.models.*;
import com.blockbuster.app.models.Employee;
import com.blockbuster.app.models.User;
import com.blockbuster.app.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Seeder {

    @Bean
    @Transactional
    public CommandLineRunner seedDatabase(
            UserRepository userRepository,
            EmployeeRepository employeeRepository,
            ClientRepository clientRepository,
            MovieRepository movieRepository,
            PaymentRepository paymentRepository,
            RentRepository rentRepository
    ) {
        return args -> {
            // Check if data already exists to prevent duplicate seeding
            if (userRepository.count() == 0) {
                // Create Users
                User user1 = new User();
                user1.setEmail("john.doe@example.com");
                user1.setPassword("password123");
                userRepository.save(user1);

                User user2 = new User();
                user2.setEmail("john.do2e@example.com");
                user2.setPassword("password123");
                userRepository.save(user2);

                Client client = new Client();
                client.setName("John");
                client.setUser(user1);
                client.setLastName("Constantine");

                client.setAddress("Calle de los marcianos 24");
                client.setPhone("33333333333");
                client.setRegisterAt(LocalDateTime.now());
                clientRepository.save(client);
                // Create Employees for user1
                Employee emp1 = new Employee();
                emp1.setUser(user2);
                emp1.setName("Dave");
                emp1.setLastName("Padel");
                emp1.setCharge("Cashier");
                emp1.setPhone("3313123242");
                emp1.setHiredAt(LocalDateTime.now());

                // Save Employees
                employeeRepository.save(emp1);

                Movie movie = new Movie();
                movie.setTitle("Toalla del mojado");
                movie.setGenre("Drama");
                movie.setDirector("Frankie Rivers");
                movie.setReleasedAt(LocalDateTime.now());
                movie.setRate("A");
                movie.setDuration(LocalTime.now());
                movie.setStock(1);
                movieRepository.save(movie);

                List<Movie> list = new ArrayList<>();
                list.add(movie);

                Rent rent = new Rent();
                rent.setRentAt(LocalDateTime.now());
                rent.setDevolutionAt(LocalDateTime.now());
                rent.setUser(user1);
                rent.setMovie(list);
                rent.setAmount(BigDecimal.valueOf(111));
                rent.setStatus("asfsa");
                rentRepository.save(rent);


                Payment payment = new Payment();
                payment.setPayAt(LocalDateTime.now());
                payment.setRent(rent);
                payment.setAmount(BigDecimal.valueOf(11));
                payment.setPaymentMethod("Card");
                paymentRepository.save(payment);
                System.out.println("Database seeded successfully!");
            } else {
                System.out.println("Database already contains data. Seeding skipped.");
            }
        };
    }
}
