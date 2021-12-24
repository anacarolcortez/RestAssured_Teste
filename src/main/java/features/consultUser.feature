# language: pt
@regressivo
Funcionalidade: Consultar usuários na API pública Go Rest

  Fundo: Criar usuário com sucesso
    Dado que acesso o endpoint "/users"
    Quando efetuo uma requisição do tipo post para incluir um usuario com sucesso
    Então valido que o status code da requisição é "201"

  @CT2
  Cenario: Consultar usuário com sucesso pelo método GET
    Dado que acesso o endpoint "/users"
    Quando consulto o usuário pelo id
    Então valido que o corpo da resposta trouxe os dados do usuário pesquisado
    E valido que o status code da requisição é "200"