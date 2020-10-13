package ru.panifidkin.springbootapp_1.springbootapp_1.mapper;

import ru.panifidkin.springbootapp_1.springbootapp_1.model.User;
import ru.panifidkin.springbootapp_1.springbootapp_1.model.UserTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserTO toUserTO(User user) {
        UserTO to = new UserTO();
        to.setId(user.getId());
        to.setName(user.getName());
        to.setLastName(user.getLastName());
        to.setRoles(user.getRoles());
        return to;
    }


    public static User toUser(UserTO to) {
        User user = new User();
        user.setId(to.getId());
        user.setName(to.getName());
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
