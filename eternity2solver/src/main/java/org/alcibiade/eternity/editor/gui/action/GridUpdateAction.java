package org.alcibiade.eternity.editor.gui.action;

import org.alcibiade.eternity.editor.gui.EditableStatusListener;
import org.alcibiade.eternity.editor.gui.EditableStatusProvider;

public abstract class GridUpdateAction extends GridAction implements EditableStatusListener {
	private static final long serialVersionUID = 1L;

	public GridUpdateAction(String name, EditableStatusProvider editable) {
		super(name);
		editable.addEditableStatusListener(this);
	}

	public void editableStatusUpdated(boolean editable) {
		setEnabled(editable);
	}

}
