/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wizard.product;

import com.wizard.interfaces.ConsoleAskable;
import com.wizard.interfaces.Filterable;
import com.wizard.interfaces.TablePrintable;
import com.wizard.utils.ConsoleUtils;
import com.wizard.utils.DataValidator;
import java.util.Arrays;

/**
 *
 * @author ilichh1
 */
public class Product implements Filterable, TablePrintable, ConsoleAskable {
    // CATEGORIES
    public static final int BIKES = 0;
    public static final int WATER_GUNS = 1;
    public static final int DOLLS = 2;
    public static final int BEARS = 3;
    public static final int TABLE_GAMES = 4;
    public static final int RC_CARS = 5;
    public static final String[] CATEGORIES = new String[] {
        "Bicicletas",
        "Pistolas de agua",
        "Muñecas / Muñecos",
        "Osos de felpa",
        "Juegos de mesa",
        "Carros a control remoto"
    };
    
    // Fields
    private String name;
    private int category;
    private int stock;
    private double price;
    
    public Product() {
        this.name = null;
        this.category = 0;
        this.stock = 0;
        this.price = Double.NaN;
    }
    
    public Product(String name, int category, int stock, double price) {
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }
    
    @Override
    public String[] getAskableFieldNames() {
        return new String[] {"name", "category", "stock", "price"};
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
                    case "category":
                        printToyCategories();
                        System.out.print("Categoria del producto: ");
                        // Substract one becaus of array index starts on 0
                        this.setCategory(ConsoleUtils.askForInteger() - 1);
                    break;
                    case "stock":
                        System.out.print("Existencia del producto: ");
                        this.setStock(ConsoleUtils.askForInteger());
                    break;
                    case "price":
                        System.out.print("Precio del producto: ");
                        this.setPrice(ConsoleUtils.askForDouble());
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
    
    // Getters / Setters
    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name.length() < 3)
            throw new Exception("El nombre debe tener mínimo 3 letras.");
        this.name = name;
    }

    public String getCategory() {
        return CATEGORIES[this.category];
    }

    public void setCategory(int category) throws Exception {
        if (!DataValidator.validateToyCategory(category))
            throw new Exception("Esa categoria no existe");
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) throws Exception {
        if (stock < 0)
            throw new Exception("No puede haber existencia negativa.");
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws Exception {
        if (price < 0)
            throw new Exception("No puede existir un precio negativo.");
        this.price = price;
    }
    
    // STATIC MEHTOD USED ONLY TO PRINT ALL TOYS CATEGORIES
    public static void printToyCategories() {
        int categoryId = 1;
        
        int longestCategoryName = 0;
        for (String category : CATEGORIES)
            if (category.length() > longestCategoryName)
                longestCategoryName = category.length();
        
        longestCategoryName += 8;
        
        String topLine = "==== Categoria ====";
        System.out.print("\n" + topLine + "\n");
        for (String category : CATEGORIES) {
            String lineToPrint = " *  " + category + " ";
            String remainingDots = "";
            for (int i = 0; i < longestCategoryName - lineToPrint.length(); i++)
                remainingDots += ".";
            System.out.println(lineToPrint + remainingDots + " " + categoryId + ")");
            categoryId++;
        }
        
        String bottomDividerLine = "";
        for(int i = 0; i < topLine.length(); i++) {
            bottomDividerLine += "=";
        }
        
        System.out.println(bottomDividerLine);
    }
    
    @Override
    public String[] toStringArray() {
        return new String[] {
            this.getName(),
            this.getCategory(),
            Integer.toString(this.getStock()),
            Double.toString(this.getPrice())
        };
    }
    
    @Override
    public String toString() {
        return Arrays.toString(this.toStringArray());
    }
    
}
