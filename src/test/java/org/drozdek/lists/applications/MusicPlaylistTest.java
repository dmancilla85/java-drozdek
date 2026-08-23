package org.drozdek.lists.applications;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MusicPlaylist Tests")
class MusicPlaylistTest {

  private MusicPlaylist playlist;
  private Track t1;
  private Track t2;
  private Track t3;

  @BeforeEach
  void setUp() {
    playlist = new MusicPlaylist();
    t1 = new Track("Bohemian Rhapsody", "Queen", 354);
    t2 = new Track("Stairway to Heaven", "Led Zeppelin", 482);
    t3 = new Track("Hotel California", "Eagles", 391);
  }

  @Test
  @DisplayName("Track validation and duration formatting")
  void testTrackRecord() {
    assertThrows(IllegalArgumentException.class, () -> new Track(null, "Queen", 100));
    assertThrows(IllegalArgumentException.class, () -> new Track("", "Queen", 100));
    assertThrows(IllegalArgumentException.class, () -> new Track("Song", null, 100));
    assertThrows(IllegalArgumentException.class, () -> new Track("Song", "", 100));
    assertThrows(IllegalArgumentException.class, () -> new Track("Song", "Queen", 0));
    assertThrows(IllegalArgumentException.class, () -> new Track("Song", "Queen", -10));

    assertEquals("05:54", t1.formattedDuration());
    assertEquals("08:02", t2.formattedDuration());
  }

  @Test
  @DisplayName("Empty playlist behavior")
  void testEmptyPlaylist() {
    assertTrue(playlist.isEmpty());
    assertEquals(0, playlist.size());
    assertNull(playlist.getCurrentTrack());
    assertNull(playlist.nextTrack());
    assertNull(playlist.previousTrack());
    assertFalse(playlist.removeTrack(t1));
  }

  @Test
  @DisplayName("Adding tracks and sequential forward navigation")
  void testAddAndNextNavigation() {
    playlist.addTrack(t1);
    playlist.addTrack(t2);
    playlist.addTrack(t3);

    assertEquals(3, playlist.size());
    assertFalse(playlist.isEmpty());
    assertEquals(t1, playlist.getCurrentTrack());

    assertEquals(t2, playlist.nextTrack());
    assertEquals(t3, playlist.nextTrack());
    // Looping wraps back to start
    assertEquals(t1, playlist.nextTrack());
  }

  @Test
  @DisplayName("Backward navigation with looping enabled")
  void testPreviousNavigationWithLooping() {
    playlist.addTrack(t1);
    playlist.addTrack(t2);
    playlist.addTrack(t3);

    assertEquals(t1, playlist.getCurrentTrack());
    // Rewinding from head wraps to tail when looping
    assertEquals(t3, playlist.previousTrack());
    assertEquals(t2, playlist.previousTrack());
    assertEquals(t1, playlist.previousTrack());
  }

  @Test
  @DisplayName("Navigation without looping stops at boundaries")
  void testNonLoopingNavigation() {
    playlist.setLooping(false);
    assertFalse(playlist.isLooping());

    playlist.addTrack(t1);
    playlist.addTrack(t2);

    assertEquals(t1, playlist.getCurrentTrack());
    assertEquals(t2, playlist.nextTrack());
    // At tail with looping off: remains at tail
    assertEquals(t2, playlist.nextTrack());

    assertEquals(t1, playlist.previousTrack());
    // At head with looping off: remains at head
    assertEquals(t1, playlist.previousTrack());
  }

  @Test
  @DisplayName("Get all tracks list preserves insertion order")
  void testGetAllTracks() {
    playlist.addTrack(t1);
    playlist.addTrack(t2);

    List<Track> all = playlist.getAllTracks();
    assertEquals(2, all.size());
    assertEquals(t1, all.get(0));
    assertEquals(t2, all.get(1));
  }

  @Test
  @DisplayName("Removing tracks updates cursor and size")
  void testRemoveTrack() {
    playlist.addTrack(t1);
    playlist.addTrack(t2);
    playlist.addTrack(t3);

    assertTrue(playlist.removeTrack(t1));
    assertEquals(2, playlist.size());
    assertEquals(t2, playlist.getCurrentTrack());

    assertTrue(playlist.removeTrack(t2));
    assertEquals(1, playlist.size());
    assertEquals(t3, playlist.getCurrentTrack());

    assertTrue(playlist.removeTrack(t3));
    assertTrue(playlist.isEmpty());
    assertNull(playlist.getCurrentTrack());
  }

  @Test
  @DisplayName("Clear playlist resets all state")
  void testClear() {
    playlist.addTrack(t1);
    playlist.addTrack(t2);
    playlist.clear();

    assertTrue(playlist.isEmpty());
    assertEquals(0, playlist.size());
    assertNull(playlist.getCurrentTrack());
  }
}
