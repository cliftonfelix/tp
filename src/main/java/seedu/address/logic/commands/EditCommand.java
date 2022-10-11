package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CAP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_GRADUATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_UNIVERSITY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECORDS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
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
 * Edits the details of an existing record in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the record identified "
            + "by the index number used in the displayed record list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_PERSON_NAME + "NAME] "
            + "[" + PREFIX_PERSON_PHONE + "PHONE] "
            + "[" + PREFIX_PERSON_EMAIL + "EMAIL] "
            + "[" + PREFIX_PERSON_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_PERSON_GENDER + "GENDER] "
            + "[" + PREFIX_PERSON_GRADUATION_DATE + "GRADUATION DATE] "
            + "[" + PREFIX_PERSON_CAP + "CAP] "
            + "[" + PREFIX_PERSON_UNIVERSITY + "UNIVERSITY] "
            + "[" + PREFIX_PERSON_MAJOR + "MAJOR] "
            + "[" + PREFIX_JOB_ID + "ID] "
            + "[" + PREFIX_JOB_TITLE + "TITLE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PERSON_PHONE + "91234567 "
            + PREFIX_PERSON_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_RECORD_SUCCESS = "Edited Record: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_RECORD = "This record already exists in the address book.";

    private final Index index;
    private final EditRecordDescriptor editRecordDescriptor;

    /**
     * @param index of the record in the filtered record list to edit
     * @param editRecordDescriptor details to edit the record with
     */
    public EditCommand(Index index, EditRecordDescriptor editRecordDescriptor) {
        requireNonNull(index);
        requireNonNull(editRecordDescriptor);

        this.index = index;
        this.editRecordDescriptor = new EditRecordDescriptor(editRecordDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Record> lastShownList = model.getFilteredRecordList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECORD_DISPLAYED_INDEX);
        }

        Record recordToEdit = lastShownList.get(index.getZeroBased());
        Record editedRecord = createEditedRecord(recordToEdit, editRecordDescriptor);

        if (!recordToEdit.isSameRecord(editedRecord) && model.hasRecord(editedRecord)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECORD);
        }

        model.setRecord(recordToEdit, editedRecord);
        model.updateFilteredRecordList(PREDICATE_SHOW_ALL_RECORDS);
        return new CommandResult(String.format(MESSAGE_EDIT_RECORD_SUCCESS, editedRecord));
    }

    /**
     * Creates and returns a {@code Record} with the details of {@code recordToEdit}
     * edited with {@code editRecordDescriptor}.
     */
    private static Record createEditedRecord(Record recordToEdit, EditRecordDescriptor editRecordDescriptor) {
        assert recordToEdit != null;

        Name updatedName = editRecordDescriptor.getName().orElse(recordToEdit.getPerson().getName());
        Phone updatedPhone = editRecordDescriptor.getPhone().orElse(recordToEdit.getPerson().getPhone());
        Email updatedEmail = editRecordDescriptor.getEmail().orElse(recordToEdit.getPerson().getEmail());
        Address updatedAddress = editRecordDescriptor.getAddress().orElse(recordToEdit.getPerson().getAddress());
        Gender updatedGender = editRecordDescriptor.getGender().orElse(recordToEdit.getPerson().getGender());
        Cap updatedCap = editRecordDescriptor.getCap().orElse(recordToEdit.getPerson().getCap());
        GraduationDate updatedGraduationDate = editRecordDescriptor.getGraduationDate()
                .orElse(recordToEdit.getPerson().getGraduationDate());
        University updatedUniversity = editRecordDescriptor.getUniversity().orElse(recordToEdit.getPerson().getUniversity());
        Major updatedMajor = editRecordDescriptor.getMajor().orElse(recordToEdit.getPerson().getMajor());
        Id updatedJobId = editRecordDescriptor.getJobId().orElse(recordToEdit.getJob().getId());
        Title updatedJobTitle = editRecordDescriptor.getJobTitle().orElse(recordToEdit.getJob().getTitle());
        Set<Tag> updatedTags = editRecordDescriptor.getTags().orElse(recordToEdit.getTags());

        return new Record(updatedName, updatedPhone, updatedEmail,
                updatedAddress,
                updatedGender,
                updatedGraduationDate,
                updatedCap,
                updatedUniversity,
                updatedMajor,
                updatedJobId,
                updatedJobTitle,
                updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editRecordDescriptor.equals(e.editRecordDescriptor);
    }

    /**
     * Stores the details to edit the record with. Each non-empty field value will replace the
     * corresponding field value of the record.
     */
    public static class EditRecordDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Gender gender;
        private GraduationDate graduationDate;
        private Cap cap;
        private University university;
        private Major major;
        private Id id;
        private Title title;
        private Set<Tag> tags;

        public EditRecordDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditRecordDescriptor(EditRecordDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setGender(toCopy.gender);
            setGraduationDate(toCopy.graduationDate);
            setCap(toCopy.cap);
            setUniversity(toCopy.university);
            setMajor(toCopy.major);
            setId(toCopy.id);
            setTitle(toCopy.title);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email,
                    address,
                    gender,
                    graduationDate,
                    cap,
                    university,
                    major,
                    id,
                    title,
                    tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }
        public void setGraduationDate(GraduationDate graduationDate) {
            this.graduationDate = graduationDate;
        }
        public Optional<GraduationDate> getGraduationDate() {
            return Optional.ofNullable(graduationDate);
        }

        public void setCap(Cap cap) {
            this.cap = cap;
        }

        public Optional<Cap> getCap() {
            return Optional.ofNullable(cap);
        }

        public void setUniversity(University university) {
            this.university = university;
        }

        public Optional<University> getUniversity() {
            return Optional.ofNullable(university);
        }

        public void setMajor(Major major) {
            this.major = major;
        }

        public Optional<Major> getMajor() {
            return Optional.ofNullable(major);
        }

        public void setId(Id id) {
            this.id = id;
        }

        public Optional<Id> getJobId() {
            return Optional.ofNullable(id);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getJobTitle() {
            return Optional.ofNullable(title);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditRecordDescriptor)) {
                return false;
            }

            // state check
            EditRecordDescriptor e = (EditRecordDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getGender().equals(e.getGender())
                    && getCap().equals(e.getCap())
                    && getGraduationDate().equals(e.getGraduationDate())
                    && getUniversity().equals(e.getUniversity())
                    && getMajor().equals(e.getMajor())
                    && getJobId().equals(e.getJobId())
                    && getJobTitle().equals(e.getJobTitle())
                    && getTags().equals(e.getTags());
        }
    }
}
