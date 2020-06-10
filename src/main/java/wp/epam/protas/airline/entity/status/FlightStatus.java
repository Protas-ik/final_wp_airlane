package wp.epam.protas.airline.entity.status;

import wp.epam.protas.airline.entity.Flight;

public enum FlightStatus {
    OPENED,
    DONE,
    CANCELED;

    public static FlightStatus getStatus(Flight flight) {
        return values()[flight.getStatusId()];
    }

    public String getName() {
        return name();
    }

}

