package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testLoopWithZeroIterations() {
        App.loop(0);
        String output = outputStreamCaptor.toString();
        assertEquals("", output, "Loop with 0 iterations should print nothing");
    }

    @Test
    void testLoopWithOneIteration() {
        App.loop(1);
        String output = outputStreamCaptor.toString().trim();
        assertEquals("i = 1", output, "Loop with 1 iteration should print 'i = 1'");
    }

    @Test
    void testLoopWithThreeIterations() {
        App.loop(3);
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("i = 1"), "Output should contain 'i = 1'");
        assertTrue(output.contains("i = 2"), "Output should contain 'i = 2'");
        assertTrue(output.contains("i = 3"), "Output should contain 'i = 3'");
    }

    @Test
    void testLoopWithFiveIterations() {
        App.loop(5);
        String output = outputStreamCaptor.toString();
        String[] lines = output.split(System.lineSeparator());
        assertEquals(5, lines.length, "Loop with 5 iterations should print 5 lines");
    }

    @Test
    void testLoopWithNegativeNumber() {
        App.loop(-1);
        String output = outputStreamCaptor.toString();
        assertEquals("", output, "Loop with negative number should print nothing");
    }

    @Test
    void testLoopOutputFormat() {
        App.loop(2);
        String output = outputStreamCaptor.toString();
        assertTrue(output.matches("(?s)i = 1\\s+i = 2\\s*"), 
            "Output should match the expected format 'i = n'");
    }

    @Test
    void testLoopWithTenIterations() {
        App.loop(10);
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("i = 10"), "Output should contain 'i = 10'");
        assertFalse(output.contains("i = 11"), "Output should not contain 'i = 11'");
    }
}
