package wp.epam.protas.airline.entity;

public class Brigade extends Entity {


    private static final long serialVersionUID = 3513997375899440972L;

    private String name;
    private Integer userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Brigade [name=");
        builder.append(name);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", getId()=");
        builder.append(getId());
        builder.append("]");
        return builder.toString();
    }


}
