package org.motechproject.care.reporting.ft.couch.service;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.motechproject.care.reporting.ft.couch.domain.Attribute;
import org.motechproject.care.reporting.ft.couch.domain.Observation;
import org.motechproject.care.reporting.ft.couch.domain.Person;
import org.motechproject.couch.mrs.model.CouchPerson;
import org.motechproject.couch.mrs.repository.impl.AllCouchPersonsImpl;
import org.motechproject.mrs.domain.MRSAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class MRSPersonService {
    private final AllCouchPersonsImpl allCouchPersons;

    @Autowired
    public MRSPersonService(AllCouchPersonsImpl allCouchPersons) {
        this.allCouchPersons = allCouchPersons;
    }

    public Person getFor(String personId) {
        if (personId == null) {
            return null;
        }

        CouchPerson couchPerson = allCouchPersons.findByPersonId(personId).get(0);

        List<Attribute> attributes = new ArrayList<Attribute>();
        for (MRSAttribute attribute : couchPerson.getAttributes()) {
            attributes.add(new Attribute(attribute.getName(), attribute.getValue()));
        }

        Collections.sort(attributes, new Comparator<Attribute>() {
            private String getAttributeName(Attribute attribute) {
                return attribute == null ? null : attribute.getName();
            }

            @Override
            public int compare(Attribute o1, Attribute o2) {
                return new CompareToBuilder()
                        .append(getAttributeName(o1), getAttributeName(o2))
                        .toComparison();
            }
        });

        return new Person(couchPerson.getId(), couchPerson.getPersonId(), couchPerson.getFirstName(), couchPerson.getMiddleName(), couchPerson.getLastName(), couchPerson.getPreferredName(), couchPerson.getAddress(), couchPerson.getDateOfBirth(), couchPerson.getBirthDateEstimated(), couchPerson.getAge(), couchPerson.getGender(), couchPerson.isDead(), couchPerson.getDeathDate(), attributes);
    }

    public void delete(String personId) {
        allCouchPersons.removeAll("personId", personId);
    }
}
