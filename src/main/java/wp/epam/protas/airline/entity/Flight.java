package wp.epam.protas.airline.entity;

import java.sql.Date;
import java.sql.Time;

public class Flight extends Entity {


    private static final long serialVersionUID = 4579314101060652810L;

    private String name;
    private String fromPort;
    private String toPort;
    private Date dateFlight;
    private Time timeFlight;
    private Integer brigadeId;
    private Integer statusId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromPort() {
        return fromPort;
    }

    public void setFromPort(String fromPort) {
        this.fromPort = fromPort;
    }

    public String getToPort() {
        return toPort;
    }

    public void setToPort(String toPort) {
        this.toPort = toPort;
    }

    public Date getDateFlight() {
        return new Date(dateFlight.getTime());
    }

    public void setDateFlight(Date dateFlight) {
        this.dateFlight = Date.valueOf(dateFlight.toLocalDate());
    }

    public Time getTimeFlight() {
        return timeFlight;
    }

    public void setTimeFlight(Time timeFlight) {
        this.timeFlight = timeFlight;
    }

    public Integer getBrigadeId() {
        return brigadeId;
    }

    public void setBrigadeId(Integer brigadeId) {
        this.brigadeId = brigadeId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Flight [name=");
        builder.append(name);
        builder.append(", fromPort=");
        builder.append(fromPort);
        builder.append(", toPort=");
        builder.append(toPort);
        builder.append(", dateFlight=");
        builder.append(dateFlight);
        builder.append(", timeFlight=");
        builder.append(timeFlight);
        builder.append(", brigadeId=");
        builder.append(brigadeId);
        builder.append(", statusId=");
        builder.append(statusId);
        builder.append(", getId()=");
        builder.append(getId());
        builder.append("]");
        return builder.toString();
    }


}
