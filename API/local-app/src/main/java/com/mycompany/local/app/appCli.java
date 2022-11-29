/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.local.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author FP594HT
 */
public class appCli {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        Scanner leitor = new Scanner(System.in);
        Scanner leitor2 = new Scanner(System.in);
        Scanner maquina = new Scanner(System.in);
        Log log = new Log();

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection("jdbc:sqlserver://console-tech-consulting.database.windows.net:1433;database=bd-CTC;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;", "admin-ctc", "#Gfgrupo5");
        System.out.println("digite seu email");

        String email = leitor.nextLine();
        System.out.println("digite sua senha");

        String senha = leitor.nextLine();
        Statement stm = con.createStatement();
        String login = "select * from funcionario where email='" + email + "' and senha='" + senha + "'";
        ResultSet rs = stm.executeQuery(login);
        Statement stm2 = con.createStatement();
        String login2 = "select * from funcionario where email='" + email + "' and senha='" + senha + "'";
        ResultSet rs2 = stm2.executeQuery(login2);

        while (!rs.next()) {
            System.out.println("Usuario ou senha incorretos");
            System.out.println("----------------------------");

            System.out.println("digite seu usuario");

            email = leitor2.nextLine();
            System.out.println("digite sua senha");

            senha = leitor2.nextLine();
            stm2 = con.createStatement();
            login2 = "select * from funcionario where email='" + email + "' and senha='" + senha + "'";
            rs2 = stm2.executeQuery(login2);
            stm = con.createStatement();
            login = "select * from funcionario where email='" + email + "' and senha='" + senha + "'";
            rs = stm.executeQuery(login);
        }
        if (rs.next()) {

            System.out.println("Insira o id da Maquina");

            Integer idMaquina = maquina.nextInt();

            log.gerarLog(email, idMaquina);
            TesteSistema iniciarDados = new TesteSistema(idMaquina);
            iniciarDados.exec();

        }

        if (rs2.next()) {
            System.out.println("Insira o id da Maquina");

            Integer idMaquina = maquina.nextInt();

            log.gerarLog(email, idMaquina);
            TesteSistema iniciarDados = new TesteSistema(idMaquina);
            iniciarDados.exec();
        }

    }

}
