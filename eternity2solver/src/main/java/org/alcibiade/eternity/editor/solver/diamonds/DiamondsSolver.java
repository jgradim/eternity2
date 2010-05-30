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

package org.alcibiade.eternity.editor.solver.diamonds;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;

public class DiamondsSolver extends EternitySolver {

	private GridModel problemGrid;
	private GridModel solutionGrid;
	private GridModel diamondsOverlay;

	private long iterationsLimit = -1;

	protected long iterations = 0;

	public SwappingSolver(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager) {
		super(clusterManager);
		this.problemGrid = grid;
		this.solutionGrid = solutionGrid;
		problemGrid.copyTo(solutionGrid);
	}

	@Override
	public long getIterations() {
		return iterations;
	}

	public void setIterationsLimit(long limit) {
		iterationsLimit = limit;
	}

	@Override
	public void run() {
		notifyStart();
		clusterManager.showStartMessage();
		notifyEnd(true);

		/*boolean solved = clusterManager.submitSolution(solutionGrid);

		while (!solved && !interrupted && (iterationsLimit == -1 || iterations < iterationsLimit)) {
			solve(solutionGrid);

			solved = clusterManager.submitSolution(solutionGrid);

			if (slowmotion) {
				try {
					Thread.sleep(SLOWMOTION_DELAY);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		if (solved) {
			clusterManager.showStats(iterations);
		}*/

		notifyEnd(solved);
	}

	private void solve(int line, int column) {
	  iterations++;
	}
}
