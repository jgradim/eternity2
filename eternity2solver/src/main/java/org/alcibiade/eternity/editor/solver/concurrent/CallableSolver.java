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

import java.io.Serializable;
import java.util.concurrent.Callable;

import org.alcibiade.eternity.editor.log.ConsoleLog;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;
import org.alcibiade.eternity.editor.solver.SolverFactory;

public class CallableSolver implements Callable<ClusterManager>, Serializable {
	private static final long serialVersionUID = 1L;

	private String solverName;

	private GridModel grid;

	public CallableSolver(String solverName, GridModel problem) {
		this.solverName = solverName;
		this.grid = problem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	public ClusterManager call() throws Exception {
		ClusterManager clusterManager = new ClusterManager(new ConsoleLog());
		GridModel solution = new GridModel();
		EternitySolver solver = SolverFactory.createSolver(solverName, grid, solution,
				clusterManager);

		clusterManager.logMessage("Starting " + solver);
		solver.start();
		solver.join(5 * 1000);

		if (solver.isAlive()) {
			clusterManager.logMessage("Solver timeout reached, interrupting " + solver);
			solver.interrupt();
			solver.join();
		}

		return clusterManager;
	}
}
