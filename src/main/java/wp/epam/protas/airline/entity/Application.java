package wp.epam.protas.airline.entity;

public class Application extends Entity {

    private String message;
    private Integer brigadeId;
    private Integer statusId;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String title) {
        this.message = title;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Application [message=");
        builder.append(message);
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
