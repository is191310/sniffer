package at.ac.fhstp.sniffer.entity;

import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.*;

@Entity
@Table
public class Pupdate implements Comparable<Pupdate>
{
    @Id
    @Column
    @GeneratedValue
    private int id;
   
    @Column
    private String title;

    @Column
    private String metadata;

    @Column 
    int likes;
   
    @ManyToMany
    private Set<Sniffer> liked_by;

    @OneToMany(mappedBy = "pup", orphanRemoval = true)
    private Set<Comments> comment;

    @ManyToOne
    private Sniffer powner;

    @Column
    private Date date;

    public Pupdate(){}

    public Pupdate(String title, Sniffer powner) 
    {
        this.title = title;
        this.powner = powner;
        setDate();
        this.metadata = genBase64();
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public Set<Sniffer> getliked_by() 
    {
        return liked_by;
    }   
    
    public void setliked_by(Sniffer liked_by) 
    {
        this.liked_by.add(liked_by);
    }

    public void removeliked_by(Sniffer liked_by) 
    {
        this.liked_by.remove(liked_by);
    }

    public Set<Comments> getComment() 
    {
        return comment;
    }

    public void setComment(Comments comment) 
    {
        this.comment.add(comment);
    }

    public void removeComment(Comments comment)
    {
        this.comment.remove(comment);
    }

    public Sniffer getPowner() 
    {
        return powner;
    }

    public void setPowner(Sniffer powner)
    {
        this.powner = powner;
    }

    public Date getDate() 
    {
        return date;
    }

    public void setDate () 
    {
        this.date = new Date();
    }
    
    public int getLikes() {
        if(this.liked_by!=null)
        {
            return this.liked_by.size();
        }
        return 0;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    private String genBase64()
    {
        Random random = ThreadLocalRandom.current();
        byte[] randomBytes = new byte[16];
        random.nextBytes(randomBytes);
        return Base64.getUrlEncoder().encodeToString(randomBytes);
    }
 
    @Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pupdate other = (Pupdate) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int compareTo(Pupdate p)
    {
        return p.getDate().compareTo(this.date);
    }
}
