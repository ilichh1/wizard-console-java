/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wizard.utils.menu;

import com.wizard.interfaces.MainCallable;
import com.wizard.main.WizardConsole;
import com.wizard.main.controllers.ClientsController;
import com.wizard.main.controllers.ProductsController;
import com.wizard.main.controllers.SellsController;
import com.wizard.utils.ConsoleUtils;
import java.util.ArrayList;

/**
 *
 * @author ilichh1
 */
public class MenuController implements MainCallable {
    
    // Wizard Controllers
    public static final ProductsController PRODUCTS_CONTROLLER = new ProductsController();
    public static final ClientsController CLIENTS_CONTROLLER = new ClientsController();
    public static final SellsController SELLS_CONTROLLER = new SellsController();
    
    private final ArrayList<Menu> menus = new ArrayList<>();
    
    private Menu startingMenu;
    private Menu productsMenu;
    private Menu clientsMenu;
    private Menu salesmenMenu;
    private Menu sellsMenu;
    
    public MenuController() {
        try {
            this.initializateMenus();
        } catch (Exception ex) {
            ConsoleUtils.printErrorMessage(ex.getLocalizedMessage());
        }
    }
    
    public void startMenuController() {
        this.triggerLastMenu();
    }
    
    /*
     * Returns true if the end of the execution is reached
     */ 
    @Override
    public boolean doSpecificAction(String actionName) {
        // TODO: Completar codigo para realizar cada acción en especifico
        switch(actionName) {
            case "exit":
                System.out.println("¡Hasta luego! - Webtix Software");
                WizardConsole.isExecutionEnded = true;
            break;
            // PRODUCTS MENU ACTIONS
            case "goToProductsMenu":
                this.moveToMenu("products");
            break;
            case "addProduct":
                PRODUCTS_CONTROLLER.createAndSaveProduct();
            break;
            case "viewAllProducts":
                PRODUCTS_CONTROLLER.printTable();
            break;
            case "searchProduct":
                System.out.print("Ingrese el valor por el que desea buscar: ");
                PRODUCTS_CONTROLLER.search(ConsoleUtils.askForString());
            break;
            case "editProduct":
                System.out.print("Ingrese el ID del producto a editar: ");
                PRODUCTS_CONTROLLER.editProduct(ConsoleUtils.askForInteger());
            break;
            case "removeProduct":
                System.out.print("Ingrese el ID del producto a eliminar: ");
                PRODUCTS_CONTROLLER.removeProduct(ConsoleUtils.askForInteger());
            break;
            // CLIENTS MENU ACTIONS
            case "goToClientsMenu":
                this.moveToMenu("clients");
            break;
            case "addClient":
                CLIENTS_CONTROLLER.createAndSaveClient();
            break;
            case "searchClient":
                System.out.print("Ingrese el valor de búsqueda: ");
                CLIENTS_CONTROLLER.search(ConsoleUtils.askForString());
            break;
            case "editClient":
                System.out.print("Ingrese el ID del cliente que quiere editar: ");
                CLIENTS_CONTROLLER.editClient(ConsoleUtils.askForInteger());
            break;
            case "viewAllClients":
                CLIENTS_CONTROLLER.printTable();
            break;
            // SALEMEN MENU ACTIONS
            case "goToSalesmenMenu":
                this.moveToMenu("salesmen");
            break;
            // SELLS MENU ACTIONS
            case "goToSellsMenu":
                this.moveToMenu("sells");
            break;
            case "viewAllSells":
                SELLS_CONTROLLER.printTable();
            break;
            case "searchSell":
                System.out.print("Ingrese el valor de búsqueda de la venta: ");
                SELLS_CONTROLLER.search(ConsoleUtils.askForString());
            break;
            // GENERAL ACTIONS
            case "goBack":
                this.previousMenu();
            break;
            case "continue": break;
            default:
                System.out.println("STILL WORKING ON: " + actionName);
            break;
        }
        return true;
    }
    
    public boolean triggerLastMenu() {
        // TODO: Probablemente de un error aquí por el índice
        if (this.menus.size() == 1) {
            return this.menus.get(0).printMenu(this);
        } else {
            return this.menus.get(this.menus.size() - 1).printMenu(this);
        }
    }
    
    private void initializateMenus() throws Exception {
        // Menú de inicio
        this.startingMenu = new Menu("Menú de inicio", new MenuEntry[] {
            new MenuEntry("PRODUCTOS", 'A', "goToProductsMenu"),
            new MenuEntry("CLIENTES", 'B', "goToClientsMenu"),
            // new MenuEntry("VENDEDORES", 'C', "goToSalesmenMenu"),
            new MenuEntry("VENTAS", 'D', "goToSellsMenu"),
            new MenuEntry("SALIR", 'E', "exit")
        });
        this.menus.add(startingMenu);

        // Menú de productos
        this.productsMenu = new Menu("Menú de productos", new MenuEntry[] {
            new MenuEntry("AGREGAR PRODUCTO", 'A', "addProduct"),
            new MenuEntry("VER TODOS LOS PRODUCTOS", 'B', "viewAllProducts"),
            new MenuEntry("BUSCAR PRODUCTO", 'C', "searchProduct"),
            new MenuEntry("ELIMINAR PRODUCTO", 'D', "removeProduct"),
            new MenuEntry("EDITAR PRODUCTO", 'E', "editProduct"),
            new MenuEntry("REGRESAR", 'F', "goBack")
        });

        // Menú de clientes
        this.clientsMenu = new Menu("Menú de clientes", new MenuEntry[] {
            new MenuEntry("AGREGAR CLIENTE", 'A', "addClient"),
            new MenuEntry("VER TODOS LOS CLIENTES", 'B', "viewAllClients"),
            new MenuEntry("BUSCAR CLIENTE", 'C', "searchClient"),
            new MenuEntry("EDITAR CLIENTE", 'D', "editClient"),
            new MenuEntry("REGRESAR", 'E', "goBack")
        });

        // Menú de vendedores
        this.salesmenMenu = new Menu("Menú de vendedores", new MenuEntry[] {
            new MenuEntry("AGREGAR VENDEDOR", 'A', "addSalesman"),
            new MenuEntry("VER TODOS LOS VENDEDOR", 'B', "viewAllSalesmen"),
            new MenuEntry("BUSCAR VENDEDOR", 'C', "searchSalesman"),
            new MenuEntry("EDITAR VENDEDOR", 'D', "editSalesman"),
            new MenuEntry("REGRESAR", 'E', "goBack")
        });

        // Menú de ventas
        this.sellsMenu = new Menu("Menú de ventas", new MenuEntry[] {
            // new MenuEntry("AGREGAR VENTA", 'A', "addSell"),
            new MenuEntry("VER TODAS LAS VENTAS", 'B', "viewAllSells"),
            new MenuEntry("BUSCAR VENTA", 'C', "searchSell"),
            // new MenuEntry("EDITAR VENTA", 'D', "editSell"),
            new MenuEntry("REGRESAR", 'E', "goBack")
        });
    }
    
    public void moveToMenu(String menuName) {
        switch (menuName) {
            case "products":
                this.menus.add(this.productsMenu);
            break;
            case "clients":
                this.menus.add(this.clientsMenu);
            break;
            case "salesmen":
                this.menus.add(this.salesmenMenu);
            break;
            case "sells":
                this.menus.add(this.sellsMenu);
            break;
            default:
                ConsoleUtils.printErrorMessage("El menú " + menuName + " no exite.");
        }
        this.triggerLastMenu();
    }
    
    public void previousMenu() {
        this.menus.remove(this.menus.size() - 1);
        this.triggerLastMenu();
    }
}
