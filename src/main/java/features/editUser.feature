# language: pt
@regressivo
Funcionalidade: Editar usuários usando a API pública Go Rest

  Fundo: Criar usuário com sucesso pelo método POST
    Dado que acesso o endpoint "/users"
    Quando efetuo uma requisição do tipo post para incluir um usuario com sucesso
    Então valido que o status code da requisição é "201"

  @CT3
  Cenario: Editar dados de usuário com sucesso pelo método PATCH
    Dado que acesso o endpoint "/users"
    Quando edito as informações do usuário selecionado
    Então valido que o status code da requisição é "200"