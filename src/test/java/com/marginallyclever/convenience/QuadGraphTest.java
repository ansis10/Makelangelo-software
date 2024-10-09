package com.marginallyclever.convenience;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;

public class QuadGraphTest {
    private QuadGraph quadGraph;
    private Faker faker;

    @BeforeEach
    public void setUp() {
        // Initialize the QuadGraph to cover a region from (0, 0) to (100, 100)
        quadGraph = new QuadGraph(0, 0, 100, 100);
        // Initialize Faker with a fixed seed for reproducibility
        faker = new Faker(new Random(42));
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

        for (int i = 0; i < 6; i++) {
            Point2D randomPoint = generateRandomPoint();
            quadGraph.insert(randomPoint);
        }

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
        double x = faker.number().randomDouble(6, 0, 100); // Generates a random double with up to 6 decimal places
        double y = faker.number().randomDouble(6, 0, 100); // Generates a random double with up to 6 decimal places
        return new Point2D(x, y);
    }
}
