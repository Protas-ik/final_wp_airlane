package wp.epam.protas.airline.command;

import wp.epam.protas.airline.dao.DAOFactory;
import wp.epam.protas.airline.dao.FlightDAO;
import wp.epam.protas.airline.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteFlightCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {
        Integer flightId = Integer.parseInt(request.getParameter("id"));
        DAOFactory factory = DAOFactory.getDAOFactory();
        FlightDAO dao = factory.getFlightDAO();
        dao.removeFlight(flightId);
        return "/controller?command=viewFlights";
    }

}
