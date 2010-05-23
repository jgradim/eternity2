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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.JComponent;

import org.alcibiade.eternity.editor.gui.transfer.DataFlavors;
import org.alcibiade.eternity.editor.gui.transfer.TransferablePattern;
import org.alcibiade.eternity.editor.gui.transfer.TransferableQuadModel;
import org.alcibiade.eternity.editor.model.Pattern;
import org.alcibiade.eternity.editor.model.QuadModel;
import org.alcibiade.eternity.editor.model.QuadObserver;

public class QuadView extends JComponent implements QuadObserver, MouseListener,
		MouseWheelListener, MouseMotionListener, DragGestureListener, DragSourceListener,
		DropTargetListener, KeyListener {

	private static final int CURSOR_COMPLETE_QUAD = 5;
	private static final Color COLOR_UNLOCKED = Color.BLACK;
	private static final Color COLOR_LOCKED = Color.YELLOW;
	private static final Color COLOR_ERROR = Color.RED;
	private static final Color COLOR_CURSOR = new Color(0xF2F8FD);

	private static final long serialVersionUID = 1L;

	private QuadModel model;
	private boolean editable;
	private boolean showPatternIds;
	private boolean[] error = new boolean[4];

	private Integer cursor = null;

	private DragSource dragSource;

	public QuadView(QuadModel model) {
		this.model = model;
		this.model.addQuadObserver(this);
		setEditable(true);
		setShowPatternIds(false);
		addMouseListener(this);
		addMouseWheelListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		dragSource = DragSource.getDefaultDragSource();
		dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY_OR_MOVE, this);
		setDropTarget(new DropTarget(this, this));
		setFocusable(true);

		setError(QuadModel.DIR_NORTH, false);
		setError(QuadModel.DIR_EAST, false);
		setError(QuadModel.DIR_WEST, false);
		setError(QuadModel.DIR_SOUTH, false);
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public void setShowPatternIds(boolean show) {
		this.showPatternIds = show;
		repaint();
	}

	public boolean getShowPatternIds() {
		return showPatternIds;
	}

	public void setError(int direction, boolean error) {
		if (error != this.error[direction]) {
			this.error[direction] = error;
			repaint();
		}
	}

	public QuadModel getModel() {
		return model;
	}

	public void quadUpdated() {
		repaint();
	}

	@Override
	public void paint(Graphics graphics) {
		Dimension2D size = getSize();
		double wd = size.getWidth();
		double ht = size.getHeight();
		double minsize = Math.min(wd, ht);
		Graphics2D g2d = (Graphics2D) graphics;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Fill background

		// Draw the patterns

		double dx = wd / 2;
		double dy = ht / 2;

		Shape prev_clip = g2d.getClip();
		g2d.clip(new Rectangle2D.Double(0, 0, wd - 0.5, ht - 0.5));
		AffineTransform prev_trx = g2d.getTransform();

		g2d.translate(0, -dy);
		model.getPattern(QuadModel.DIR_NORTH).paint(g2d, size);
		g2d.translate(dx, dy);
		model.getPattern(QuadModel.DIR_EAST).paint(g2d, size);
		g2d.translate(-dx, dy);
		model.getPattern(QuadModel.DIR_SOUTH).paint(g2d, size);
		g2d.translate(-dx, -dy);
		model.getPattern(QuadModel.DIR_WEST).paint(g2d, size);

		g2d.setTransform(prev_trx);
		g2d.setClip(prev_clip);

		// Draw the separation lines
		if (model.isLocked()) {
			g2d.setColor(COLOR_LOCKED);
		} else {
			g2d.setColor(COLOR_UNLOCKED);
		}

		g2d.setStroke(new BasicStroke((float) (minsize / 50.)));
		g2d.draw(new Rectangle2D.Double(0, 0, wd, ht));

		g2d.draw(new Line2D.Double(0, 0, wd, ht));
		g2d.draw(new Line2D.Double(wd, 0, 0, ht));

		if (cursor != null && editable) {
			g2d.setColor(COLOR_CURSOR);
			if (cursor == CURSOR_COMPLETE_QUAD) {
				drawTriangle(g2d, dx, dy, wd, ht, 0);
				drawTriangle(g2d, dx, dy, wd, ht, 1);
				drawTriangle(g2d, dx, dy, wd, ht, 2);
				drawTriangle(g2d, dx, dy, wd, ht, 3);
			} else {
				drawTriangle(g2d, dx, dy, wd, ht, cursor);
			}
		}

		g2d.setColor(COLOR_ERROR);
		g2d.setStroke(new BasicStroke((float) (minsize / 20.)));
		for (int direction = 0; direction < 4; direction++) {
			if (error[direction]) {
				drawTriangle(g2d, dx, dy, wd, ht, direction);
			}
		}

		if (showPatternIds) {
			Font font = new Font("SansSerif", Font.PLAIN, (int) (dy / 3.));
			g2d.setFont(font);

			g2d.setColor(Color.BLACK);
			drawPatternIds(g2d, dx, 1, dy, 1);
			g2d.setColor(Color.WHITE);
			drawPatternIds(g2d, dx, 0, dy, 0);
		}
	}

	private void drawPatternIds(Graphics2D g2d, double dx, double ox, double dy, double oy) {
		drawPatternId(g2d, Integer.toString(model.getPattern(QuadModel.DIR_NORTH).getCode()), dx
				+ ox, 0.3 * dy + oy);
		drawPatternId(g2d, Integer.toString(model.getPattern(QuadModel.DIR_SOUTH).getCode()), dx
				+ ox, 1.7 * dy + oy);
		drawPatternId(g2d, Integer.toString(model.getPattern(QuadModel.DIR_EAST).getCode()), 1.7
				* dx + ox, dy + oy);
		drawPatternId(g2d, Integer.toString(model.getPattern(QuadModel.DIR_WEST).getCode()), 0.3
				* dx + ox, dy + oy);

	}

	private void drawPatternId(Graphics2D g2d, String text, double x, double y) {
		Rectangle2D bounds = g2d.getFont().getStringBounds(text, g2d.getFontRenderContext());
		g2d.drawString(text, (float) (x - bounds.getWidth() / 2),
				(float) (y + bounds.getHeight() / 3));
	}

	private void drawTriangle(Graphics2D g2d, double dx, double dy, double wd, double ht,
			int direction) {
		GeneralPath triangle = new GeneralPath();
		triangle.moveTo((float) (dx), (float) (dy));

		if (direction == QuadModel.DIR_NORTH) {
			triangle.lineTo((0), (0));
			triangle.lineTo((float) (wd), (0));
		} else if (direction == QuadModel.DIR_EAST) {
			triangle.lineTo((float) (wd), (0));
			triangle.lineTo((float) (wd), (float) (ht));
		} else if (direction == QuadModel.DIR_SOUTH) {
			triangle.lineTo((float) (wd), (float) (ht));
			triangle.lineTo((0), (float) (ht));
		} else if (direction == QuadModel.DIR_WEST) {
			triangle.lineTo((0), (float) (ht));
			triangle.lineTo((0), (0));
		}

		triangle.lineTo((float) (dx), (float) (dy));

		g2d.draw(triangle);

	}

	/**
	 * MouseListener
	 */

	public void mouseReleased(MouseEvent event) {
		if (editable) {
			switch (event.getButton()) {
			case MouseEvent.BUTTON1:
				Point2D mouse = event.getPoint();
				model.rotatePattern(computeDirection(mouse));
				break;

			case MouseEvent.BUTTON2:
				// Switch the lock status
				model.setLocked(!model.isLocked());
			}
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		if (!hasFocus()) {
			requestFocus();
		}
	}

	public void mouseExited(MouseEvent arg0) {
		this.cursor = null;
		repaint();
	}

	public void mouseClicked(MouseEvent arg0) {
		// Do nothing
	}

	public void mousePressed(MouseEvent arg0) {
		// Do nothing
	}

	/**
	 * MouseMotionListener
	 */

	public void mouseDragged(MouseEvent e) {
		// Do nothing
	}

	public void mouseMoved(MouseEvent e) {
		Point point = e.getPoint();
		handleMouseHover(point, e.isShiftDown());
	}

	/**
	 * MouseWheelListener
	 */

	public void mouseWheelMoved(MouseWheelEvent e) {
		if (editable) {
			if (e.getWheelRotation() < 0) {
				model.rotateClockwise();
			} else {
				model.rotateCounterclockwise();
			}
		}
	}

	/**
	 * KeyListener
	 */

	public void keyPressed(KeyEvent e) {
		if (editable && cursor != null) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				if (cursor == CURSOR_COMPLETE_QUAD) {
					model.rotatePattern(0, true);
					model.rotatePattern(1, true);
					model.rotatePattern(2, true);
					model.rotatePattern(3, true);
				} else {
					model.rotatePattern(cursor, true);
				}
				break;
			case KeyEvent.VK_LEFT:
				if (cursor == CURSOR_COMPLETE_QUAD) {
					model.rotatePattern(0, false);
					model.rotatePattern(1, false);
					model.rotatePattern(2, false);
					model.rotatePattern(3, false);
				} else {
					model.rotatePattern(cursor, false);
				}
				break;
			}

			handleMouseHover(null, e.isShiftDown());
			repaint();
		}
	}

	public void keyReleased(KeyEvent e) {
		handleMouseHover(null, e.isShiftDown());
	}

	public void keyTyped(KeyEvent e) {
		// Do nothing
	}

	/**
	 * DragGestureListener
	 */

	public void dragGestureRecognized(DragGestureEvent dge) {
		try {
			boolean wholeQuad = dge.getTriggerEvent().isShiftDown();

			Transferable transferable;
			if (wholeQuad) {
				transferable = new TransferableQuadModel(model);
			} else {
				Pattern pattern = model.getPattern(computeDirection(dge.getDragOrigin()));
				transferable = new TransferablePattern(pattern);
			}

			dge.startDrag(DragSource.DefaultCopyNoDrop, transferable, this);
		} catch (InvalidDnDOperationException idoe) {
			System.err.println(idoe);
		}
	}

	/**
	 * DragSourceListener
	 */

	public void dragDropEnd(DragSourceDropEvent dsde) {
		cursor = null;
		repaint();
	}

	public void dragEnter(DragSourceDragEvent dsde) {
		// Do nothing
	}

	public void dragExit(DragSourceEvent dse) {
		// Do nothing
	}

	public void dragOver(DragSourceDragEvent dsde) {
		// Do nothing
	}

	public void dropActionChanged(DragSourceDragEvent dsde) {
		// Do nothing
	}

	public void dragEnter(DropTargetDragEvent dtde) {
		// Do nothing
	}

	public void dragExit(DropTargetEvent dte) {
		// Do nothing
	}

	public void dragOver(DropTargetDragEvent dtde) {
		// Do nothing
	}

	/**
	 * DropTargetListener
	 */

	public void drop(DropTargetDropEvent dtde) {
		if (editable) {
			try {
				Transferable transferable = dtde.getTransferable();

				Point2D point = dtde.getLocation();

				if (transferable.isDataFlavorSupported(DataFlavors.quadModelDataFlavor)) {
					QuadModel sourceQuadModel = (QuadModel) transferable
							.getTransferData(DataFlavors.quadModelDataFlavor);
					model.swap(sourceQuadModel);
				} else if (transferable.isDataFlavorSupported(DataFlavors.patternDataFlavor)) {
					Pattern pattern = (Pattern) transferable
							.getTransferData(DataFlavors.patternDataFlavor);
					model.setPattern(computeDirection(point), pattern);
				}

				handleMouseHover(point, false);

				if (!hasFocus()) {
					requestFocus();
				}

			} catch (UnsupportedFlavorException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}

	public void dropActionChanged(DropTargetDragEvent dtde) {
		// Do nothing
	}

	private Point2D lastPoint = null;

	/**
	 * Handle mouse hovering over this component and adjust cursor accordingly.
	 * 
	 * @param point
	 *            Mouse coordinates
	 */
	private void handleMouseHover(Point2D point, boolean fullQuad) {
		if (point == null) {
			point = lastPoint;
		} else {
			lastPoint = point;
		}

		int cursor;

		if (fullQuad) {
			cursor = CURSOR_COMPLETE_QUAD;
		} else {
			cursor = computeDirection(point);
		}

		if (this.cursor == null || cursor != this.cursor) {
			this.cursor = cursor;
			repaint();
		}
	}

	/**
	 * Compute the quad direction from the mouse coordinates.
	 * 
	 * @param point
	 * @return
	 */
	private int computeDirection(Point2D point) {
		int direction;
		Dimension2D size = getSize();
		Point2D relative = new Point2D.Double(point.getX() / size.getWidth(), point.getY()
				/ size.getHeight());

		boolean ne = relative.getX() > relative.getY();
		boolean nw = relative.getX() + relative.getY() <= 1;

		if (ne) {
			if (nw) {
				direction = QuadModel.DIR_NORTH;
			} else {
				direction = QuadModel.DIR_EAST;
			}
		} else {
			if (nw) {
				direction = QuadModel.DIR_WEST;
			} else {
				direction = QuadModel.DIR_SOUTH;
			}
		}

		return direction;
	}
}
