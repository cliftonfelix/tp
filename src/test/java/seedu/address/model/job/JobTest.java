package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_BOB;
import static seedu.address.testutil.TypicalRecords.ALICE;
import static seedu.address.testutil.TypicalRecords.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.record.Record;
import seedu.address.testutil.RecordBuilder;

public class JobTest {

    @Test
    public void isSameJob() {
        // same object -> returns true
        assertTrue(ALICE.getJob().isSameJob(ALICE.getJob()));

        // null -> returns false
        assertFalse(ALICE.getJob().isSameJob(null));

        // same job ID, different job title -> returns true
        Record editedAlice = new RecordBuilder(ALICE).withTitle(VALID_JOB_TITLE_BOB).build();
        Job editedAliceJob = editedAlice.getJob();
        assertTrue(ALICE.getJob().isSameJob(editedAliceJob));

        // different job ID, same job title -> returns false
        editedAlice = new RecordBuilder(ALICE).withId(VALID_JOB_ID_BOB).build();
        editedAliceJob = editedAlice.getJob();
        assertFalse(ALICE.getJob().isSameJob(editedAliceJob));

        // job ID differs in case, same job title -> returns false
        Record editedBob = new RecordBuilder(BOB).withId(VALID_JOB_ID_BOB.toLowerCase()).build();
        Job editedBobJob = editedBob.getJob();
        assertFalse(BOB.getJob().isSameJob(editedBobJob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Record aliceCopy = new RecordBuilder(ALICE).build();
        assertTrue(ALICE.getJob().equals(aliceCopy.getJob()));

        // same object -> returns true
        assertTrue(ALICE.getJob().equals(ALICE.getJob()));

        // null -> returns false
        assertFalse(ALICE.getJob().equals(null));

        // different type -> returns false
        assertFalse(ALICE.getJob().equals(5));

        // different job -> returns false
        assertFalse(ALICE.getJob().equals(BOB.getJob()));

        // different job ID -> returns false
        Record editedAlice = new RecordBuilder(ALICE).withId(VALID_JOB_ID_BOB).build();
        assertFalse(ALICE.getJob().equals(editedAlice.getJob()));

        // different job title -> returns false
        editedAlice = new RecordBuilder(ALICE).withTitle(VALID_JOB_TITLE_BOB).build();
        assertFalse(ALICE.getJob().equals(editedAlice.getJob()));
    }
}
