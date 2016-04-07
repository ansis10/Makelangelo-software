package com.marginallyclever.basictypes;

public class Point2D {
	public float x, y;

	public Point2D(Point2D p) {
		set(p.x, p.y);
	}

	public Point2D() {
		set(0, 0);
	}

	public Point2D(float xx, float yy) {
		set(xx, yy);
	}

	public Point2D(double xx, double yy) {
		set((float) xx, (float) yy);
	}

	public void set(float xx, float yy) {
		x = xx;
		y = yy;
	}

	public void normalize() {
		scale(1.0f/lengthSquared());
	}
	
	public void scale(float s) {
		x*=s;
		y*=s;
	}


	public float lengthSquared() {
		return x*x+y*y;
	}

	public float length() {
		return (float)Math.sqrt(lengthSquared());
	}
}

/**
 * This file is part of Makelangelo.
 * <p>
 * Makelangelo is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * Makelangelo is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with Makelangelo.  If not, see <http://www.gnu.org/licenses/>.
 */
