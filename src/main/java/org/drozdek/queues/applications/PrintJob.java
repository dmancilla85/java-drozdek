package org.drozdek.queues.applications;

/// Immutable representation of a print request submitted to a print spooler.
///
/// @param jobId        unique identifier for the print job
/// @param documentName filename or title of the document to print
/// @param owner        username or workstation requesting the print
/// @param pageCount    number of pages in the document (must be positive)
///
/// @since 1.3
public record PrintJob(String jobId, String documentName, String owner, int pageCount) {

  /// Validates job parameters upon creation.
  public PrintJob {
    if (jobId == null || jobId.isBlank()) {
      throw new IllegalArgumentException("jobId cannot be null or blank");
    }
    if (documentName == null || documentName.isBlank()) {
      throw new IllegalArgumentException("documentName cannot be null or blank");
    }
    if (owner == null || owner.isBlank()) {
      throw new IllegalArgumentException("owner cannot be null or blank");
    }
    if (pageCount <= 0) {
      throw new IllegalArgumentException("pageCount must be greater than zero");
    }
  }
}
