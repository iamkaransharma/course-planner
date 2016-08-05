package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class OfferingDetailsPanel extends GUIPanel {

    public OfferingDetailsPanel(CoursePlanner coursePlanner){
        super(coursePlanner);
        this.setLabel("Details of Course Offering");
    }

    @Override
    protected JPanel getPanel(){
        JPanel offeringDetailsPanel = new JPanel();
        offeringDetailsPanel.setLayout(new BorderLayout());
        offeringDetailsPanel.add(getLabel(),BorderLayout.NORTH);
        offeringDetailsPanel.add(getComponent(),BorderLayout.CENTER);
        offeringDetailsPanel.setPreferredSize(new Dimension(250,300));
        return offeringDetailsPanel;
    }

}
