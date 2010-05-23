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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.alcibiade.eternity.editor.gui.EditableStatusProvider;
import org.alcibiade.eternity.editor.model.GridModel;

public class FileSaveAsAction extends GridUpdateAction {
	private static final long serialVersionUID = 1L;
	private GridModel gridModel;
	private Component parent;

	public FileSaveAsAction(GridModel grid, EditableStatusProvider editable, Component parent) {
		super("Save as ...", editable);
		this.gridModel = grid;
		this.parent = parent;
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(parent);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fc.getSelectedFile();
				FileWriter writer = new FileWriter(file);
				writer.write(gridModel.toQuadString());
				writer.flush();
				writer.close();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(parent, "IO Error: " + e1.getMessage(), "IO error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
