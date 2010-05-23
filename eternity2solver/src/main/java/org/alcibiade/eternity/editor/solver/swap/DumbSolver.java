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

package org.alcibiade.eternity.editor.solver.swap;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;

public class DumbSolver extends SwappingSolver {

	public DumbSolver(GridModel grid, GridModel solution_grid, ClusterManager clusterManager) {
		super(grid, solution_grid, clusterManager);
	}

	@Override
	public String getSolverName() {
		return "Dumb Solver $Revision: 206 $";
	}

	@Override
	protected void computeWeights(GridModel grid, WeightMatrix weights) {
		int positions = grid.getSize() * grid.getSize();

		for (int i = 0; i < positions; i++) {
			weights.setWeight(i, 1);
		}
	}

	@Override
	protected boolean validateSwap(GridModel grid, SwapCoords coords) {
		return !grid.getQuad(coords.getCoordA()).isLocked()
				&& !grid.getQuad(coords.getCoordB()).isLocked();
	}

	@Override
	protected void adaptWeights(GridModel grid, WeightMatrix weights, int firstSelection) {
		weights.setWeight(firstSelection, 0);
	}

	@Override
	protected int computeSwapCoord(GridModel grid, WeightMatrix weightMatrix) {
		return weightMatrix.pick();
	}
}
