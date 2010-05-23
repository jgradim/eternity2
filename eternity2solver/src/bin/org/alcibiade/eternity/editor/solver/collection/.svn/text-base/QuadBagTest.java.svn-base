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

package org.alcibiade.eternity.editor.solver.collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.QuadModel;
import org.alcibiade.eternity.editor.model.operation.GridFiller;
import org.junit.Test;

public class QuadBagTest {

	@Test
	public void testBagFromGrid() {
		GridModel problem = new GridModel(4);

		GridFiller filler = new GridFiller(problem);
		filler.fillRandom(6);
		problem.shuffle();

		QuadBag bag = new QuadBag(problem);
		assertEquals(4 * 4, bag.size());

		for (QuadModel quad : problem) {
			assertTrue(bag.contains(quad));
		}
	}

	@Test
	public void testBagFromCollection() {
		GridModel problem = new GridModel(4);

		GridFiller filler = new GridFiller(problem);
		filler.fillRandom(6);
		problem.shuffle();

		QuadBag bag = new QuadBag(problem);
		QuadBag bag2 = new QuadBag(bag);
		assertEquals(4 * 4, bag2.size());

		for (QuadModel quad : problem) {
			assertTrue(bag2.contains(quad));
		}
	}
}
