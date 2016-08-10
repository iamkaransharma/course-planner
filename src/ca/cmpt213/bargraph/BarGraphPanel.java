package ca.cmpt213.bargraph;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Karan on 09/08/2016.
 */
public class BarGraphPanel extends JPanel {
    public static final int ICON_WIDTH = 250;
    public static final int ICON_HEIGHT = 180;
    private static final Dimension VERTICAL_PADDING = new Dimension(0, 10);
    private BarGraphModel barGraphModel;

    public BarGraphPanel(String title, String[] barTitles) {
        int[] arrayOfZeroes = new int[barTitles.length];
        this.barGraphModel = new BarGraphModel(arrayOfZeroes, barTitles);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createRigidArea(VERTICAL_PADDING));

        JLabel titleLabel = new JLabel(title);
        add(titleLabel);

        BarGraphIcon barGraph = new BarGraphIcon(barGraphModel, ICON_WIDTH, ICON_HEIGHT);
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
