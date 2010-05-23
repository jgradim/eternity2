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
 * Extends MkII to add a validation step which accepts only a subset of bad
 * moves. It does not reject them all to avoid being locked in local best
 * solutions.
 */
public class WeightedRandomMkIII extends WeightedRandomMkII {

	public WeightedRandomMkIII(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager) {
		super(grid, solutionGrid, clusterManager);
	}

	@Override
	public String getSolverName() {
		return "WeightedRandomMkIII Solver $Revision: 206 $";
	}

	@Override
	protected boolean validateSwap(GridModel grid, SwapCoords coords) {
		QuadModel quadA = grid.getQuad(coords.getCoordA());
		QuadModel missA = grid.getMissingQuad(coords.getCoordA());

		QuadModel quadB = grid.getQuad(coords.getCoordB());
		QuadModel missB = grid.getMissingQuad(coords.getCoordB());

		int matchdelta = 0;
		matchdelta += quadA.matchDegrees(missB) - quadA.matchDegrees(missA);
		matchdelta += quadB.matchDegrees(missA) - quadB.matchDegrees(missB);

		boolean validated = matchdelta >= 0 ? true : rand.nextBoolean();
		// if (getIterations() % 2000 == 0) {
		// System.out.println(String.format("Delta: %+1d -> %-5s", matchdelta,
		// validated));
		// }

		return validated;
	}

}
