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

package org.alcibiade.eternity.editor.solver.pipeline;

import org.alcibiade.eternity.editor.log.NullLog;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;
import org.alcibiade.eternity.editor.solver.backtracking.IterativePathSolverMkIII;
import org.alcibiade.eternity.editor.solver.path.LinearPath;
import org.alcibiade.eternity.editor.solver.swap.WeightedRandomMkV;

public class PipelineSolver extends EternitySolver {
	private static final long SOLVER1_DELAY = 60 * 1000;

	private GridModel pieces;
	private GridModel solution;

	private EternitySolver runningSolver;

	public PipelineSolver(GridModel pieces, GridModel solution, ClusterManager clusterManager) {
		super(clusterManager);
		this.pieces = pieces;
		this.solution = solution;
	}

	@Override
	public void run() {
		ClusterManager localManager = new ClusterManager(new NullLog());

		try {
			if (!isInterrupted()) {
				step1(localManager);
			}

			clusterManager.logMessage("Backtracking ended with a score of %d", localManager
					.getBestScore());

			if (!isInterrupted()) {
				step2(localManager);
			}
		} catch (InterruptedException e) {
			clusterManager.logMessage(e.getLocalizedMessage());
		}
	}

	private void step1(ClusterManager localManager) throws InterruptedException {
		EternitySolver solver = new IterativePathSolverMkIII(pieces, solution, localManager,
				new LinearPath());

		runningSolver = solver;
		solver.start();
		solver.join(SOLVER1_DELAY);
		solver.interrupt();
		solver.join();
	}

	private void step2(ClusterManager localManager) throws InterruptedException {
		EternitySolver solver = new WeightedRandomMkV(localManager.getBestSolution(), solution,
				getClusterManager());

		runningSolver = solver;
		solver.start();
		solver.join();
	}

	@Override
	public void interrupt() {
		super.interrupt();
		if (runningSolver != null) {
			runningSolver.interrupt();
		}
	}

	@Override
	public long getIterations() {
		return 0;
	}

	@Override
	public String getSolverName() {
		return "Pipeline{...}";
	}

}
