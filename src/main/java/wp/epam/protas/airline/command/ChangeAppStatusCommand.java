package wp.epam.protas.airline.command;

import wp.epam.protas.airline.dao.ApplicationDAO;
import wp.epam.protas.airline.dao.DAOFactory;
import wp.epam.protas.airline.entity.status.ApplicationStatus;
import wp.epam.protas.airline.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeAppStatusCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("statusId");
        if (status == null || status.isEmpty()) {
            throw new AppException();
        }

        DAOFactory factory = DAOFactory.getDAOFactory();
        ApplicationDAO dao = factory.getApplicationDAO();
        if (status.equals("1")) {
            dao.changeStatus(id, ApplicationStatus.DONE);
        }
        if (status.equals("2")) {
            dao.changeStatus(id, ApplicationStatus.REJECTED);
        }
        return "/controller?command=viewOpenedApps";
    }

}
