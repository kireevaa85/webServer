package ru.kireev.services;

import org.eclipse.jetty.security.AbstractLoginService;
import org.eclipse.jetty.util.security.Password;
import ru.kireev.dao.UserDao;
import ru.kireev.model.User;

import java.util.Optional;

public class InMemoryLoginServiceImpl extends AbstractLoginService {

    private final UserDao userDao;

    public InMemoryLoginServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected String[] loadRoleInfo(UserPrincipal userPrincipal) {
        return new String[] {"user"};
    }

    @Override
    protected UserPrincipal loadUserInfo(String login) {
        System.out.println(String.format("InMemoryLoginService#loadUserInfo(%s)", login));
        Optional<User> dbUser = userDao.findByLogin(login);
        return dbUser.map(u -> new UserPrincipal(u.getLogin(), new Password(u.getPassword()))).orElse(null);
    }
}
