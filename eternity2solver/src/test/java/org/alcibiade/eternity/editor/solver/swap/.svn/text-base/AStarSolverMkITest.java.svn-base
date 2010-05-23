package org.alcibiade.eternity.editor.solver.swap;

import static org.junit.Assert.assertTrue;

import org.alcibiade.eternity.editor.log.NullLog;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.operation.GridFiller;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.junit.Test;

public class AStarSolverMkITest {

	@Test
	public void testSolving() {
		GridModel grid = new GridModel(4);

		GridFiller filler = new GridFiller(grid);
		filler.fillRandom(grid.getSize() * 3 / 2);
		grid.shuffle();

		ClusterManager cluster = new ClusterManager(new NullLog());
		GridModel solutionGrid = new GridModel();

		AStarSolverMkI solver = new AStarSolverMkI(grid, solutionGrid, cluster);
		solver.run();
		assertTrue(solutionGrid.isComplete());
	}
}
