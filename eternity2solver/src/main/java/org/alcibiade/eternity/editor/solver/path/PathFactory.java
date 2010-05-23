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

public class PathFactory {

	public static final String LABEL_LINEAR = "Linear";
	public static final String LABEL_SNAIL = "Snail";
	public static final String LABEL_RSNAIL = "Snail Reverse";
	public static final String LABEL_RANDOM = "Random";
	public static final String LABEL_ANGLE = "Angle";
	public static final String LABEL_RANGLE = "Angle Reverse";
	public static final String LABEL_BISHOP = "Bishop";
	public static final String LABEL_CIRCLES = "Circles";
	public static final String LABEL_HUMAN = "Human";
	public static final String LABEL_HUMAN2 = "Human MkII";

	public static List<String> getAvailablePaths() {
		List<String> paths = new ArrayList<String>();
		paths.add(LABEL_LINEAR);
		paths.add(LABEL_HUMAN);
		paths.add(LABEL_HUMAN2);
		paths.add(LABEL_SNAIL);
		paths.add(LABEL_RSNAIL);
		paths.add(LABEL_RANDOM);
		paths.add(LABEL_ANGLE);
		paths.add(LABEL_RANGLE);
		paths.add(LABEL_BISHOP);
		paths.add(LABEL_CIRCLES);
		return paths;
	}

	public static PathProvider createPath(String label) throws UnknownPathException {
		PathProvider path;
		if (LABEL_LINEAR.equals(label)) {
			path = new LinearPath();
		} else if (LABEL_SNAIL.equals(label)) {
			path = new SnailPath();
		} else if (LABEL_RSNAIL.equals(label)) {
			path = new RSnailPath();
		} else if (LABEL_RANDOM.equals(label)) {
			path = new RandomPath();
		} else if (LABEL_ANGLE.equals(label)) {
			path = new AnglePath();
		} else if (LABEL_RANGLE.equals(label)) {
			path = new RAnglePath();
		} else if (LABEL_BISHOP.equals(label)) {
			path = new BishopPath();
		} else if (LABEL_CIRCLES.equals(label)) {
			path = new CirclesPath();
		} else if (LABEL_HUMAN.equals(label)) {
			path = new HumanPathMkI();
		} else if (LABEL_HUMAN2.equals(label)) {
			path = new HumanPathMkII();
		} else {
			throw new UnknownPathException("Unknown path type: " + label);
		}

		return path;
	}

}
