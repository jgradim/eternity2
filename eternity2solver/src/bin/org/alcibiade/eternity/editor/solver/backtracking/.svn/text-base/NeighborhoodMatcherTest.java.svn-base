package org.alcibiade.eternity.editor.solver.backtracking;

import static org.junit.Assert.assertTrue;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.operation.GridFiller;
import org.junit.Test;

public class NeighborhoodMatcherTest {

	@Test
	public void testNeighborhoodComputation() {
		GridModel grid = new GridModel(4);

		GridFiller filler = new GridFiller(grid);
		do {
			filler.fillRandom(grid.getSize() * 3 / 2);
		} while (grid.getPatternStats().getDuplicates() > 0);

		grid.shuffle();

		NeighborhoodMatcher matcher = new NeighborhoodMatcher();
		GridNeighborhoods gn = matcher.computeNeighborHoods(grid);

		assertTrue(!gn.getCorners().isEmpty());
		assertTrue(!gn.getEdges().isEmpty());
		assertTrue(gn.getInners().isEmpty());
	}

}
