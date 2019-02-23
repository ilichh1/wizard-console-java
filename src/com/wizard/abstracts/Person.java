/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wizard.abstracts;

import com.wizard.interfaces.ConsoleAskable;
import com.wizard.utils.ConsoleUtils;
import com.wizard.utils.DataValidator;

/**
 *
 * @author ilichh1
 */
public abstract class Person implements ConsoleAskable {
    private String name;
    private String surname;
    
    public Person () {
        this.name = null;
        this.surname = null;
    }
    
    public Person (String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    
    @Override
    public void askForFieldName(String fieldName) {
        boolean isDataValid = false;
        do {
            try {
                switch (fieldName) {
                    case "name":
                        System.out.print("Nombre: ");
                        this.setName(ConsoleUtils.askForString());
                    break;
                    case "surname":
                        System.out.print("Apellido: ");
                        this.setSurname(ConsoleUtils.askForString());
                    break;
                    default:
                        System.out.println("ERROR: ESE CAMPO NO EXISTE EN 'Person'");
                        System.exit(0);
                    return;
                }
                isDataValid = true;
            } catch (Exception ex)  {
                ConsoleUtils.printErrorMessage(ex.getLocalizedMessage());
            }
        } while(!isDataValid);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (!DataValidator.validateName(name))
            throw new Exception("El nombre es incorrecto.");
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws Exception {
        if (!DataValidator.validateName(surname))
            throw new Exception("El apellido es incorrecto.");
        this.surname = surname;
    }
    
}
