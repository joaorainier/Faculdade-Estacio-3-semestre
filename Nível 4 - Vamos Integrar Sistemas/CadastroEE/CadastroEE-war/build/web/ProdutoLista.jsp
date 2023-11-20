<%-- 
    Document   : ProdutoLista
    Created on : 23 de ago. de 2023, 20:05:27
    Author     : Joao Rainier
--%>

<%@page import="cadastroee.model.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    </head>
    <body class="container">
        <h1>Listagem de Produtos</h1>
        <br>
        <a class="btn btn-primary m-2" href="ServletProdutoFC?acao=formIncluir">Novo Produto</a>
        <br>
        <table class="table table-striped">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Nome</th>
                    <th>Quantidade</th>
                    <th>Preço de Venda</th>
                    <th>Opções</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Produto> lista = (List<Produto>) request.getAttribute("lista");
                    for (Produto p : lista) {
                %>
                <tr>
                    <td><%=p.getIdProduto()%></td>
                    <td><%=p.getNome()%></td>
                    <td><%=p.getQuantidade()%></td>
                    <td><%=p.getPrecoVenda()%></td>
                    <td>
                        <a class="btn btn-primary btn-sm" href="ServletProdutoFC?acao=formAlterar&id=<%=p.getIdProduto()%>">Alterar</a> 
                        <a class="btn btn-danger btn-sm" href="ServletProdutoFC?acao=excluir&id=<%=p.getIdProduto()%>">Excluir</a>
                    </td>
                </tr>
                <% }%>
            </tbody>
        </table>
    </body>
</html>
