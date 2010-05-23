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
 * Swap implementation with basic weighting using GridModel.countPairs to weight
 * quads.
 * 
 * Locks and border pieces are handled in the move validation step.
 */
public class WeightedRandomMkI extends SwappingSolver {

	public static final double WEIGHTFACTOR_VARIABLE = 0.915 * 0.64;
	public static final double WEIGHTFACTOR_FIXED = 0.915 * 2.6;

	protected double weightFactor;

	public WeightedRandomMkI(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager) {
		this(grid, solutionGrid, clusterManager, WEIGHTFACTOR_FIXED + WEIGHTFACTOR_VARIABLE
				* grid.getSize());
	}

	public WeightedRandomMkI(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager,
			double weightFactor) {
		super(grid, solutionGrid, clusterManager);
		this.weightFactor = weightFactor;
	}

	public void setWeightFactor(double weightFactor) {
		clusterManager.logMessage("Weight factor set to %.3f", weightFactor);
		this.weightFactor = weightFactor;
	}

	public double getWeightFactor() {
		return weightFactor;
	}

	@Override
	public String getSolverName() {
		return "WeightedRandomMkI Solver $Revision: 249 $";
	}

	@Override
	protected void computeWeights(GridModel grid, WeightMatrix weights) {
		int positions = grid.getSize() * grid.getSize();

		for (int i = 0; i < positions; i++) {
			if (grid.getQuad(i).isLocked()) {
				weights.setWeight(i, 0);
			} else {
				long w = 5 - grid.countPairs(i);
				w = (long) Math.pow(w, weightFactor);
				weights.setWeight(i, w);
			}
		}
	}

	@Override
	protected boolean validateSwap(GridModel grid, SwapCoords coords) {
		int pos_a = coords.getCoordA();
		int pos_b = coords.getCoordB();
		int gsize = grid.getSize();
		Pattern defaultpat = Pattern.getDefaultPattern();

		QuadModel quad_a = grid.getQuad(pos_a);
		QuadModel quad_b = grid.getQuad(pos_b);

		// Count expected border patterns according to offsets

		boolean vborder_a = pos_a < gsize || pos_a >= gsize * (gsize - 1);
		boolean hborder_a = (pos_a % gsize == 0) || (pos_a % gsize == gsize - 1);
		int defsides_a = (vborder_a ? 1 : 0) + (hborder_a ? 1 : 0);
		int defpat_a = quad_a.countPattern(defaultpat);

		boolean vborder_b = pos_b < gsize || pos_b >= gsize * (gsize - 1);
		boolean hborder_b = (pos_b % gsize == 0) || (pos_b % gsize == gsize - 1);
		int defsides_b = (vborder_b ? 1 : 0) + (hborder_b ? 1 : 0);
		int defpat_b = quad_b.countPattern(defaultpat);

		// Check whether we actually swap
		// Informations gathered (X in [a,b]):
		// pos_X: grid offset
		// quad_X: QuadModel instance
		// defpat_X: Number of border patterns in quad_X
		// defsides_X: Number of sides that should be border at pos_X

		boolean do_swap = false;

		if (!quad_a.isLocked() && !quad_b.isLocked()) {
			if (defpat_a == defpat_b || defsides_a == defsides_b) {
				do_swap = true;
			} else if (defpat_a == defsides_b || defpat_b == defsides_a) {
				do_swap = true;
			}
		}

		return do_swap;
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
