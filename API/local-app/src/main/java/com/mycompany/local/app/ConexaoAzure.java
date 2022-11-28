/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.local.app;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author FP594HT
 */
public class ConexaoAzure {
    
    private JdbcTemplate conexaoAzure;
    
    public void conectarAzure() {       
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://console-tech-consulting.database.windows.net:1433;database=bd-CTC;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;");
        dataSource.setUsername("admin-ctc");
        dataSource.setPassword("#Gfgrupo5");
        
        this.conexaoAzure = new JdbcTemplate(dataSource);
    }
      public JdbcTemplate getConnectionAzure() {
        return conexaoAzure;
    }
}
