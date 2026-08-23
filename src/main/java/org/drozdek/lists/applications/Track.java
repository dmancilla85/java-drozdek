package org.drozdek.lists.applications;

/// Immutable audio track representation stored within a playlist.
///
/// @param title           song title
/// @param artist          performing artist or band name
/// @param durationSeconds length of the audio track in seconds
///
/// @since 1.3
public record Track(String title, String artist, int durationSeconds) {

  /// Validates track parameters upon creation.
  public Track {
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("title cannot be null or blank");
    }
    if (artist == null || artist.isBlank()) {
      throw new IllegalArgumentException("artist cannot be null or blank");
    }
    if (durationSeconds <= 0) {
      throw new IllegalArgumentException("durationSeconds must be greater than zero");
    }
  }

  /// Formatted duration in MM:SS notation.
  ///
  /// @return formatted duration string (e.g. "03:45")
  public String formattedDuration() {
    int minutes = durationSeconds / 60;
    int seconds = durationSeconds % 60;
    return String.format("%02d:%02d", minutes, seconds);
  }
}
