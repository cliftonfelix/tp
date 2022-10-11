package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditRecordDescriptor;
import seedu.address.model.job.Id;
import seedu.address.model.job.Title;
import seedu.address.model.person.Address;
import seedu.address.model.person.Cap;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GraduationDate;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.record.Record;
import seedu.address.model.person.Phone;
import seedu.address.model.person.University;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditRecordDescriptor objects.
 */
public class EditRecordDescriptorBuilder {

    private EditRecordDescriptor descriptor;

    public EditRecordDescriptorBuilder() {
        descriptor = new EditRecordDescriptor();
    }

    public EditRecordDescriptorBuilder(EditRecordDescriptor descriptor) {
        this.descriptor = new EditRecordDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditRecordDescriptor} with fields containing {@code record}'s details
     */
    public EditRecordDescriptorBuilder(Record record) {
        descriptor = new EditRecordDescriptor();
        descriptor.setName(record.getPerson().getName());
        descriptor.setPhone(record.getPerson().getPhone());
        descriptor.setEmail(record.getPerson().getEmail());
        descriptor.setAddress(record.getPerson().getAddress());
        descriptor.setGender(record.getPerson().getGender());
        descriptor.setGraduationDate(record.getPerson().getGraduationDate());
        descriptor.setCap(record.getPerson().getCap());
        descriptor.setUniversity(record.getPerson().getUniversity());
        descriptor.setMajor(record.getPerson().getMajor());
        descriptor.setId(record.getJob().getId());
        descriptor.setTitle(record.getJob().getTitle());
        descriptor.setTags(record.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withGender(String gender) {
        descriptor.setGender(new Gender(gender));
        return this;
    }

    /**
     * Sets the {@code GraduationDate} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withGraduationDate(String graduationDate) {
        descriptor.setGraduationDate(new GraduationDate(graduationDate));
        return this;
    }

    /**
     * Sets the {@code Cap} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withCap(double capValue, double maximumCapValue) {
        descriptor.setCap(new Cap(capValue, maximumCapValue));
        return this;
    }

    /**
     * Sets the {@code University} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withUniversity(String university) {
        descriptor.setUniversity(new University(university));
        return this;
    }

    /**
     * Sets the {@code Major} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withMajor(String major) {
        descriptor.setMajor(new Major(major));
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withId(String id) {
        descriptor.setId(new Id(id));
        return this;
    }

    /**
     * Sets the {@code Title} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditRecordDescriptor}
     * that we are building.
     */
    public EditRecordDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditRecordDescriptor build() {
        return descriptor;
    }
}
