package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-08-03.
 */
abstract public class GUIPanel extends JPanel {

    private CoursePlanner coursePlanner;
    private JPanel internalPanel;

    public GUIPanel(CoursePlanner coursePlanner, String title) {
        this.coursePlanner = coursePlanner;
        JLabel titleLabel = new JLabel(title, JLabel.LEFT);
        titleLabel.setForeground(Color.blue);
        this.setLayout(new BorderLayout());
        this.add(titleLabel, BorderLayout.NORTH);
        this.internalPanel = new JPanel();
    }
    
    protected void setInternalPanel(JPanel internalPanel) {
        this.internalPanel = internalPanel;
        internalPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        this.add(internalPanel, BorderLayout.CENTER);
    }

    protected void resizeHorizontallyOnly() {
        Dimension prefSize = getPreferredSize();
        Dimension newSize = new Dimension(
                Integer.MAX_VALUE,
                (int) prefSize.getHeight()
        );
        setMaximumSize(newSize);
    }

    protected CoursePlanner getModel() {
        return coursePlanner;
    }
}
