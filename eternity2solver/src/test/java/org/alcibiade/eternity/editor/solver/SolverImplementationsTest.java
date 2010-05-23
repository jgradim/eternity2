/* This file is part of Eternity II Editor.
 * 
 * Eternity II Editor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Eternity II Editor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Eternity II Editor.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Eternity II Editor project is hosted on SourceForge:
 * http://sourceforge.net/projects/eternityii/
 * and maintained by Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */

package org.alcibiade.eternity.editor.solver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.alcibiade.eternity.editor.log.NullLog;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.operation.GridFiller;
import org.alcibiade.eternity.editor.solver.path.PathFactory;
import org.alcibiade.eternity.editor.solver.path.PathProvider;
import org.alcibiade.eternity.editor.solver.path.UnknownPathException;
import org.junit.Test;

public class SolverImplementationsTest {

	@Test
	public void testAllSolvers() throws UnknownSolverException, UnknownPathException {
		for (String name : SolverFactory.getAvailableSolvers()) {
			if (name != SolverFactory.LABEL_SWAPDUMB) {
				testSolver(name);
			}
		}
	}

	@Test
	public void testAllSolversWithLockedPieces() throws UnknownSolverException,
			UnknownPathException {
		for (String name : SolverFactory.getAvailableSolvers()) {
			if (name != SolverFactory.LABEL_SWAPDUMB) {
				testSolverWithLockedPieces(name);
			}
		}
	}

	private void testSolver(String name) throws UnknownSolverException, UnknownPathException {
		GridModel problem = new GridModel(5);
		GridFiller filler = new GridFiller(problem);
		filler.fillRandom(8);
		problem.shuffle();
		problem.setReadOnly(true);

		solve(name, problem);
	}

	private void testSolverWithLockedPieces(String name) throws UnknownSolverException,
			UnknownPathException {
		Set<Integer> locks = new HashSet<Integer>();
		locks.add(2);
		locks.add(3);
		locks.add(18);
		locks.add(19);
		locks.add(24);

		GridModel problem = new GridModel(5);
		GridFiller filler = new GridFiller(problem);
		filler.fillRandom(8);

		for (int lockIndex : locks) {
			problem.getQuad(lockIndex).setLocked(true);
		}

		problem.shuffle();
		problem.setReadOnly(true);

		Set<GridModel> solutions = solve(name, problem);

		for (GridModel solution : solutions) {
			for (int lockIndex : locks) {
				assertEquals(problem.getQuad(lockIndex), solution.getQuad(lockIndex));
			}
		}
	}

	private Set<GridModel> solve(String name, GridModel problem) throws UnknownSolverException,
			UnknownPathException {
		Set<GridModel> resultGrids = new HashSet<GridModel>();
		if (SolverFactory.isSolverPathSensitive(name)) {
			resultGrids.add(solve(name, problem, PathFactory.createPath(PathFactory.LABEL_LINEAR)));
			resultGrids.add(solve(name, problem, PathFactory.createPath(PathFactory.LABEL_RSNAIL)));
		} else {
			resultGrids.add(solve(name, problem, null));
		}

		return resultGrids;
	}

	private GridModel solve(String name, GridModel problem, PathProvider path)
			throws UnknownSolverException {
		GridModel solution = new GridModel(5);
		ClusterManager clusterManager = new ClusterManager(new NullLog());
		EternitySolver solver = SolverFactory.createSolver(name, problem, solution, clusterManager,
				path);

		solver.run();

		assertTrue(clusterManager.isSolutionFound());

		return solution;
	}

}
