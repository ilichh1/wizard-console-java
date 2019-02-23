/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wizard.main;

import com.wizard.utils.ConsoleUtils;
import com.wizard.utils.menu.MenuController;

/**
 *
 * @author ilichh1
 */
public class WizardConsole {
    
    public static final MenuController MENU_CONTROLLER = new MenuController();
    public static boolean isExecutionEnded = false;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ConsoleUtils.validateUsername();
        do {
            MENU_CONTROLLER.startMenuController();
        } while(!isExecutionEnded);
    }
    
}
