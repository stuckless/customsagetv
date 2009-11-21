package org.jdna.bmt.web.client.ui.prefs;

import org.jdna.bmt.web.client.ui.input.ArrayEditorTextBox;
import org.jdna.bmt.web.client.ui.input.FileChooserTextBox;
import org.jdna.bmt.web.client.util.Log;

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class EditorFactory {
    public static Widget createEditor(String id, TextBox tb, String caption) {
        if (id==null || id.trim().length()==0) {
            return null;
        }
        
        if ("dirChooser".equals(id)) {
            return new FileChooserTextBox(tb, caption, true);
        } else if ("fileChooser".equals(id)) {
                return new FileChooserTextBox(tb, caption, false);
        } else if ("array".equals(id)) {
            return new ArrayEditorTextBox(tb, caption, ",");
        } else if ("array(;)".equals(id)) {
            return new ArrayEditorTextBox(tb, caption, ";");
        } else {
            Log.debug("Missing Editor: " + id + " for field: " + caption);
        }
        return null;
    }

    public static Widget createEditor(String editor) {
        if ("log4jEditor".equals(editor)) {
            return new Log4jEditorPanel();
        } else if ("videoSourcesEditor".equals(editor)) {
            return new VideoSourcesEditorPanel();
        } else {
            Log.debug("Unknown Editor: " + editor);
        }
        return null;
    }
}
