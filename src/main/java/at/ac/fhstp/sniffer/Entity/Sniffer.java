package at.ac.fhstp.sniffer.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "Sniffers")
@Entity

public class Sniffer {

    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @JsonIgnore
    @Column
    @OneToMany
    private List<Sniffer> followed_by = new ArrayList<Sniffer>();

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

    public void setfollowed_by(Sniffer followed) {
        this.followed_by.add(followed);
    }

}
