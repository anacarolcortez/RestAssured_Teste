package stepsDefinitions;

import APIs.GoRestAPI;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.Assert.assertTrue;

public class GoRest {
    GoRestAPI goRestAPI = new GoRestAPI();

    @Dado("que acesso o endpoint {string}")
    public void queAcessoOEndpoint(String arg0) {
        goRestAPI.setEndpoint(arg0);
    }

    @Quando("efetuo uma requisição do tipo post para incluir um usuario com sucesso")
    public void efetuoUmaRequisiçãoDoTipoPostParaIncluirUmUsuarioComSucesso() {
        goRestAPI.criarUsuarioValido();
        goRestAPI.adicionarUsuarioComSucesso();
    }

    @Então("valido que o status code da requisição é {string}")
    public void validoQueOStatusCodeDaRequisiçãoÉ(String code) {
        assertTrue(goRestAPI.validarStatus(code));
    }

    @Quando("consulto o usuário pelo id")
    public void consultoOUsuárioPeloId() {
        goRestAPI.consultarUsuarioComSucesso();
    }

    @Então("valido que o corpo da resposta trouxe os dados do usuário pesquisado")
    public void validoQueOCorpoDaRespostaTrouxeOsDadosDoUsuárioPesquisado() {
        System.out.println("ok");
    }

    @Quando("deleto o usuário selecionado")
    public void deletoOUsuárioSelecionado() {
        goRestAPI.deletarUsuarioComSucesso();
    }

    @Quando("edito as informações do usuário selecionado")
    public void editoAsInformaçõesDoUsuárioSelecionado() {
        goRestAPI.editarUsuarioComSucesso();
    }
}
