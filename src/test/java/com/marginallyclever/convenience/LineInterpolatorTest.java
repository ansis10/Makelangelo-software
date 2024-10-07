package com.marginallyclever.convenience;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LineInterpolatorTest {

    @Test
    public void testGetPoint() {
        Faker faker = new Faker(); // Initialize Faker

        // Generate random start and end points for the line
        for (int i = 0; i < 100; i++) {
            Point2D start = new Point2D(faker.number().randomDouble(6, 0, 100), faker.number().randomDouble(6, 0, 100));
            Point2D end = new Point2D(faker.number().randomDouble(6, 0, 100), faker.number().randomDouble(6, 0, 100));
            LineInterpolator lineInterpolator = new LineInterpolator(start, end);

            // Generate a random value of t between 0 and 1
            double t = faker.number().randomDouble(6, 0, 1);
            Point2D point = new Point2D();
            lineInterpolator.getPoint(t, point);

            // Manually calculate the expected point
            double expectedX = (end.x - start.x) * t + start.x;
            double expectedY = (end.y - start.y) * t + start.y;

            // Assert that the point calculated by getPoint is correct
            Assertions.assertEquals(expectedX, point.x, 0.000001, "X coordinate should match the expected value");
            Assertions.assertEquals(expectedY, point.y, 0.000001, "Y coordinate should match the expected value");
        }
    }

    @Test
    public void testGetTangent() {
        Faker faker = new Faker(); // Initialize Faker

        // Generate random start and end points for the line
        for (int i = 0; i < 100; i++) {
            Point2D start = new Point2D(faker.number().randomDouble(6, 0, 100), faker.number().randomDouble(6, 0, 100));
            Point2D end = new Point2D(faker.number().randomDouble(6, 0, 100), faker.number().randomDouble(6, 0, 100));
            LineInterpolator lineInterpolator = new LineInterpolator(start, end);

            // Generate a random value of t between 0 and 1
            double t = faker.number().randomDouble(6, 0, 1);
            Point2D tangent = new Point2D();
            lineInterpolator.getTangent(t, tangent);

            // The tangent should be normalized
            double length = Math.sqrt(tangent.x * tangent.x + tangent.y * tangent.y);
            Assertions.assertEquals(1.0, length, 0.000001, "The tangent should be normalized to length 1");
        }
    }

    @Test
    public void testGetNormal() {
        Faker faker = new Faker(); // Initialize Faker

        // Generate random start and end points for the line
        for (int i = 0; i < 100; i++) {
            Point2D start = new Point2D(faker.number().randomDouble(6, 0, 100), faker.number().randomDouble(6, 0, 100));
            Point2D end = new Point2D(faker.number().randomDouble(6, 0, 100), faker.number().randomDouble(6, 0, 100));
            LineInterpolator lineInterpolator = new LineInterpolator(start, end);

            // Generate a random value of t between 0 and 1
            double t = faker.number().randomDouble(6, 0, 1);
            Point2D normal = new Point2D();
            lineInterpolator.getNormal(t, normal);

            // The normal should be perpendicular to the tangent
            Point2D tangent = new Point2D();
            lineInterpolator.getTangent(t, tangent);

            double dotProduct = normal.x * tangent.x + normal.y * tangent.y;
            Assertions.assertEquals(0.0, dotProduct, 0.000001, "The normal should be perpendicular to the tangent (dot product should be 0)");
        }
    }
}