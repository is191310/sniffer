package at.ac.fhstp.sniffer.entity;

import java.util.Date;

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

    @ManyToOne
    private Pubdate pub;

    @OneToOne
    @JoinColumn
    private Sniffer cowner;

    public Comments(){}

    public Comments(String comment, Sniffer cowner, Pubdate pub) 
    {
        this.comment = comment;
        this.cowner = cowner;
        this.pub = pub;
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

    public int getPub()
    {
        return this.pub.getId();
    }

    public void setPub(Pubdate pub)
    {
        this.pub = pub;
    }
}
