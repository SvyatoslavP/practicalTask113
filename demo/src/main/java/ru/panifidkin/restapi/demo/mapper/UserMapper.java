package ru.panifidkin.restapi.demo.mapper;

import ru.panifidkin.restapi.demo.model.User;
import ru.panifidkin.restapi.demo.model.UserTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserTO toUserTO(User user) {
        UserTO to = new UserTO();
        to.setId(user.getId());
        to.setFirstName(user.getFirstName());
        to.setLastName(user.getLastName());
        to.setPassword(user.getPassword());
        to.setRoles(user.getRoles());
        return to;
    }


    public static User toUser(UserTO to) {
        User user = new User();
        user.setId(to.getId());
        user.setFirstName(to.getFirstName());
        user.setLastName(to.getLastName());
        user.setPassword(to.getPassword());
        user.setRoles(to.getRoles());
        return user;
    }

    public static List<UserTO> toUsersTO(List<User> users) {
        return users.stream()
                .map(UserMapper::toUserTO)
                .collect(Collectors.toList());
    }
}
