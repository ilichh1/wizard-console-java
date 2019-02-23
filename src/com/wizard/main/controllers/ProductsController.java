/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wizard.main.controllers;

import com.wizard.interfaces.TablePrintable;
import com.wizard.product.Product;
import com.wizard.utils.ConsoleUtils;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author ilichh1
 */
public class ProductsController {
    // FIELDS
    private final ArrayList<Product> controllerArray = new ArrayList<>();
    private static final String[] PRODUCTS_HEADERS = new String[] {
        " ID ",
        "Nombre",
        "Categoria",
        "Existencia",
        "Precio"
    };
    
    public ProductsController() {
        this.initializateProductsController();
    }
    
    public void createAndSaveProduct() {
        Product productToSave = new Product();
        productToSave.promptInConsole();
        
        this.controllerArray.add(productToSave);
        ConsoleUtils.printSuccessMessage("Producto guardado correctamente.");
    }
    
    public void editProduct(int productId) {
        try {
            this.controllerArray.get(productId).promptInConsole();
            ConsoleUtils.printSuccessMessage("Producto editado correctamente");
        } catch (Exception ex) {
            ConsoleUtils.printErrorMessage("No existe ningún producto con ese ID.");
        }
    }
    
    public void removeProduct(int productId) {
        try {
            this.controllerArray.remove(productId);
            ConsoleUtils.printSuccessMessage("Producto eliminado correctamente");
        } catch (Exception ex) {
            ConsoleUtils.printErrorMessage("No existe ningún producto con ese ID.");
        }
    }
    
    public Product getProductById(int id) {
        try {
            return this.controllerArray.get(id);
        } catch (Exception ex) {
            return null;
        }
    }
    
    public void search(String searchPattern) {
        // Aquí me quedé
        // TODO:
        // Añadir la funcionalidad de eliminar un producto
        // Tambien eliminar el resto de cosas
        // Decidir si dejar o quitar "Editar producto"
        ArrayList<Product> filteredProducts = (ArrayList<Product>) this.controllerArray.clone();
        filteredProducts.removeIf(product -> !product.contains(searchPattern));
        
        if(filteredProducts.isEmpty()) {
            ConsoleUtils.printSuccessMessage("La búsqueda terminó sin resultados");
            return;
        }
        
        TablePrintable[] rows = filteredProducts.toArray(new TablePrintable[filteredProducts.size()]);
        ConsoleUtils.printAsTable(rows, PRODUCTS_HEADERS);
    }
    
    private void initializateProductsController() {
        this.controllerArray.addAll(
                Arrays.asList(new Product[] {
                    new Product("Nerf SuperSoaker 501", Product.WATER_GUNS, 15, 499.99),
                    new Product("Barbie Super Estrella", Product.DOLLS, 25, 299.99),
                    new Product("Triciclo 4x4 Apache", Product.BIKES, 5, 1499.99),
                    new Product("El Oso de Tasha", Product.BEARS, 30, 199.99),
                    new Product("Ferrari 911", Product.RC_CARS, 10, 849.99)
                }) );
    }
    
    public void printTable() {
        TablePrintable[] rows = controllerArray.toArray(new TablePrintable[controllerArray.size()]);
        ConsoleUtils.printAsTable(rows, PRODUCTS_HEADERS);
    }
    
}
