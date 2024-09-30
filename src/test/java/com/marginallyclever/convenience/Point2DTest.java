package com.marginallyclever.convenience;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Point2DTest {
    @Test
    public void addTest(){
        // Create a Point2D object with known values
        Point2D p = new Point2D(10,20);
        Point2D p2 = new Point2D(0,10);
        Point2D p3 = new Point2D(-10,-10);

        p2.add(p); // Adds p coordinates to p2 coordinates
        p3.add(p); // Adds p coordinates to p3 coordinates

        // Checks if p2 has been added
        Assertions.assertEquals(10,p2.x);
        Assertions.assertEquals(30,p2.y);

        // Checks if p3 has been added
        Assertions.assertEquals(0,p3.x);
        Assertions.assertEquals(10,p3.y);
    }

    @Test
    public void testNormalize() {
        // Arrange: Create a Point2D object with known values
        Point2D point = new Point2D(3.0, 4.0); // The length of this vector is 5.0

        // Act: Normalize the point.
        point.normalize();

        // Assert: Check that the length of the vector after normalization is 1.0
        double length = Math.sqrt(point.x * point.x + point.y * point.y);
        Assertions.assertEquals(1.0, length, 1e-9); // Using a small delta for precision

        // Assert: Check that the direction is preserved (scaled down but same ratio)
        Assertions.assertEquals(0.6, point.x, 1e-9); // Expected value for x after normalization
        Assertions.assertEquals(0.8, point.y, 1e-9); // Expected value for y after normalization
    }

    @Test
    public void testNormalizeZeroVector() {
        // Arrange: Create a Point2D object that is a zero vector
        Point2D point = new Point2D(0.0, 0.0);

        // Act: Normalize the zero vector
        point.normalize();

        // Assert: Ensure that the vector remains unchanged
        Assertions.assertEquals(0.0, point.x, 1e-9);
        Assertions.assertEquals(0.0, point.y, 1e-9);
    }

    @Test
    public void testScalePositiveFactor() {
        // Arrange: Create a Point2D object with known values.
        Point2D p1 = new Point2D(2.0, 3.0);
        // Arrange: Create a Point2D object with known values.
        Point2D p2 = new Point2D(2.0, 3.0);

        // Act: Scale the point by a positive factor.
        p1.scale(2.0);
        // Act: Scale the point by a negative factor.
        p2.scale(-1.0);

        // Assert: Check that the values are scaled correctly.
        Assertions.assertEquals(4.0, p1.x, 1e-9);  // 2.0 * 2.0 = 4.0
        Assertions.assertEquals(6.0, p1.y, 1e-9);  // 3.0 * 2.0 = 6.0
        // Assert: Check that the values are scaled correctly.
        Assertions.assertEquals(-2.0, p2.x, 1e-9);  // 2.0 * -1.0 = -2.0
        Assertions.assertEquals(-3.0, p2.y, 1e-9);  // 3.0 * -1.0 = -3.0
    }
}
