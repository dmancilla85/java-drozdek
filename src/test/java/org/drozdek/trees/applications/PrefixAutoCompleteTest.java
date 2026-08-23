package org.drozdek.trees.applications;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PrefixAutoComplete Tests")
class PrefixAutoCompleteTest {

  private PrefixAutoComplete autoComplete;

  @BeforeEach
  void setUp() {
    autoComplete = new PrefixAutoComplete();
  }

  @Test
  @DisplayName("Empty dictionary behavior")
  void testEmptyDictionary() {
    assertTrue(autoComplete.isEmpty());
    assertEquals(0, autoComplete.getDictionarySize());
    assertFalse(autoComplete.contains("apple"));
    assertTrue(autoComplete.suggest("app", 5).isEmpty());
  }

  @Test
  @DisplayName("Invalid word inputs throw IllegalArgumentException")
  void testInvalidInputs() {
    assertThrows(IllegalArgumentException.class, () -> autoComplete.addWord(null));
    assertThrows(IllegalArgumentException.class, () -> autoComplete.addWord("   "));
    assertThrows(IllegalArgumentException.class, () -> autoComplete.addWords(null));
  }

  @Test
  @DisplayName("Adding words and exact search")
  void testAddAndContains() {
    autoComplete.addWord("Apple");
    autoComplete.addWord("application");
    autoComplete.addWord("banana");

    assertEquals(3, autoComplete.getDictionarySize());
    assertFalse(autoComplete.isEmpty());

    // Case-insensitive lookup
    assertTrue(autoComplete.contains("apple"));
    assertTrue(autoComplete.contains("APPLE"));
    assertTrue(autoComplete.contains("application"));
    assertTrue(autoComplete.contains("banana"));

    assertFalse(autoComplete.contains("app"));
    assertFalse(autoComplete.contains("orange"));
    assertFalse(autoComplete.contains(null));
  }

  @Test
  @DisplayName("Bulk word insertion")
  void testAddWordsCollection() {
    autoComplete.addWords(List.of("cat", "caterpillar", "category", "dog", "door"));
    assertEquals(5, autoComplete.getDictionarySize());
    assertTrue(autoComplete.contains("cat"));
    assertTrue(autoComplete.contains("door"));
  }

  @Test
  @DisplayName("Prefix suggestion queries with limits")
  void testSuggest() {
    autoComplete.addWords(List.of("car", "card", "care", "carpet", "careful", "cat", "dog"));

    List<String> suggestions = autoComplete.suggest("car", 3);
    assertEquals(3, suggestions.size());
    assertTrue(suggestions.contains("car"));
    assertTrue(suggestions.contains("card"));
    assertTrue(suggestions.contains("care"));

    List<String> allCarMatches = autoComplete.suggest("car", 10);
    assertEquals(5, allCarMatches.size());
    assertFalse(allCarMatches.contains("cat"));
    assertFalse(allCarMatches.contains("dog"));

    List<String> noMatches = autoComplete.suggest("zebra", 5);
    assertTrue(noMatches.isEmpty());

    assertTrue(autoComplete.suggest(null, 5).isEmpty());
    assertTrue(autoComplete.suggest("car", 0).isEmpty());
  }

  @Test
  @DisplayName("Clear dictionary resets state")
  void testClear() {
    autoComplete.addWord("test");
    autoComplete.clear();

    assertTrue(autoComplete.isEmpty());
    assertEquals(0, autoComplete.getDictionarySize());
    assertFalse(autoComplete.contains("test"));
  }
}
