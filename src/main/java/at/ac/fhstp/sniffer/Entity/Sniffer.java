package at.ac.fhstp.sniffer.entity;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "Sniffers")
@Entity

public class Sniffer 
{
    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @JsonIgnore
    @Column
    @OneToMany(orphanRemoval = true, mappedBy = "powner")
    private Set<Pubdate> pubdates;

    //@JsonIgnore
    //@Column
    //@OneToMany(orphanRemoval = true)
   // private Set<Comments> comment;

    @JsonIgnore
    @Column
    @ManyToMany
    private Set<Sniffer> followed_by;

    @JsonIgnore
    @Column
    @ManyToMany
    private Set<Sniffer> followed;

    @JsonIgnore
    @Column
    @ManyToMany
    private Set<Pubdate> shared;

    public Sniffer() 
    {

    }

    public void delete(Pubdate p)
    {
        this.pubdates.remove(p);
    }
    
    public Sniffer(String name) 
    {
        this.name = name;
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
        Sniffer other = (Sniffer) obj;
        if (id != other.id)
            return false;
        return true;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public Set<Sniffer> getfollowed_by() 
    {
        return followed_by;
    }

    public void setfollowed_by(Sniffer follow) 
    {
        this.followed_by.add(follow);
    }
    
    public Set<Sniffer> getfollowed() 
    {
        return followed;
    }

    public void setfollowed(Sniffer follower) 
    {
        this.followed.add(follower);
    }

    public Set<Pubdate> getShared() 
    {
        return shared;
    }

    public void setShared(Pubdate shared)
    {
        this.shared.add(shared);
    }

    public Set<Pubdate> getPubdates() 
    {
        return pubdates;
    }

    public void setPubdates(Pubdate pub) 
    {
        this.pubdates.add(pub);
    }

   // public Set<Comments> getCommentss() 
   // {
   //     return comment;
  //  }

   // public void setComments(Comments com) 
  //  {
  //      this.comment.add(com);
  //  }

    
}
