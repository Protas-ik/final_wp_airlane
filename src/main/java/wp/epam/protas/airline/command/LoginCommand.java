package wp.epam.protas.airline.command;

import org.apache.log4j.Logger;
import wp.epam.protas.airline.dao.DAOFactory;
import wp.epam.protas.airline.dao.UserDAO;
import wp.epam.protas.airline.entity.User;
import wp.epam.protas.airline.entity.UserRole;
import wp.epam.protas.airline.exception.AppException;
import wp.epam.protas.airline.web.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends Command {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {

        LOG.info("start login command");
        HttpSession session = request.getSession();
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.err.println("user = " + login);
        System.err.println("pass = " + password);
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new AppException();
        }
        User user = userDAO.getUserByLogin(login);
        if (user == null || !password.equals(user.getPassword())) {
            throw new AppException();
        }
        UserRole role = UserRole.getRole(user);
        String page = Pages.PAGE_ERROR;
        if (role == UserRole.ADMIN) {
            page = Pages.PAGE_ADMIN;
        }
        if (role == UserRole.DISPATCHER) {
            page = Pages.PAGE_USER;
        }

        LOG.info("user --> " + user);

        session.setAttribute("user", user);
        session.setAttribute("userRole", role);
        LOG.info("finished login command");

        return page;
    }

}
