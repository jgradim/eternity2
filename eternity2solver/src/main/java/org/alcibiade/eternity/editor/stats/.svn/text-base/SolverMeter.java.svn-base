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

package org.alcibiade.eternity.editor.stats;

import java.io.Serializable;
import java.util.concurrent.Callable;

import org.alcibiade.eternity.editor.log.NullLog;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;
import org.alcibiade.eternity.editor.solver.SolverFactory;
import org.alcibiade.eternity.editor.solver.path.PathFactory;
import org.alcibiade.eternity.editor.solver.path.PathProvider;

public class SolverMeter implements Callable<Collector>, Serializable {
	private static final long serialVersionUID = 1L;

	private String solverName;
	private String pathName;
	private GridModel grid;

	public SolverMeter(String solverName, String pathName, GridModel grid) {
		this.solverName = solverName;
		this.pathName = pathName;
		this.grid = grid;
	}

	public Collector call() throws Exception {
		Collector collector = new Collector(solverName);

		GridModel solution = new GridModel();
		PathProvider path = null;

		if (pathName != null) {
			path = PathFactory.createPath(pathName);
		}

		EternitySolver solver = SolverFactory.createSolver(solverName, grid, solution,
				new ClusterManager(new NullLog()), path);

		long tsStart = System.currentTimeMillis();

		solver.run();

		long ts_end = System.currentTimeMillis();

		if (!solution.isComplete()) {
			throw new IllegalStateException("Solver " + solver + " did not succeed");
		}

		long duration = ts_end - tsStart;
		collector.recordExecution(duration, solver.getIterations());

		return collector;
	}
}
