/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wizard.product;

import com.wizard.interfaces.ConsoleAskable;
import com.wizard.utils.ConsoleUtils;
import com.wizard.utils.DataValidator;
import com.wizard.utils.menu.MenuController;

/**
 *
 * @author ilichh1
 */
public class SoldProduct implements ConsoleAskable {
    private String name;
    private double price;
    private int amount;
    
    // THIS FIELD ONLY WORKS AS A LOCAL REFERENCE TO GET MAX STOCK AVAILABLE
    private int productIdReference = -1;
    
    public SoldProduct(String nm, double pr, int amt) {
        this.name = nm;
        this.price = pr;
        this.amount = amt;
    }
    
    public SoldProduct() {
        this.name = null;
        this.price = -1;
        this.amount = -1;
    }

    @Override
    public String[] getAskableFieldNames() {
        return new String[] {
            "product",
            "amount"
        };
        // TODO:
        // Definir la manera de preguntar el producto de la venta
        // Definir que campos iba a preguntar a la hora de reigistrar la venta
        // Definir si podrá editar los productos de la venta.
    }

    @Override
    public void askForFieldName(String fieldName) {
        boolean isDataValid;
        do {
            try {
                switch (fieldName) {
                    case "product":
                        MenuController.PRODUCTS_CONTROLLER.printTable();
                        System.out.print("\nIngrese el ID del producto que desea comprar: ");
                        this.setProductName(ConsoleUtils.askForInteger());
                    break;
                    case "amount":
                        System.out.print(
                                "¿Cuantas unidades desea comprar?. (Maximo "
                                + this.getMaxAvailableStock() + ")" );
                        
                    break;
                    default:
                        System.out.println("ERROR: ESE CAMPO NO EXISTE EN 'SoldProduct'");
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
    
    private int getMaxAvailableStock() {
        return MenuController.PRODUCTS_CONTROLLER.getProductById(this.productIdReference).getStock();
    }
    
    // GETTERS & SETTERS
    
    public void setProductName(int productID) throws Exception {
        if (!DataValidator.validateProductExists(productID))
            throw new Exception("Ese producto no existe.");
        this.productIdReference = productID;
        this.name = MenuController.PRODUCTS_CONTROLLER.getProductById(productID).getName();
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setProductAmount(int amountToBuy) throws Exception {
        int productStock = MenuController.PRODUCTS_CONTROLLER.getProductById(this.productIdReference).getStock();
        if (productStock < amountToBuy)
            throw new Exception("No se puede comprar esa cantidad");
        this.amount = amountToBuy;
    }
    
    public Double getTotal() {
        return this.price * this.amount;
    }
}
