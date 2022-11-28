/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.local.app;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.util.Conversor;
import java.io.IOException;
import org.json.JSONObject;

/**
 *
 * @author Victor
 */
public class AppSlack {

        public void mensagemSlack() throws IOException, InterruptedException {

        JSONObject json = new JSONObject();
        Maquina maquina = new Maquina();
        Processador proc = new Processador();
        Memoria memoria = new Memoria();
        Sistema sistema = new Sistema();
        DiscosGroup discos = new DiscosGroup();
        Conversor conversor = new Conversor();
        Looca looca = new Looca();

        maquina.setUsoMemoria(memoria.getEmUso().doubleValue());
        String memorias = Conversor.formatarBytes(memoria.getEmUso()).replace("GiB", "").replace(",", ".");
        Double memoriaAtual = Double.parseDouble(memorias);
        maquina.setUsoCPU(proc.getUso());
        String cpus = Conversor.formatarBytes(proc.getUso().longValue()).replace("GiB", "").replace(",", ".").replace("bytes", "");
        Double cpuAtual = Double.parseDouble(cpus);

        Validacao maquinaSlack = new Validacao(1, cpuAtual, memoriaAtual, 50.0);

        maquinaSlack.validarMaquina(json);
        
        }
}
