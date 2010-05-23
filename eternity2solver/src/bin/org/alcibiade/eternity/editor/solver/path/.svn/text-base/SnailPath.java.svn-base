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

public class SnailPath extends StaticPathProvider {

	@Override
	protected int[] buildPath(int gsize) {
		int positions = gsize * gsize;
		int[] result = new int[positions];
		int ix = 0;

		for (int rad = gsize; rad > 0; rad -= 2) {
			int offs = (gsize - rad) / 2;

			// Top side

			for (int i = 0; i < rad; i++) {
				int x = offs + i;
				int y = offs;
				result[ix++] = x + gsize * y;
			}

			// Right side

			for (int i = 1; i < rad; i++) {
				int x = offs + rad - 1;
				int y = offs + i;
				result[ix++] = x + gsize * y;
			}

			// Bottom side

			for (int i = 1; i < rad; i++) {
				int x = offs + rad - 1 - i;
				int y = offs + rad - 1;
				result[ix++] = x + gsize * y;
			}

			// Left side

			for (int i = 1; i < rad - 1; i++) {
				int x = offs;
				int y = offs + rad - 1 - i;
				result[ix++] = x + gsize * y;
			}
		}

		return result;
	}

}
