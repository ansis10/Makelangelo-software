package com.marginallyclever.convenience;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;

public class QuadGraphTest {
    private QuadGraph quadGraph;
    private Random random;

    @BeforeEach
    public void setUp() {
        // Initialize the QuadGraph to cover a region from (0, 0) to (100, 100)
        quadGraph = new QuadGraph(0, 0, 100, 100);
        // Use a fixed seed for reproducibility
        random = new Random(42);
    }

    @Test
    public void testInsert() {
        // Insert random points into the QuadGraph
        for (int i = 0; i < 10; i++) {
            Point2D randomPoint = generateRandomPoint();
            Assertions.assertTrue(quadGraph.insert(randomPoint), "Point should be inserted successfully");
        }

        // Check that the total number of points is correct
        int totalPoints = quadGraph.countPoints();
        Assertions.assertEquals(10, totalPoints, "There should be 10 points inserted into the QuadGraph");
    }

    @Test
    public void testInsertAndSplit() {
        // Insert more than MAX_POINTS (which is 5) to trigger a split
        for (int i = 0; i < 6; i++) {
            Point2D randomPoint = generateRandomPoint();
            quadGraph.insert(randomPoint);
        }

        // Verify that the QuadGraph has split
        Assertions.assertNotNull(quadGraph.children, "QuadGraph should have children after split");

        // Verify that the points are moved into the children after the split
        int totalPoints = quadGraph.countPoints();
        Assertions.assertEquals(6, totalPoints, "There should still be 6 points after the split");
    }



    @Test
    public void testCountPoints() {
        // Insert random points into the QuadGraph
        for (int i = 0; i < 15; i++) {
            Point2D randomPoint = generateRandomPoint();
            quadGraph.insert(randomPoint);
        }

        // Verify the total count of points
        int totalPoints = quadGraph.countPoints();
        Assertions.assertEquals(15, totalPoints, "There should be 15 points in the QuadGraph");
    }

    // Helper method to generate random points within the bounds of the QuadGraph (0, 0) to (100, 100)
    private Point2D generateRandomPoint() {
        double x = random.nextDouble() * 100;
        double y = random.nextDouble() * 100;
        return new Point2D(x, y);
    }
}
