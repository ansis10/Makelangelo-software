package com.marginallyclever.convenience.helpers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class OSHelperTest {
    private String originalOS;

    @BeforeEach
    public void setUp() {
        // Store the original OS name before each test
        originalOS = System.getProperty("os.name");
    }

    @AfterEach
    public void tearDown() {
        // Restore the original OS name after each test
        System.setProperty("os.name", originalOS);
    }

    @Test
    public void testIsWindows() {
        // Mock the system property to simulate Windows
        System.setProperty("os.name", "Windows 10");
        assertTrue(OSHelper.isWindows(), "isWindows should return true for 'Windows 10'");

        // Mock the system property to simulate a non-Windows OS
        System.setProperty("os.name", "Linux");
        assertFalse(OSHelper.isWindows(), "isWindows should return false for 'Linux'");
    }

    @Test
    public void testIsOSX() {
        // Mock the system property to simulate macOS
        System.setProperty("os.name", "Mac OS X");
        assertTrue(OSHelper.isOSX(), "isOSX should return true for 'Mac OS X'");

        // Mock the system property to simulate a non-macOS OS
        System.setProperty("os.name", "Linux");
        assertFalse(OSHelper.isOSX(), "isOSX should return false for 'Linux'");

        // Simulate alternative macOS names
        System.setProperty("os.name", "OSX");
        assertTrue(OSHelper.isOSX(), "isOSX should return true for 'OSX'");
    }
}
