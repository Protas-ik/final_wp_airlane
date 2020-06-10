package wp.epam.protas.airline.command;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("viewUsers", new ViewUsersCommand());
        commands.put("viewEmployees", new ViewEmployeesCommand());
        commands.put("addEmployee", new AddEmployeeCommand());
        commands.put("removeEmployee", new RemoveEmployeeCommand());
        commands.put("viewBrigades", new ViewBrigadesCommand());
        commands.put("addBrigade", new AddBrigadeCommand());
        commands.put("brigadeInf", new BrigadeInformationCommand());
        commands.put("createApp", new CreateAppCommand());
        commands.put("viewApplications", new ViewApplicationsCommand());
        commands.put("viewOpenedApps", new ViewOpenedAppCommand());
        commands.put("changeStatus", new ChangeAppStatusCommand());
        commands.put("viewFlights", new ViewFlightsStatusCommand());
        commands.put("addFlight", new AddFlightCommand());
        commands.put("deleteFlight", new DeleteFlightCommand());
        commands.put("manageStatus", new ManageFlightsCommand());
        commands.put("addEmpIntoBrigade", new AddEmployeeIntoBrigadeCommand());

    }

    public static Command getCommand(String name) {
        if (name == null || !commands.containsKey(name)) {
            return commands.get("noCommand");
        }
        return commands.get(name);

    }

}
