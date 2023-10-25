import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImp implements UserRepository {
    private final Connection connection;
    private static final String SQL_SELECT_ALL_FROM_DRIVER = "SELECT * FROM users";

    public UserRepositoryJdbcImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAllByAge(Integer age) {
        try {
            String sql = SQL_SELECT_ALL_FROM_DRIVER + " WHERE age = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, age);
            ResultSet resultSet = statement.executeQuery();
            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age")
                );

                result.add(user);
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        try {
            String sql = SQL_SELECT_ALL_FROM_DRIVER + " WHERE first_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            ResultSet resultSet = statement.executeQuery();
            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age")
                );

                result.add(user);
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException("Error al buscar usuarios por nombre", e);
        }
    }

    @Override
    public List<User> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_DRIVER);
            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age")
                );

                result.add(user);
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException("Error al encontrar usuarios", e);
        }
    }
}
