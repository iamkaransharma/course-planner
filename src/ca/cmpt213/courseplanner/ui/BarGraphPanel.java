package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;
import ca.cmpt213.bargraph.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
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
    protected Component getComponent() {
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        panel.setBackground(Color.white);
        // Constructor for BarGraphModel and BarGraphIcon

        JPanel semesterGraphs = new JPanel();
        int[] data = {8,7,9};
        String[] titles = {"Spring","Summer","Fall"};
        BarGraphModel testGraphModel = new BarGraphModel(data,titles);
        testGraphModel.setData(data);

        BarGraphIcon testGraph = new BarGraphIcon(testGraphModel,50,100);

        //Graphics g = new Graphics();
        //testGraph.paintIcon(testGraphModel,,0,0);

        JPanel locationGraphs = new JPanel();

        panel.add(semesterGraphs);

        return panel;
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
