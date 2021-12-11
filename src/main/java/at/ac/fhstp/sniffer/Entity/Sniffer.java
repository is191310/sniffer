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
    @ManyToMany
    private List<Sniffer> followed_by = new ArrayList<Sniffer>();

    @JsonIgnore
    @Column
    @ManyToMany
    private List<Sniffer> followed = new ArrayList<Sniffer>();

    @JsonIgnore
    @Column
    @ManyToMany
    private List<Pubdate> shared = new ArrayList<Pubdate>();

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

    public void setfollowed_by(Sniffer follow) {
        this.followed_by.add(follow);
    }
    
    public List<Sniffer> getfollowed() {
        return followed;
    }

    public void setfollowed(Sniffer follower) {
        this.followed.add(follower);
    }

    public List<Pubdate> getShared() {
        return shared;
    }

    public void setShared(Pubdate shared) {
        this.shared.add(shared);
    }
}
