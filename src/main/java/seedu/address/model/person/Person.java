package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.job.Id;
import seedu.address.model.job.Job;
import seedu.address.model.job.Title;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Gender gender;
    private final GraduationDate graduationDate;
    private final Cap cap;
    private final University university;
    private final Major major;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email,
                  Address address,
                  Gender gender,
                  GraduationDate graduationDate,
                  Cap cap,
                  University university,
                  Major major) {
        requireAllNonNull(name, phone, email,
                address,
                gender,
                graduationDate,
                cap,
                university,
                major);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.graduationDate = graduationDate;
        this.cap = cap;
        this.university = university;
        this.major = major;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Gender getGender() {
        return gender;
    }

    public GraduationDate getGraduationDate() {
        return graduationDate;
    }

    public Cap getCap() {
        return cap;
    }

    public University getUniversity() {
        return university;
    }

    public Major getMajor() {
        return major;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getGraduationDate().equals(getGraduationDate())
                && otherPerson.getCap().equals(getCap())
                && otherPerson.getUniversity().equals(getUniversity())
                && otherPerson.getMajor().equals(getMajor());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address,
                gender,
                graduationDate,
                cap,
                university,
                major);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Gender: ")
                .append(getGender())
                .append("; Graduation Date: ")
                .append(getGraduationDate())
                .append("; CAP: ")
                .append(getCap())
                .append("; University: ")
                .append(getUniversity())
                .append("; Major: ")
                .append(getMajor());

        return builder.toString();
    }

}
