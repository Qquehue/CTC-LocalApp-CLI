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
public class Validacao {
    
    private Integer idMaquina;
    private Double usoCPU;
    private Double usoMemoria;
    private Double usoDisco;

    public Validacao(Integer idMaquina, Double usoCPU, Double usoMemoria, Double usoDisco) {
        
        this.idMaquina = idMaquina;
        this.usoCPU = usoCPU;
        this.usoMemoria = usoMemoria;
        this.usoDisco = usoDisco;
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

    public Double getUsoDisco() {
        return usoDisco;
    }

    public void setUsoDisco(Double usoDisco) {
        this.usoDisco = usoDisco;
    }
    
    public void validarMaquina (JSONObject json) throws IOException, InterruptedException {

            json.put("text", String.format("\nId da Máquina: %d"
                + "\nUso CPU: %.2f"
                + "\nUso Memória: %.2f"
                + "\nUso Disco: %.2f"
                + "\nFazer manutenção na máquina %d", 
                idMaquina,
                usoCPU,
                usoMemoria,
                usoDisco,
                idMaquina));
            Slack.sendMessage(json);
    }
}
