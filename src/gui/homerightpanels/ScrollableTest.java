package gui.homerightpanels;
import javax.swing.*;
import java.awt.*;

public class ScrollableTest extends JPanel {
    public ScrollableTest() {
        // Set layout for the main panel (this will act as a container for JScrollPane)
        setLayout(null);

        // Create the content panel that will hold components
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null); // Use null layout for absolute positioning
        contentPanel.setPreferredSize(new Dimension(800, 1000)); // Set the preferred size for scrolling

        // Add components to the content panel
        for (int i = 0; i < 20; i++) {
            JLabel label = new JLabel("Label " + (i + 1));
            label.setBounds(50, i * 50, 200, 30); // Set bounds for each label
            contentPanel.add(label);
        }

        // Wrap the content panel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBounds(300, 0, 900, 800); // Set the bounds for the scrollable panel
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add the scroll pane to this panel
        add(scrollPane);
    }
}
