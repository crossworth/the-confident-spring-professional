package br.dev.pedro.pdfinvoices.service;

import br.dev.pedro.pdfinvoices.model.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Component make this service a bean when spring find it
 * to enable spring to find it we need to add ComponentScan to the configuration
 */

@Component
public class UserService {

    public User findByID(String id) {
        String randomName = UUID.randomUUID().toString();
        return new User(id, randomName);
    }
}
