package com.mycompany.local.app;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.util.Conversor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import oshi.driver.linux.proc.CpuInfo;
import oshi.driver.linux.proc.CpuStat;

/**
 *
 * @author ctc
 */
public class Log {

    public void gerarLog(String email, Integer id) throws IOException, InterruptedException {

        //Instanciando classes
        Looca looca = new Looca();
        Maquina maquina = new Maquina();
        Processador proc = new Processador();
        Memoria memoria = new Memoria();
        Sistema sistema = new Sistema();
        DiscosGroup discos = new DiscosGroup();
        Conversor conversor = new Conversor();

        //Classe reponsável em formatar a data e horário
        DateFormat hoursFormat = new SimpleDateFormat("HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dateHoursFormat = new SimpleDateFormat("dd-MM-yyyy HH'h'mm'm'ss'ss'");

        for (int b = 0; b < 1; b++) {
            Date dateHours = new Date();
            Date date = new Date();

            System.out.print("Captando log das máquinas...\n");
            
            //Cria um novo diretório
            File myDir = new File ("/home/ubuntu/Logs");
            // File myDir = new File ("C:\\Users\\FP594HT\\Downloads\\LogsAtual");
             myDir.mkdir();
            
            //Cria um arquivo .txt no caminhos destinado
            FileWriter arqLog = new FileWriter
            (String.format("/home/ubuntu/Logs/log %s.txt", 
            dateHoursFormat.format(dateHours)));
//            FileWriter arqLog = new FileWriter
//            (String.format("C:\\Users\\Victor\\Desktop\\Testando\\log %s.txt", 
//            dateHoursFormat.format(dateHours)));
            
            //Faz gravação dos arquivos
            PrintWriter gravarArqLog = new PrintWriter(arqLog);

            for (int i = 0; i < 1; i++) {
                Date hours = new Date();
                
                String memoriasHD = Conversor.formatarBytes(discos.getTamanhoTotal()).replace("TiB", "").replace(",", ".");
                Double memoriaHDAtual = Double.parseDouble(memoriasHD);
                
                String memorias = Conversor.formatarBytes(memoria.getTotal()).replace("GiB", "").replace(",", ".");
                Double memoriaAtual = Double.parseDouble(memorias);
                
                Integer cpusFisicos = proc.getNumeroCpusFisicas();
                
                Integer cpusLogicas = proc.getNumeroCpusLogicas();
                
                String fabricante = proc.getFabricante();
                
                String nomeProc = proc.getNome();
                
                String frequencia = Conversor.formatarBytes(proc.getFrequencia()).replace("GiB", "").replace(",", ".");
                
                String so = sistema.getSistemaOperacional();
                                
                gravarArqLog.print(String.format("Log de instalação gerado às %s horário de Brasília no dia %s \n\n"
                        + "Seja bem vindo(a): %s \n\n"
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
                        + "Em caso de dúvidas entre em contato conosco a CTC - Console Tech Consulting!\n"
                        + "Email: consoletechconsulting@gmail.com",
                        hoursFormat.format(hours),
                        dateFormat.format(date),
                        email,
                        id,
                        nomeProc,
                        fabricante,
                        cpusFisicos,
                        cpusLogicas,
                        memoriaHDAtual,
                        memoriaAtual,
                        frequencia,
                        so));
//                TimeUnit.SECONDS.sleep(1);
            }
            arqLog.close();
        }
        System.out.println("Seu log foi gravado com sucesso :)");
    }

}
