package edu.iastate.cs228.hw1;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LivingTest {
    @Test
    public void testLivingCensusStartingCell() throws FileNotFoundException {
        Plain b = new Plain("public1-3x3.txt");
        int[] population = new int[5];
        int[] expectedPopulation = {1, 0, 2, 1, 0};
        b.grid[0][0].census(population);
        assertEquals(expectedPopulation[0], population[0]);
        assertEquals(expectedPopulation[1], population[1]);
        assertEquals(expectedPopulation[2], population[2]);
        assertEquals(expectedPopulation[3], population[3]);
        assertEquals(expectedPopulation[4], population[4]);
    }
    @Test
    public void testLivingCensusMiddleCell() throws FileNotFoundException {
        Plain b = new Plain("public1-3x3.txt");
        int[] population = new int[5];
        int[] expectedPopulation = {1, 1, 4, 2, 1};
        b.grid[1][1].census(population);
        assertEquals(expectedPopulation[0], population[0]);
        assertEquals(expectedPopulation[1], population[1]);
        assertEquals(expectedPopulation[2], population[2]);
        assertEquals(expectedPopulation[3], population[3]);
        assertEquals(expectedPopulation[4], population[4]);
    }

    @Test
    public void testLivingCensusEndCell() throws FileNotFoundException {
        Plain b = new Plain("public1-3x3.txt");
        int[] population = new int[5];
        int[] expectedPopulation = {0, 1, 3, 0, 0};
        b.grid[2][0].census(population);
        assertEquals(expectedPopulation[0], population[0]);
        assertEquals(expectedPopulation[1], population[1]);
        assertEquals(expectedPopulation[2], population[2]);
        assertEquals(expectedPopulation[3], population[3]);
        assertEquals(expectedPopulation[4], population[4]);
    }

}
