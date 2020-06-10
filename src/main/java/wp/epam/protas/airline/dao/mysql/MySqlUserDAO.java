package wp.epam.protas.airline.dao.mysql;

import wp.epam.protas.airline.dao.UserDAO;
import wp.epam.protas.airline.db.Fields;
import wp.epam.protas.airline.entity.User;
import wp.epam.protas.airline.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDAO implements UserDAO {

    @Override
    public User getUserById(Integer id) throws DBException {
        User user = null;
        String sql = "SELECT * FROM users WHERE user_id=?;";
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, id);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        user = extractUser(rs);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) throws DBException {
        User user = null;
        String sql = "SELECT * FROM users WHERE login=?;";
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, login);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        user = extractUser(rs);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return user;
    }

    @Override
    public List<User> getUsers() throws DBException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users;";
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(sql)) {
                    while (rs.next()) {
                        User user = extractUser(rs);
                        users.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return users;
    }

    @Override
    public boolean addUser(User user) throws DBException {
        String sql = "insert into users (login, password, first_name, last_name, email, role_id) "
                + "values(?,?,?,?,?,?);";
        boolean isAdded = false;
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                int index = 1;
                st.setString(index++, user.getLogin());
                st.setString(index++, user.getPassword());
                st.setString(index++, user.getFirstName());
                st.setString(index++, user.getLastName());
                st.setString(index++, user.getEmail());
                st.setInt(index++, user.getRoleId());
                int count = st.executeUpdate();
                if (count == 1) {
                    isAdded = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return isAdded;
    }

    @Override
    public boolean removeUser(Integer id) throws DBException {
        String sql = "delete from users where user_id=?;";
        boolean isRemoved = false;
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, id);
                int count = st.executeUpdate();
                if (count == 1) {
                    isRemoved = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return isRemoved;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(Fields.USERS_USER_ID));
        user.setLogin(rs.getString(Fields.USERS_LOGIN));
        user.setPassword(rs.getString(Fields.USERS_PASSWORD));
        user.setFirstName(rs.getString(Fields.USERS_FIRST_NAME));
        user.setLastName(rs.getString(Fields.USERS_LAST_NAME));
        user.setEmail(rs.getString(Fields.USERS_EMAIL));
        user.setRoleId(rs.getInt(Fields.USERS_ROLE_ID));
        return user;
    }

}
