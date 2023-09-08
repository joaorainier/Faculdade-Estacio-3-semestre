/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Joao Rainier
 */


package cadastropoo;


import model.entidades.PessoaFisica;
import model.entidades.PessoaJuridica;
import model.gerenciadores.PessoaFisicaRepo;
import model.gerenciadores.PessoaJuridicaRepo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CadastroPOO {

    static PessoaFisicaRepo repoPF;
    static PessoaJuridicaRepo repoPJ;
    static Scanner sc;

    public static void main(String[] args) {

        sc = new Scanner(System.in);
        repoPF = new PessoaFisicaRepo();
        repoPJ = new PessoaJuridicaRepo();

        int opcao;

        do {
            
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Buscar");
            System.out.println("5 - Mostrar Todos");
            System.out.println("6 - Salvar Lista");
            System.out.println("7 - Recuperar");
            System.out.println("0 - Finalizar");
            
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> incluir();
                case 2 -> alterar();
                case 3 -> excluir();
                case 4 -> obter();
                case 5 -> obterTodos();
                case 6 -> salvar();
                case 7 -> recuperar();
                case 0 -> System.out.println("Programa fechado com sucesso!");
                default -> System.out.println("Opcao invalida. ");
            }

        } while (opcao != 0);

        sc.close();

    }

    public static void incluir() {
        System.out.println(" Pessoa Fisica |F| Pessoa Juridica |J| ");
        char tipoPessoa = sc.nextLine().charAt(0);
        if (tipoPessoa != 'F' && tipoPessoa != 'f' && tipoPessoa != 'J' && tipoPessoa != 'j') {
            System.out.println("Opcao invalida");
            return;
        }

        System.out.println("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Nome: ");
        String nome = sc.nextLine();

        if (tipoPessoa == 'F' || tipoPessoa == 'f') {
            System.out.println("CPF: ");
            String cpf = sc.nextLine();
            System.out.println("Idade: ");
            int idade = sc.nextInt();
            sc.nextLine();
            repoPF.inserir(new PessoaFisica(id, nome, cpf, idade));
        } else {
            System.out.println("CNPJ: ");
            String cnpj = sc.nextLine();
            repoPJ.inserir(new PessoaJuridica(id, nome, cnpj));
        }

        System.out.println("Cadastro concluido com sucesso! ");
    }

    public static void alterar() {
        System.out.println(" Pessoa Fisica |F| Pessoa Juridica |J| ");
        char tipoPessoa = sc.nextLine().charAt(0);
        if (tipoPessoa != 'F' && tipoPessoa != 'f' && tipoPessoa != 'J' && tipoPessoa != 'j') {
            System.out.println("Opcao invalida");
            return;
        }
        System.out.println("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (tipoPessoa == 'F' || tipoPessoa == 'f') {
            PessoaFisica pf = repoPF.obter(id);
            if (pf == null) {
                System.out.println("Perfil nao encontrado. ");
                return;
            }
            System.out.println(pf.exibir());

            System.out.println("Nome: ");
            String nomePf = sc.nextLine();
            System.out.println("CPF: ");
            String cpfPf = sc.nextLine();
            System.out.println("Idade: ");
            int idadePf = sc.nextInt();
            sc.nextLine();
            pf.setNome(nomePf);
            pf.setCpf(cpfPf);
            pf.setIdade(idadePf);
            repoPF.alterar(pf);
        } else {
            PessoaJuridica pj = repoPJ.obter(id);
            if (pj == null) {
                System.out.println("Perfil nao encontrado. ");
                return;
            }

            System.out.println(pj.exibir());
            System.out.println("Nome: ");
            String nomePj = sc.nextLine();
            System.out.println("CNPJ: ");
            String cnpjPj = sc.nextLine();
            pj.setNome(nomePj);
            pj.setCnpj(cnpjPj);
            repoPJ.alterar(pj);
        }
        System.out.println("Alteracao realizada com sucesso");
    }

    public static void excluir() {
        System.out.println("Pessoa Fisica |F| Pessoa Juridica |J| ");
        char tipoPessoa = sc.nextLine().charAt(0);
        if (tipoPessoa != 'F' && tipoPessoa != 'f' && tipoPessoa != 'J' && tipoPessoa != 'j') {
            System.out.println("Opcao invalida");
            return;
        }
        System.out.println("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (tipoPessoa == 'F' || tipoPessoa == 'f') {
            repoPF.excluir(id);
        } else {
            repoPJ.excluir(id);
        }
        System.out.println("Perfil deletado com sucesso. ");
    }

    public static void obter() {
        System.out.println("Pessoa Fisica |F| Pessoa Juridica |J| ");
        char tipoPessoa = sc.nextLine().charAt(0);
        if (tipoPessoa != 'F' && tipoPessoa != 'f' && tipoPessoa != 'J' && tipoPessoa != 'j') {
            System.out.println("Opcao invalida");
            return;
        }
        System.out.println("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (tipoPessoa == 'F' || tipoPessoa == 'f') {
            PessoaFisica pf = repoPF.obter(id);
            System.out.println(pf.exibir());
        } else {
            PessoaJuridica pj = repoPJ.obter(id);
            System.out.println(pj.exibir());
        }
    }

    public static void obterTodos() {
        System.out.println("Pessoa Fisica |F| Pessoa Juridica |J| ");
        char tipoPessoa = sc.nextLine().charAt(0);
        if (tipoPessoa != 'F' && tipoPessoa != 'f' && tipoPessoa != 'J' && tipoPessoa != 'j') {
            System.out.println("Opcao invalida");
            return;
        }

        if (tipoPessoa == 'F' || tipoPessoa == 'f') {
            ArrayList<PessoaFisica> pfs = repoPF.obterTodos();
            for (PessoaFisica pf : pfs) {
                System.out.println(pf.exibir());
                System.out.println("-----------------");
            }
        } else {
            ArrayList<PessoaJuridica> pjs = repoPJ.obterTodos();
            for (PessoaJuridica pj : pjs) {
                System.out.println(pj.exibir());
                System.out.println("-----------------");
            }
        }
    }

    public static void salvar() {
        System.out.println("Escolha um nome para salvar: ");
        String prefixo = sc.nextLine();

        try {
            repoPF.persistir(prefixo + "fisica.bin");
        } catch (IOException ex) {
            System.out.println("Erro ao salvar arquivo.");
            return;
        }

        try {
            repoPJ.persistir(prefixo + "juridica.bin");
        } catch (IOException ex) {
            System.out.println("Erro ao salvar arquivo.");
            return;
        }

        System.out.println("Salvo com sucesso! ");
    }

    public static void recuperar() {
        System.out.println("Digite o nome do arquivo que deseja recuperar: ");
        String prefixo = sc.nextLine();
        try {
            repoPF.recuperar(prefixo + "fisica.bin");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("O arquivo nao foi encontrado. ");
            return;
        }

        try {
            repoPJ.recuperar(prefixo + "juridica.bin");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("O arquivo nao foi encontrado. ");
            return;
        }

        System.out.println("Recuperacao realizada com sucesso! ");
    }
}