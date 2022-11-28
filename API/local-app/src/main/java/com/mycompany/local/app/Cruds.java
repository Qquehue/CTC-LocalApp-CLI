/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.local.app;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.processos.ProcessosGroup;
import com.github.britooo.looca.api.util.Conversor;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbcp2.BasicDataSource;
import org.json.JSONObject;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.driver.linux.proc.CpuInfo;
import oshi.driver.linux.proc.CpuStat;

/**
 *
 * @author UL334AW
 */
public class Cruds {

    private JdbcTemplate jdbcTemplate;
    private Looca looca = new Looca();
    private Conexao conexao = new Conexao();
    private ConexaoAzure conexaoAzure = new ConexaoAzure();
    private AppSlack slack = new AppSlack();
    private Maquina maquina = new Maquina();
    private Processador proc = new Processador();
    private Memoria memoria = new Memoria();
    private Sistema sistema = new Sistema();
    private DiscosGroup discos = new DiscosGroup();
    private ProcessosGroup processos = new ProcessosGroup();
    Conversor conversor = new Conversor();
    


    
    
    

    public Cruds() {
        BasicDataSource dataSource = new BasicDataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);

// configuração do dataSource, como visto antes
    }

    public void programa(Integer id) throws IOException, InterruptedException {

        
        // Parte de inserção banco local
        
        conexao.conectar();
        JdbcTemplate database = conexao.getConnection();
        
        maquina.setUsoMemoria(memoria.getEmUso().doubleValue());
        String memorias = Conversor.formatarBytes(memoria.getEmUso()).replace("GiB", "").replace(",", ".");
        Double memoriaAtual = Double.parseDouble(memorias);
        maquina.setUsoCPU(proc.getUso());
        String cpus = Conversor.formatarBytes(proc.getUso().longValue()).replace("GiB", "").replace(",", ".").replace("bytes", "");
        Double cpuAtual = Double.parseDouble(cpus);
        Integer processosAtual = processos.getTotalProcessos();
        String memoria2 = Conversor.formatarBytes(memoria.getTotal()).replace("GiB", "").replace(",", ".");
        Double memoriaTotal = Double.parseDouble(memoria2);
        Double porcentagemSlack = (memoriaAtual / memoriaTotal) * 100;
        String x = String.valueOf(porcentagemSlack);
        Float porcentagemAzure = Float.valueOf(x);
        
        
        
        
        String insertBanco = "INSERT INTO usoMaquinaReal VALUES (null,?,?,?,CURRENT_TIMESTAMP,?)";
        database.update(insertBanco, processosAtual, cpuAtual, memoriaAtual, id);
        
        
        
        // parte de inserção banco Azure
        
        conexaoAzure.conectarAzure();
        JdbcTemplate databaseAzure = conexaoAzure.getConnectionAzure();
        
        Float cpuAzure = Float.valueOf(cpus);
        
        String insertBancoAzure = "INSERT INTO UsoMaquina VALUES (?,?,?,CURRENT_TIMESTAMP,?)";
        databaseAzure.update(insertBancoAzure, processosAtual, cpuAzure, porcentagemAzure, id);
        
        
        
        
        
        // Parte de conexao slack
        
        
        
        System.out.println(x);
        
        if (cpuAtual > 90 || porcentagemSlack > 90) {
            
            JSONObject json = new JSONObject();
            Validacao maquinaSlack = new Validacao(id, cpuAtual, porcentagemSlack, 50.0);
            maquinaSlack.validarMaquina(json);
            
        }

    }

    
//    public Maquina returnBytes() {
//    Double memorias = maquina.getUsoMemoria();
//        
//    };
    
    
    /*public static void main(String[] args) {
        DiscosGroup grupoDeDiscos = new DiscosGroup();
        List<Disco> discos = grupoDeDiscos.getDiscos();

        for (Disco disco : discos) {
            System.out.println(disco);
        }

    }*/
//    public void cadastroDeMaquina(Integer id) {
//        conexao.conectar();
//        JdbcTemplate database = conexao.getConnection();;
//        
//        
//    
//    
//
//        maquina.setModeloCpu(looca.getProcessador().getNome());
//        maquina.setTotalDisco(discos.getTamanhoTotal().doubleValue());
//        maquina.setTotalMemoria((looca.getMemoria().getTotal()).doubleValue());
//
//        String insertCadastro = "INSERT INTO Maquina VALUES (?,?,?,?,CURRENT_TIMESTAMP,null)";
//        database.update(insertCadastro,this.programa(id), maquina.getModeloCpu(), maquina.getTotalMemoria(), maquina.getTotalDisco());
//
//      
//    
//    
//      
//   }

    }
    

