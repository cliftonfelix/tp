package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditRecordDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PERSON_NAME, PREFIX_PERSON_PHONE, PREFIX_PERSON_EMAIL,
                        PREFIX_PERSON_ADDRESS,
                        PREFIX_PERSON_GENDER,
                        PREFIX_PERSON_GRADUATION_DATE,
                        PREFIX_PERSON_CAP,
                        PREFIX_PERSON_UNIVERSITY,
                        PREFIX_PERSON_MAJOR,
                        PREFIX_JOB_ID,
                        PREFIX_JOB_TITLE,
                        PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditRecordDescriptor editRecordDescriptor = new EditRecordDescriptor();
        if (argMultimap.getValue(PREFIX_PERSON_NAME).isPresent()) {
            editRecordDescriptor
                .setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_PERSON_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PERSON_PHONE).isPresent()) {
            editRecordDescriptor
                .setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PERSON_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_PERSON_EMAIL).isPresent()) {
            editRecordDescriptor
                .setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_PERSON_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_PERSON_ADDRESS).isPresent()) {
            editRecordDescriptor
                .setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_PERSON_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_PERSON_CAP).isPresent()) {
            editRecordDescriptor
                .setCap(ParserUtil.parseCap(argMultimap.getValue(PREFIX_PERSON_CAP).get()));
        }
        if (argMultimap.getValue(PREFIX_PERSON_GENDER).isPresent()) {
            editRecordDescriptor
                .setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_PERSON_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_PERSON_GRADUATION_DATE).isPresent()) {
            editRecordDescriptor
                    .setGraduationDate(ParserUtil.parseGraduationDate(
                            argMultimap.getValue(PREFIX_PERSON_GRADUATION_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_PERSON_UNIVERSITY).isPresent()) {
            editRecordDescriptor
                .setUniversity(ParserUtil.parseUniversity(argMultimap.getValue(PREFIX_PERSON_UNIVERSITY).get()));
        }
        if (argMultimap.getValue(PREFIX_PERSON_MAJOR).isPresent()) {
            editRecordDescriptor
                .setMajor(ParserUtil.parseMajor(argMultimap.getValue(PREFIX_PERSON_MAJOR).get()));
        }
        if (argMultimap.getValue(PREFIX_JOB_ID).isPresent()) {
            editRecordDescriptor
                .setId(ParserUtil.parseId(argMultimap.getValue(PREFIX_JOB_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_JOB_TITLE).isPresent()) {
            editRecordDescriptor
                .setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_JOB_TITLE).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editRecordDescriptor::setTags);

        if (!editRecordDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editRecordDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
