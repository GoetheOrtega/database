import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "22121";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-200";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM users");

        while (result.next()) {
            System.out.println(result.getInt("id") + result.getString("first_name"));
        }

        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.nextLine();
        String lastName = scanner.nextLine();
        int age = scanner.nextInt();

        String sqlInsertUser = "INSERT INTO users (first_name, last_name, age) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setInt(3, age);

        int affectedRows = preparedStatement.executeUpdate();

        System.out.println("Было добавлено " + affectedRows + " строк");
    }
}