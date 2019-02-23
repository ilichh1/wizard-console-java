/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wizard.salemen;

import com.wizard.abstracts.Person;
import com.wizard.interfaces.TablePrintable;
import com.wizard.utils.ConsoleUtils;
import com.wizard.utils.menu.MenuController;

/**
 *
 * @author ilichh1
 */
public class Saleman extends Person implements TablePrintable {
    
    private static final int NORMAL = 0;
    private static final int ESPECIAL = 1;
    private static final int ESPECIAL_PLUS = 2;
    private static final double[] SALEMEN_COMISSIONS = new double[] {
        0.03,
        0.05,
        0.10
    };
    private static final String[] SALEMEN_TYPES = new String[] {
        "Normal",
        "Especial",
        "Especial Plus"
    };
    
    int salemanId;
    int ventasPrevias;
    
    public Saleman() {
        super();
        this.ventasPrevias = -1;
        this.salemanId = -1;
    }
    
    

    @Override
    public String[] getAskableFieldNames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void askForFieldName(String fieldName) {
        boolean isDataValid;
        do {
            try {
                switch (fieldName) {
                    case "name":
                        System.out.print("Nombre del producto: ");
                        this.setName(ConsoleUtils.askForString());
                    break;
                    default:
                        System.out.println("ERROR: ESE CAMPO NO EXISTE EN 'Product'");
                        System.exit(0);
                    return;
                }
                isDataValid = true;
            } catch (Exception ex)  {
                ConsoleUtils.printErrorMessage(ex.getLocalizedMessage());
                isDataValid = false;
            }
        } while(!isDataValid);
    }
    
    public int getSalemanSells() {
        return this.ventasPrevias + MenuController.SELLS_CONTROLLER.countSellsBySaleman(this.salemanId);
    }

    @Override
    public String[] toStringArray() {
        return new String[] {
            this.getName() + this.getSurname(),
            
        };
    }
}
