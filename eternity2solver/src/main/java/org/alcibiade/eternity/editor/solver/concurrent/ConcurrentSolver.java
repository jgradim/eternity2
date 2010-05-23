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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;
import org.alcibiade.eternity.editor.solver.RandomFactory;
import org.gridgain.grid.Grid;

public class ConcurrentSolver extends EternitySolver {

	private Collection<String> solverNames;
	private GridModel grid;

	private EternitySolver solver;
	private Random rand;
	private Grid gridComputer;

	public ConcurrentSolver(Collection<String> solverNames, GridModel grid, GridModel solution,
			ClusterManager clusterManager, Grid gridComputer) {
		super(clusterManager);
		this.solverNames = solverNames;
		this.grid = grid;

		this.setName(getSolverName() + "-" + getId());
		this.rand = RandomFactory.getRandom();
		this.gridComputer = gridComputer;
	}

	private String pickSolverName() {
		String result = null;
		int index = rand.nextInt(solverNames.size());

		Iterator<String> itNames = solverNames.iterator();
		while (index >= 0 && itNames.hasNext()) {
			result = itNames.next();
		}

		assert result != null;

		return result;
	}

	@Override
	public void run() {
		do {
			Collection<Callable<ClusterManager>> solvers = new HashSet<Callable<ClusterManager>>();

			for (int i = 0; i < 1; i++) {
				String solverName = pickSolverName();
				CallableSolver solver = new CallableSolver(solverName, grid);
				solvers.add(solver);
			}

			try {
				// ExecutorService executor =
				// Executors.newFixedThreadPool(Runtime
				// .getRuntime().availableProcessors());
				ExecutorService executor = gridComputer.newGridExecutorService();
				List<Future<ClusterManager>> futures = executor.invokeAll(solvers);

				for (Future<ClusterManager> clusterManager : futures) {
					this.clusterManager.submitSolution(clusterManager.get().getBestSolution());
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

		} while (!interrupted() && !clusterManager.isSolutionFound());
	}

	@Override
	public void interrupt() {
		super.interrupt();
		solver.interrupt();
	}

	@Override
	public String getSolverName() {
		return "ConcurrentSolver";
	}

	@Override
	public long getIterations() {
		return solver.getIterations();
	}
}
