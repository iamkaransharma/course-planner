package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;
import ca.cmpt213.bargraph.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class BarGraphPanel extends GUIPanel {

    public BarGraphPanel(CoursePlanner coursePlanner){
        super(coursePlanner);
        this.setLabel("Statistics");
    }

    @Override
    protected JPanel getPanel(){
        JPanel barGraphPanel = new JPanel();
        barGraphPanel.setLayout(new BorderLayout());
        barGraphPanel.add(getLabel(),BorderLayout.NORTH);
        barGraphPanel.add(getComponent(),BorderLayout.CENTER);
        barGraphPanel.setPreferredSize(new Dimension(250,300));
        return barGraphPanel;
    }
}
