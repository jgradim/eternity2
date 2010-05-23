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

public abstract class StaticPathProvider implements PathProvider {

	@Override
	public int getNextPathIndex(GridModel pieces, GridModel grid) {
		int[] path = buildPath(grid.getSize());
		int result = -1;

		for (int i = 0; result < 0 && i < path.length; i++) {
			int pathIndex = path[i];
			QuadModel quad = grid.getQuad(pathIndex);

			if (quad.isClear()) {
				result = pathIndex;
			}
		}

		assert result >= 0;
		assert result < path.length;
		return result;
	}

	protected abstract int[] buildPath(int gsize);
}
