package wp.epam.protas.airline.dao.mysql;

import org.apache.log4j.Logger;
import wp.epam.protas.airline.dao.BrigadeDAO;
import wp.epam.protas.airline.db.Fields;
import wp.epam.protas.airline.entity.Brigade;
import wp.epam.protas.airline.entity.Employee;
import wp.epam.protas.airline.exception.DBException;
import wp.epam.protas.airline.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlBrigadeDAO implements BrigadeDAO {
    private static final Logger LOG = Logger.getLogger(MySqlBrigadeDAO.class);

    @Override
    public Brigade getBrigadeById(Integer id) throws DBException {
        Brigade brigade = null;
        String sql = "SELECT * FROM brigades WHERE brigade_id=?;";
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, id);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        brigade = extractBrigade(rs);
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_BRIGADE_BY_ID, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_BRIGADE_BY_ID, e);
        }
        return brigade;
    }

    @Override
    public List<Brigade> getBrigades() throws DBException {
        List<Brigade> brigades = new ArrayList<>();
        String sql = "SELECT * FROM brigades;";
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(sql)) {
                    while (rs.next()) {
                        Brigade brigade = extractBrigade(rs);
                        brigades.add(brigade);
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_BRIGADES, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_BRIGADES, e);
        }
        return brigades;
    }

    @Override
    public List<Employee> getFreeEmployees() throws DBException {
        String sql = "SELECT employees.* FROM employees WHERE "
                + "employees.id NOT IN (SELECT employee_id FROM brigades_employees);";
        MySqlEmployeeDAO dao = new MySqlEmployeeDAO();
        List<Employee> employees;
        try {
            employees = dao.getEmployees(sql);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_FREE_EMPLOYEES, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_FREE_EMPLOYEES, e);
        }

        return employees;
    }

    @Override
    public List<Employee> getEmployeesInBrigade(Integer brigadeId) throws DBException {
        String sql = "SELECT employees.* FROM employees, brigades, brigades_employees "
                + "WHERE  brigades.brigade_id=" + brigadeId + " AND brigades.brigade_id=brigades_employees.brigade_id "
                + "AND employees.id=brigades_employees.employee_id ;";
        List<Employee> employees;
        try {
            employees = new MySqlEmployeeDAO().getEmployees(sql);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_EMPLOYEES_IN_BRIGADE + brigadeId, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_EMPLOYEES_IN_BRIGADE + brigadeId, e);
        }

        return employees;
    }

    @Override
    public void createBrigade(Brigade brigade) throws DBException {
        String sql = "INSERT INTO brigades(name, user_id) VALUES (?, ?);";

        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, brigade.getName());
                st.setInt(2, brigade.getUserId());
                st.executeUpdate();

            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_CREATE_BRIGADE, e);
            throw new DBException(Messages.ERR_CANNOT_CREATE_BRIGADE, e);
        }

    }

    @Override
    public void addEmployeeIntoBrigade(Integer idBrigade, Integer idEmployee) throws DBException {
        String sql = "INSERT INTO brigades_employees VALUES(?,?);";
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {

                st.setInt(1, idBrigade);
                st.setInt(2, idEmployee);
                st.executeUpdate();

            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_ADD_EMPLOYEE_IN_BRIGADE, e);
            throw new DBException(Messages.ERR_CANNOT_ADD_EMPLOYEE_IN_BRIGADE, e);
        }

    }

    private Brigade extractBrigade(ResultSet rs) throws SQLException {
        Brigade b = new Brigade();
        b.setId(rs.getInt(Fields.BRIGADES_BRIGADE_ID));
        b.setName(rs.getString(Fields.BRIGADES_NAME));
        b.setUserId(rs.getInt(Fields.BRIGADES_USER_ID));
        return b;
    }


}
