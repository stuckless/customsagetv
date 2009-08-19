package org.jdna.bmt.web.client.ui.layout;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Widget;

public class FlowGrid extends Composite {
    private int widgetCount=0;
    private int cols = 0;
    private FlexTable grid = new FlexTable();
    
    public FlowGrid(int cols) {
        this.cols=cols;
        initWidget(grid);
        System.out.println("=================================================");
    }

    public void add(String text) {
        add(new HTML(text));
    }
    
    public void add(Widget w) {
        int col = widgetCount % (cols);
        int row = (int)((float)widgetCount / (float)cols);
        
        if (col==0) System.out.println("");
        System.out.println("CurCount: " + widgetCount + "; Row: " + row + "; col: " + col + "; newCount: " + (widgetCount+1));
        grid.setWidget(row, col, w);
        grid.getCellFormatter().setAlignment(row, col, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_TOP);
        widgetCount++;
    }
    
}
