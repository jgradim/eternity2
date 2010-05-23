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

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class WeightedSetTest {
	@Test
	public void testPick() {
		Map<Character, Integer> pickupResults = new HashMap<Character, Integer>();
		pickupResults.put('A', 0);
		pickupResults.put('B', 0);
		pickupResults.put('C', 0);

		WeightedSet<Character> characters = new WeightedSet<Character>();
		characters.put('A', 1);
		characters.put('B', 0);
		characters.put('C', 2);

		for (int i = 0; i < 1000; i++) {
			char c = characters.pickRandom();
			pickupResults.put(c, pickupResults.get(c) + 1);
		}

		assertEquals('C', (char) characters.getFirst());
		assertEquals(0, (int) pickupResults.get('B'));
		assertEquals(2 * pickupResults.get('A'), pickupResults.get('C'), 200);
	}

	@Test
	public void testSize() {
		WeightedSet<Character> characters = new WeightedSet<Character>();
		characters.put('A', 1);
		characters.put('B', 0);
		characters.put('C', 2);

		assertEquals(3, characters.size());

		characters.put('D', 2);
		assertEquals(4, characters.size());
	}
}
