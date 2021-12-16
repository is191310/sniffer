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
    private String text;

    @Column
    private Date date;

    @ManyToOne
    private Pupdate pup;

    @ManyToOne
    private Sniffer cowner;

    public Comments()
    {
    }

    public Comments(String text, Sniffer cowner, Pupdate pup) 
    {
        this.text = text;
        this.cowner = cowner;
        this.pup = pup;
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
    
    public String getText() 
    {
        return text;
    }
     
    public void setText(String text) 
    {
        this.text = text;
    }
     
    public Date getDate() 
    {
        return date;
    }

    public void setDate() 
    {
        this.date = new Date();
    }

    public int getPup()
    {
        return this.pup.getId();
    }

    public void setPup(Pupdate pup)
    {
        this.pup = pup;
    }
}
