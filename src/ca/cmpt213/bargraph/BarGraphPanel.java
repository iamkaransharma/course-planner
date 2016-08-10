package ca.cmpt213.bargraph;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Karan on 09/08/2016.
 */
public class BarGraphPanel extends JPanel {
    private BarGraphModel barGraphModel;
    private String title;

    public BarGraphPanel(String title, String[] barTitles) {
        this.title = title;
        int[] arrayOfZeroes = new int[barTitles.length];
        this.barGraphModel = new BarGraphModel(arrayOfZeroes, barTitles);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel titleLabel = new JLabel(title);
        add(titleLabel);

        BarGraphIcon barGraph = new BarGraphIcon(barGraphModel, 250, 180);
        add(new JLabel(barGraph));
    }

    public void resetGraph() {
        int numberOfBars = barGraphModel.getNumberBars();
        barGraphModel.setData(new int[numberOfBars]);
    }

    public void updateGraph(int[] data) {
        barGraphModel.setData(data);
    }
}
