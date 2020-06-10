package wp.epam.protas.airline.command;

import wp.epam.protas.airline.dao.BrigadeDAO;
import wp.epam.protas.airline.dao.DAOFactory;
import wp.epam.protas.airline.entity.Brigade;
import wp.epam.protas.airline.exception.AppException;
import wp.epam.protas.airline.web.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewBrigadesCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {
        DAOFactory factory = DAOFactory.getDAOFactory();
        BrigadeDAO dao = factory.getBrigadeDAO();
        List<Brigade> brigades = dao.getBrigades();
        request.setAttribute("brigades", brigades);
        String forward = Pages.PAGE_VIEW_BRIGADES;
        return forward;
    }

}
