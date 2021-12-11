package at.ac.fhstp.sniffer.Entity;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Comments {
    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column
    private String comment;

    @Column
    private Date date;

    @OneToOne
    @JoinColumn
    private Sniffer owner;

    @Column
    @OneToMany
    private List<Sniffer> liked_by = new ArrayList<Sniffer>();
 

    public Comments() {
    }

    public Comments(String comment, Sniffer owner) {
        this.comment = comment;
        this.owner = owner;
        setDate();
    }

    public Sniffer getOwner() {
        return owner;
    }

    public void setOwner(Sniffer owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getComment() {
        return comment;
    }
     
    public void setComment(String comment) 
    {
        this.comment = comment;
    }
     
    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = new Date();
    }

    public List<Sniffer> getliked_by() {
        return liked_by;

    }

    public void setliked_by(Sniffer liked_by) {
        this.liked_by.add(liked_by);
    }

}
