package at.ac.fhstp.sniffer.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Pubdate {
    @Id
    @Column
    @GeneratedValue
    private int id;
   
    @Column
    private String title;
   
    @Column
    @OneToMany
    private List<Sniffer> liked_by = new ArrayList<Sniffer>();

    @Column
    @OneToMany
    private List<Comments> comment; 

    @OneToOne
    @JoinColumn
    private Sniffer owner;

    @Column
    private Date date;

    


    public Pubdate() {
    }

    public Pubdate(String title, Sniffer owner) {
        this.title = title;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Sniffer> getliked_by() {
        return liked_by;

     }   
    public void setliked_by(Sniffer liked_by) {
        this.liked_by.add(liked_by);
    }

    public List<Comments> getComment() {
        return comment;
    }

    public void setComment(List<Comments> comment) {
        this.comment = comment;
    }

    public Sniffer getOwner() {
        return owner;
    }

    public void setOwner(Sniffer owner){
        this.owner = owner;
    }
    public Date getDate() {
        return date;
    }
    public void setDate (Date date) {
        this.date = date;
    }
}
