package com.mycompany.ctc.slack;

import java.io.IOException;
import org.json.JSONObject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Victor
 */
public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        JSONObject json = new JSONObject();
        
        Validacao m1 = new Validacao(1, 90.0, 30.0, 40.0);
        Validacao m2 = new Validacao(2, 50.0, 80.0, 40.0);
        Validacao m3 = new Validacao(3, 60.0, 40.0, 30.0);
        Validacao m4 = new Validacao(4, 90.0, 50.0, 60.0);
        Validacao m5 = new Validacao(5, 70.0, 30.0, 60.0);
        
        m1.validarMaquina(json);
        m2.validarMaquina(json);
        m3.validarMaquina(json);
        m4.validarMaquina(json);
        m5.validarMaquina(json);
    }
}
