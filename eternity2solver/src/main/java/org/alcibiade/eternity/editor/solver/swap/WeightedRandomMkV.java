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
import org.alcibiade.eternity.editor.model.QuadModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;

/**
 * Extends MkII weight adaptation step by ponderating pieces according to their
 * respective fitness in the selected spot. This tends to make the MkIII
 * validation obsolete, that's why it extends MkII.
 */
public class WeightedRandomMkV extends WeightedRandomMkII {

	@SuppressWarnings("unused")
	private void weightStats(String message, WeightMatrix weights) {
		long weightMax = Long.MIN_VALUE;
		long weightMin = Long.MAX_VALUE;
		for (long weight : weights) {
			if (weight < weightMin) {
				weightMin = weight;
			}
			if (weight > weightMax) {
				weightMax = weight;
			}
		}

		clusterManager.logMessage("%s: Weight values %d -> %d", message, weightMin, weightMax);
	}

	public WeightedRandomMkV(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager) {
		super(grid, solutionGrid, clusterManager);
	}

	@Override
	public String getSolverName() {
		return "WeightedRandomMkV Solver $Revision: 206 $";
	}

	@Override
	protected void adaptWeights(GridModel grid, WeightMatrix weights, int firstSelection) {
		super.adaptWeights(grid, weights, firstSelection);

		int positions = grid.getSize() * grid.getSize();
		QuadModel missing = grid.getMissingQuad(firstSelection);

		for (int index = 0; index < positions; index++) {
			QuadModel quad = grid.getQuad(index);
			int degrees = missing.matchDegrees(quad);
			weights.setWeight(index, weights.getWeight(index) * (1 + degrees * degrees));
		}
	}
}
