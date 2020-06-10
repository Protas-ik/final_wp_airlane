package wp.epam.protas.airline.command;

import wp.epam.protas.airline.dao.DAOFactory;
import wp.epam.protas.airline.dao.UserDAO;
import wp.epam.protas.airline.entity.User;
import wp.epam.protas.airline.exception.AppException;
import wp.epam.protas.airline.web.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewUsersCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO dao = factory.getUserDAO();
        List<User> users = dao.getUsers();
        request.setAttribute("users", users);
        return Pages.PAGE_VIEW_USERS;
    }

}
