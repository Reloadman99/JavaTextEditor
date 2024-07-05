package reloadman;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main {
    private static JFrame frame;
    private static JTextArea textArea;
    private static JFileChooser fileChooser;

    public static void main(String[] args) {
    	frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Simple Notepad");
        frame.setVisible(true);
        // Create a JTextArea
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create a JMenuBar and add File menu
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // Create File menu items (New, Open, Save, Exit)
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem themeItem = new JMenuItem("Toggle theme");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(themeItem);
        fileMenu.add(exitItem);

        // Add ActionListener to menu items
        newItem.addActionListener(e -> newFile());
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
        exitItem.addActionListener(e -> System.exit(0));
    	frame.setVisible(true);
    }

    private static void newFile() {
        textArea.setText(""); // Clear the text area
    }

    private static void openFile() {
        if (fileChooser == null) {
            fileChooser = new JFileChooser();
        }
        int returnVal = fileChooser.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.setText(""); // Clear the text area
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveFile() {
        if (fileChooser == null) {
            fileChooser = new JFileChooser();
        }
        int returnVal = fileChooser.showSaveDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
