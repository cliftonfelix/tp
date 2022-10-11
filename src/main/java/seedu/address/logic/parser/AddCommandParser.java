package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
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
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PERSON_NAME, PREFIX_PERSON_PHONE, PREFIX_PERSON_EMAIL,
                PREFIX_PERSON_ADDRESS,
                PREFIX_PERSON_CAP,
                PREFIX_PERSON_GENDER,
                PREFIX_PERSON_GRADUATION_DATE,
                PREFIX_PERSON_UNIVERSITY,
                PREFIX_PERSON_MAJOR,
                PREFIX_JOB_ID,
                PREFIX_JOB_TITLE,
                PREFIX_TAG);
        if (!arePrefixesPresent(argMultimap, PREFIX_PERSON_NAME, PREFIX_PERSON_PHONE, PREFIX_PERSON_EMAIL,
                PREFIX_PERSON_ADDRESS,
                PREFIX_PERSON_GENDER,
                PREFIX_PERSON_GRADUATION_DATE,
                PREFIX_PERSON_CAP,
                PREFIX_PERSON_UNIVERSITY,
                PREFIX_PERSON_MAJOR,
                PREFIX_JOB_ID,
                PREFIX_JOB_TITLE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_PERSON_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PERSON_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_PERSON_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_PERSON_ADDRESS).get());
        Cap cap = ParserUtil.parseCap(argMultimap.getValue(PREFIX_PERSON_CAP).get());
        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_PERSON_GENDER).get());
        GraduationDate graduationDate = ParserUtil
                .parseGraduationDate(argMultimap.getValue(PREFIX_PERSON_GRADUATION_DATE).get());
        University university = ParserUtil.parseUniversity(argMultimap.getValue(PREFIX_PERSON_UNIVERSITY).get());
        Major major = ParserUtil.parseMajor(argMultimap.getValue(PREFIX_PERSON_MAJOR).get());
        Id id = ParserUtil.parseId(argMultimap.getValue(PREFIX_JOB_ID).get());
        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_JOB_TITLE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Record record = new Record(name, phone, email, address,
            gender,
            graduationDate,
            cap,
            university,
            major,
            id,
            title,
            tagList);

        return new AddCommand(record);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
