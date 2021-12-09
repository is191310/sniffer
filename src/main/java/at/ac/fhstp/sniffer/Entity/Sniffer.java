package at.ac.fhstp.sniffer.Entity;

import java.util.List;

import javax.persistence.*;

@Table(name = "Sniffers")
@Entity

public class Sniffer {

    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    @OneToMany
    private List<Sniffer> followed_by;

    public Sniffer() {
    }
    
    public Sniffer(String name) {
        this.name = name;
    }

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
