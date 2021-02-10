package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        String n1 = "Alex", n2 = "Nik", n3 = "Vlad", n4 = "Max";
        String lastN1 = "Lim", lastN2 = "Ad", lastN3 = "Don", lastN4 = "Kilin";
        byte age1 = 33, age2 = 20, age3 = 25, age4 = 30;
        userService.saveUser(n1, lastN1, age1);
        System.out.println("User с именем " + n1 + " добавлен в базу данных");
        userService.saveUser(n2, lastN2, age2);
        System.out.println("User с именем " + n2 + " добавлен в базу данных");
        userService.saveUser(n3, lastN3, age3);
        System.out.println("User с именем " + n3 + " добавлен в базу данных");
        userService.saveUser(n4, lastN4, age4);
        System.out.println("User с именем " + n4 + " добавлен в базу данных");

        List<User> list = userService.getAllUsers();
        System.out.println(list.toString());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
