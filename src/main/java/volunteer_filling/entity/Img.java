package volunteer_filling.entity;

import java.io.Serializable;

public class Img implements Serializable{

    private static final long serialVersionUID = 8990798455744465533L;

    private int id;
    private String path;
    private String owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
