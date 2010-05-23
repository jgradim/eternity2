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

package org.alcibiade.eternity.editor.solver.path;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.QuadModel;
import org.alcibiade.eternity.editor.solver.collection.LightestSelector;
import org.alcibiade.eternity.editor.solver.collection.Selector;

public class HumanPathMkII extends HumanPathMkI {

	@Override
	public int getNextPathIndex(GridModel pieces, GridModel grid) {
		int gsize = grid.getSize();
		int positions = gsize * gsize;

		int[] matches = new int[positions];
		for (int i = 0; i < positions; i++) {
			QuadModel missingQuad = grid.getMissingQuad(i);
			matches[i] = getMatchingQuads(pieces, missingQuad);
		}

		// int[] blurred = matches;
		int[] blurred = blur(grid, matches);
		// blurred = blur(grid, blurred);

		Selector<Integer> possibleIndices = new LightestSelector<Integer>();

		for (int i = 0; i < positions; i++) {
			QuadModel quad = grid.getQuad(i);
			if (quad.isClear()) {
				possibleIndices.put(i, blurred[i]);
			}
		}

		int result = possibleIndices.getSelected();

		return result;
	}

	private int[] blur(GridModel grid, int[] matches) {
		int gsize = grid.getSize();
		int positions = gsize * gsize;
		int[] blurred = new int[positions];

		for (int i = 0; i < positions; i++) {
			blurred[i] = gsize * matches[i];
			int nval = 1;

			for (int d = 0; d < 4; d++) {
				int nIndex = grid.computeNeighborIndex(i, d);
				if (nIndex >= 0) {
					blurred[i] += matches[nIndex];
					nval += 1;
				}
			}

			blurred[i] /= nval;
		}

		return blurred;
	}

}
