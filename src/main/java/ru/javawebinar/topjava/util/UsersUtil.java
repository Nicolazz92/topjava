package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nicolas on 18.09.2016.
 */
public class UsersUtil {
    private static Set<Role> user = new HashSet<>();
    private static Set<Role> admin = new HashSet<>();
    private static Set<Role> full = new HashSet<>();

    static {
        user.add(Role.ROLE_USER);
        admin.add(Role.ROLE_ADMIN);
        full.addAll(Arrays.asList(Role.ROLE_ADMIN, Role.ROLE_USER));
    }

    public static final List<User> USERS = Arrays.asList(
            new User(1, "Joe", "Joe@mail", "pass1", 2000, true, user),
            new User(2, "Mark", "Mark@mail", "pass2", 2100, true, admin),
            new User(3, "Peter", "peter@mail", "pass3", 2200, true, full)
    );

}
