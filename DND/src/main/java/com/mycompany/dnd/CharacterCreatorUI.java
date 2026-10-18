package com.mycompany.dnd;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 *
 * @author gokuu
 */
// Handles the user interface for creating characters
public class CharacterCreatorUI extends JFrame {
    private JTextField nameField, extraInfoField;
    private JComboBox<String> classBox, alignmentBox;
    private JTextField[] attributeFields;
    private JButton rollButton, saveButton, deleteButton, newCharButton;
    private JTextArea sheetArea;
    private Random random;

    public CharacterCreatorUI() {
        super("D&D Character Creator");
        random = new Random();
        setLayout(new BorderLayout());

        // Top panel for character info
        JPanel infoPanel = new JPanel(new GridLayout(4, 2));
        infoPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        infoPanel.add(nameField);

        infoPanel.add(new JLabel("Class:"));
        classBox = new JComboBox<>(new String[]{"Fighter", "Wizard", "Rogue", "Cleric", "Barbarian"});
        infoPanel.add(classBox);

        infoPanel.add(new JLabel("Alignment:"));
        alignmentBox = new JComboBox<>(new String[]{"Lawful Good", "Neutral", "Chaotic Evil"});
        infoPanel.add(alignmentBox);

        infoPanel.add(new JLabel("Extra Info:"));
        extraInfoField = new JTextField();
        infoPanel.add(extraInfoField);

        add(infoPanel, BorderLayout.NORTH);

        // Middle panel for attributes
        JPanel attrPanel = new JPanel(new GridLayout(6, 2));
        String[] attrNames = {"STR", "DEX", "CON", "INT", "WIS", "CHA"};
        attributeFields = new JTextField[6];
        for (int i = 0; i < 6; i++) {
            attrPanel.add(new JLabel(attrNames[i] + ":"));
            attributeFields[i] = new JTextField();
            attrPanel.add(attributeFields[i]);
        }
        add(attrPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        rollButton = new JButton("Roll Dice");
        saveButton = new JButton("Save Character");
        deleteButton = new JButton("Delete Character");
        newCharButton = new JButton("New Character");
        buttonPanel.add(rollButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(newCharButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Character sheet display
        sheetArea = new JTextArea(10, 30);
        sheetArea.setEditable(false);
        add(new JScrollPane(sheetArea), BorderLayout.EAST);

        // Event listeners
        rollButton.addActionListener(e -> rollAttributes());
        saveButton.addActionListener(e -> saveCharacter());
        deleteButton.addActionListener(e -> deleteCharacter());
        newCharButton.addActionListener(e -> newCharacter());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // Rolls six random numbers between 1–20
    private void rollAttributes() {
        for (int i = 0; i < attributeFields.length; i++) {
            attributeFields[i].setText(String.valueOf(random.nextInt(20) + 1));
        }
    }

    // Saves character and displays sheet
    private void saveCharacter() {
        int[] attrs = new int[6];
        for (int i = 0; i < attributeFields.length; i++) {
            attrs[i] = Integer.parseInt(attributeFields[i].getText());
        }
        Character c = new Character(
            nameField.getText(),
            (String) classBox.getSelectedItem(),
            (String) alignmentBox.getSelectedItem(),
            extraInfoField.getText(),
            attrs
        );
        sheetArea.setText(c.getCharacterSheet());
    }

    // Deletes character info
    private void deleteCharacter() {
        sheetArea.setText("Character deleted.");
    }

    // Clears fields for new character
    private void newCharacter() {
        nameField.setText("");
        extraInfoField.setText("");
        for (JTextField field : attributeFields) {
            field.setText("");
        }
        sheetArea.setText("");
    }
}
