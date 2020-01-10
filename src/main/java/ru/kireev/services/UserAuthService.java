package ru.kireev.services;

public interface UserAuthService {
    boolean authenticate(String login, String password);
}
