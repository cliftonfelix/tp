package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Major;

public class MajorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Major(null));
    }

    @Test
    public void constructor_invalidMajorType_throwsIllegalArgumentException() {
        String invalidMajorType = "";
        assertThrows(IllegalArgumentException.class, () -> new Major(invalidMajorType));
    }

    @Test
    public void isValidMajor() {
        // null major type
        assertThrows(NullPointerException.class, () -> Major.isValidMajor(null));

        // invalid major name
        assertFalse(Major.isValidMajor("")); // empty string
        assertFalse(Major.isValidMajor(" ")); // spaces only
        assertFalse(Major.isValidMajor("^")); // only non-alphanumeric characters
        assertFalse(Major.isValidMajor("Art*")); // contains non-alphanumeric characters
        assertFalse(Major.isValidMajor("12345")); // numbers only
        assertFalse(Major.isValidMajor("Art25")); // alphanumeric characters

        // valid major name
        assertTrue(Major.isValidMajor("Mathematics and the Foundations of Computer Science")); // long names
        assertTrue(Major.isValidMajor("Chemical Engineering")); // alphabets only
        assertTrue(Major.isValidMajor("CHEMICAL ENGINEERING")); // with capital letters
    }
}
