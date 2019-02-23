/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wizard.utils.menu;

import com.wizard.interfaces.MainCallable;
import com.wizard.utils.ConsoleUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ilichh1
 */
public class Menu {
    
    private final List<MenuEntry> menuOptions;
    private final String menuName;
    private int longestMenuLabel = 0;
    private HashMap<Character, String> menuMap;
    
    public Menu(String name, MenuEntry[] initialOptions) throws Exception {
        this.menuName = name;
        this.menuOptions = new ArrayList<>(Arrays.asList(initialOptions));
        this.determineLongestMenuLabel();
    }
    
    private void determineLongestMenuLabel() throws Exception {
        this.menuMap = new HashMap<>();
        
        for (MenuEntry menuOption : menuOptions) {
            
            if (this.menuMap.getOrDefault(menuOption.menuOption, "NO_OPTION").equals("NO_OPTION")) {
                this.menuMap.put(menuOption.menuOption, menuOption.actionToCall);
            } else {
                throw new Exception("No pueden existir dos opciones de menú con la misma letra.");
            }
            
            if (menuOption.menuLabel.length() > this.longestMenuLabel)
                this.longestMenuLabel = menuOption.menuLabel.length();
        }
    }
    
    private boolean validateAndCallAction(char selectedOption, MainCallable actioner) {
        selectedOption = Character.toUpperCase(selectedOption);
        if (this.menuMap.getOrDefault(selectedOption, "NO_OPTION").equals("NO_OPTION")) {
            ConsoleUtils.printErrorMessage("Esa opción no existe.");
            return false;
        }
        actioner.doSpecificAction(this.menuMap.get(selectedOption));
        return true;
    }
    
    private boolean promptUser(MainCallable actioner) {
        for(;;) {
            System.out.print("Su eleccion: ");
            if (this.validateAndCallAction(ConsoleUtils.askForCharacter(), actioner))
                return false;
        }
    }
    
    public void addMenuOption(MenuEntry newOption) {
        this.menuOptions.add(newOption);
        if(newOption.menuLabel.length() > this.longestMenuLabel)
            this.longestMenuLabel = newOption.menuLabel.length();
    }
    
    public boolean printMenu(MainCallable actioner) {
        int suposedFinalLength = this.longestMenuLabel + 8;
        
        String topDividerLine = "======== " + this.menuName + " ========";
        String bottomDividerLine = "";
        
        for (int i = 0; i < topDividerLine.length(); i++)
            bottomDividerLine += "=";
        
        
        System.out.print("\n" + topDividerLine + "\n");
        for (MenuEntry menuOption : this.menuOptions) {
            String lineToPrint = " *  " + menuOption.menuLabel + " ";
            String remainingDots = "";
            for (int i = 0; i < suposedFinalLength - lineToPrint.length(); i++)
                remainingDots += ".";
            System.out.println(lineToPrint + remainingDots + " " + menuOption.menuOption + ")");
        }
        System.out.println(bottomDividerLine);
        
        return this.promptUser(actioner);
    }
    
    public void printMenuWithoutPrompt(MainCallable actioner) {
        int suposedFinalLength = this.longestMenuLabel + 8;
        
        String topDividerLine = "======== " + this.menuName + " ========";
        String bottomDividerLine = "";
        
        for (int i = 0; i < topDividerLine.length(); i++)
            bottomDividerLine += "=";
        
        
        System.out.print("\n" + topDividerLine + "\n");
        for (MenuEntry menuOption : this.menuOptions) {
            String lineToPrint = " *  " + menuOption.menuLabel + " ";
            String remainingDots = "";
            for (int i = 0; i < suposedFinalLength - lineToPrint.length(); i++)
                remainingDots += ".";
            System.out.println(lineToPrint + remainingDots + " " + menuOption.menuOption + ")");
        }
        System.out.println(bottomDividerLine);
    }
}
