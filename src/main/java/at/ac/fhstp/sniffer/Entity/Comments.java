package at.ac.fhstp.sniffer.Entity;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table
public class Comments {
    @Id
    @Column
    private int id;
    @Column
    private String comment;
    @Column
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
