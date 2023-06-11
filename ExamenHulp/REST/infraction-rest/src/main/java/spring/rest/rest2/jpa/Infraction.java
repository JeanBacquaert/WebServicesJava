package spring.rest.rest2.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@Table(name = "infraction")
@XmlRootElement
@Data
public class Infraction {
    @Id
    @Column(name = "col_id")
    @XmlElement(name = "id")
    private Long id;

    @Column(name = "col_year")
    @XmlElement(name = "year")
    private String year;

    @Column(name = "col_month")
    @XmlElement(name = "month")
    private String month;

    @Column(name = "col_date")
    @XmlElement(name = "date")
    private String date;

    @Column(name = "col_street")
    @XmlElement(name = "street")
    private String street;

    @Column(name = "col_driving_direction")
    @XmlElement(name = "driving_direction")
    private String drivingDirection;

    @Column(name = "col_speed_limit")
    @XmlElement(name = "speed_limit")
    private String speedLimit;

    @Column(name = "col_passers_by")
    @XmlElement(name = "passersby")
    private String passersBy;

    @Column(name = "col_infractions_speed")
    @XmlElement(name = "infractions_speed")
    private String infractionsSpeed;

    @Column(name = "col_infractions_red_light")
    @XmlElement(name = "infractions_red_light")
    private String infractionsRedLight;

}
