package br.dev.pedro.pdfinvoices.service;

import br.dev.pedro.pdfinvoices.model.User;

import java.util.UUID;

public class UserService {

    public User findByID(String id) {
        String randomName = UUID.randomUUID().toString();
        return new User(id, randomName);
    }
}
