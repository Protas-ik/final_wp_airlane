package wp.epam.protas.airline.command;

import wp.epam.protas.airline.dao.DAOFactory;
import wp.epam.protas.airline.dao.EmployeeDAO;
import wp.epam.protas.airline.entity.Employee;
import wp.epam.protas.airline.entity.Profession;
import wp.epam.protas.airline.exception.AppException;
import wp.epam.protas.airline.web.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewEmployeesCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AppException {
        DAOFactory factory = DAOFactory.getDAOFactory();
        EmployeeDAO dao = factory.getEmployeeDAO();
        List<Employee> employees = dao.getEmployees();
        request.setAttribute("employees", employees);
        request.setAttribute("profs", Profession.values());
        System.err.println(employees.size());

        return Pages.PAGE_VIEW_EMPLOYEES;
    }

}
