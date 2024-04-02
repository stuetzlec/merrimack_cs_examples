package CSC2620_Practice6_6;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author Chris
 */
public class ViewController extends JFrame {

    private Model model;
    private JCheckBox[] boxes = new JCheckBox[26];
    private JTextArea wordslist;

    public ViewController(Model _data) {
        super("Practice 6.6");
        setLayout(new GridBagLayout());

        model = _data;

        // First, initialize the checkboxes
        JPanel letCBs = new JPanel(new GridLayout(13, 2, 5, 5));
        int let = 0;
        for (Character c = 'a'; c <= 'z'; ++c) {
            boxes[let] = new JCheckBox(Character.toString(c), false);
            boxes[let].addItemListener(new CheckBoxHandler());
            letCBs.add(boxes[let++]);
        }

        // Then create the text area display
        wordslist = new JTextArea(model.getWordsWithLetters(boxes));
        JScrollPane wordspane = new JScrollPane(wordslist);
        wordspane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        wordspane.setPreferredSize(new Dimension(200, 700));

        // Add JFreeChart word histogram
        JFreeChart barChart = ChartFactory.createBarChart(
                "Number of Words that Start With Each Letter",
                "First Letter",
                "Number of Words",
                model.createDataset(boxes),
                PlotOrientation.VERTICAL,
                true, true, false);
        ChartPanel chartPanel = new ChartPanel(barChart);

        // Then add the components to the GUI
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(wordspane, gbc);

        // Add the checkboxes for the letters to the display
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(letCBs, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(chartPanel, gbc);

    }

    private class CheckBoxHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            // Rebuild the words list
            String s = model.getWordsWithLetters(boxes);
            wordslist.setText(s);

            validate();
        }

    }
}
