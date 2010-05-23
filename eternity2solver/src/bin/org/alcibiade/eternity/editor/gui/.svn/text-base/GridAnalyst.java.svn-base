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

package org.alcibiade.eternity.editor.gui;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.GridObserver;
import org.alcibiade.eternity.editor.model.Pattern;
import org.alcibiade.eternity.editor.model.PatternStats;
import org.alcibiade.eternity.editor.model.QuadModel;
import org.alcibiade.eternity.editor.model.QuadObserver;

public class GridAnalyst implements GridObserver, QuadObserver {
	private Document docScore;
	private Document docStatus;
	private Document docInfos;
	private JCheckBox checkBox;

	private GridView gridView;

	public GridAnalyst(GridView grid, Document score, Document status, Document infos,
			JCheckBox checkBox) {
		this.docScore = score;
		this.docStatus = status;
		this.docInfos = infos;
		this.checkBox = checkBox;
		gridView = grid;
		gridView.getGridModel().addGridObserver(this);
		registerQuads();
		refreshTargets();
	}

	public void quadUpdated() {
		refreshTargets();
	}

	public void gridSizeUpdated(int size) {
		registerQuads();
		refreshTargets();
	}

	public void gridUpdated() {
		refreshTargets();
	}

	private void registerQuads() {
		for (QuadModel quad : gridView.getGridModel().getQuads()) {
			quad.addQuadObserver(this);
		}
	}

	private void refreshTargets() {
		GridModel gridModel = gridView.getGridModel();
		String textstatus = gridModel.getStatusMessage();

		checkBox.setSelected(gridView.getShowPatternIds());

		if (gridView.isEditable()) {
			updateGridErrors(gridModel);
		}

		int cnx = gridModel.countConnections();
		int pairs = gridModel.countPairs();
		PatternStats patterns = gridModel.getPatternStats();
		int pc = 100 * pairs / cnx;

		try {
			docScore.remove(0, docScore.getLength());
			docScore.insertString(0, "" + pairs + " out of " + cnx + " (" + pc + "%)", null);

			docStatus.remove(0, docStatus.getLength());
			docStatus.insertString(0, textstatus, null);

			docInfos.remove(0, docInfos.getLength());
			docInfos.insertString(0, gridModel.getSize() + "x" + gridModel.getSize() + ", "
					+ patterns.getPatterns().size() + " patterns " + patterns.getDuplicates()
					+ " dups", null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private void updateGridErrors(GridModel gridModel) {
		Set<Pattern> oddPatterns = new HashSet<Pattern>();

		for (Pattern pattern : Pattern.getNonDefaultPatterns()) {
			int occurences = gridModel.countOccurences(pattern);
			if ((occurences % 2) == 1) {
				oddPatterns.add(pattern);
			}
		}

		for (QuadView quadView : gridView.getQuadViews()) {
			for (int direction = 0; direction < 4; direction++) {
				quadView.setError(direction, oddPatterns.contains(quadView.getModel().getPattern(
						direction)));
			}
		}

	}
}
