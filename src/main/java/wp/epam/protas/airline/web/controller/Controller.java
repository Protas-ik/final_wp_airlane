package wp.epam.protas.airline.web.controller;

import wp.epam.protas.airline.command.Command;
import wp.epam.protas.airline.command.CommandContainer;
import wp.epam.protas.airline.exception.AppException;
import wp.epam.protas.airline.web.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);
        String page = Pages.PAGE_ERROR;
        try {
            page = command.execute(request, response);
        } catch (AppException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(page).forward(request, response);
    }

}
