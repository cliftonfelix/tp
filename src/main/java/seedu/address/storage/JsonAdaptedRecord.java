package seedu.address.storage;

import static seedu.address.logic.parser.ParserUtil.parseCap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
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
 * Jackson-friendly version of {@link Record}.
 */
class JsonAdaptedRecord {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Record's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String gender;
    private final String graduationDate;
    private final String cap;
    private final String university;
    private final String major;
    private final String id;
    private final String title;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedRecord} with the given record details.
     */
    @JsonCreator
    public JsonAdaptedRecord(
            @JsonProperty("name") String name,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email,
            @JsonProperty("address") String address,
            @JsonProperty("gender") String gender,
            @JsonProperty("graduationDate") String graduationDate,
            @JsonProperty("cap") String cap,
            @JsonProperty("university") String university,
            @JsonProperty("major") String major,
            @JsonProperty("id") String id,
            @JsonProperty("title") String title,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.cap = cap;
        this.graduationDate = graduationDate;
        this.university = university;
        this.major = major;
        this.id = id;
        this.title = title;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Record} into this class for Jackson use.
     */
    public JsonAdaptedRecord(Record source) {
        name = source.getPerson().getName().fullName;
        phone = source.getPerson().getPhone().value;
        email = source.getPerson().getEmail().value;
        address = source.getPerson().getAddress().value;
        gender = source.getPerson().getGender().value;
        cap = source.getPerson().getCap().toString();
        graduationDate = source.getPerson().getGraduationDate().value;
        university = source.getPerson().getUniversity().value;
        major = source.getPerson().getMajor().value;
        id = source.getJob().getId().value;
        title = source.getJob().getTitle().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted record object into the model's {@code Record} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted record.
     */
    public Record toModelType() throws IllegalValueException {
        final List<Tag> recordTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            recordTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (graduationDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GraduationDate.class.getSimpleName()));
        }
        if (!GraduationDate.isValidGraduationDate(graduationDate)) {
            throw new IllegalValueException(GraduationDate.MESSAGE_CONSTRAINTS);
        }
        final GraduationDate modelGraduationDate = new GraduationDate(graduationDate);

        if (cap == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Cap.class.getSimpleName()));
        }
        // isValidCap is already handled inside the parseCap() method
        final Cap modelCap = parseCap(cap);

        if (university == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, University.class.getSimpleName()));
        }
        if (!University.isValidUniversity(university)) {
            throw new IllegalValueException(University.MESSAGE_CONSTRAINTS);
        }
        final University modelUniversity = new University(university);

        if (major == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Major.class.getSimpleName()));
        }
        if (!Major.isValidMajor(major)) {
            throw new IllegalValueException(Major.MESSAGE_CONSTRAINTS);
        }
        final Major modelMajor = new Major(major);

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        if (!Id.isValidId(id)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }
        final Id modelId = new Id(id);

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        final Set<Tag> modelTags = new HashSet<>(recordTags);
        return new Record(modelName, modelPhone, modelEmail, modelAddress,
                modelGender,
                modelGraduationDate,
                modelCap,
                modelUniversity,
                modelMajor,
                modelId,
                modelTitle,
                modelTags);
    }

}
