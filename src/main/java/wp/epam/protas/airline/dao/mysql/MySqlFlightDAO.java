package wp.epam.protas.airline.dao.mysql;

import org.apache.log4j.Logger;
import wp.epam.protas.airline.dao.FlightDAO;
import wp.epam.protas.airline.entity.Flight;
import wp.epam.protas.airline.exception.DBException;
import wp.epam.protas.airline.exception.Messages;
import wp.epam.protas.airline.db.Fields;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlFlightDAO implements FlightDAO {
    private static final Logger LOG = Logger.getLogger(MySqlFlightDAO.class);

    @Override
    public Flight getFlightById(Integer id) throws DBException {
        Flight flight = null;
        String sql = "SELECT * FROM flights WHERE flight_id=?; ";
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setInt(1, id);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        flight = extractFlight(rs);
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_FLIGHT_BY_ID, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_FLIGHT_BY_ID, e);
        }

        return flight;
    }

    @Override
    public List<Flight> getFlightsByParameters(String from, String to, Date date) throws DBException {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights WHERE from_port=? AND to_port=? AND date_flight=? ;";
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                int index = 1;
                st.setString(index++, from);
                st.setString(index++, to);
                st.setDate(index, date);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Flight f = extractFlight(rs);
                        flights.add(f);
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_FLIGHTS_BY_PARAMETERS, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_FLIGHTS_BY_PARAMETERS, e);
        }
        return flights;
    }

    @Override
    public List<Flight> getFlights() throws DBException {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights";
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(sql)) {
                    while (rs.next()) {
                        Flight f = extractFlight(rs);
                        flights.add(f);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return flights;
    }

    @Override
    public void addFlight(Flight flight) throws DBException {
        String sql = "INSERT INTO flights (name, from_port, to_port, date_flight, "
                + "time_flight,brigade_id, status_id) VALUES (?,?,?,?,?,?,?);";



        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                int index = 1;
                st.setString(index++, flight.getName());
                st.setString(index++, flight.getFromPort());
                st.setString(index++, flight.getToPort());
                st.setDate(index++, flight.getDateFlight());
                st.setTime(index++, flight.getTimeFlight());
                st.setInt(index++, flight.getBrigadeId());
                st.setInt(index, flight.getStatusId());
                st.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }

    }

    @Override
    public void removeFlight(Integer id) throws DBException {
        String sql = "DELETE FROM flights WHERE flight_id=?;";

        try (Connection conn = MySqlDAOFactory.getInstance().getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
    }

    @Override
    public void updateFlight(Integer id, Flight flight) throws DBException {
        String sql = "UPDATE flights SET name=?, from_port=?, to_port=?, date_flight=?, "
                + "time_flight=?, brigade_id=?, status_id=? WHERE flight_id=?;";
        try (Connection conn = MySqlDAOFactory.getInstance().getConnection()) {
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                int index = 1;
                st.setString(index++, flight.getName());
                st.setString(index++, flight.getFromPort());
                st.setString(index++, flight.getToPort());
                st.setDate(index++, flight.getDateFlight());
                st.setTime(index++, flight.getTimeFlight());
                st.setInt(index++, flight.getBrigadeId());
                st.setInt(index++, flight.getStatusId());
                st.setInt(index, flight.getId());
                st.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }

    }

    private Flight extractFlight(ResultSet rs) throws SQLException {
        Flight f = new Flight();
        f.setId(rs.getInt(Fields.FLIGHTS_FLIGHT_ID));
        f.setName(rs.getString(Fields.FLIGHTS_NAME));
        f.setFromPort(rs.getString(Fields.FLIGHTS_FROM_PORT));
        f.setToPort(rs.getString(Fields.FLIGHTS_TO_PORT));
        f.setDateFlight(rs.getDate(Fields.FLIGHTS_DATE_FLIGHT));
        f.setTimeFlight(rs.getTime(Fields.FLIGHTS_TIME_FLIGHT));
        f.setBrigadeId(rs.getInt(Fields.FLIGHTS_BRIGADE_ID));
        f.setStatusId(rs.getInt(Fields.FLIGHTS_STATUS_ID));

        return f;
    }


}
