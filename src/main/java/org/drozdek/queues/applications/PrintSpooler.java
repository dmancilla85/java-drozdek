package org.drozdek.queues.applications;

import org.drozdek.queues.Queue;
import org.drozdek.queues.interfaces.QueueInterface;

/// Multi-user print spooler simulator managing asynchronous printing jobs
/// in strict First-In, First-Out (FIFO) arrival order.
///
/// Incoming print requests are buffered in an internal FIFO queue so that
/// client applications are not blocked while waiting for physical printer
/// hardware. The spooler dispatches jobs sequentially to the printing engine.
///
/// **Real-world use case:** Operating system print spoolers (CUPS in Unix/Linux,
/// Windows Print Spooler service), network shared printer servers, and batch
/// document rendering pipelines.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for job submission (enqueue), dispatching (dequeue),
///                  and status peek
/// Auxiliary Space: O(n) where n is the number of currently queued jobs
///
/// Bibliography:
///
/// - Adam Drozdek. *Data Structures and Algorithms in Java*, 2nd Ed. Chapter 4.
///
/// @see Queue
/// @see QueueInterface
public class PrintSpooler {

  private final QueueInterface<PrintJob> jobQueue;
  private int totalPagesProcessed;
  private int totalJobsCompleted;

  /// Creates a new, empty print spooler.
  public PrintSpooler() {
    this.jobQueue = new Queue<>();
    this.totalPagesProcessed = 0;
    this.totalJobsCompleted = 0;
  }

  /// Submits a new print job to the rear of the spooler queue.
  ///
  /// @param job the print job to queue
  /// @throws IllegalArgumentException if the job is null
  public void submitJob(PrintJob job) {
    if (job == null) {
      throw new IllegalArgumentException("job cannot be null");
    }
    jobQueue.enqueue(job);
  }

  /// Dispatches and completes the next print job at the front of the queue.
  ///
  /// @return the completed print job, or null if the queue is empty
  public PrintJob processNextJob() {
    if (jobQueue.isEmpty()) {
      return null;
    }
    PrintJob job = jobQueue.dequeue();
    totalPagesProcessed += job.pageCount();
    totalJobsCompleted++;
    return job;
  }

  /// Inspects the next job to be printed without removing it from the queue.
  ///
  /// @return the front print job, or null if the queue is empty
  public PrintJob peekNextJob() {
    if (jobQueue.isEmpty()) {
      return null;
    }
    return jobQueue.peek();
  }

  /// Checks whether there are pending jobs awaiting processing.
  ///
  /// @return true if the spooler has at least one pending job
  public boolean hasPendingJobs() {
    return !jobQueue.isEmpty();
  }

  /// Returns the number of jobs currently waiting in the spooler.
  ///
  /// @return current queue length
  public int getPendingJobCount() {
    return jobQueue.size();
  }

  /// Returns the cumulative number of pages successfully processed.
  ///
  /// @return total pages printed since initialization
  public int getTotalPagesProcessed() {
    return totalPagesProcessed;
  }

  /// Returns the total number of jobs completed by this spooler.
  ///
  /// @return total job count processed
  public int getTotalJobsCompleted() {
    return totalJobsCompleted;
  }

  /// Cancels and removes all pending print jobs from the spooler.
  public void cancelAllJobs() {
    jobQueue.clear();
  }
}
