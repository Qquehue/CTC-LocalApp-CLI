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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author Victor
 */
public class Validacao {

    private Integer idMaquina;
    private Double usoCPU;
    private Double usoMemoria;
    private Integer qtdProcessos;

    public Validacao(Integer idMaquina, Double usoCPU, Double usoMemoria, Integer qtdProcessos) {

        this.idMaquina = idMaquina;
        this.usoCPU = usoCPU;
        this.usoMemoria = usoMemoria;
        this.qtdProcessos = qtdProcessos;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Double getUsoCPU() {
        return usoCPU;
    }

    public void setUsoCPU(Double usoCPU) {
        this.usoCPU = usoCPU;
    }

    public Double getUsoMemoria() {
        return usoMemoria;
    }

    public void setUsoMemoria(Double usoMemoria) {
        this.usoMemoria = usoMemoria;
    }

    public Integer getQtdProcessos() {
        return qtdProcessos;
    }

    public void setQtdProcessos(Integer qtdProcessos) {
        this.qtdProcessos = qtdProcessos;
    }

    public void validarCPU(JSONObject json) throws IOException, InterruptedException {

        json.put("text", String.format("\nA Máquina %d está com problemas de CPU!",
                idMaquina));
        Slack.sendMessage(json);
    }

    public void validarMemoria(JSONObject json) throws IOException, InterruptedException {

        json.put("text", String.format("\nA Máquina %d está com problemas de memória!",
                idMaquina));
        Slack.sendMessage(json);
    }

    public void validarProcessos(JSONObject json) throws IOException, InterruptedException {

        json.put("text", String.format("\nA Máquina %d está com muitos processos abertos!",
                idMaquina));
        Slack.sendMessage(json);
    }

    public void validarMaquina(JSONObject json, Integer id) throws IOException, InterruptedException {

        Looca looca = new Looca();
        Maquina maquina = new Maquina();
        Processador proc = new Processador();
        Memoria memoria = new Memoria();
        Sistema sistema = new Sistema();
        DiscosGroup discos = new DiscosGroup();
        Conversor conversor = new Conversor();

        String memoriasHD = Conversor.formatarBytes(discos.getTamanhoTotal()).replace("GiB", "").replace(",", ".");
        Double memoriaHDAtual = Double.parseDouble(memoriasHD);
        String memorias = Conversor.formatarBytes(memoria.getTotal()).replace("GiB", "").replace(",", ".");
        Double memoriaAtual = Double.parseDouble(memorias);
        Integer cpusFisicos = proc.getNumeroCpusFisicas();
        Integer cpusLogicas = proc.getNumeroCpusLogicas();
        String fabricante = proc.getFabricante();
        String nomeProc = proc.getNome();
        String frequencia = Conversor.formatarBytes(proc.getFrequencia()).replace("GiB", "").replace(",", ".");
        String so = sistema.getSistemaOperacional();

        json.put("text", String.format("**MÁQUINA %d COM CPU, MEMÓRIA RAM E PROCESSOS TOTALMENTE SOBRECARREGADOS, FAZER MANUTENÇÃO URGENTE!!!**\n\n\n"
                        + "--- Informações --- \n\n"
                        + "ID máquina:   %d \n"
                        + "Processador:  %s \n"
                        + "Fabricante:   %s \n"
                        + "CPU - físico: %s \n"
                        + "CPU - lógico: %s \n"
                        + "Memória HD:   %.0f \n"
                        + "Memória RAM:  %.0f \n"
                        + "Frequência:   %s \n"
                        + "SO:           %s \n\n"
                        + "--- Informações de Uso --- \n"
                        + "\nUso CPU (por cento): %.2f"
                        + "\nUso Memória (por cento): %.2f"
                        + "\nQuantidade de Processos: %d"
                        + "\n\n\nEm caso de dúvidas entre em contato conosco a CTC - Console Tech Consulting!\n"
                        + "Email: consoletechconsulting@gmail.com", 
                        idMaquina,
                        id,
                        nomeProc,
                        fabricante,
                        cpusFisicos,
                        cpusLogicas,
                        memoriaHDAtual,
                        memoriaAtual,
                        frequencia,
                        so,
                        usoCPU,
                        usoMemoria,
                        qtdProcessos,
                        idMaquina));

        Slack.sendLog(json);

        System.out.println("Seu log foi gravado com sucesso :)\n");
    }
}
