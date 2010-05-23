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

import java.util.SortedSet;

public class PatternStats {

	private SortedSet<Pattern> patterns;

	private SortedSet<Pattern> outerPatterns;

	private SortedSet<Pattern> innerPatterns;

	private int duplicates;

	public PatternStats(SortedSet<Pattern> patterns, SortedSet<Pattern> outerPatterns,
			SortedSet<Pattern> innerPatterns, int duplicates) {
		this.patterns = patterns;
		this.outerPatterns = outerPatterns;
		this.innerPatterns = innerPatterns;
		this.duplicates = duplicates;
	}

	public SortedSet<Pattern> getPatterns() {
		return patterns;
	}

	public SortedSet<Pattern> getOuterPatterns() {
		return outerPatterns;
	}

	public SortedSet<Pattern> getInnerPatterns() {
		return innerPatterns;
	}

	public int getDuplicates() {
		return duplicates;
	}
}
