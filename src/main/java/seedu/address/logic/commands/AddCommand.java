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

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.record.Record;

/**
 * Adds a record to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a record to the address book. "
            + "Parameters: "
            + PREFIX_PERSON_NAME + "NAME "
            + PREFIX_PERSON_PHONE + "PHONE "
            + PREFIX_PERSON_EMAIL + "EMAIL "
            + PREFIX_PERSON_ADDRESS + "ADDRESS "
            + PREFIX_PERSON_CAP + "CAP "
            + PREFIX_PERSON_GENDER + "GENDER "
            + PREFIX_PERSON_UNIVERSITY + "UNIVERSITY "
            + PREFIX_PERSON_MAJOR + "MAJOR "
            + PREFIX_JOB_ID + "ID "
            + PREFIX_JOB_TITLE + "TITLE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PERSON_NAME + "John Doe "
            + PREFIX_PERSON_PHONE + "98765432 "
            + PREFIX_PERSON_EMAIL + "johnd@example.com "
            + PREFIX_PERSON_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_PERSON_CAP + "3.50/4.00 "
            + PREFIX_PERSON_GENDER + "male "
            + PREFIX_PERSON_UNIVERSITY + "Nanyang Polytechnic "
            + PREFIX_PERSON_GRADUATION_DATE + "05-2024"
            + PREFIX_PERSON_MAJOR + "Computer Science "
            + PREFIX_JOB_ID + "173296 "
            + PREFIX_JOB_TITLE + "Software Engineer Intern "
            + PREFIX_TAG + "rejected "
            + PREFIX_TAG + "KIV";

    public static final String MESSAGE_SUCCESS = "New record added: %1$s";
    public static final String MESSAGE_DUPLICATE_RECORD = "This record already exists in the address book";

    private final Record toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Record}
     */
    public AddCommand(Record record) {
        requireNonNull(record);
        toAdd = record;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasRecord(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECORD);
        }

        model.addRecord(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
