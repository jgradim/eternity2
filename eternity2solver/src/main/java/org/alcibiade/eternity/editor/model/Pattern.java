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
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Dimension2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public enum Pattern implements Comparable<Pattern>, Serializable {

	// "Empty" pattern a.k.a. default pattern
	PAT_00(0, PatternColor.COL_GRAY, PatternColor.COL_GRAY, PatternShape.SHAPE_NONE),

	// Original patterns

	// 1-8

	PAT_01(1, PatternColor.COL_ORANGE, PatternColor.COL_LIGHTBLUE, PatternShape.SHAPE_ROUNDCROSS), PAT_02(
			2, PatternColor.COL_DARKBLUE, PatternColor.COL_YELLOW, PatternShape.SHAPE_FLOWER), PAT_03(
			3, PatternColor.COL_PINK, PatternColor.COL_LIGHTBLUE, PatternShape.SHAPE_HOLLOWCROSS), PAT_04(
			4, PatternColor.COL_GREEN, PatternColor.COL_DARKBLUE, PatternShape.SHAPE_BOULON), PAT_05(
			5, PatternColor.COL_BROWN, PatternColor.COL_ORANGE, PatternShape.SHAPE_ROUNDCASTLE), PAT_06(
			6, PatternColor.COL_PINK, PatternColor.COL_YELLOW, PatternShape.SHAPE_CROSSBALLS), PAT_07(
			7, PatternColor.COL_DARKPURPLE, PatternColor.COL_LIGHTBLUE, PatternShape.SHAPE_LYS), PAT_08(
			8, PatternColor.COL_YELLOW, PatternColor.COL_LIGHTBLUE, PatternShape.SHAPE_STAR),

	// 9-22

	PAT_09(9, PatternColor.COL_DARKPURPLE, PatternColor.COL_YELLOW, PatternShape.SHAPE_TOXIC), PAT_10(
			10, PatternColor.COL_GREEN, PatternColor.COL_PINK, PatternShape.SHAPE_CROSSBALLS), PAT_11(
			11, PatternColor.COL_DARKPURPLE, PatternColor.COL_GREEN, PatternShape.SHAPE_TOXIC), PAT_12(
			12, PatternColor.COL_GREEN, PatternColor.COL_ORANGE, PatternShape.SHAPE_LYS), PAT_13(
			13, PatternColor.COL_DARKPURPLE, PatternColor.COL_YELLOW, PatternShape.SHAPE_STAR), PAT_14(
			14, PatternColor.COL_LIGHTBLUE, PatternColor.COL_PINK, PatternShape.SHAPE_CASTLE), PAT_15(
			15, PatternColor.COL_YELLOW, PatternColor.COL_GREEN, PatternShape.SHAPE_SQUARE), PAT_16(
			16, PatternColor.COL_LIGHTBLUE, PatternColor.COL_PINK, PatternShape.SHAPE_LYS), PAT_17(
			17, PatternColor.COL_YELLOW, PatternColor.COL_DARKBLUE, PatternShape.SHAPE_CASTLE), PAT_18(
			18, PatternColor.COL_ORANGE, PatternColor.COL_DARKPURPLE, PatternShape.SHAPE_STAR), PAT_19(
			19, PatternColor.COL_DARKBLUE, PatternColor.COL_ORANGE, PatternShape.SHAPE_CROSSBALLS), PAT_20(
			20, PatternColor.COL_DARKBLUE, PatternColor.COL_LIGHTBLUE, PatternShape.SHAPE_SQUARE), PAT_21(
			21, PatternColor.COL_PINK, PatternColor.COL_YELLOW, PatternShape.SHAPE_CASTLE), PAT_22(
			22, PatternColor.COL_DARKBLUE, PatternColor.COL_PINK, PatternShape.SHAPE_TOXIC),

	// Extra patterns

	PAT_23(23, PatternColor.COL_MAGENTA, PatternColor.COL_LIGHTBLUE, PatternShape.SHAPE_TOXIC), PAT_24(
			24, PatternColor.COL_GREEN, PatternColor.COL_ORANGE, PatternShape.SHAPE_SQUARE), PAT_25(
			25, PatternColor.COL_LIGHTBLUE, PatternColor.COL_PINK, PatternShape.SHAPE_SQUARE), PAT_26(
			26, PatternColor.COL_YELLOW, PatternColor.COL_GREEN, PatternShape.SHAPE_FLOWER), PAT_27(
			27, PatternColor.COL_MAGENTA, PatternColor.COL_LIGHTBLUE, PatternShape.SHAPE_LYS), PAT_28(
			28, PatternColor.COL_PURPLE, PatternColor.COL_YELLOW, PatternShape.SHAPE_CASTLE), PAT_29(
			29, PatternColor.COL_GREEN, PatternColor.COL_LIGHTBLUE, PatternShape.SHAPE_STAR), PAT_30(
			30, PatternColor.COL_YELLOW, PatternColor.COL_LIGHTBLUE, PatternShape.SHAPE_BOULON),

	// End of new 1.1 batch

	PAT_31(31, PatternColor.COL_MAGENTA, PatternColor.COL_GREEN, PatternShape.SHAPE_BOULON), PAT_32(
			32, PatternColor.COL_MAGENTA, PatternColor.COL_GREEN, PatternShape.SHAPE_HOLLOWCROSS), PAT_33(
			33, PatternColor.COL_PURPLE, PatternColor.COL_MAGENTA, PatternShape.SHAPE_ROUNDCROSS), PAT_34(
			34, PatternColor.COL_DARKPURPLE, PatternColor.COL_ORANGE, PatternShape.SHAPE_CROSSBALLS), PAT_35(
			35, PatternColor.COL_ORANGE, PatternColor.COL_GREEN, PatternShape.SHAPE_LYS), PAT_36(
			36, PatternColor.COL_LIGHTBLUE, PatternColor.COL_DARKBLUE, PatternShape.SHAPE_STAR), PAT_37(
			37, PatternColor.COL_PURPLE, PatternColor.COL_GREEN, PatternShape.SHAPE_CASTLE), PAT_38(
			38, PatternColor.COL_YELLOW, PatternColor.COL_PURPLE, PatternShape.SHAPE_HOLLOWCROSS), PAT_39(
			39, PatternColor.COL_GREEN, PatternColor.COL_YELLOW, PatternShape.SHAPE_STAR);

	private int patternCode;
	private PatternColor patternBg;
	private PatternColor patternFg;
	private PatternShape patternShape;

	private Pattern(int code, PatternColor bg, PatternColor fg, PatternShape shape) {
		patternCode = code;
		patternBg = bg;
		patternFg = fg;
		patternShape = shape;
	}

	public int getCode() {
		return patternCode;
	}

	public PatternColor getPatternBg() {
		return patternBg;
	}

	public PatternColor getPatternFg() {
		return patternFg;
	}

	public PatternShape getPatternShape() {
		return patternShape;
	}

	public String getCodeString() {
		String result = null;

		if (patternCode < 10) {
			result = " " + patternCode;
		} else {
			result = Integer.toString(patternCode);
		}

		return result;
	}

	public void paint(Graphics2D g2d, Dimension2D size) {
		double w = size.getWidth();
		double h = size.getHeight();

		Color bg = patternBg.getColor();

		if (patternBg != PatternColor.COL_GRAY) {
			bg = bg.darker();
		}

		GradientPaint grad_back = new GradientPaint(new Point2D.Double(0, 0), patternBg.getColor()
				.brighter(), new Point2D.Double(0, h), bg);
		g2d.setPaint(grad_back);

		GeneralPath globalshape = new GeneralPath();
		globalshape.moveTo(0, (float) h / 2);
		globalshape.lineTo((float) w / 2, 0);
		globalshape.lineTo((float) w, (float) h / 2);
		globalshape.lineTo((float) w / 2, (float) h);

		Shape previous_clip = g2d.getClip();
		g2d.fill(globalshape);
		g2d.clip(globalshape);

		double cx = w / 2.;
		double cy = h / 2.;
		double minsize = Math.min(w, h);

		if (patternShape == PatternShape.SHAPE_NONE) {
			// Nothing to do
		} else if (patternShape == PatternShape.SHAPE_ROUNDCROSS) {
			double rad_ext = minsize * 0.60;
			g2d.setColor(patternFg.getColor());
			g2d.fill(new Ellipse2D.Double(cx - rad_ext / 2, cy - rad_ext / 2, rad_ext, rad_ext));

			double rad_in = minsize * 0.28;
			double rad_out = minsize * 0.22;

			g2d.setPaint(grad_back);
			g2d.fill(new Ellipse2D.Double(cx - rad_out - rad_in / 2, cy - rad_out - rad_in / 2,
					rad_in, rad_in));
			g2d.fill(new Ellipse2D.Double(cx - rad_out - rad_in / 2, cy + rad_out - rad_in / 2,
					rad_in, rad_in));
			g2d.fill(new Ellipse2D.Double(cx + rad_out - rad_in / 2, cy - rad_out - rad_in / 2,
					rad_in, rad_in));
			g2d.fill(new Ellipse2D.Double(cx + rad_out - rad_in / 2, cy + rad_out - rad_in / 2,
					rad_in, rad_in));

		} else if (patternShape == PatternShape.SHAPE_HOLLOWCROSS) {
			double rad_ext = minsize * 0.60;
			g2d.setColor(patternFg.getColor());
			g2d.fill(new Rectangle2D.Double(cx - rad_ext / 2, cy - rad_ext / 2, rad_ext, rad_ext));

			double rad_in = minsize * 0.40;
			double rad_out = rad_ext * 0.52;

			g2d.setPaint(grad_back);
			g2d.fill(new Ellipse2D.Double(cx - rad_out - rad_in / 2, cy - rad_out - rad_in / 2,
					rad_in, rad_in));
			g2d.fill(new Ellipse2D.Double(cx - rad_out - rad_in / 2, cy + rad_out - rad_in / 2,
					rad_in, rad_in));
			g2d.fill(new Ellipse2D.Double(cx + rad_out - rad_in / 2, cy - rad_out - rad_in / 2,
					rad_in, rad_in));
			g2d.fill(new Ellipse2D.Double(cx + rad_out - rad_in / 2, cy + rad_out - rad_in / 2,
					rad_in, rad_in));

			double rad_hole = minsize * 0.15;

			GeneralPath inside = new GeneralPath();
			inside.moveTo((float) (cx), (float) (cy - rad_hole));
			inside.lineTo((float) (cx + rad_hole), (float) (cy));
			inside.lineTo((float) (cx), (float) (cy + rad_hole));
			inside.lineTo((float) (cx - rad_hole), (float) (cy));
			g2d.fill(inside);
		} else if (patternShape == PatternShape.SHAPE_BOULON) {
			double p1 = minsize * 0.4;
			double p2 = p1 * 0.66;
			double p3 = p1 * 0.33;

			GeneralPath boulon = new GeneralPath();
			boulon.moveTo((float) (cx - p2), (float) (cy));
			boulon.lineTo((float) (cx - p2), (float) (cy - p3));
			boulon.lineTo((float) (cx - p3), (float) (cy - p2));
			boulon.lineTo((float) (cx), (float) (cy - p2));
			boulon.lineTo((float) (cx + p3), (float) (cy - p2));
			boulon.lineTo((float) (cx + p2), (float) (cy - p3));
			boulon.lineTo((float) (cx + p2), (float) (cy));
			boulon.lineTo((float) (cx + p2), (float) (cy + p3));
			boulon.lineTo((float) (cx + p3), (float) (cy + p2));
			boulon.lineTo((float) (cx), (float) (cy + p2));
			boulon.lineTo((float) (cx - p3), (float) (cy + p2));
			boulon.lineTo((float) (cx - p2), (float) (cy + p3));
			g2d.setColor(patternFg.getColor());
			g2d.fill(boulon);

			double rad_in = minsize * 0.27;

			g2d.setPaint(grad_back);
			g2d.fill(new Ellipse2D.Double(cx - rad_in / 2, cy - rad_in / 2, rad_in, rad_in));

		} else if (patternShape == PatternShape.SHAPE_FLOWER) {
			double rad = minsize / 7;

			g2d.setColor(patternFg.getColor());
			g2d.fill(new Ellipse2D.Double(cx - 2 * rad, cy - 1 * rad, 2 * rad, 2 * rad));
			g2d.fill(new Ellipse2D.Double(cx - 1 * rad, cy - 2 * rad, 2 * rad, 2 * rad));
			g2d.fill(new Ellipse2D.Double(cx + 0 * rad, cy - 1 * rad, 2 * rad, 2 * rad));
			g2d.fill(new Ellipse2D.Double(cx - 1 * rad, cy + 0 * rad, 2 * rad, 2 * rad));

			rad *= 0.9;

			g2d.setPaint(grad_back);
			g2d.fill(new Ellipse2D.Double(cx - rad, cy - rad, 2 * rad, 2 * rad));
		} else if (patternShape == PatternShape.SHAPE_LYS) {
			double p1 = minsize * 0.35;
			double p2 = p1 * 0.66;
			double p3 = p1 * 0.33;
			double p4 = p1 * 0.20;

			g2d.setColor(patternFg.getColor());
			GeneralPath lys = new GeneralPath();
			lys.moveTo((float) (cx - p1), (float) (cy));
			lys.lineTo((float) (cx - p2), (float) (cy - p3));
			lys.lineTo((float) (cx - p4), (float) (cy - p4));
			lys.lineTo((float) (cx - p3), (float) (cy - p2));
			lys.lineTo((float) (cx), (float) (cy - p1));
			lys.lineTo((float) (cx + p3), (float) (cy - p2));
			lys.lineTo((float) (cx + p4), (float) (cy - p4));
			lys.lineTo((float) (cx + p2), (float) (cy - p3));
			lys.lineTo((float) (cx + p1), (float) (cy));
			lys.lineTo((float) (cx + p2), (float) (cy + p3));
			lys.lineTo((float) (cx + p4), (float) (cy + p4));
			lys.lineTo((float) (cx + p3), (float) (cy + p2));
			lys.lineTo((float) (cx), (float) (cy + p1));
			lys.lineTo((float) (cx - p3), (float) (cy + p2));
			lys.lineTo((float) (cx - p4), (float) (cy + p4));
			lys.lineTo((float) (cx - p2), (float) (cy + p3));
			g2d.setColor(patternFg.getColor());
			g2d.fill(lys);
		} else if (patternShape == PatternShape.SHAPE_ROUNDCASTLE) {
			double p1 = minsize * 0.23;
			double p2 = p1 * 0.90; // Main circle radius
			double p3 = p1 * 0.45; // External circles radius
			double p4 = p1 * 0.90; // Inner star major radius
			double p5 = p1 * 0.30; // Inner star minor radius

			g2d.setColor(patternFg.getColor());
			g2d.fill(new Ellipse2D.Double(cx - p2, cy - p2, 2 * p2, 2 * p2));
			g2d.fill(new Ellipse2D.Double(cx - p1 - p3, cy - p3, 2 * p3, 2 * p3));
			g2d.fill(new Ellipse2D.Double(cx - p3, cy - p1 - p3, 2 * p3, 2 * p3));
			g2d.fill(new Ellipse2D.Double(cx + p1 - p3, cy - p3, 2 * p3, 2 * p3));
			g2d.fill(new Ellipse2D.Double(cx - p3, cy + p1 - p3, 2 * p3, 2 * p3));

			GeneralPath innerStar = new GeneralPath();
			innerStar.moveTo((float) (cx), (float) (cy - p4));
			innerStar.lineTo((float) (cx + p5), (float) (cy - p5));
			innerStar.lineTo((float) (cx + p4), (float) (cy));
			innerStar.lineTo((float) (cx + p5), (float) (cy + p5));
			innerStar.lineTo((float) (cx), (float) (cy + p4));
			innerStar.lineTo((float) (cx - p5), (float) (cy + p5));
			innerStar.lineTo((float) (cx - p4), (float) (cy));
			innerStar.lineTo((float) (cx - p5), (float) (cy - p5));
			innerStar.lineTo((float) (cx), (float) (cy - p4));

			g2d.setColor(patternBg.getColor());
			g2d.fill(innerStar);
		} else if (patternShape == PatternShape.SHAPE_CROSSBALLS) {
			double p1 = minsize * 0.23;
			double p2 = p1 * 0.30;
			double p3 = p1 * 0.45;

			g2d.setColor(patternFg.getColor());
			g2d.fill(new Rectangle2D.Double(cx - p1, cy - p2, 2 * p1, 2 * p2));
			g2d.fill(new Rectangle2D.Double(cx - p2, cy - p1, 2 * p2, 2 * p1));
			g2d.fill(new Ellipse2D.Double(cx - p1 - p3, cy - p3, 2 * p3, 2 * p3));
			g2d.fill(new Ellipse2D.Double(cx - p3, cy - p1 - p3, 2 * p3, 2 * p3));
			g2d.fill(new Ellipse2D.Double(cx + p1 - p3, cy - p3, 2 * p3, 2 * p3));
			g2d.fill(new Ellipse2D.Double(cx - p3, cy + p1 - p3, 2 * p3, 2 * p3));
		} else if (patternShape == PatternShape.SHAPE_CASTLE) {
			double p1 = minsize * 0.35;
			double p2 = p1 * 0.66;
			double p3 = p1 * 0.33;
			double p4 = p1 * 0.15;

			GeneralPath castle = new GeneralPath();
			castle.moveTo((float) (cx - p1), (float) (cy));
			castle.lineTo((float) (cx - p2), (float) (cy - p3));
			castle.lineTo((float) (cx - p2 + p4), (float) (cy - p3 + p4));
			castle.lineTo((float) (cx - p3 + p4), (float) (cy - p2 + p4));
			castle.lineTo((float) (cx - p3), (float) (cy - p2));
			castle.lineTo((float) (cx), (float) (cy - p1));
			castle.lineTo((float) (cx + p3), (float) (cy - p2));
			castle.lineTo((float) (cx + p3 - p4), (float) (cy - p2 + p4));
			castle.lineTo((float) (cx + p2 - p4), (float) (cy - p3 + p4));
			castle.lineTo((float) (cx + p2), (float) (cy - p3));
			castle.lineTo((float) (cx + p1), (float) (cy));
			castle.lineTo((float) (cx + p2), (float) (cy + p3));
			castle.lineTo((float) (cx + p2 - p4), (float) (cy + p3 - p4));
			castle.lineTo((float) (cx + p3 - p4), (float) (cy + p2 - p4));
			castle.lineTo((float) (cx + p3), (float) (cy + p2));
			castle.lineTo((float) (cx), (float) (cy + p1));
			castle.lineTo((float) (cx - p3), (float) (cy + p2));
			castle.lineTo((float) (cx - p3 + p4), (float) (cy + p2 - p4));
			castle.lineTo((float) (cx - p2 + p4), (float) (cy + p3 - p4));
			castle.lineTo((float) (cx - p2), (float) (cy + p3));
			g2d.setColor(patternFg.getColor());
			g2d.fill(castle);
			g2d.setColor(patternFg.getColor().darker());
			g2d.draw(castle);
		} else if (patternShape == PatternShape.SHAPE_SQUARE) {
			double p1 = minsize * 0.35;
			double p2 = p1 * 0.50;

			GeneralPath square_ext = new GeneralPath();
			square_ext.moveTo((float) (cx - p1), (float) (cy));
			square_ext.lineTo((float) (cx), (float) (cy - p1));
			square_ext.lineTo((float) (cx + p1), (float) (cy));
			square_ext.lineTo((float) (cx), (float) (cy + p1));
			g2d.setColor(patternFg.getColor());
			g2d.fill(square_ext);
			g2d.setColor(patternFg.getColor().darker());
			g2d.draw(square_ext);

			GeneralPath square_int = new GeneralPath();
			square_int.moveTo((float) (cx - p2), (float) (cy));
			square_int.lineTo((float) (cx), (float) (cy - p2));
			square_int.lineTo((float) (cx + p2), (float) (cy));
			square_int.lineTo((float) (cx), (float) (cy + p2));
			g2d.setColor(patternBg.getColor());
			g2d.fill(square_int);
			g2d.setColor(patternBg.getColor().darker());
			g2d.draw(square_int);
		} else if (patternShape == PatternShape.SHAPE_TOXIC) {
			double p1 = minsize * 0.30;
			double p2 = p1 * 0.30;
			double p3 = p2 * 1.10;

			Ellipse2D circle_ext = new Ellipse2D.Double(cx - p1, cy - p1, 2 * p1, 2 * p1);
			g2d.setColor(patternFg.getColor());
			g2d.fill(circle_ext);
			g2d.setColor(patternFg.getColor().darker());
			g2d.draw(circle_ext);

			GeneralPath cross_int = new GeneralPath();
			cross_int.moveTo((float) (cx - p2), (float) (cy));
			cross_int.lineTo((float) (cx - p2 - p3), (float) (cy - p3));
			cross_int.lineTo((float) (cx - p3), (float) (cy - p2 - p3));

			cross_int.lineTo((float) (cx), (float) (cy - p2));
			cross_int.lineTo((float) (cx + p3), (float) (cy - p2 - p3));
			cross_int.lineTo((float) (cx + p2 + p3), (float) (cy - p3));

			cross_int.lineTo((float) (cx + p2), (float) (cy));
			cross_int.lineTo((float) (cx + p2 + p3), (float) (cy + p3));
			cross_int.lineTo((float) (cx + p3), (float) (cy + p2 + p3));

			cross_int.lineTo((float) (cx), (float) (cy + p2));
			cross_int.lineTo((float) (cx - p3), (float) (cy + p2 + p3));
			cross_int.lineTo((float) (cx - p2 - p3), (float) (cy + p3));

			g2d.setColor(patternBg.getColor());
			g2d.fill(cross_int);
			g2d.setColor(patternBg.getColor().darker());
			g2d.draw(cross_int);
		} else if (patternShape == PatternShape.SHAPE_STAR) {
			double rad1 = minsize * 0.3;
			double rad2 = rad1 * 0.5;

			g2d.setColor(patternFg.getColor());
			GeneralPath star = new GeneralPath();
			star.moveTo((float) (cx - rad1), (float) (cy));

			for (int a = 0; a <= 360; a += 30) {
				double rad;

				if (a % 60 == 0) {
					rad = rad1;
				} else {
					rad = rad2;
				}

				double arad = Math.PI * 2. * a / 360.;

				star.lineTo((float) (cx + rad * Math.cos(arad)),
						(float) (cy + rad * Math.sin(arad)));
			}

			g2d.fill(star);
		}

		g2d.setClip(previous_clip);
	}

	public static List<Pattern> getAllPatterns() {
		return Arrays.asList(values());
	}

	public static List<Pattern> getNonDefaultPatterns() {
		List<Pattern> allPatterns = getAllPatterns();
		return allPatterns.subList(1, allPatterns.size());
	}

	public static Pattern getDefaultPattern() {
		return PAT_00;
	}

	public static Pattern getPatternByCode(int patternCode) {
		Pattern result = null;

		for (Pattern pattern : getAllPatterns()) {
			if (pattern.getCode() == patternCode) {
				result = pattern;
			}
		}

		return result;
	}

	@Override
	public String toString() {
		return String.format("Pat%02d", patternCode);
	}

}
