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

import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This is the dialog that will display the splash screen when the user clicks
 * the about menu
 * 
 * @author Yannick Kirschhoffer
 * 
 */
public class AboutDialog extends JDialog {

	/**
	 * Default Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The parent frame of the dialog. It will be disabled while the dialog is
	 * visible since it is declared modal
	 */
	private JFrame parent;

	/**
	 * Create a new dialog. It won't be visible until setVisible is called.
	 * 
	 * @param parent
	 *            The parent frame of this modal dialog.
	 */
	public AboutDialog(JFrame parent) {
		super(parent, "About Eternity II Editor");
		this.parent = parent;

		setModal(true);
		setResizable(false);
		setUndecorated(false);

		JTextArea text = new JTextArea();
		text.setEditable(false);

		try {
			text.append(readTextFromJar("About.txt"));
		} catch (IOException e) {
			text.append(e.getLocalizedMessage());
		}

		text.setCaretPosition(0);

		JScrollPane pane = new JScrollPane(text);
		getContentPane().add(pane);

		setPreferredSize(new Dimension(600, 400));

		pack();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean visibility) {
		if (visibility) {
			Dimension splashSize = this.getPreferredSize();

			Dimension parentSize = parent.getSize();
			Point parentLocation = parent.getLocationOnScreen();
			setLocation(parentLocation.x + parentSize.width / 2 - (splashSize.width / 2),
					parentLocation.y + parentSize.height / 2 - (splashSize.height / 2));
		}

		super.setVisible(visibility);
	}

	/**
	 * Read text from a file in the current jar file.
	 * 
	 * @param filename
	 *            The file name relative to this class' package
	 * @return The file contents
	 * @throws IOException
	 *             If an I/O error occurs
	 */
	private String readTextFromJar(String filename) throws IOException {
		StringBuffer result = new StringBuffer();

		InputStream is = getClass().getResourceAsStream(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String thisline;

		while ((thisline = br.readLine()) != null) {
			result.append(thisline);
			result.append('\n');
		}

		return result.toString();
	}

}
