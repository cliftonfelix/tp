package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents Record's major in the address book.
 * Guarantees: immutable; is always valid
 */
public class Major {

    public static final String MESSAGE_CONSTRAINTS =
        "Major should only contain alphabetical characters and spaces, and it should not be blank";

    /*
     * The first character of the major name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alpha}][\\p{Alpha} ]*";

    public final String value;

    /**
     * Constructs an {@code Major}.
     *
     * @param majorName A valid major name.
     */
    public Major(String majorName) {
        requireNonNull(majorName);
        checkArgument(isValidMajor(majorName), MESSAGE_CONSTRAINTS);
        value = majorName;
    }

    /**
     * Returns true if a given string is a valid major name.
     */
    public static boolean isValidMajor(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Major // instanceof handles nulls
            && value.equals(((Major) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
