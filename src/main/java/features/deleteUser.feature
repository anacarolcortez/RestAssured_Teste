# language: pt
@regressivo
Funcionalidade: Excluir usuários usando a API pública Go Rest

  Fundo: Criar usuário com sucesso pelo método POST
    Dado que acesso o endpoint "/users"
    Quando efetuo uma requisição do tipo post para incluir um usuario com sucesso
    Então valido que o status code da requisição é "201"

  @CT4
  Cenario: Excluir usuário com sucesso pelo método DELETE
    Dado que acesso o endpoint "/users"
    Quando deleto o usuário selecionado
    Então valido que o status code da requisição é "204"
