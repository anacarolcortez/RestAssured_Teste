# language: pt
@regressivo
Funcionalidade: Criar usuários usando a API pública Go Rest

  @CT1
  Cenario: Criar usuário com sucesso pelo método POST
    Dado que acesso o endpoint "/users"
    Quando efetuo uma requisição do tipo post para incluir um usuario com sucesso
    Então valido que o status code da requisição é "201"