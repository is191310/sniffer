package at.ac.fhstp.sniffer.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table
public class Comments 
{
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
    private Sniffer cowner;

    @ManyToOne
    private Pubdate pub;

    @Column
    @OneToMany(orphanRemoval = true)
    private Set<Sniffer> liked_by;
 

    public Comments() 
    {

    }

    public Comments(String comment, Sniffer cowner) 
    {
        this.comment = comment;
        this.cowner = cowner;
        setDate();
    }

    public Sniffer getCowner() 
    {
        return cowner;
    }

    public void setCowner(Sniffer cowner) 
    {
        this.cowner = cowner;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    
    public String getComment() 
    {
        return comment;
    }
     
    public void setComment(String comment) 
    {
        this.comment = comment;
    }
     
    public Date getDate() 
    {
        return date;
    }

    public void setDate() 
    {
        this.date = new Date();
    }

    public Set<Sniffer> getliked_by() 
    {
        return liked_by;

    }

    public void setliked_by(Sniffer liked_by) 
    {
        this.liked_by.add(liked_by);
    }

}
