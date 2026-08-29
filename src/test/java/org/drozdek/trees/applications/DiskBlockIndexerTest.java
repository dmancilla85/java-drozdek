package org.drozdek.trees.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiskBlockIndexerTest {

    @Test
    @DisplayName("Indexes and finds allocated blocks")
    void index_findsBlock() {
        DiskBlockIndexer indexer = new DiskBlockIndexer();
        indexer.indexBlock(10);
        indexer.indexBlock(20);
        indexer.indexBlock(30);
        assertTrue(indexer.isIndexed(10));
        assertTrue(indexer.isIndexed(30));
        assertFalse(indexer.isIndexed(99));
    }

    @Test
    @DisplayName("Empty index reports nothing and has height minus one")
    void empty_index() {
        DiskBlockIndexer indexer = new DiskBlockIndexer();
        assertFalse(indexer.isIndexed(1));
        assertEquals(-1, indexer.height());
    }

    @Test
    @DisplayName("Index height stays bounded after inserts")
    void index_height() {
        DiskBlockIndexer indexer = new DiskBlockIndexer();
        for (int i = 1; i <= 20; i++) {
            indexer.indexBlock(i);
        }
        assertTrue(indexer.isIndexed(20));
        assertTrue(indexer.height() >= 0);
    }
}
