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

package org.alcibiade.eternity.editor.model;

import java.awt.Color;

public enum PatternColor {

	COL_GRAY(0x9090A0), COL_ORANGE(0xec991a), COL_DARKBLUE(0x2a5577), COL_LIGHTBLUE(0x4bc5e8), COL_YELLOW(
			0xe0df10), COL_PURPLE(0xda72c7), COL_DARKPURPLE(0x622d57), COL_MAGENTA(0x9f1e47), COL_GREEN(
			0x2f8557), COL_PINK(0xda618a), COL_VIOLET(0x5d307d), COL_BROWN(0x4e291d);

	private Color color;

	private PatternColor(int color) {
		this.color = new Color(color);
	}

	public Color getColor() {
		return color;
	}
}
