package wp.epam.protas.airline.command;

import wp.epam.protas.airline.dao.ApplicationDAO;
import wp.epam.protas.airline.dao.DAOFactory;
import wp.epam.protas.airline.entity.Application;
import wp.epam.protas.airline.exception.AppException;
import wp.epam.protas.airline.web.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewApplicationsCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ApplicationDAO dao = factory.getApplicationDAO();
        List<Application> applications = dao.getApplications();
        request.setAttribute("apps", applications);
        request.setAttribute("status", "all");
        return Pages.PAGE_VIEW_APPS;
    }

}
