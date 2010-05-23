package org.alcibiade.eternity.editor.solver.backtracking;

import static org.junit.Assert.assertTrue;

import org.alcibiade.eternity.editor.log.NullLog;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.operation.GridFiller;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;
import org.alcibiade.eternity.editor.solver.path.LinearPath;
import org.alcibiade.eternity.editor.solver.path.PathProvider;
import org.junit.Test;

public class IterativePathSolverMkITest {

	@Test
	public void testSolver() throws InterruptedException {
		GridModel problem = new GridModel(3);

		GridFiller filler = new GridFiller(problem);
		filler.fillRandom(3);

		problem.shuffle();

		GridModel solution = new GridModel();
		ClusterManager clusterManager = new ClusterManager(new NullLog());
		PathProvider path = new LinearPath();

		EternitySolver solver = new IterativePathSolverMkI(problem, solution, clusterManager, path);
		solver.start();
		solver.join();

		assertTrue(solution.isComplete());
	}
}
