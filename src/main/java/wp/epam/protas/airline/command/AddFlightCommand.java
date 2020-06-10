package wp.epam.protas.airline.command;

import wp.epam.protas.airline.dao.DAOFactory;
import wp.epam.protas.airline.dao.FlightDAO;
import wp.epam.protas.airline.entity.Flight;
import wp.epam.protas.airline.entity.status.FlightStatus;
import wp.epam.protas.airline.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

public class AddFlightCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {
        String name = request.getParameter("name");
        String from = request.getParameter("fromPort");
        String to = request.getParameter("toPort");
        String dateStr = request.getParameter("date");
        String timeStr = request.getParameter("time");
        Integer brigadeId = Integer.parseInt(request.getParameter("brigadeId"));
        if (name == null || name.isEmpty() || from == null || from.isEmpty() ||
                to == null || to.isEmpty() || dateStr == null || dateStr.isEmpty() ||
                timeStr == null || timeStr.isEmpty() || brigadeId == null) {
            throw new AppException();
        }
        System.err.println("date = " + dateStr);
        System.err.println("time = " + timeStr);
        timeStr += ":00";
        Date date = Date.valueOf(dateStr);
        Time time = Time.valueOf(timeStr);
        DAOFactory factory = DAOFactory.getDAOFactory();
        FlightDAO dao = factory.getFlightDAO();
        Flight flight = new Flight();
        flight.setName(name);
        flight.setFromPort(from);
        flight.setToPort(to);
        flight.setDateFlight(date);
        flight.setTimeFlight(time);
        flight.setBrigadeId(brigadeId);
        flight.setStatusId(FlightStatus.OPENED.ordinal());
        dao.addFlight(flight);

        return "/controller?command=viewFlights";
    }

}
