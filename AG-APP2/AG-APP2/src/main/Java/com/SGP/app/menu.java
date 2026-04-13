package com.SGP.app;

import com.SGP.modelo.Aluno;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class menu {

    private final List<Aluno> alunos   = new ArrayList<>();
    private final Scanner     scanner  = new Scanner(System.in); 
    private int               proximoId = 1;

    public void exibir() {
        int opcao;
        do {
            cabecalho();
            System.out.println("  [1] Cadastrar Aluno");
            System.out.println("  [2] Listar Todos os Alunos");
            System.out.println("  [3] Buscar Aluno");
            System.out.println("  [4] Remover Aluno");           
            System.out.println("  [0] Sair");
            System.out.print("  Escolha um ai: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listarTodos();
                case 3 -> buscar();
                case 4 -> remover();                             
                case 0 -> System.out.println("\n  Sistema desligou, chefe. Ate mais tarde!\n");
                default -> System.out.println("\n  fez coisa errada seu tchongo! Tenta denovo.\n");
            }
        } while (opcao != 0);
    }

    private void cadastrar() {
    subtitulo("CADASTRAR FUTURO MR.OLIMPIA");
    try {
        System.out.print("  Nome : ");
        String nome = scanner.nextLine().trim();

        Aluno a = new Aluno(proximoId, nome);
        proximoId++;
        alunos.add(a);

        System.out.println("\n  pode ir puxar ferro a vossa vontade agora em patrao, hora de ficar monstro!");
        System.out.println(a);
    } catch (IllegalArgumentException e) {
        System.out.println("\n  kkkkkkkkkkkkk vc e uma lenda irmao, fez dar erro aqui na parada: " + e.getMessage());
    }
    pausar();
}

    private void listarTodos() {
        subtitulo("TODOS OS FERAS (" + alunos.size() + ")");
        if (alunos.isEmpty()) {
            System.out.println("  Ta achando que eu sou muleque? Vai fazer seu cadastro rapá!");
        } else {
            alunos.forEach(System.out::println);
        }
        pausar();
    }

   private void buscar() {
    subtitulo("BUSCAR ALUNO");
    System.out.println("  [1] Buscar por ID");
    System.out.println("  [2] Buscar por Nome");
    System.out.print("  Opcao: ");
    int op = lerInt();

    if (op == 1) {
        System.out.print("  ID: ");
        int id = lerInt();
        Optional<Aluno> opt = alunos.stream().filter(a -> a.getId() == id).findFirst();
        if (opt.isPresent()) System.out.println(opt.get());
        else System.out.println("  Digitou direito? Nao achei nao em.");

    } else if (op == 2) {
        System.out.print("  Nome (ou parte): ");
        scanner.nextLine(); // limpa o buffer antes de ler o nome
        String nome = scanner.nextLine().trim().toLowerCase();
        List<Aluno> resultado = new ArrayList<>();
        for (Aluno a : alunos)
            if (a.getNome().toLowerCase().contains(nome)) resultado.add(a);

        if (resultado.isEmpty()) System.out.println("  Ta procurando indigente? Nao achei nada aqui nao filho.");
        else resultado.forEach(System.out::println);
    } else {
        System.out.println("  vc conseguiu errar isso? Me ajuda a te ajudar ne.");
    }
    pausar();
}

    private void remover() {
        subtitulo("REMOVER CONTRIBUINTE");
        System.out.print("  ID do aluno: ");
        int id = lerInt();
        System.out.print("  Quer msm pular fora :( ? (s/n): ");
        String conf = scanner.nextLine().trim();

        if (conf.equalsIgnoreCase("s")) {
            boolean ok = alunos.removeIf(a -> a.getId() == id);
            System.out.println(ok ? "  SEU VACILÃO!" : "   Ta procurando indigente? Nao achei nada aqui nao filho.");
        } else {
            System.out.println("  EXCELENTE CAMPEAO! Operacao cancelada.");
        }
        pausar();
    }

    private void cabecalho() {
        System.out.println("\n  ======================================");
        System.out.println("        SISTEMA DE ACADEMIA  - SGP      ");
        System.out.println("  ========================================");
    }

    private void subtitulo(String titulo) {
        System.out.println("\n  --------------------------------------");
        System.out.println("   " + titulo);
        System.out.println("  --------------------------------------");
    }

    private void pausar() {
        System.out.print("\n  Aperta ENTER para continuar ai rei...");
        scanner.nextLine();
    }

    private int lerInt() {
        try { return Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { return -1; }
    }

    private double lerDouble() {
        try {
            double val = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            if (val <= 0) return -1;
            return val;
        } catch (NumberFormatException e) { return -1; }
    }
}
