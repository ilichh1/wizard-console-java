/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wizard.main.controllers;

import com.wizard.client.Client;
import java.util.ArrayList;

/**
 *
 * @author ilichh1
 */
public class SalesmenController {
    private final ArrayList<Client> salemen = new ArrayList<>();
    
    private static final String[] COLUMN_HEADERS = new String[] {
        "ID",
        "Nombre(s)",
        "Apellido(s)",
        "Télefono",
        "Email",
        "Dirección"
    };
}
