package wp.epam.protas.airline.dao.mysql;

import org.apache.log4j.Logger;
import wp.epam.protas.airline.dao.EmployeeDAO;
import wp.epam.protas.airline.db.Fields;
import wp.epam.protas.airline.entity.Employee;
import wp.epam.protas.airline.exception.DBException;
import wp.epam.protas.airline.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlEmployeeDAO implements EmployeeDAO {

    private static final Logger LOG = Logger.getLogger(MySqlEmployeeDAO.class);

    @Override
    public Employee getEmployeeById(Integer id) throws DBException {
        Employee employee = null;
        String sql = "SELECT * FROM employees WHERE id=?;";
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, id);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        employee = extractEmployee(rs);
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_EMPLOYEE_BY_ID, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_EMPLOYEE_BY_ID, e);
        }
        return employee;
    }

    @Override
    public Employee getEmployeeByLastName(String lastName) throws DBException {
        Employee employee = null;
        String sql = "SELECT * FROM employees WHERE last_name=?;";
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, lastName);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        employee = extractEmployee(rs);
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_EMPLOYEE_BY_LAST_NAME, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_EMPLOYEE_BY_LAST_NAME, e);
        }
        return employee;
    }


    @Override
    public List<Employee> getEmployees() throws DBException {
        List<Employee> employees;
        String sql = "SELECT * FROM employees";
        try {
            employees = getEmployees(sql);
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_EMPLOYEES, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_EMPLOYEES, e);
        }
        return employees;
    }

    @Override
    public void addEmployee(Employee employee) throws DBException {
        String sql = "INSERT INTO employees(first_name, last_name, profession_id) "
                + "VALUES (?,?,?);";

        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                int index = 1;
                st.setString(index++, employee.getFirstName());
                st.setString(index++, employee.getLastName());
                st.setInt(index, employee.getProfessionId());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_ADD_EMPLOYEE, e);
            throw new DBException(Messages.ERR_CANNOT_ADD_EMPLOYEE, e);
        }

    }

    @Override
    public void removeEmployee(Integer id) throws DBException {
        String sql = "DELETE FROM employees WHERE id=?;";

        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, id);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_REMOVE_EMPLOYEE, e);
            throw new DBException(Messages.ERR_CANNOT_REMOVE_EMPLOYEE, e);
        }
    }

    @Override
    public void changeEmployeeData(Employee employee) throws DBException {
        String sql = "UPDATE employees SET first_name=?, last_name=?, profession_id=? "
                + "WHERE id=?;";

        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                int index = 1;
                st.setString(index++, employee.getFirstName());
                st.setString(index++, employee.getLastName());
                st.setInt(index++, employee.getProfessionId());
                st.setInt(index, employee.getId());
                st.executeUpdate();

            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_UPDATE_EMPLOYEE, e);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_EMPLOYEE, e);
        }

    }

    private Employee extractEmployee(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setId(rs.getInt(Fields.EMPLOYEES_ID));
        e.setFirstName(rs.getString(Fields.EMPLOYEES_FIRST_NAME));
        e.setLastName(rs.getString(Fields.EMPLOYEES_LAST_NAME));
        e.setProfessionId(rs.getInt(Fields.EMPLOYEES_PROFESSION_ID));
        return e;
    }

    List<Employee> getEmployees(String sql) throws SQLException, DBException {
        List<Employee> list = new ArrayList<>();
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(sql)) {
                    while (rs.next()) {
                        Employee e = extractEmployee(rs);
                        list.add(e);
                    }
                }
            }
        }
        return list;
    }

}
