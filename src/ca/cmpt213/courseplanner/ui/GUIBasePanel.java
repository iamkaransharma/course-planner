package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-08-03.
 */
abstract public class GUIBasePanel extends JPanel {

    private CoursePlanner coursePlanner;
    private JPanel internalPanel;
    private String title;

    public GUIBasePanel(CoursePlanner coursePlanner, String title) {
        this.coursePlanner = coursePlanner;
        this.title = title;
        JLabel titleLabel = new JLabel(title, JLabel.LEFT);
        titleLabel.setForeground(Color.blue);
        this.setLayout(new BorderLayout());
        this.add(titleLabel, BorderLayout.NORTH);
        this.internalPanel = new JPanel();
    }

    protected void setInternalPanel(JPanel internalPanel) {
        removeAll();
        JLabel titleLabel = new JLabel(title, JLabel.LEFT);
        titleLabel.setForeground(Color.blue);
        this.add(titleLabel, BorderLayout.NORTH);
        internalPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        this.add(internalPanel, BorderLayout.CENTER);
    }

    protected void resizeHorizontallyOnly(JComponent component) {
        Dimension prefSize = component.getPreferredSize();
        Dimension newSize = new Dimension(
                Integer.MAX_VALUE,
                (int) prefSize.getHeight()
        );
        component.setMaximumSize(newSize);
    }

    protected CoursePlanner getModel() {
        return coursePlanner;
    }
}
