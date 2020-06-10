package wp.epam.protas.airline.dao;

import wp.epam.protas.airline.dao.mysql.MySqlDAOFactory;
import wp.epam.protas.airline.exception.DBException;

public abstract class DAOFactory {
    public static DAOFactory getDAOFactory() throws DBException {
        return MySqlDAOFactory.getInstance();
    }

    public abstract ApplicationDAO getApplicationDAO();

    public abstract BrigadeDAO getBrigadeDAO();

    public abstract FlightDAO getFlightDAO();

    public abstract EmployeeDAO getEmployeeDAO();

    public abstract UserDAO getUserDAO();


}
