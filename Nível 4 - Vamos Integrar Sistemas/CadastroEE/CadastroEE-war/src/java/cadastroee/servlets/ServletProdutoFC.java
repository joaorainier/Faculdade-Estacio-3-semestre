/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cadastroee.servlets;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Joao Rainier
 */
public class ServletProdutoFC extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB ProdutoFacadeLocal facade;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if (acao == null) {
            acao = "listar";
        }

        String destino = "ProdutoLista.jsp";

        switch (acao) {
            case "listar" ->
                request.setAttribute("lista", facade.findAll());
            case "incluir" -> {
                String nome = request.getParameter("nome");
                String quantidade = request.getParameter("quantidade");
                String preco = request.getParameter("preco");

                Produto produto = new Produto();
                produto.setNome(nome);
                produto.setPrecoVenda(Float.parseFloat(preco));
                produto.setQuantidade(Integer.parseInt(quantidade));

                facade.create(produto);

                request.setAttribute("lista", facade.findAll());
            }
            case "alterar" -> {
                String id = request.getParameter("id");
                String nome = request.getParameter("nome");
                String quantidade = request.getParameter("quantidade");
                String preco = request.getParameter("preco");

                Produto produto = facade.find(Integer.valueOf(id));

                produto.setNome(nome);
                produto.setPrecoVenda(Float.parseFloat(preco));
                produto.setQuantidade(Integer.parseInt(quantidade));

                facade.edit(produto);
                request.setAttribute("lista", facade.findAll());
            }
            case "excluir" -> {
                String id = request.getParameter("id");
                facade.remove(facade.find(Integer.valueOf(id)));
                request.setAttribute("lista", facade.findAll());
            }

            case "formIncluir" ->
                destino = "ProdutoDados.jsp";

            case "formAlterar" -> {
                String id = request.getParameter("id");
                request.setAttribute("produto", facade.find(Integer.valueOf(id)));
                destino = "ProdutoDados.jsp";
            }
        }
        request.getRequestDispatcher(destino).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
