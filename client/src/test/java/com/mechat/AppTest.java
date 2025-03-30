package com.mechat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.mechat.utils.AppDataStorage;
import com.mechat.utils.HostWithPortValidator;
import com.mechat.utils.Time;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */

    @Test
    public void testValidHostWithPort() {
        assertTrue(HostWithPortValidator.isValidHostWithPort("127.0.0.1:111"), "127.0.0.1:111 should be valid");
        assertTrue(HostWithPortValidator.isValidHostWithPort("192.168.1.1:8080"), "192.168.1.1:8080 should be valid");
        assertTrue(HostWithPortValidator.isValidHostWithPort("localhost:3000"), "localhost:3000 should be valid");
        assertTrue(HostWithPortValidator.isValidHostWithPort("[::1]:5000"), "[::1]:5000 should be valid");
        assertTrue(HostWithPortValidator.isValidHostWithPort("example.com:80"), "example.com:80 should be valid");
    }

    @Test
    public void testInvalidHostWithPort() {
        // Invalid cases
        assertFalse(HostWithPortValidator.isValidHostWithPort("1:1"), "1:1 should be invalid");
        assertFalse(HostWithPortValidator.isValidHostWithPort("1.1:1"), "1.1:1 should be invalid");
    }

    @Test
    void testTimeFormat() {
        assertTrue(Time.getFormatDate("2025", "1", "1").equals("01/01/25"), "Date format should be 01/01/25");
        assertTrue(Time.getFormatDate("2025", "10", "1").equals("01/10/25"), "Date format should be 01/10/25");
        assertTrue(Time.getFormatTime("1", "1").equals("01:01"), "Time format should be 01:01");
        assertTrue(Time.getFormatTime("10", "1").equals("10:01"), "Time format should be 10:01");
    }

    @Test
    void testSaveAndLoadData() {
        String fileName = "test";
        String data = "THIS IS A TEST FILE FOR TESTING PURPOSES";

        AppDataStorage.saveData(fileName, data);

        String loadedData = AppDataStorage.loadData(fileName);

        assertTrue(loadedData.equals(data), "Loaded data should match saved data");

        AppDataStorage.deleteData(fileName);

        String deletedData = AppDataStorage.loadData(fileName);

        assertTrue(deletedData == null, "Deleted data should be null");
    }
}
