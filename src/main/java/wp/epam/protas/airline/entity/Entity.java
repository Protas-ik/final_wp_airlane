package wp.epam.protas.airline.entity;

import java.io.Serializable;

public class Entity implements Serializable {

    private static final long serialVersionUID = 1727160706781150493L;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Entity [id=" + id + "]";
    }


}
