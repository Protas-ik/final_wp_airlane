package wp.epam.protas.airline.dao;

import wp.epam.protas.airline.entity.Brigade;
import wp.epam.protas.airline.entity.Employee;
import wp.epam.protas.airline.exception.DBException;

import java.util.List;

public interface BrigadeDAO {
    Brigade getBrigadeById(Integer id) throws DBException;

    List<Brigade> getBrigades() throws DBException;

    List<Employee> getEmployeesInBrigade(Integer brigadeId) throws DBException;

    List<Employee> getFreeEmployees() throws DBException;

    void createBrigade(Brigade brigade) throws DBException;

    void addEmployeeIntoBrigade(Integer idBrigade, Integer idEmployee) throws DBException;


}
