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
    private Pubdate pub;

    @ManyToOne
    private Sniffer cowner;

    public Comments()
    {
    }

    public Comments(String text, Sniffer cowner, Pubdate pub) 
    {
        this.text = text;
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

    public int getPub()
    {
        return this.pub.getId();
    }

    public void setPub(Pubdate pub)
    {
        this.pub = pub;
    }
}
