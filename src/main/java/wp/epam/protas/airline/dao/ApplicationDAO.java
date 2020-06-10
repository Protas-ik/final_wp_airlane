package wp.epam.protas.airline.dao;

import wp.epam.protas.airline.entity.Application;
import wp.epam.protas.airline.entity.status.ApplicationStatus;
import wp.epam.protas.airline.exception.DBException;

import java.util.List;

public interface ApplicationDAO {

    Application getApplicationById(Integer id) throws DBException;

    List<Application> getApplications() throws DBException;

    List<Application> getOpenedApplications() throws DBException;

    void changeStatus(Integer id, ApplicationStatus status) throws DBException;

    void addApplication(Application app) throws DBException;


}
