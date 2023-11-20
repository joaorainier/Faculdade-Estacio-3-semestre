<%-- 
    Document   : ProdutoDados
    Created on : 23 de ago. de 2023, 21:14:01
    Author     : Joao Rainier
--%>

<%@page import="cadastroee.model.Produto"%>
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
        <form class="form" action="ServletProdutoFC" method="post">
            <%
                Produto produto = (Produto) request.getAttribute("produto");
                String acao = produto == null ? "incluir" : "alterar";
            %>
            <h1><%=(acao == "incluir") ? "Novo" : "Dados do"%> Produto</h1>

            <input type="hidden" name="acao" value="<%=acao%>"/>

            <% if (acao == "alterar") {%>
            <input type="hidden" name="id" value="<%=produto.getIdProduto()%>"/>
            <%}%>

            <div class="mb-3">
                <label class="form-label">Nome:</label>
                <input class="form-control" type="text" name="nome" value="<%=(acao == "alterar") ? produto.getNome() : ""%>"/>
            </div>

            <div class="mb-3">
                <label class="form-label">Quantidade:</label>
                <input class="form-control" type="text" name="quantidade" value="<%=(acao == "alterar") ? produto.getQuantidade() : ""%>"/>
            </div>

            <div class="mb-3">
                <label class="form-label">Preço:</label>
                <input class="form-control" type="text" name="preco" value="<%=(acao == "alterar") ? produto.getPrecoVenda() : ""%>"/>
            </div>

            <br>
            <input class="btn btn-primary" type="submit"  value="<%=(acao == "incluir") ? "Adicionar" : "Alterar"%> Produto"/>
        </form>
    </body>
</html>
