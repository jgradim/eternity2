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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.alcibiade.eternity.editor.solver.RandomFactory;

public class RandomPath extends StaticPathProvider {

	private long seed = System.currentTimeMillis();

	@Override
	protected int[] buildPath(int gsize) {
		int positions = gsize * gsize;
		int[] result = new int[positions];

		List<Integer> pool = new ArrayList<Integer>();
		Random rand = RandomFactory.getRandom(seed);

		for (int i = 0; i < positions; i++) {
			pool.add(i);
		}

		int offset = 0;
		while (pool.size() > 0) {
			int r = rand.nextInt(pool.size());
			Integer ix = pool.get(r);
			pool.remove(r);
			result[offset++] = ix;
		}

		return result;
	}

}
