package wp.epam.protas.airline.command;

import wp.epam.protas.airline.dao.ApplicationDAO;
import wp.epam.protas.airline.dao.DAOFactory;
import wp.epam.protas.airline.entity.Application;
import wp.epam.protas.airline.entity.status.ApplicationStatus;
import wp.epam.protas.airline.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateAppCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {
        String message = request.getParameter("message");
        Integer brigadeId = Integer.parseInt(request.getParameter("brigadeId"));
        if (message == null || message.isEmpty() || brigadeId == null) {
            throw new AppException();
        }
        Application app = new Application();
        app.setBrigadeId(brigadeId);
        app.setMessage(message);
        app.setStatusId(ApplicationStatus.OPENED.ordinal());
        DAOFactory factory = DAOFactory.getDAOFactory();
        ApplicationDAO dao = factory.getApplicationDAO();
        dao.addApplication(app);
        String forward = "/controller?command=brigadeInf&id=" + brigadeId;
        return forward;
    }

}
