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

package org.alcibiade.eternity.editor.gui.action;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import org.alcibiade.eternity.editor.gui.EditableStatusProvider;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.Pattern;
import org.alcibiade.eternity.editor.model.operation.GridFiller;

public class E2CloneGridAction extends GridUpdateAction {
	private static final long serialVersionUID = 1L;
	private GridModel gridModel;

	public E2CloneGridAction(GridModel grid, EditableStatusProvider editable) {
		super("16x16 E2 Clone (5 pat. border, 17 pat center)", editable);
		gridModel = grid;
	}

	public void actionPerformed(ActionEvent e) {
		gridModel.setSize(16);
		GridFiller filler = new GridFiller(gridModel);

		filler.fillBorder(createPatterns(1, 5));
		filler.fillCenter(createPatterns(6, 22));
	}

	private Set<Pattern> createPatterns(int low, int high) {
		assert low <= high;

		Set<Pattern> patterns = new HashSet<Pattern>();

		for (int i = low; i <= high; i++) {
			patterns.add(Pattern.getPatternByCode(i));
		}

		return patterns;
	}
}
