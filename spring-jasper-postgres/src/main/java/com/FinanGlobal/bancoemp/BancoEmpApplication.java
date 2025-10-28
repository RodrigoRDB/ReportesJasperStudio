package com.FinanGlobal.bancoemp;

import com.FinanGlobal.bancoemp.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BancoEmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(BancoEmpApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UsuarioRepository repo) {
        return args -> {
            long count = repo.count();
            System.out.println("TOTAL USUARIOS EN BD: " + count);
            repo.findAll().forEach(u -> System.out.println(u.getIdUsuario() + " - " + u.getNombre() + " " + u.getApellidos()));
        };
    }
}
