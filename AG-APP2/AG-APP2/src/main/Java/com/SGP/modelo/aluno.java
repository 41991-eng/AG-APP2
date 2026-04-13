package com.SGP.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Aluno {

    private int       id;
    private String    nome;
    private LocalDate dataCadastro;

    public Aluno() {
        this.id           = 0;
        this.nome         = "Sem Nome";
        this.dataCadastro = LocalDate.now();
    }

    public Aluno(int id, String nome) {
        setId(id);
        setNome(nome);
        this.dataCadastro = LocalDate.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        return String.format(
            "\n+---------------------------------------+" +
            "\n| ID          : %-23d|" +
            "\n| Nome        : %-23s|" +
            "\n| Cadastro    : %-23s|" +
            "\n+---------------------------------------+",
            id,
            nome,
            dataCadastro.format(fmt)
        );
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("Oque q passou na sua mente? ID NAO pode ser negativo.");
        this.id = id;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("coloca o maldito nome que ta no RG, precisa de instrucao ate p isso?");
        this.nome = nome.trim();
    }

    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) {
        if (dataCadastro == null)
            throw new IllegalArgumentException("O amigao, eu nao tenho uma bola de cristal. A data de cadastro NAO pode ser nula.");
        this.dataCadastro = dataCadastro;
    }
}
