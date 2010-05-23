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

package org.alcibiade.eternity.editor.model.operation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.Pattern;
import org.alcibiade.eternity.editor.model.QuadModel;
import org.alcibiade.eternity.editor.solver.RandomFactory;

public class GridFiller extends GridOperation {

	private Random rand;

	public GridFiller(GridModel gridModel) {
		super(gridModel);
		rand = RandomFactory.getRandom();
	}

	public void fillRandom(int nbPatterns) {
		assert nbPatterns > 0;

		do {
			Set<Pattern> patterns = selectRandomPatterns(nbPatterns);
			fillBorder(patterns);
			fillCenter(patterns);
		} while (getModel().getPatternStats().getPatterns().size() != nbPatterns);
	}

	public void FillRandomAsymetric(int nbPatterns) {
		assert nbPatterns > 1;

		boolean valid;

		do {
			fillRandom(nbPatterns);
			valid = true;
			valid = valid && !getModel().isHSymetric();
			valid = valid && !getModel().isVSymetric();
			valid = valid && !getModel().isRSymetric();
		} while (!valid);
	}

	public void FillBorderRandom(int nbPatterns) {
		do {
			fillBorder(selectRandomPatterns(nbPatterns, getModel().getPatternStats()
					.getInnerPatterns()));
		} while (getModel().getPatternStats().getOuterPatterns().size() != nbPatterns);
	}

	public void FillCenterRandom(int nbPatterns) {
		do {
			fillCenter(selectRandomPatterns(nbPatterns, getModel().getPatternStats()
					.getOuterPatterns()));
		} while (getModel().getPatternStats().getInnerPatterns().size() != nbPatterns);
	}

	public void fillBorder(Set<Pattern> patterns) {
		assert !patterns.isEmpty();

		Pattern defaultPattern = Pattern.getDefaultPattern();
		int gridSize = getModel().getSize();

		for (int y = 0; y < gridSize; y++) {
			for (int x = 0; x < gridSize; x++) {
				QuadModel quad = getModel().getQuad(x, y);

				for (int d = 0; d < 4; d++) {
					if (x == 0 && d == QuadModel.DIR_WEST) {
						quad.setPattern(d, defaultPattern);
					} else if (y == 0 && d == QuadModel.DIR_NORTH) {
						quad.setPattern(d, defaultPattern);
					} else if (x == gridSize - 1 && d == QuadModel.DIR_EAST) {
						quad.setPattern(d, defaultPattern);
					} else if (y == gridSize - 1 && d == QuadModel.DIR_SOUTH) {
						quad.setPattern(d, defaultPattern);
					}

					if (x == 0 || x == gridSize - 1) {
						if (d == QuadModel.DIR_NORTH) {
							if (y == 0) {
								quad.setPattern(d, defaultPattern);
							} else {
								quad.setPattern(d, getModel().getQuad(x, y - 1).getPattern(
										QuadModel.DIR_SOUTH));
							}
						} else if (d == QuadModel.DIR_SOUTH) {
							if (y < gridSize - 1) {
								quad.setPattern(d, selectRandomPattern(patterns));
							}
						}
					}

					if (y == 0 || y == gridSize - 1) {
						if (d == QuadModel.DIR_WEST) {
							if (x == 0) {
								quad.setPattern(d, defaultPattern);
							} else {
								quad.setPattern(d, getModel().getQuad(x - 1, y).getPattern(
										QuadModel.DIR_EAST));
							}
						} else if (d == QuadModel.DIR_EAST) {
							if (x < gridSize - 1) {
								quad.setPattern(d, selectRandomPattern(patterns));
							}
						}
					}
				}
			}
		}
	}

	public void fillCenter(Set<Pattern> patterns) {
		assert !patterns.isEmpty();

		Pattern defaultPattern = Pattern.getDefaultPattern();
		int gridSize = getModel().getSize();

		for (int y = 0; y < gridSize; y++) {
			for (int x = 0; x < gridSize; x++) {
				QuadModel quad = getModel().getQuad(x, y);

				for (int d = 0; d < 4; d++) {

					if (x > 0 && x < gridSize - 1) {
						if (d == QuadModel.DIR_NORTH) {
							if (y == 0) {
								quad.setPattern(d, defaultPattern);
							} else {
								quad.setPattern(d, getModel().getQuad(x, y - 1).getPattern(
										QuadModel.DIR_SOUTH));
							}
						} else if (d == QuadModel.DIR_SOUTH) {
							if (y < gridSize - 1) {
								quad.setPattern(d, selectRandomPattern(patterns));
							}
						}
					}

					if (y > 0 && y < gridSize - 1) {
						if (d == QuadModel.DIR_WEST) {
							if (x == 0) {
								quad.setPattern(d, defaultPattern);
							} else {
								quad.setPattern(d, getModel().getQuad(x - 1, y).getPattern(
										QuadModel.DIR_EAST));
							}
						} else if (d == QuadModel.DIR_EAST) {
							if (x < gridSize - 1) {
								quad.setPattern(d, selectRandomPattern(patterns));
							}
						}
					}
				}
			}
		}

	}

	/*
	 * Pattern(s) selection methods
	 */

	private Pattern selectRandomPattern(Set<Pattern> patterns) {
		int randIndex = rand.nextInt(patterns.size());
		Pattern result = null;
		Iterator<Pattern> itPatterns = patterns.iterator();

		while (itPatterns.hasNext() && randIndex >= 0) {
			Pattern pattern = itPatterns.next();

			if (randIndex == 0) {
				result = pattern;
			}

			randIndex--;
		}

		assert result != null;

		return result;
	}

	private Set<Pattern> selectRandomPatterns(int nbPatterns) {
		return selectRandomPatterns(nbPatterns, new HashSet<Pattern>());
	}

	private Set<Pattern> selectRandomPatterns(int nbPatterns, Set<Pattern> excluded) {
		List<Pattern> patterns = new LinkedList<Pattern>();
		patterns.addAll(Pattern.getAllPatterns());
		patterns.remove(Pattern.getDefaultPattern());
		patterns.removeAll(excluded);

		while (patterns.size() > nbPatterns) {
			int rndix = rand.nextInt(patterns.size());
			patterns.remove(rndix);
		}

		return new HashSet<Pattern>(patterns);
	}

}
