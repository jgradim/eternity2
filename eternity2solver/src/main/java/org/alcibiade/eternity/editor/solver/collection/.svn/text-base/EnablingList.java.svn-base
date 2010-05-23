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

import java.util.ArrayList;
import java.util.List;

public class EnablingList<T> {
	private List<T> items;
	private List<Boolean> enabledFlags;

	public EnablingList() {
		items = new ArrayList<T>();
		enabledFlags = new ArrayList<Boolean>();
	}

	public void add(T item) {
		items.add(item);
		enabledFlags.add(true);
	}

	public int size() {
		int items = 0;

		for (Boolean b : enabledFlags) {
			if (b) {
				items += 1;
			}
		}

		return items;
	}

	public T get(int index) {
		T result = null;

		for (int i = 0; result == null && i < enabledFlags.size(); i++) {
			if (enabledFlags.get(i)) {
				if (index == 0) {
					result = items.get(i);
				} else {
					index -= 1;
				}
			}
		}

		return result;
	}

	private int indexOf(T item) {
		int result = -1;

		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).equals(item)) {
				result = i;
				break;
			}
		}

		return result;
	}

	public void enable(T item) {
		int index = indexOf(item);
		if (index >= 0) {
			assert enabledFlags.get(index) == false;
			enabledFlags.set(index, true);
		}
	}

	public void disable(T item) {
		int index = indexOf(item);
		if (index >= 0) {
			assert enabledFlags.get(index) == true;
			enabledFlags.set(index, false);
		}
	}

}
