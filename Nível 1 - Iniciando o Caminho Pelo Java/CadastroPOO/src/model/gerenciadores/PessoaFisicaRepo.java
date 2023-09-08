/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Joao Rainier
 */


package model.gerenciadores;

import java.util.ArrayList;
import java.util.Iterator;
import model.entidades.PessoaFisica;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class PessoaFisicaRepo {

    private ArrayList<PessoaFisica> repo;

    public PessoaFisicaRepo() {
        repo = new ArrayList<>();
    }

    public void inserir(PessoaFisica pessoa) {
        repo.add(pessoa);
    }

    public void alterar(PessoaFisica pessoa) {
        for (int i = 0; i < repo.size(); i++) {
            if (pessoa.getId() == repo.get(i).getId()) {
                repo.set(i, pessoa);
                break;
            }
        }
    }

    public void excluir(int Id) {
        Iterator<PessoaFisica> iterator = repo.iterator();
        while (iterator.hasNext()) {
            PessoaFisica pessoa = iterator.next();
            if (pessoa.getId() == Id) {
                iterator.remove();
                break;
            }
        }
    }

    public PessoaFisica obter(int Id) {
        for (PessoaFisica pessoa : repo) {
            if (pessoa.getId() == Id) {
                return pessoa;
            }
        }
        return null;
    }

    public ArrayList<PessoaFisica> obterTodos() {
        return repo;
    }

    public void persistir(String caminho) throws FileNotFoundException, IOException {
        FileOutputStream fout = new FileOutputStream(caminho);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(repo);
    }

    public void recuperar(String caminho) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fout = new FileInputStream(caminho);
        ObjectInputStream oos = new ObjectInputStream(fout);
        repo = (ArrayList<PessoaFisica>) oos.readObject();
    }
}