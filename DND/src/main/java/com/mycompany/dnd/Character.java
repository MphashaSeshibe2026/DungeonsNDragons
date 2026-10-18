/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dnd;

/**
 *
 * @author gokuu
 */
// Represents a D&D character with attributes and modifiers
public class Character {
    private String name;
    private String dndClass;
    private String alignment;
    private String extraInfo;
    private int[] attributes; // STR, DEX, CON, INT, WIS, CHA
    private int[] modifiers;

    // Constructor
    public Character(String name, String dndClass, String alignment, String extraInfo, int[] attributes) {
        this.name = name;
        this.dndClass = dndClass;
        this.alignment = alignment;
        this.extraInfo = extraInfo;
        this.attributes = attributes;
        this.modifiers = calculateModifiers(attributes);
    }

    // Calculates modifiers based on D&D rules: (score - 10) / 2
    private int[] calculateModifiers(int[] attributes) {
        int[] mods = new int[attributes.length];
        for (int i = 0; i < attributes.length; i++) {
            mods[i] = (attributes[i] - 10) / 2;
        }
        return mods;
    }

    // Returns a formatted character sheet string
    public String getCharacterSheet() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Class: ").append(dndClass).append("\n");
        sb.append("Alignment: ").append(alignment).append("\n");
        sb.append("Extra Info: ").append(extraInfo).append("\n\n");
        String[] attrNames = {"STR", "DEX", "CON", "INT", "WIS", "CHA"};
        for (int i = 0; i < attributes.length; i++) {
            sb.append(attrNames[i]).append(": ").append(attributes[i])
              .append(" (Modifier: ").append(modifiers[i]).append(")\n");
        }
        return sb.toString();
    }
}
