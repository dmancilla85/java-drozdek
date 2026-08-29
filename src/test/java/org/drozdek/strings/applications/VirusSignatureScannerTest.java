package org.drozdek.strings.applications;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VirusSignatureScannerTest {

    @Test
    @DisplayName("Detects a registered signature in one pass")
    void scan_detectsSignature() {
        VirusSignatureScanner scanner = new VirusSignatureScanner();
        scanner.addSignature("trojan");
        scanner.addSignature("rootkit");
        scanner.finalizeSignatures();
        assertTrue(scanner.isThreat("payload contains trojan code"));
        assertEquals(1, scanner.scan("payload contains trojan code").size());
    }

    @Test
    @DisplayName("Returns a match for each distinct signature")
    void scan_multipleSignatures() {
        VirusSignatureScanner scanner = new VirusSignatureScanner();
        scanner.addSignature("bad");
        scanner.addSignature("evil");
        scanner.finalizeSignatures();
        assertEquals(1, scanner.scan("clean real bad text").size());
        assertEquals(2, scanner.scan("bad and evil together").size());
    }

    @Test
    @DisplayName("Clean content is not flagged")
    void scan_clean() {
        VirusSignatureScanner scanner = new VirusSignatureScanner();
        scanner.addSignature("malware");
        scanner.finalizeSignatures();
        assertFalse(scanner.isThreat("perfectly innocent text"));
    }
}
