package ap.ti.examen.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class DateCheckEntry {
    @Id
    @GeneratedValue
    private long id;

    private LocalDate checkedDate;

    private Boolean valid;

    private Long days;
}
