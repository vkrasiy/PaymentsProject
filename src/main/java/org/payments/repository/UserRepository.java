package org.payments.repository;

import org.payments.entities.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getUser(int id);
    int addUser(User user);
    void deleteUser(String id);
    void updateUser(User user);
    User getUser(String login, String pass, boolean isAdmin);
    boolean existsUserWithSuchLogin(String login);
}
