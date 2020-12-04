package bandtec.com.Projetoindividualsprint3.controladores;

import bandtec.com.Projetoindividualsprint3.dominios.Produto;
import bandtec.com.Projetoindividualsprint3.repositorio.ProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.configuration.injection.MockInjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = ProdutoController.class)
class ProdutoControllerTest {


    @Autowired
    ProdutoController controller;

    @MockBean
    ProdutoRepository repository;






    @Test
    @DisplayName("Pesquisar() deve retornar status 200 se existe produtos no banco")
    void totalRequisicaoCadastros() {

        List<Produto> produtos = Arrays.asList(Mockito.mock(Produto.class));

        Mockito.when(repository.findAll()).thenReturn(produtos);

        ResponseEntity resposta = controller.totalRequisicaoCadastros();

        assertEquals(200,resposta.getStatusCodeValue(),"teste");
        assertEquals(produtos,resposta.getBody(),"teste");

    }


    @Test
    @DisplayName("delete() status 404 id não existe ")
    void delete() {
        Integer id = 51;
        Mockito.when(repository.existsById(id)).thenReturn(false);

        ResponseEntity resposta = controller.deleteUma(id);

        assertEquals(404, resposta.getStatusCodeValue());
        assertEquals(null,resposta.getBody());
    }


    @Test
    @DisplayName("delete() status 200 que foi deletado ")
    void delete2() {
        Integer id = 10;
        Mockito.when(repository.existsById(id)).thenReturn(true);

        ResponseEntity resposta = controller.deleteUma(id);

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(null,resposta.getBody());
    }

    @Test
    @DisplayName("consultarList() retornar status 200 se o identificador foi localizado")
    void consultarList(){
        UUID identificador = UUID.randomUUID();
        Map<UUID,Integer> id = new HashMap<>();


        id.put(identificador,1);

        Mockito.when(repository.existsById(id.get(identificador))).thenReturn(false);

        ResponseEntity resposta = controller.consultarList(identificador);

        assertEquals(200,resposta.getStatusCodeValue());

    }

    @Test
    @DisplayName("consultarList() retornar status 200 se o identificador não foi retornado")
    void consultarList2(){

        UUID identificador = UUID.randomUUID();
        Map<UUID,Integer> id = new HashMap<>();

        id.put(identificador,2);

        Mockito.when(repository.existsById(id.get(identificador))).thenReturn(true);

        ResponseEntity resposta = controller.consultarList(identificador);

        assertEquals(200,resposta.getStatusCodeValue());

    }

    @Test
    @DisplayName("algo")
    void enfileirar(){
        Produto produto = new Produto();

        ResponseEntity resposta = controller.enfileirarReq(produto);
        assertEquals(202,resposta.getStatusCodeValue());
    }




}

