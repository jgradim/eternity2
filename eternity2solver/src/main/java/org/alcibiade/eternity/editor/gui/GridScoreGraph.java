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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import org.alcibiade.eternity.editor.model.GridScoreRecorder;

public class GridScoreGraph extends JComponent implements GridScoreRecorderObserver {

	private static final long serialVersionUID = 1L;

	private static final Color col_back = Color.GRAY.darker();
	private static final Color col_score = Color.GREEN.brighter();

	private GridScoreRecorder scorerecorder;

	public GridScoreGraph(GridScoreRecorder recorder) {
		scorerecorder = recorder;
		scorerecorder.addObserver(this);

		setBorder(BorderFactory.createEtchedBorder());
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Dimension size = getSize();

		GradientPaint grad_back = new GradientPaint(new Point2D.Double(0, 0), col_back.brighter(),
				new Point2D.Double(0, size.height), col_back.darker());

		GradientPaint grad_score = new GradientPaint(new Point2D.Double(0, 0),
				col_score.brighter(), new Point2D.Double(0, size.height), col_score.darker());

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setPaint(grad_back);
		g2.fill(new Rectangle2D.Double(0, 0, size.getWidth(), size.getHeight()));

		Map<Long, Integer> graphdata = scorerecorder.getHistory();
		int maxval = scorerecorder.getMaxValue();

		long firsttimestamp = Long.MAX_VALUE;
		long lasttimestamp = Long.MIN_VALUE;

		for (long ts : graphdata.keySet()) {
			if (firsttimestamp > ts) {
				firsttimestamp = ts;
			}

			if (lasttimestamp < ts) {
				lasttimestamp = ts;
			}
		}

		if (firsttimestamp < lasttimestamp) {
			Point2D lastpoint = null;

			g2.setPaint(grad_score);

			for (long ts : graphdata.keySet()) {
				int val = graphdata.get(ts);

				double p_x = (ts - firsttimestamp) * size.width / (lasttimestamp - firsttimestamp);
				double p_y = size.height - val * size.height / maxval;

				Point2D point = new Point2D.Double(p_x, p_y);

				if (lastpoint != null) {
					Line2D segment = new Line2D.Double(lastpoint, point);
					g2.draw(segment);
				}

				lastpoint = point;
			}
		}

	}

	public void notifyRecorderUpdated() {
		repaint();
	}
}
