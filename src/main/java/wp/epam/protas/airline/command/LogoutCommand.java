package wp.epam.protas.airline.command;

import wp.epam.protas.airline.exception.AppException;
import wp.epam.protas.airline.web.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return Pages.PAGE_LOGIN;
    }

}
