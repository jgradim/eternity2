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

import org.junit.Test;

public class EnablingListTest {
	@Test
	public void testBasicList() {
		EnablingList<Integer> list = new EnablingList<Integer>();
		assertEquals(0, list.size());
		list.add(4);
		assertEquals(1, list.size());
		list.add(8);

		assertEquals(4, (int) list.get(0));
		assertEquals(8, (int) list.get(1));
	}

	@Test
	public void testEnabling() {
		EnablingList<Integer> list = new EnablingList<Integer>();
		list.add(4);
		list.add(8);
		list.add(15);
		list.add(16);
		list.add(23);
		list.add(42);

		assertEquals(4, (int) list.get(0));
		assertEquals(8, (int) list.get(1));
		assertEquals(6, list.size());

		list.disable(8);

		assertEquals(4, (int) list.get(0));
		assertEquals(15, (int) list.get(1));
		assertEquals(5, list.size());

		list.disable(42);

		assertEquals(4, (int) list.get(0));
		assertEquals(15, (int) list.get(1));
		assertEquals(4, list.size());

		list.enable(8);

		assertEquals(4, (int) list.get(0));
		assertEquals(8, (int) list.get(1));
		assertEquals(5, list.size());
	}

}
