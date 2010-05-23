package org.alcibiade.eternity.editor.gui;

import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * This component will receive document updates and scroll the displaying text
 * area automatically.
 * 
 * @author Yannick Kirschhoffer
 * 
 */
public class Autoscroller implements DocumentListener {

	/**
	 * The text area that this component should update.
	 */
	private JTextArea textArea;

	/**
	 * Create a new scroller
	 * 
	 * @param textArea
	 *            The target text area component
	 */
	public Autoscroller(JTextArea textArea) {
		this.textArea = textArea;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seejavax.swing.event.DocumentListener#changedUpdate(javax.swing.event.
	 * DocumentEvent)
	 */
	public void changedUpdate(DocumentEvent e) {
		textArea.setCaretPosition(e.getDocument().getLength());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seejavax.swing.event.DocumentListener#insertUpdate(javax.swing.event.
	 * DocumentEvent)
	 */
	public void insertUpdate(DocumentEvent e) {
		textArea.setCaretPosition(e.getDocument().getLength());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seejavax.swing.event.DocumentListener#removeUpdate(javax.swing.event.
	 * DocumentEvent)
	 */
	public void removeUpdate(DocumentEvent e) {
		textArea.setCaretPosition(e.getDocument().getLength());
	}

}
