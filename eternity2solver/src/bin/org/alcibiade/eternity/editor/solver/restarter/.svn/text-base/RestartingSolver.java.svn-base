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

package org.alcibiade.eternity.editor.solver.restarter;

import java.util.Random;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;
import org.alcibiade.eternity.editor.solver.RandomFactory;
import org.alcibiade.eternity.editor.solver.SolverFactory;
import org.alcibiade.eternity.editor.solver.UnknownSolverException;
import org.alcibiade.eternity.editor.solver.path.PathProvider;

public class RestartingSolver extends EternitySolver {

	private String solverName;
	private PathProvider path;
	private GridModel grid;
	private GridModel solution;
	private Long delayMillis;

	private EternitySolver solver;

	public RestartingSolver(String solverName, PathProvider path, GridModel grid,
			GridModel solution, ClusterManager clusterManager, long delayMillis) {
		super(clusterManager);
		this.solverName = solverName;
		this.grid = grid;
		this.solution = solution;
		this.delayMillis = delayMillis;
		this.path = path;

		this.setName(getSolverName() + "-" + getId());
	}

	@Override
	public void run() {
		try {
			Random random = RandomFactory.getRandom();
			GridModel grid = this.grid.clone();

			do {
				try {
					grid.shuffle();
					solver = SolverFactory.createSolver(solverName, grid, solution, clusterManager,
							path);

					clusterManager.logMessage("Starting " + solver);
					solver.start();
					solver.join(delayMillis + random.nextInt(1000));

					if (solver.isAlive()) {
						clusterManager.logMessage("Solver timeout reached, interrupting " + solver);
						solver.interrupt();
						solver.join();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (!interrupted() && !clusterManager.isSolutionFound());
		} catch (UnknownSolverException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void interrupt() {
		super.interrupt();
		solver.interrupt();
	}

	@Override
	public String getSolverName() {
		return solverName + " (Restarting)";
	}

	@Override
	public long getIterations() {
		return solver.getIterations();
	}
}
