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

/**
 * Extends MkV weight adaptation step by increasing tile weight according to the
 * duration since they were last swapped.
 */
public class WeightedRandomMkVI extends WeightedRandomMkV {

	private WeightMatrix age;

	public WeightedRandomMkVI(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager) {
		super(grid, solutionGrid, clusterManager);
		age = new WeightMatrix(grid.getSize());
	}

	@Override
	protected void computeWeights(GridModel grid, WeightMatrix weights) {
		super.computeWeights(grid, weights);

		int positions = grid.getSize() * grid.getSize();
		if (iterations % (1 + positions / 2) == 0) {
			age.addToAll(1);
		}

		weights.addIfNotZero(age);
	}

	@Override
	public String getSolverName() {
		return "WeightedRandomMkVI Solver $Revision: 206 $";
	}

	@Override
	protected boolean validateSwap(GridModel grid, SwapCoords coords) {
		age.setWeight(coords.getCoordA(), 0);
		age.setWeight(coords.getCoordB(), 0);

		return super.validateSwap(grid, coords);
	}

}
