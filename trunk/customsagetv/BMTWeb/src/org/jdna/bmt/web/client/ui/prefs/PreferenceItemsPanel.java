package org.jdna.bmt.web.client.ui.prefs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jdna.bmt.web.client.ui.layout.Simple2ColFormLayoutPanel;
import org.jdna.bmt.web.client.ui.util.Dialogs;
import org.jdna.bmt.web.client.ui.util.UpdatablePanel;
import org.jdna.bmt.web.client.util.Log;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;

public class PreferenceItemsPanel extends Composite implements UpdatablePanel {
    private final PreferencesServiceAsync preferencesService = GWT.create(PreferencesService.class);

    private Simple2ColFormLayoutPanel      grid  = null;
    private PrefItem parentItem = null;
    private String header = null;
    private String headerDescription = null;
    

    public PreferenceItemsPanel() {
        grid =  new Simple2ColFormLayoutPanel();
        grid.setWidth("100%");
        initWidget(grid);
    }

    public void setPanelItems(PrefItem parentItem) {
        grid.clear();
        this.parentItem = parentItem;
        header = parentItem.getLabel();
        headerDescription = parentItem.getDescription();

        PrefItem[] prefItems = parentItem.getChildren();
        if (prefItems != null) {
            List<PrefItem> items = new ArrayList<PrefItem>();
            for (PrefItem pitem : prefItems) {
                if (!pitem.isGroup()) {
                    items.add(pitem);
                }
            }

            for (int i = 0; i < items.size(); i++) {
                PrefItem pi = items.get(i);
                PreferenceItemEditor editor = new PreferenceItemEditor(pi);
                grid.add(editor.getLabel(), editor.getEditor());
            }
        }
    }
    
    public String getHeader() {
        return header;
    }

    public void save(final AsyncCallback<UpdatablePanel> callback) {
        final List<PrefItem> l = new LinkedList<PrefItem>();
        for (PrefItem pi : parentItem.getChildren()) {
            if (pi.hasChanged()) {
                l.add(pi);
            }
        }
        if (l.size()>0) {
        preferencesService.savePreferences(l.toArray(new PrefItem[l.size()]), new AsyncCallback<Boolean>() {
            public void onFailure(Throwable caught) {
                callback.onFailure(caught);
            }

            public void onSuccess(Boolean result) {
                if (result) {
                    for (PrefItem pi : l) {
                        pi.setResetValue(pi.getValue());
                    }
                    callback.onSuccess(PreferenceItemsPanel.this);
                } else {
                    Log.error("Failed to save preferences!");
                }
            }
        });
        } else {
            Dialogs.showMessage("Nothing to save.");
        }
    }

    public String getHelp() {
        return headerDescription;
    }

    public boolean isReadonly() {
        return grid.getRowCount()<=0;
    }
}
