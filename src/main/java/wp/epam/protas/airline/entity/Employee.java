package wp.epam.protas.airline.entity;

public class Employee extends Entity {


    private String firstName;
    private String lastName;
    private Integer professionId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Integer professionId) {
        this.professionId = professionId;
    }

    @Override
    public String toString() {
        return "getId()=" +
                getId() +
                "Employee [firstName=" +
                firstName +
                ", lastName=" +
                lastName +
                ", professionId=" +
                professionId +
                "]";
    }


}
