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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author UL334AW
 */
public class TesteSistema {
    
  
    Cruds cruds = new Cruds();
    private Integer id;
    
    
    
    
    public TesteSistema(Integer id) {
    this.id = id;
        
    }

    
    
//    public void cadastro(){
//        cruds.cadastroDeMaquina();
//   }

    
    
    public void exec() throws IOException, InterruptedException{
        
        
        
        
       /*conexao e inicio do programa*/
        for (int i = 0; i < 5; i++) {
           
            cruds.programa(id);
            TimeUnit.SECONDS.sleep(2);
        }

    }

}
/*      looca.getGrupoDeDiscos();
        looca.getGrupoDeProcessos();
        looca.getGrupoDeServicos();
        looca.getMemoria();
        looca.getProcessador();
        looca.getSistema();
        looca.getTemperatura();*/
//Sistema sistema = looca.getSistema();
/*      sistema.getPermissao();
        sistema.getFabricante();
        sistema.getArquitetura();
        sistema.getInicializado();
        sistema.getSistemaOperacional();*/
//      Exemplos pegando grupos com lista:
        /*DiscosGroup grupoDeDiscos = looca.getGrupoDeDiscos();
        List<Disco> discos = grupoDeDiscos.getDiscos();
        for (Disco disco : discos) {
        System.out.println(disco);
        }
        
        System.out.println(cruds.listarTodos());*/
