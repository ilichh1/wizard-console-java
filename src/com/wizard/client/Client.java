/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wizard.client;

import com.wizard.abstracts.Person;
import com.wizard.interfaces.ConsoleAskable;
import com.wizard.interfaces.Filterable;
import com.wizard.interfaces.TablePrintable;
import com.wizard.utils.ConsoleUtils;
import com.wizard.utils.DataValidator;

/**
 *
 * @author ilichh1
 */
public class Client extends Person implements TablePrintable, ConsoleAskable, Filterable {
    
    private String phone;
    private String email;
    private String address;
    
    public Client(String name, String surname, String phone, String email, String address) {
        super(name, surname);
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
    
    public Client() {
        super();
        this.phone = null;
        this.email = null;
        this.address = null;
    }
    
    @Override
    public String[] getAskableFieldNames() {
        return new String[] {
            "name",
            "surname",
            "phone",
            "email",
            "address"
        };
    }
    
    @Override
    public void askForFieldName(String fieldName) {
        boolean isDataValid = false;
        do {
            try {
                switch (fieldName) {
                    case "name":
                        super.askForFieldName("name");
                    break;
                    case "surname":
                        super.askForFieldName("surname");
                    break;
                    case "phone":
                        System.out.print("Télefono: ");
                        this.setPhone(ConsoleUtils.askForString());
                    break;
                    case "email":
                        System.out.print("Correo électronico: ");
                        this.setEmail(ConsoleUtils.askForString());
                    break;
                    case "address":
                        System.out.print("Dirección: ");
                        this.setAddress(ConsoleUtils.askForString());
                    break;
                    default:
                        System.out.println("ERROR: ESE CAMPO NO EXISTE EN 'Client'");
                        System.exit(0);
                    return;
                }
                isDataValid = true;
            } catch (Exception ex)  {
                ConsoleUtils.printErrorMessage(ex.getLocalizedMessage());
            }
        } while(!isDataValid);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws Exception {
        if(phone.length() != 10)
            throw new Exception("El télefono tiene que tener exactamente 10 dígitos.");
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if(!DataValidator.validateEmail(email))
            throw new Exception("Ese email es inválido.");
        this.email = email;
    }

    public String getAddress() {
        return address;
    }
    
    public String getTruncatedAddress() {
        if (this.address.length() > 48) {
            return this.address.substring(0, 32) + "...";
        }
        return this.address;
    }

    public void setAddress(String address) throws Exception {
        if(address.length() < 32) 
            throw new Exception("La direcicón es muy corta.");
        this.address = address;
    }

    @Override
    public String[] toStringArray() {
        return new String[] {
            this.getName(),
            this.getSurname(),
            this.getPhone(),
            this.getEmail(),
            this.getTruncatedAddress()
        };
    }
}
