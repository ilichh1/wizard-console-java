package com.wizard.sell;

import com.wizard.interfaces.Filterable;
import com.wizard.interfaces.TablePrintable;
import com.wizard.product.SoldProduct;
import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ilichh1
 */
public class Sell implements TablePrintable, Filterable {
    
    private int saleman;
    private int client;
    private SoldProduct[] products;
    private LocalDate dateSold;
    
    public Sell() {
        this.saleman = -1;
        this.client = -1;
        this.products = null;
        this.dateSold = null;
    }
    
    public Sell(int salemanId, int clientWhoBuys, SoldProduct[] products, LocalDate dateSold) {
        this.saleman = salemanId;
        this.client = clientWhoBuys;
        this.products = products;
        this.dateSold = dateSold;
    }
    
    // TablePrintable interface methods
    @Override
    public String[] toStringArray() {
        return new String[] {
            this.getSaleman(),
            this.getClient(),
            Integer.toString(this.products.length),
            this.getTotalAsText(),
            this.dateSold.toString()
        };
    }
    
    public String getTotalAsText() {
        return Double.toString(Math.floor(this.getTotal() * 100) / 100);
    }
            
    public double getTotal() {
        if (this.products.length < 0)
            return 0;
        // If the array is not empty
        double total = 0;
        for (SoldProduct product : this.products) {
            total += product.getTotal();
        }
        return total;
    }
    
    // GETTERS & SETTERS
    
    public String getSaleman() {
        // TODO: return correct value
        return "Juan Perez";
    }

    public void setSaleman(int saleman) {
        this.saleman = saleman;
    }
    
    public int getSalemanId() {
        return this.saleman;
    }

    public String getClient() {
        // TODO: return correct value
        return "Jorge Robles";
    }

    public void setClient(int client) {
        this.client = client;
    }

    public SoldProduct[] getProducts() {
        return products;
    }

    public void setProducts(SoldProduct[] products) {
        this.products = products;
    }

    public LocalDate getDateSold() {
        return dateSold;
    }

    public void setDateSold(LocalDate dateSold) {
        this.dateSold = dateSold;
    }
}
