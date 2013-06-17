package org.motechproject.care.reporting.ft.couch.domain;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Person extends CouchDocument {

    @JsonProperty
    @XmlElement
    private String personId;

    @JsonProperty
    @XmlElement
    private String firstName;

    @JsonProperty
    @XmlElement
    private String middleName;

    @JsonProperty
    @XmlElement
    private String lastName;

    @JsonProperty
    @XmlElement
    private String preferredName;

    @JsonProperty
    @XmlElement
    private String address;

    @JsonProperty
    @XmlElement
    private DateTime dateOfBirth;

    @JsonProperty
    @XmlElement
    private Boolean birthDateEstimated;

    @JsonProperty
    @XmlElement
    private Integer age;

    @JsonProperty
    @XmlElement
    private String gender;

    @JsonProperty
    @XmlElement
    private Boolean dead;

    @JsonProperty
    @XmlElement
    @XmlElementWrapper(name = "attributes")
    private List<Attribute> attributes = new ArrayList<Attribute>();

    @JsonProperty
    @XmlElement
    private DateTime deathDate;

    public Person() {

    }

    public Person(String couchId, String personId, String firstName, String middleName, String lastName, String preferredName, String address, DateTime dateOfBirth, Boolean birthDateEstimated, Integer age, String gender, Boolean dead, DateTime deathDate, List<Attribute> attributes) {
        super(couchId, "motech-person-repository");
        this.personId = personId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.preferredName = preferredName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.birthDateEstimated = birthDateEstimated;
        this.age = age;
        this.gender = gender;
        this.dead = dead;
        this.deathDate = deathDate;
        this.attributes = attributes;
    }

    public String getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public String getAddress() {
        return address;
    }

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public Boolean getBirthDateEstimated() {
        return birthDateEstimated;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Boolean getDead() {
        return dead;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public DateTime getDeathDate() {
        return deathDate;
    }
}
