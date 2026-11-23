package com.example.panalsuite.Config;

import com.example.panalsuite.model.User;
import com.example.panalsuite.model.UserRole;
import com.example.panalsuite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
public class DataLoader implements CommandLineRunner {
    private final UserRepository users;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        if (users.count() > 0) return;
        create("Victor Castro", "victor@panal.cl", UserRole.REQUESTER);
        create("Martin Contreras", "martin@panal.cl", UserRole.RESOLVER);
        create("Marta Hernandez", "marta@panal.cl", UserRole.SUPERVISOR);
        create("Helena Vera", "helena@panal.cl", UserRole.MANAGER);
        create("Alex Silva", "admin@panal.cl", UserRole.ADMIN);
    }

    private void create(String name, String email, UserRole role) {
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPassword(encoder.encode("1234"));
        u.setRole(role);
        users.save(u);
    }
}
