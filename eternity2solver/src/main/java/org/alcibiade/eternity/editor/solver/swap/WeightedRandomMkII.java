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
import org.alcibiade.eternity.editor.model.Pattern;
import org.alcibiade.eternity.editor.model.QuadModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;

/**
 * Extension of MkI.
 * 
 * Uses a specific weighting function to boost border frame patterns. Locks are
 * handled at weighting computation time and border pieces are handled at weight
 * adjustment time.
 * 
 */
public class WeightedRandomMkII extends WeightedRandomMkI {

	public WeightedRandomMkII(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager) {
		super(grid, solutionGrid, clusterManager);
	}

	@Override
	public String getSolverName() {
		return "WeightedRandomMkII Solver $Revision: 248 $";
	}

	@Override
	protected void computeWeights(GridModel grid, WeightMatrix weights) {
		int positions = grid.getSize() * grid.getSize();

		for (int i = 0; i < positions; i++) {
			if (grid.getQuad(i).isLocked()) {
				weights.setWeight(i, 0);
			} else {
				long w = evaluateWeight(grid, i);
				w = (long) Math.pow(w, weightFactor);
				weights.setWeight(i, w);
			}
		}

		// In a 3x3 grid, the center can't be swapped if it is in place
		if (positions == 9 && grid.getQuad(1, 1).countDefaultPattern() == 0) {
			weights.setWeight(1, 1, 0);
		}
	}

	@Override
	protected void adaptWeights(GridModel grid, WeightMatrix weights, int firstSelection) {
		int positions = grid.getSize() * grid.getSize();
		int borders = grid.countExternalBorders(firstSelection);

		for (int index = 0; index < positions; index++) {
			if (index == firstSelection) {
				weights.setWeight(index, 0);
			} else {
				int defaults = grid.getQuad(index).countDefaultPattern();
				if (borders != defaults) {
					weights.setWeight(index, 0);
				}
			}
		}
	}

	@Override
	protected boolean validateSwap(GridModel grid, SwapCoords coords) {
		return true;
	}

	private int evaluateWeight(GridModel grid, int i) {
		assert i >= 0 && i < grid.getQuads().size();

		Pattern defaultpat = Pattern.getDefaultPattern();
		int weight = 1;

		QuadModel quad = grid.getQuad(i);

		for (int direction = 0; direction < 4; direction++) {
			int left = (direction + 3) % 4;
			int right = (direction + 1) % 4;
			int opposite = (direction + 2) % 4;
			int localW = 0;

			QuadModel neighbor = grid.getNeighbor(i, direction);

			if (neighbor == null) {
				if (quad.getPattern(direction) != defaultpat) {
					localW += 1;
				}
			} else {
				if (quad.getPattern(direction) != neighbor.getPattern(opposite)) {
					if (neighbor.isLocked()) {
						localW += 4;
					} else {
						localW += 1;
					}
				}
			}

			if (quad.getPattern(left) == defaultpat || quad.getPattern(right) == defaultpat) {
				localW *= 2;
			}

			weight += localW;
		}

		return weight;
	}

}
