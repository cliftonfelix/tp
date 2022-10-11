package seedu.address.model.record;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.job.Id;
import seedu.address.model.job.Job;
import seedu.address.model.job.Title;
import seedu.address.model.person.Address;
import seedu.address.model.person.Cap;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GraduationDate;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.University;
import seedu.address.model.tag.Tag;

/**
 * Represents a Record in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Record {

    private final Person person;
    private final Job job;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Record(Name name, Phone phone, Email email,
                  Address address,
                  Gender gender,
                  GraduationDate graduationDate,
                  Cap cap,
                  University university,
                  Major major,
                  Id id,
                  Title title,
                  Set<Tag> tags) {
        requireAllNonNull(name, phone, email,
            address,
            gender,
            graduationDate,
            cap,
            university,
            major,
            id,
            title,
            tags);
        this.person = new Person(name, phone, email,
                address,
                gender,
                graduationDate,
                cap,
                university,
                major);
        this.job = new Job(id, title);
        this.tags.addAll(tags);
    }

    public Person getPerson() {
        return person;
    }

    public Job getJob() {
        return job;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both records have the same person and job.
     * This defines a weaker notion of equality between two records.
     */
    public boolean isSameRecord(Record otherRecord) {
        if (otherRecord == this) {
            return true;
        }

        return otherRecord != null
                && otherRecord.getPerson().isSamePerson(getPerson())
                && otherRecord.getJob().equals(getJob());
    }

    /**
     * Returns true if both records have the same person, job, and tags
     * This defines a stronger notion of equality between two records.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Record)) {
            return false;
        }

        Record otherRecord = (Record) other;
        return otherRecord.getPerson().equals(getPerson())
                && otherRecord.getJob().equals(getJob())
                && otherRecord.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(person,
                job,
                tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Person: ")
                .append(getPerson())
                .append("; Job ID: ")
                .append(getJob());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
