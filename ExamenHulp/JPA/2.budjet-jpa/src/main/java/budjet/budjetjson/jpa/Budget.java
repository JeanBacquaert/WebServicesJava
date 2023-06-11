package budjet.budjetjson.jpa;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Budget {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private int amount;

    @Column
    private String description;

    @Column
    private Date date;


    public Budget() {
    }

    public Budget(int amount, String description, Date date) {
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return amount + " " + description + " " + date ;
    }

}
