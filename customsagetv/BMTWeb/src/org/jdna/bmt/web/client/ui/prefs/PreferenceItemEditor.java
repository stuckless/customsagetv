package org.jdna.bmt.web.client.ui.prefs;

import org.jdna.bmt.web.client.ui.input.ArrayEditorTextBox;
import org.jdna.bmt.web.client.ui.input.InputFactory;
import org.jdna.bmt.web.client.ui.input.LargeStringTextBox;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class PreferenceItemEditor extends Composite {
    private Label    label  = new Label();
    private Widget   editor = null;
    private PrefItem item   = null;

    public PreferenceItemEditor(final PrefItem item) {
        this.item = item;
        this.label = new Label(item.getLabel());
        this.label.setTitle(item.getDescription());

        if (item.getEditor() != null) {
            this.editor = EditorFactory.createEditor(item.getEditor(), InputFactory.bind(new TextBox(), item), item.getLabel());
        }

        if (this.editor == null) {
            if (item.isArray()) {
                this.editor = new ArrayEditorTextBox(InputFactory.bind(new TextBox(), item), item.getLabel(), ",");
            } else if (item.getType() == null || item.getType().equals("string")) {
                this.editor = new LargeStringTextBox(InputFactory.bind(new TextBox(), item), item.getLabel());
            } else if (item.getType().equals("int")) {
                this.editor = InputFactory.bind(InputFactory.createIntTextbox(), item);
            } else if (item.getType().equals("long")) {
                this.editor = InputFactory.bind(InputFactory.createLongTextbox(), item);
            } else if (item.getType().equals("float")) {
                this.editor = InputFactory.bind(InputFactory.createFloatTextbox(), item);
            } else if (item.getType().equals("boolean")) {
                this.editor = InputFactory.bind(new CheckBox(), item);
            } else {
                TextBox tb = new TextBox();
                tb.setValue("No Editor For: " + item.getType());
                this.editor = tb;
            }
        }
    }

    public Label getLabel() {
        return label;
    }

    public Widget getEditor() {
        return editor;
    }
}
