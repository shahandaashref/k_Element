import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class KthElementGUI {
    //---Color and font ---//
    private static final Color[] COLORS = {new Color(203, 108, 250), new Color(33, 53, 85),
            new Color(216, 196, 182), new Color(216, 196, 182)};
    private static final Font FONT = new Font("Monotype Corsiva", Font.BOLD, 20);
    private static final Font FONT2 = new Font("Monotype Corsiva", Font.BOLD, 18);

    public static void main(String[] args) {
        JFrame frame = new JFrame("K-th Element Algorithms==>> Divide and Conquer vs Brute Force");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        //---Create the panels and components---//
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        JPanel visualizationPanel = new JPanel(new GridLayout(1, 1));
        JPanel array1Panel = new JPanel();
        JPanel array2Panel = new JPanel();
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(FONT);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        //---Create the input fields and buttons---//
        JLabel label1 = new JLabel("Array 1 (comma-separated): ");
        label1.setFont(FONT);
        label1.setForeground(Color.WHITE);
        JTextField array1Field = new JTextField();
        array1Field.setForeground(new Color(170, 0, 255));
        array1Field.setFont(FONT2);
        JLabel label2 = new JLabel("Array 2 (comma-separated): ");
        label2.setFont(FONT);
        label2.setForeground(Color.WHITE);
        JTextField array2Field = new JTextField();
        array2Field.setForeground(new Color(170, 0, 255));
        array2Field.setFont(FONT2);
        JLabel labelK = new JLabel("K (position): ");
        labelK.setFont(FONT);
        labelK.setForeground(Color.WHITE);
        JTextField kField = new JTextField();
        kField.setForeground(new Color(170, 0, 255));
        kField.setFont(FONT2);
        //--create the buttons--//
        JButton bruteForceButton = new JButton("Brute Force");
        JButton divideConquerButton = new JButton("Divide & Conquer");
        bruteForceButton.setFont(FONT);
        divideConquerButton.setFont(FONT);
        bruteForceButton.setBackground(COLORS[2]);
        bruteForceButton.setForeground(Color.WHITE);
        divideConquerButton.setBackground(COLORS[2]);
        divideConquerButton.setForeground(Color.WHITE);
        //---Set the background colors of the panels---//
        inputPanel.setBackground(COLORS[0]);
        array1Panel.setBackground(COLORS[1]);
        array2Panel.setBackground(COLORS[1]);
        outputArea.setBackground(COLORS[3]);
        //---Add the components to the panels and the frame---//
        inputPanel.add(label1);
        inputPanel.add(array1Field);
        inputPanel.add(label2);
        inputPanel.add(array2Field);
        inputPanel.add(labelK);
        inputPanel.add(kField);
        inputPanel.add(bruteForceButton);
        inputPanel.add(divideConquerButton);
        //---Add the panels to the visualization panel---//
        visualizationPanel.add(array1Panel);
        visualizationPanel.add(array2Panel);
        //---Add the panels to the frame---//
        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(visualizationPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        //impliment the action listener for the Drute Force Button
        bruteForceButton.addActionListener(e -> {
            try {
                int[] array1 = Arrays.stream(array1Field.getText().split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int[] array2 = Arrays.stream(array2Field.getText().split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int k = Integer.parseInt(kField.getText());

                outputArea.setText("Brute Force steps...\n");
                visualizeBruteForce(array1, array2, k, array1Panel, array2Panel, outputArea);
            } catch (Exception ex) {
                outputArea.setText("Error: " + ex.getMessage());
            }
        });
        //---impliment the action listener for the Divide and Conquer Button---//
        divideConquerButton.addActionListener(e -> {
            try {
                int[] array1 = Arrays.stream(array1Field.getText().split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int[] array2 = Arrays.stream(array2Field.getText().split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int k = Integer.parseInt(kField.getText());

                outputArea.setText("Divide and Conquer steps...\n");
                visualizeDivideAndConquer(array1, array2, k, array1Panel, array2Panel, outputArea);
            } catch (Exception ex) {
                outputArea.setText("Error: " + ex.getMessage());
            }
        });

        frame.setVisible(true);
    }

    private static void visualizeBruteForce(int[] array1, int[] array2, int k,JPanel array1Panel, JPanel array2Panel, JTextArea outputArea) {
        /* 
         *  Create  the SwingWorker object that will be used to perform the merge sort algorithm on the 
         *  output area and the components of the output area that will be updated to reflect the progress of the algorithm.
         * impliment merging the two arrays together and find the k-th element 
        */
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                int[] merged = new int[array1.length + array2.length];
                int i = 0, j = 0, index = 0;

                try {
                    while (i < array1.length && j < array2.length) {
                        if (array1[i] < array2[j]) {
                            merged[index++] = array1[i++];
                        } else {
                            merged[index++] = array2[j++];
                        }
                        updatePanels(array1, array2, i, j, array1Panel, array2Panel);
                        outputArea.append("Step: Merged Array: " + Arrays.toString(merged) + "\n");
                        Thread.sleep(1000);
                    }

                    while (i < array1.length) {
                        merged[index++] = array1[i++];
                        updatePanels(array1, array2, i, j, array1Panel, array2Panel);
                        outputArea.append("Step: Merged Array: " + Arrays.toString(merged) + "\n");
                        Thread.sleep(1000);
                    }

                    while (j < array2.length) {
                        merged[index++] = array2[j++];
                        updatePanels(array1, array2, i, j, array1Panel, array2Panel);
                        outputArea.append("Step: Merged Array: " + Arrays.toString(merged) + "\n");
                        Thread.sleep(1000);
                    }

                    outputArea.append("Result: The " + k + "-th element is: " + merged[k - 1] + "\n");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                return null;
            }
        };
        worker.execute();
    }

    private static void visualizeDivideAndConquer(int[] array1, int[] array2, int k,JPanel array1Panel, JPanel array2Panel, JTextArea outputArea) {
        /*
         *  Create the SwingWorker object that will be used to perform the divide and conquer algorithm on the
         *  specified arrays and the output area and the components of the output area that will be updated to reflect the progress of the algorithm.
         */
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try {
                    outputArea.append("Starting Divide and Conquer...\n");
                    int result = findKthElement(array1, array2, k, array1Panel, array2Panel, outputArea);
                    outputArea.append("Result: The " + k + "-th element is: " + result + "\n");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                return null;
            }
        };
        worker.execute();
    }
//----Find the k-th element of the two arrays using the divide and conquer approach----//
    private static int findKthElement(int[] a, int[] b, int k, JPanel array1Panel, JPanel array2Panel, JTextArea outputArea)
            throws InterruptedException {
        if (a.length == 0) return b[k - 1];
        if (b.length == 0) return a[k - 1];
        if (k == 1) return Math.min(a[0], b[0]);

        int mida = Math.min(k / 2, a.length) - 1;
        int midb = Math.min(k / 2, b.length) - 1;

        updatePanels(a, b, mida + 1, midb + 1, array1Panel, array2Panel);
        Thread.sleep(1000);

        if (a[mida] <= b[midb]) {
            outputArea.append("Step: Removing first " + (mida + 1) + " elements from Array A\n");
            return findKthElement(Arrays.copyOfRange(a, mida + 1, a.length), b, k - (mida + 1), array1Panel, array2Panel, outputArea);
        } else {
            outputArea.append("Step: Removing first " + (midb + 1) + " elements from Array B\n");
            return findKthElement(a, Arrays.copyOfRange(b, midb + 1, b.length), k - (midb + 1), array1Panel, array2Panel, outputArea);
        }
    }

    private static void updatePanels(int[] array1, int[] array2, int i, int j, JPanel array1Panel, JPanel array2Panel) {
        SwingUtilities.invokeLater(() -> {
            array1Panel.removeAll();
            array2Panel.removeAll();
    
            for (int idx = 0; idx < array1.length; idx++) {
                JPanel labelPanel = createCircleLabel(String.valueOf(array1[idx]), idx < i);
                array1Panel.add(labelPanel);
            }
    
            for (int idx = 0; idx < array2.length; idx++) {
                JPanel labelPanel = createCircleLabel(String.valueOf(array2[idx]), idx < j);
                array2Panel.add(labelPanel);
            }
    
            array1Panel.revalidate();
            array2Panel.revalidate();
            array1Panel.repaint();
            array2Panel.repaint();
        });
    }
    
    private static JPanel createCircleLabel(String text, boolean highlight) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
                // Set the background color
                Color backgroundColor = highlight ? new Color(255, 177, 0) : COLORS[3];
                g2d.setColor(backgroundColor);
    
                // Draw the circle
                int diameter = Math.min(getWidth(), getHeight());
                g2d.fillOval(0, 0, diameter, diameter);
    
                // Draw the text in the center
                g2d.setColor(Color.WHITE);
                FontMetrics fm = g2d.getFontMetrics(FONT);
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() + textHeight) / 2 - fm.getDescent();
                g2d.setFont(FONT);
                g2d.drawString(text, x, y);
            }
    
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50, 50); // Set the size of the circle
            }
        };
    }
    
}
