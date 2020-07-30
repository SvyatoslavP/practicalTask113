package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("K001HH54",10);
      Car car2 = new Car("K002HH54",7);
      Car car3 = new Car("K003HH54",11);
      Car car4 = new Car("K001HH54",9);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru",car1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru",car1));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",car2));
      userService.add(new User("User5", "Lastname5", "user5@mail.ru",car3));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru",car4));

      List<User> users = userService.getTheUserByHisCar("K001HH54",9);
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Car = " + user.getCar());
         System.out.println("--------------");
      }

      context.close();
   }
}
