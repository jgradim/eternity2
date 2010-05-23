package org.alcibiade.eternity.editor.solver.backtracking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NeighborhoodTest {

	@Test
	public void testNeighborhoodBuild() {
		Neighborhood n = new Neighborhood(3, 2);
		assertEquals(6, n.getPositions());
		assertEquals(3, n.getWidth());
		assertEquals(2, n.getHeight());
	}

	@Test
	public void testNeighborhoodCopy() {
		Neighborhood n1 = new Neighborhood(3, 2);
		Neighborhood n2 = new Neighborhood(n1);
		assertEquals(n1, n2);
		assertEquals(n1.hashCode(), n2.hashCode());
	}
}
