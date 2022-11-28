/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.local.app;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author UL334AW
 */
public class Conexao {

    private JdbcTemplate conexao; 

    public void conectar() {       
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/CTC");
        dataSource.setUsername("root");
        dataSource.setPassword("G@briel17022004");
        
        this.conexao = new JdbcTemplate(dataSource);
    }
    

    
    public JdbcTemplate getConnection() {
        return conexao;
    }
}
