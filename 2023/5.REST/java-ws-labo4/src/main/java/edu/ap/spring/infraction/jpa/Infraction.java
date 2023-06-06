package edu.ap.spring.infraction.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "infraction")
@XmlRootElement
public class Infraction {
    @Id
    @Column(name = "col_id")
    private Long id;
    @Column(name = "col_year")
    private String year;
    @Column(name = "col_month")
    private String month;
    @Column(name = "col_date")
    private String date;
    @Column(name = "col_street")
    private String street;
    @Column(name = "col_driving_direction")
    private String drivingDirection;
    @Column(name = "col_speed_limit")
    private String speedLimit;
    @Column(name = "col_passers_by")
    private String passersBy;
    @Column(name = "col_infractions_speed")
    private String infractionsSpeed;
    @Column(name = "col_infractions_red_light")
    private String infractionsRedLight;

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @XmlElement(name = "month")
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @XmlElement(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @XmlElement(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @XmlElement(name = "driving_direction")
    public String getDrivingDirection() {
        return drivingDirection;
    }

    public void setDrivingDirection(String drivingDirection) {
        this.drivingDirection = drivingDirection;
    }

    @XmlElement(name = "speed_limit")
    public String getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(String speedLimit) {
        this.speedLimit = speedLimit;
    }

    @XmlElement(name = "passersby")
    public String getPassersBy() {
        return passersBy;
    }

    public void setPassersBy(String passersBy) {
        this.passersBy = passersBy;
    }

    @XmlElement(name = "infractions_speed")
    public String getInfractionsSpeed() {
        return infractionsSpeed;
    }

    public void setInfractionsSpeed(String infractionsSpeed) {
        this.infractionsSpeed = infractionsSpeed;
    }

    @XmlElement(name="infractions_red_light")
    public String getInfractionsRedLight() {
        return infractionsRedLight;
    }

    public void setInfractionsRedLight(String infractionsRedLight) {
        this.infractionsRedLight = infractionsRedLight;
    }
}
