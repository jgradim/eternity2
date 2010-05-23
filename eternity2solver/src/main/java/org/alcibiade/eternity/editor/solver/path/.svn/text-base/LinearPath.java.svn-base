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

public class LinearPath implements PathProvider {

	@Override
	public int getNextPathIndex(GridModel pieces, GridModel grid) {
		int result = -1;
		int gsize = grid.getSize();
		int positions = gsize * gsize;

		for (int i = 0; result < 0 && i < positions; i++) {
			if (grid.getQuad(i).isClear()) {
				result = i;
			}
		}

		assert result != -1;
		return result;
	}

}
