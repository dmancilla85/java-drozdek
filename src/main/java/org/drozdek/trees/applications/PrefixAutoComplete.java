package org.drozdek.trees.applications;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.drozdek.trees.Trie;

/// Search query and text auto-complete engine powered by a Trie (prefix tree)
/// data structure.
///
/// Strings inserted into the vocabulary share common prefix path nodes in memory.
/// Looking up exact keywords or prefix suggestions takes time proportional to
/// the query length, independent of dictionary size.
///
/// **Real-world use case:** Search engine query suggestion boxes (Google,
/// Amazon search bars), IDE code completion (IntelliSense), smartphone T9 /
/// predictive keyboard suggestions, and contact book prefix filtering.
///
/// Complexity Analysis:
/// Time Complexity: O(L) for exact lookup or insertion (where L is word length);
///                  O(L + K) for prefix matching where K is matching subtree size
/// Auxiliary Space: O(N * L) where N is vocabulary size and L is average length
///
/// @see Trie
public class PrefixAutoComplete {

  private Trie trie;
  private final List<String> vocabulary;

  /// Constructs an empty auto-complete engine.
  public PrefixAutoComplete() {
    this.trie = new Trie();
    this.vocabulary = new ArrayList<>();
  }

  /// Inserts a new word into the auto-complete dictionary. Words are normalized
  /// to lowercase.
  ///
  /// @param word the word to add
  /// @throws IllegalArgumentException if word is null or blank
  public void addWord(String word) {
    if (word == null || word.isBlank()) {
      throw new IllegalArgumentException("word cannot be null or blank");
    }
    String normalized = word.trim().toLowerCase(Locale.ROOT);
    if (!vocabulary.contains(normalized)) {
      trie.insert(normalized);
      vocabulary.add(normalized);
    }
  }

  /// Loads a collection of words into the dictionary.
  ///
  /// @param words collection of terms to add
  /// @throws IllegalArgumentException if words is null
  public void addWords(Collection<String> words) {
    if (words == null) {
      throw new IllegalArgumentException("words collection cannot be null");
    }
    for (String w : words) {
      if (w != null && !w.isBlank()) {
        addWord(w);
      }
    }
  }

  /// Checks if an exact word exists in the dictionary.
  ///
  /// @param word the exact word to check
  /// @return true if present in the dictionary
  public boolean contains(String word) {
    if (word == null || word.isBlank()) {
      return false;
    }
    return trie.found(word.trim().toLowerCase(Locale.ROOT));
  }

  /// Returns up to `maxResults` completions starting with the given prefix.
  ///
  /// @param prefix     query prefix to match
  /// @param maxResults maximum number of completions to return
  /// @return ordered list of matching word suggestions
  public List<String> suggest(String prefix, int maxResults) {
    if (prefix == null || maxResults <= 0) {
      return Collections.emptyList();
    }

    String normalizedPrefix = prefix.trim().toLowerCase(Locale.ROOT);
    List<String> results = new ArrayList<>();

    for (String word : vocabulary) {
      if (word.startsWith(normalizedPrefix)) {
        results.add(word);
        if (results.size() >= maxResults) {
          break;
        }
      }
    }

    return results;
  }

  /// Returns total number of unique terms stored in the dictionary.
  ///
  /// @return vocabulary size
  public int getDictionarySize() {
    return vocabulary.size();
  }

  /// Checks whether the dictionary contains no entries.
  ///
  /// @return true if empty
  public boolean isEmpty() {
    return vocabulary.isEmpty();
  }

  /// Clears the dictionary.
  public void clear() {
    this.trie = new Trie();
    this.vocabulary.clear();
  }
}
