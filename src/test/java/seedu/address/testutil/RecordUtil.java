package seedu.address.testutil;

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

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditRecordDescriptor;
import seedu.address.model.record.Record;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Record.
 */
public class RecordUtil {

    /**
     * Returns an add command string for adding the {@code record}.
     */
    public static String getAddCommand(Record record) {
        return AddCommand.COMMAND_WORD + " " + getRecordDetails(record);
    }

    /**
     * Returns the part of command string for the given {@code record}'s details.
     */
    public static String getRecordDetails(Record record) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_PERSON_NAME + record.getPerson().getName().fullName + " ");
        sb.append(PREFIX_PERSON_PHONE + record.getPerson().getPhone().value + " ");
        sb.append(PREFIX_PERSON_EMAIL + record.getPerson().getEmail().value + " ");
        sb.append(PREFIX_PERSON_ADDRESS + record.getPerson().getAddress().value + " ");
        sb.append(PREFIX_PERSON_GENDER + record.getPerson().getGender().value + " ");
        sb.append(PREFIX_PERSON_GRADUATION_DATE + record.getPerson().getGraduationDate().value + " ");
        sb.append(PREFIX_PERSON_CAP + record.getPerson().getCap().toString() + " ");
        sb.append(PREFIX_PERSON_UNIVERSITY + record.getPerson().getUniversity().value + " ");
        sb.append(PREFIX_PERSON_MAJOR + record.getPerson().getMajor().value + " ");
        sb.append(PREFIX_JOB_ID + record.getJob().getId().value + " ");
        sb.append(PREFIX_JOB_TITLE + record.getJob().getTitle().value + " ");
        record.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditRecordDescriptor}'s details.
     */
    public static String getEditRecordDescriptorDetails(EditRecordDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName()
                .ifPresent(name -> sb.append(PREFIX_PERSON_NAME).append(name.fullName).append(" "));
        descriptor.getPhone()
                .ifPresent(phone -> sb.append(PREFIX_PERSON_PHONE).append(phone.value).append(" "));
        descriptor.getEmail()
                .ifPresent(email -> sb.append(PREFIX_PERSON_EMAIL).append(email.value).append(" "));
        descriptor.getAddress()
                .ifPresent(address -> sb.append(PREFIX_PERSON_ADDRESS).append(address.value).append(" "));
        descriptor.getGender()
            .ifPresent(gender -> sb.append(PREFIX_PERSON_GENDER).append(gender.value).append(" "));
        descriptor.getGraduationDate()
                .ifPresent(graduationDate -> sb.append(PREFIX_PERSON_GRADUATION_DATE)
                        .append(graduationDate.value).append(" "));
        descriptor.getCap()
                .ifPresent(cap -> sb.append(PREFIX_PERSON_CAP).append(cap).append(" "));
        descriptor.getUniversity()
                .ifPresent(university -> sb.append(PREFIX_PERSON_UNIVERSITY).append(university.value).append(" "));
        descriptor.getMajor()
                .ifPresent(major -> sb.append(PREFIX_PERSON_MAJOR).append(major.value).append(" "));
        descriptor.getJobId()
                .ifPresent(id -> sb.append(PREFIX_JOB_ID).append(id.value).append(" "));
        descriptor.getJobTitle()
                .ifPresent(title -> sb.append(PREFIX_JOB_TITLE).append(title.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
