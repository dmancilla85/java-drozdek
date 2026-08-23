package org.drozdek.queues.applications;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PrintSpooler Tests")
class PrintSpoolerTest {

  private PrintSpooler spooler;

  @BeforeEach
  void setUp() {
    spooler = new PrintSpooler();
  }

  @Test
  @DisplayName("PrintJob validation requirements")
  void testPrintJobValidation() {
    assertThrows(IllegalArgumentException.class, () -> new PrintJob(null, "doc.pdf", "alice", 5));
    assertThrows(IllegalArgumentException.class, () -> new PrintJob("", "doc.pdf", "alice", 5));
    assertThrows(IllegalArgumentException.class, () -> new PrintJob("1", null, "alice", 5));
    assertThrows(IllegalArgumentException.class, () -> new PrintJob("1", "", "alice", 5));
    assertThrows(IllegalArgumentException.class, () -> new PrintJob("1", "doc.pdf", null, 5));
    assertThrows(IllegalArgumentException.class, () -> new PrintJob("1", "doc.pdf", "", 5));
    assertThrows(IllegalArgumentException.class, () -> new PrintJob("1", "doc.pdf", "alice", 0));
    assertThrows(IllegalArgumentException.class, () -> new PrintJob("1", "doc.pdf", "alice", -3));

    PrintJob valid = new PrintJob("job-101", "invoice.pdf", "bob", 12);
    assertEquals("job-101", valid.jobId());
    assertEquals("invoice.pdf", valid.documentName());
    assertEquals("bob", valid.owner());
    assertEquals(12, valid.pageCount());
  }

  @Test
  @DisplayName("Spooler starts empty")
  void testInitialState() {
    assertFalse(spooler.hasPendingJobs());
    assertEquals(0, spooler.getPendingJobCount());
    assertEquals(0, spooler.getTotalPagesProcessed());
    assertEquals(0, spooler.getTotalJobsCompleted());
    assertNull(spooler.peekNextJob());
    assertNull(spooler.processNextJob());
  }

  @Test
  @DisplayName("Submitting null job throws exception")
  void testSubmitNullJob() {
    assertThrows(IllegalArgumentException.class, () -> spooler.submitJob(null));
  }

  @Test
  @DisplayName("FIFO processing order is strictly maintained")
  void testFifoProcessingOrder() {
    PrintJob job1 = new PrintJob("1", "doc1.pdf", "alice", 3);
    PrintJob job2 = new PrintJob("2", "doc2.pdf", "bob", 7);
    PrintJob job3 = new PrintJob("3", "doc3.pdf", "carol", 10);

    spooler.submitJob(job1);
    spooler.submitJob(job2);
    spooler.submitJob(job3);

    assertTrue(spooler.hasPendingJobs());
    assertEquals(3, spooler.getPendingJobCount());
    assertEquals(job1, spooler.peekNextJob());

    PrintJob p1 = spooler.processNextJob();
    assertEquals(job1, p1);
    assertEquals(2, spooler.getPendingJobCount());
    assertEquals(3, spooler.getTotalPagesProcessed());
    assertEquals(1, spooler.getTotalJobsCompleted());

    PrintJob p2 = spooler.processNextJob();
    assertEquals(job2, p2);
    assertEquals(1, spooler.getPendingJobCount());
    assertEquals(10, spooler.getTotalPagesProcessed());
    assertEquals(2, spooler.getTotalJobsCompleted());

    PrintJob p3 = spooler.processNextJob();
    assertEquals(job3, p3);
    assertFalse(spooler.hasPendingJobs());
    assertEquals(0, spooler.getPendingJobCount());
    assertEquals(20, spooler.getTotalPagesProcessed());
    assertEquals(3, spooler.getTotalJobsCompleted());

    assertNull(spooler.processNextJob());
  }

  @Test
  @DisplayName("Cancelling all jobs clears pending queue")
  void testCancelAllJobs() {
    spooler.submitJob(new PrintJob("1", "file.pdf", "dan", 2));
    spooler.submitJob(new PrintJob("2", "file2.pdf", "dan", 4));

    assertEquals(2, spooler.getPendingJobCount());
    spooler.cancelAllJobs();
    assertEquals(0, spooler.getPendingJobCount());
    assertFalse(spooler.hasPendingJobs());
    assertNull(spooler.processNextJob());
  }
}
