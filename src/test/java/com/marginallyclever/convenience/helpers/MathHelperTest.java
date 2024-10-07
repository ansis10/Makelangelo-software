package com.marginallyclever.convenience.helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.vecmath.Point2d;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathHelperTest {
    @Test
    public void testBetween() {
        Point2d a = new Point2d();
        Point2d b = new Point2d();
        Point2d c;
        double epsilon = 1e-9;

        for (int i = 0; i < 50; ++i) {
            a.set(Math.random() * 500 - 250, Math.random() * 500 - 250);
            b.set(Math.random() * 500 - 250, Math.random() * 500 - 250);
            c = MathHelper.lerp(a, b, Math.random());
            assert (MathHelper.between(a, b, c, epsilon));
        }
    }

    @Test
    public void testNotBetween() {
        Point2d a = new Point2d();
        Point2d b = new Point2d();
        Point2d c;
        double epsilon = 1e-9;

        for (int i = 0; i < 50; ++i) {
            a.set(Math.random() * 500 - 250, Math.random() * 500 - 250);
            b.set(Math.random() * 500 - 250, Math.random() * 500 - 250);
            c = MathHelper.lerp(a, b, 1.0 + epsilon + Math.random());
            Assertions.assertFalse(MathHelper.between(a, b, c, epsilon));
        }

        for (int i = 0; i < 50; ++i) {
            a.set(Math.random() * 500 - 250, Math.random() * 500 - 250);
            b.set(Math.random() * 500 - 250, Math.random() * 500 - 250);
            c = MathHelper.lerp(a, b, -epsilon - Math.random());
            Assertions.assertFalse(MathHelper.between(a, b, c, epsilon));
        }
    }

    @Test
    public void testOffLine() {
        Point2d a = new Point2d();
        Point2d b = new Point2d();
        Point2d ortho = new Point2d();
        Point2d c;
        double epsilon = 1e-9;

        for (int i = 0; i < 50; ++i) {
            a.set(Math.random() * 500 - 250, Math.random() * 500 - 250);
            b.set(Math.random() * 500 - 250, Math.random() * 500 - 250);
            c = MathHelper.lerp(a, b, Math.random());
            ortho.set(b);
            ortho.sub(a);
            c.x += ortho.y;
            c.y += ortho.x;
            Assertions.assertFalse(MathHelper.between(a, b, c, epsilon));
        }

        for (int i = 0; i < 50; ++i) {
            a.set(Math.random() * 500 - 250, Math.random() * 500 - 250);
            b.set(Math.random() * 500 - 250, Math.random() * 500 - 250);
            c = MathHelper.lerp(a, b, 1.0 + epsilon + Math.random());
            ortho.set(b);
            ortho.sub(a);
            c.x += ortho.y;
            c.y += ortho.x;
            Assertions.assertFalse(MathHelper.between(a, b, c, epsilon));
        }

        for (int i = 0; i < 50; ++i) {
            a.set(Math.random() * 500 - 250, Math.random() * 500 - 250);
            b.set(Math.random() * 500 - 250, Math.random() * 500 - 250);
            c = MathHelper.lerp(a, b, -epsilon - Math.random());
            ortho.set(b);
            ortho.sub(a);
            c.x += ortho.y;
            c.y += ortho.x;
            Assertions.assertFalse(MathHelper.between(a, b, c, epsilon));
        }
    }
    // ----------------------------test for LengthSquared()---------------------
    @Test
    public void testLengthSquared() {
        // Test case 1: both dx and dy are positive
        double result = MathHelper.lengthSquared(3.0, 4.0);
        assertEquals(25.0, result, 0.0001, "Length squared of (3, 4) should be 25");

        // Test case 2: dx and dy are zero (expect 0 as result)
        result = MathHelper.lengthSquared(0.0, 0.0);
        assertEquals(0.0, result, 0.0001, "Length squared of (0, 0) should be 0");

        // Test case 3: dx and dy are negative
        result = MathHelper.lengthSquared(-3.0, -4.0);
        assertEquals(25.0, result, 0.0001, "Length squared of (-3, -4) should be 25");

        // Test case 4: large values
        result = MathHelper.lengthSquared(100.0, 200.0);
        assertEquals(50000.0, result, 0.0001, "Length squared of (100, 200) should be 50000");
    }
    // ----------------------------------test for Equal()---------------------
    @Test
    public void testEquals() {
        // Test case 1: Exact match
        Point2d a0 = new Point2d(0.0, 0.0);
        Point2d a1 = new Point2d(1.0, 1.0);
        Point2d b0 = new Point2d(0.0, 0.0);
        Point2d b1 = new Point2d(1.0, 1.0);
        Assertions.assertTrue(MathHelper.equals(a0, a1, b0, b1, 0.0001), "Segments should match exactly");

        // Test case 2: Exact match but reversed order
        a0.set(0.0, 0.0);
        a1.set(1.0, 1.0);
        b0.set(1.0, 1.0);
        b1.set(0.0, 0.0);
        Assertions.assertTrue(MathHelper.equals(a0, a1, b0, b1, 0.0001), "Segments should match in reverse order");

        // Test case 3: Match within epsilon
        a0.set(0.0, 0.0);
        a1.set(1.0, 1.0);
        b0.set(0.00005, 0.00005); // Slightly different but within epsilon
        b1.set(1.00005, 1.00005);
        Assertions.assertTrue(MathHelper.equals(a0, a1, b0, b1, 0.001), "Segments should match within epsilon");

        // Test case 4: No match
        a0.set(0.0, 0.0);
        a1.set(1.0, 1.0);
        b0.set(2.0, 2.0); // Different points
        b1.set(3.0, 3.0);
        Assertions.assertFalse(MathHelper.equals(a0, a1, b0, b1, 0.0001), "Segments should not match");

        // Test case 5: Partial match
        a0.set(0.0, 0.0);
        a1.set(1.0, 1.0);
        b0.set(0.0, 0.0); // One point matches
        b1.set(2.0, 2.0);
        Assertions.assertFalse(MathHelper.equals(a0, a1, b0, b1, 0.0001), "Segments should not match if only one endpoint matches");
    }
    //------------------test for lerp()---------------------------
    @Test
    public void testLerp() {
        // Test case 1: t = 0 (Should return 'a')
        double t = 0.0;
        double a = 10.0;
        double b = 20.0;
        double result = MathHelper.lerp(t, a, b);
        Assertions.assertEquals(10.0, result, 0.0001, "When t=0, result should be 'a'");

        // Test case 2: t = 1 (Should return 'b')
        t = 1.0;
        result = MathHelper.lerp(t, a, b);
        Assertions.assertEquals(20.0, result, 0.0001, "When t=1, result should be 'b'");

        // Test case 3: t = 0.5 (Should return the midpoint of 'a' and 'b')
        t = 0.5;
        result = MathHelper.lerp(t, a, b);
        Assertions.assertEquals(15.0, result, 0.0001, "When t=0.5, result should be the midpoint between 'a' and 'b'");

        // Test case 4: t < 0 (Extrapolation below 'a')
        t = -0.5;
        result = MathHelper.lerp(t, a, b);
        Assertions.assertEquals(5.0, result, 0.0001, "When t=-0.5, result should be an extrapolation below 'a'");

        // Test case 5: t > 1 (Extrapolation beyond 'b')
        t = 1.5;
        result = MathHelper.lerp(t, a, b);
        Assertions.assertEquals(25.0, result, 0.0001, "When t=1.5, result should be an extrapolation beyond 'b'");

        // Test case 6: t = 0 (a = b) (When 'a' equals 'b', result should always equal 'a' or 'b')
        t = 0.5;
        a = 10.0;
        b = 10.0;
        result = MathHelper.lerp(t, a, b);
        Assertions.assertEquals(10.0, result, 0.0001, "When a=b, result should always be 'a' or 'b'");
    }
}