package wp.epam.protas.airline.dao.mysql;

import org.apache.log4j.Logger;
import wp.epam.protas.airline.dao.*;
import wp.epam.protas.airline.exception.DBException;
import wp.epam.protas.airline.exception.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class MySqlDAOFactory extends DAOFactory {
    private static final Logger LOG = Logger.getLogger(MySqlDAOFactory.class);

    private static MySqlDAOFactory instance;
    private DataSource ds;

    private MySqlDAOFactory() throws DBException {
        InitialContext context;
        try {
            context = new InitialContext();
            Context envCon = (Context) context.lookup("java:/comp/env");
            ds = (DataSource) envCon.lookup("jdbc/aircompany");
        } catch (NamingException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, e);
        }
    }

    public static synchronized MySqlDAOFactory getInstance() throws DBException {
        if (instance == null) {
            instance = new MySqlDAOFactory();
        }
        return instance;
    }

    @Override
    public ApplicationDAO getApplicationDAO() {
        return new MySqlApplicationDAO();
    }

    @Override
    public BrigadeDAO getBrigadeDAO() {
        return new MySqlBrigadeDAO();
    }

    @Override
    public FlightDAO getFlightDAO() {
        return new MySqlFlightDAO();
    }

    @Override
    public EmployeeDAO getEmployeeDAO() {
        return new MySqlEmployeeDAO();
    }

    @Override
    public UserDAO getUserDAO() {
        return new MySqlUserDAO();
    }


    public Connection getConnection() throws DBException {
        Connection conn = null;
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, e);
        }
        return conn;
    }

}
