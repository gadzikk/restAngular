package jpa;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by gadzik on 09.03.17.
 */
@Entity
@Table(name = "person")
public class Person implements Serializable{
    private static final long serialVersionUID = -5303610267032836218L;

    @SequenceGenerator(name = "PERSON_SEQ" ,sequenceName = "person_id_seq", allocationSize = 1)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "PERSON_SEQ")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lname")
    private String lname;

    @Column(name = "dob")
    private LocalDate dob;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}

