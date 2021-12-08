package at.ac.fhstp.sniffer.Entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Sniffer {

    @Id

    @Column
    private int id;

    @Column
    private String name;

    @Column
    private List<Sniffer> followed_by;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sniffer> getfollowed_by() {
        return followed_by;
    }

    public void setfollowed_by(List<Sniffer> followed_by) {
        this.followed_by = followed_by;
    }

}
