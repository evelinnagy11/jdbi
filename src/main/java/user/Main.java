package user;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());

        try (Handle handle = jdbi.open()) {
            UserDao user = handle.attach(UserDao.class);
            //User user = new User;
            user.createTable();
            user.name("James Bond")
                    .username("007")
                    // ...
                    .dob(LocalDate.parse("1920-11-11"))
                    .build();
            user.listUsers().stream().forEach(System.out::println);
        }
    }
}
