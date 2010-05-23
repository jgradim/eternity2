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

package org.alcibiade.eternity.editor.solver.backtracking;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterListener;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;

public class BlockSolverMkI extends EternitySolver implements ClusterListener {

	protected long iterations = 0;

	private GridModel solutionGrid;
	private GridModel problemGrid;

	public BlockSolverMkI(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager) {
		super(clusterManager);
		this.problemGrid = grid;
		this.solutionGrid = solutionGrid;
	}

	@Override
	public String getSolverName() {
		return "Block Solver MkI $Revision: 245 $";
	}

	@Override
	public long getIterations() {
		return iterations;
	}

	@Override
	public void run() {
		notifyStart();
		clusterManager.showStartMessage();

		boolean solved = solve();

		if (solved) {
			clusterManager.submitSolution(solutionGrid);
			clusterManager.showStats(iterations);
		}

		notifyEnd(solved);
	}

	private boolean solve() {
		NeighborhoodMatcher matcher = new NeighborhoodMatcher();
		matcher.computeNeighborHoods(problemGrid);

		return solutionGrid.isComplete();
	}

	@Override
	public void bestSolutionUpdated(int bestScore) {
		// It does not matter here
	}

}
