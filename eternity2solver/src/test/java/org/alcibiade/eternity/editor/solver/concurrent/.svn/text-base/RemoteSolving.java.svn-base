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

package org.alcibiade.eternity.editor.solver.concurrent;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.alcibiade.eternity.editor.log.ConsoleLog;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.operation.GridFiller;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;
import org.alcibiade.eternity.editor.solver.SolverFactory;
import org.gridgain.grid.Grid;
import org.gridgain.grid.GridFactory;
import org.junit.Test;

public class RemoteSolving {

	@Test
	public void testRemovteSolving() throws Exception {
		GridModel gridModel = new GridModel(3);
		GridFiller filler = new GridFiller(gridModel);

		// Local test

		// filler.fillRandom(3);
		// gridModel.shuffle();
		//
		// RemoteRunner runner = new RemoteRunner(gridModel);
		// assertEquals(1, runner.call());

		// Remote test
		if (System.getProperty("GRIDGAIN_HOME") == null) {
			System.out.println("Variable GRIDGAIN_HOME not found, skipping remote solving test");
		} else {

			filler.fillRandom(3);
			gridModel.shuffle();

			Grid grid = GridFactory.start();
			ExecutorService executor = grid.newGridExecutorService();
			Future<Integer> futureResult = executor.submit(new RemoteRunner(gridModel));
			executor.awaitTermination(10, TimeUnit.SECONDS);
			GridFactory.stop(true);

			assertEquals(new Integer(1), futureResult.get());
		}
	}

	private class RemoteRunner implements Callable<Integer>, Serializable {
		private static final long serialVersionUID = 1L;

		private GridModel gridModel;

		public RemoteRunner(GridModel gridModel) {
			this.gridModel = gridModel;
		}

		public Integer call() throws Exception {
			try {
				ClusterManager clusterManager = new ClusterManager(new ConsoleLog());

				clusterManager.logMessage("Grid:\n" + gridModel.toQuadString());

				GridModel solution = new GridModel();
				EternitySolver solver = SolverFactory.createSolver(SolverFactory.LABEL_ITPATHMKI,
						gridModel, solution, clusterManager);
				solver.start();
				solver.join();

				clusterManager.logMessage("Solution:\n" + solution.toQuadString());
				return clusterManager.isSolutionFound() ? 1 : 0;
			} catch (AssertionError e) {
				e.printStackTrace();
				return -1;
			}
		}
	}
}
