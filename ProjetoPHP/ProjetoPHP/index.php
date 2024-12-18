<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciamento de Projetos</title>
    <style>
        /* Estilos globais */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            color: #333;
        }

        .container {
            width: 80%;
            margin: 50px auto;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
            text-align: center;
        }

        /* Estilos para o título */
        h1 {
            margin-bottom: 20px;
            font-size: 28px;
            color: #444;
        }

        /* Estilos de navegação */
        .navigation nav ul {
            list-style: none;
            padding: 0;
            display: flex;
            justify-content: center;
        }

        .navigation nav ul li {
            margin: 0 15px;
        }

        .navigation nav ul li a {
            text-decoration: none;
            color: #007BFF;
            font-size: 18px;
            font-weight: bold;
            transition: color 0.3s ease;
        }

        .navigation nav ul li a:hover {
            color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Bem-vindo ao Sistema de Gerenciamento de Projetos</h1>
        <div class="navigation">
            <nav>
                <ul>
                    <li><a href="CadastrarProjeto.php">Área dos Projetos</a></li>
                    <li><a href="tarefas.php">Área das Tarefas</a></li>
                    <li><a href="equipe.php">Área da Equipe</a></li>
                </ul>
            </nav>
        </div>
    </div>
</body>
</html>
