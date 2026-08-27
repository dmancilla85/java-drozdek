package org.drozdek.lists.applications;

import java.util.ArrayList;
import java.util.List;
import org.drozdek.lists.DoubleLinkedList;
import org.drozdek.lists.nodes.DoubleLinkedListNode;

/// Music playlist manager utilizing a doubly-linked list (`DoubleLinkedList`)
/// to enable sequential, bidirectional audio track navigation (next/previous)
/// and optional continuous loop playback.
///
/// In modern audio applications, tracks can be inserted or reordered at arbitrary
/// positions without copying surrounding elements in memory. Bidirectional
/// pointers allow instant O(1) skipping to the preceding or succeeding song.
///
/// **Real-world use case:** Media players (Spotify, Apple Music, VLC playlist
/// engine), DJ mixing cue lists, and audio carousel carousels.
///
/// Complexity Analysis:
/// Time Complexity: O(1) for adding to head/tail, advancing or rewinding cursor;
///                  O(n) for searching/deleting arbitrary tracks by value
/// Auxiliary Space: O(n) for storing n track nodes
///
/// @see DoubleLinkedList
/// @see DoubleLinkedListNode
public class MusicPlaylist {

  private DoubleLinkedList<Track> tracks;
  private DoubleLinkedListNode<Track> currentCursor;
  private boolean looping;

  /// Creates a new, empty music playlist with looping enabled by default.
  public MusicPlaylist() {
    this.tracks = new DoubleLinkedList<>();
    this.currentCursor = null;
    this.looping = true;
  }

  /// Appends a new track to the end of the playlist.
  ///
  /// @param track the audio track to add
  /// @throws IllegalArgumentException if track is null
  public void addTrack(Track track) {
    if (track == null) {
      throw new IllegalArgumentException("track cannot be null");
    }
    tracks.addToTail(track);
    if (currentCursor == null) {
      currentCursor = tracks.viewHeadNode();
    }
  }

  /// Advances playback to the next track in the playlist. If looping is enabled
  /// and the cursor reaches the end, it wraps around to the beginning.
  ///
  /// @return the newly active track, or null if playlist is empty
  public Track nextTrack() {
    if (tracks.isEmpty()) {
      currentCursor = null;
      return null;
    }

    if (currentCursor != null && currentCursor.getNext() != null) {
      currentCursor = currentCursor.getNext();
    } else if (looping) {
      currentCursor = tracks.viewHeadNode();
    }

    return getCurrentTrack();
  }

  /// Rewinds playback to the previous track in the playlist. If looping is enabled
  /// and the cursor is at the head, it wraps around to the tail.
  ///
  /// @return the newly active track, or null if playlist is empty
  public Track previousTrack() {
    if (tracks.isEmpty()) {
      currentCursor = null;
      return null;
    }

    if (currentCursor != null && currentCursor.getPrevious() != null) {
      currentCursor = currentCursor.getPrevious();
    } else if (looping) {
      currentCursor = tracks.viewTailNode();
    }

    return getCurrentTrack();
  }

  /// Returns the currently active audio track.
  ///
  /// @return current track, or null if empty
  public Track getCurrentTrack() {
    return currentCursor != null ? currentCursor.data : null;
  }

  /// Removes a track from the playlist by value.
  ///
  /// @param track the track to remove
  /// @return true if the track was found and deleted; false otherwise
  public boolean removeTrack(Track track) {
    if (track == null || tracks.isEmpty()) {
      return false;
    }

    boolean isCurrent = currentCursor != null && currentCursor.data.equals(track);
    if (isCurrent) {
      if (currentCursor.getNext() != null) {
        currentCursor = currentCursor.getNext();
      } else if (currentCursor.getPrevious() != null) {
        currentCursor = currentCursor.getPrevious();
      } else {
        currentCursor = null;
      }
    }

    tracks.delete(track);

    if (tracks.isEmpty()) {
      currentCursor = null;
    }

    return true;
  }

  /// Returns whether playlist looping is enabled.
  ///
  /// @return true if continuous looping is enabled
  public boolean isLooping() {
    return looping;
  }

  /// Configures whether playlist looping should wrap around at boundaries.
  ///
  /// @param looping true to enable continuous looping
  public void setLooping(boolean looping) {
    this.looping = looping;
  }

  /// Returns the total number of tracks in the playlist.
  ///
  /// @return track count
  public int size() {
    return tracks.size();
  }

  /// Checks if the playlist contains no tracks.
  ///
  /// @return true if empty
  public boolean isEmpty() {
    return tracks.isEmpty();
  }

  /// Returns a snapshot list of all tracks in playlist order.
  ///
  /// @return list of tracks
  public List<Track> getAllTracks() {
    List<Track> result = new ArrayList<>();
    for (Track t : tracks) {
      result.add(t);
    }
    return result;
  }

  /// Clears all tracks from the playlist.
  public void clear() {
    this.tracks = new DoubleLinkedList<>();
    this.currentCursor = null;
  }
}
