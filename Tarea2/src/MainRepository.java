import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class MainRepository {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "22121";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-200";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            UserRepository userRepository = new UserRepositoryJdbcImp(connection);

            List<User> usersByAge = userRepository.findAllByAge(25);
            System.out.println("Users with age 25:");
            for (User user : usersByAge) {
                System.out.println(user);
            }

            List<User> usersByName = userRepository.findByFirstName("Juan");
            System.out.println("Users with first name Juan:");
            for (User user : usersByName) {
                System.out.println(user);
            }

            List<User> allUsers = userRepository.findAll();
            System.out.println("All users:");
            for (User user : allUsers) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
