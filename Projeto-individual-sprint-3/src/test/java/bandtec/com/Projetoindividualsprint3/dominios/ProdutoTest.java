package bandtec.com.Projetoindividualsprint3.dominios;

import org.junit.jupiter.api.Test;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void anotacoesClasse(){
        Class produto = Produto.class;

        boolean temEntidade = produto.isAnnotationPresent(Entity.class);

        assertTrue(temEntidade,"Produto deveria estar anotado com @Entity");

        Table table = (Table) produto.getDeclaredAnnotation(Table.class);

        assertNotNull(table,"Produto deveria estar com anotação @Table");

        assertEquals("tbl_produto", table.name());
    }

    @Test
    void anotacoesNome() throws NoSuchFieldException {
        Class produto = Produto.class;

        Field nome = produto.getDeclaredField("nome");

        boolean temColuna = nome.isAnnotationPresent(Column.class);

        assertTrue(temColuna,"nome deveria estar com anotação @Column");
    }

    @Test
    void anotacoesDescricao() throws NoSuchFieldException {
        Class produto = Produto.class;

        Field descricao = produto.getDeclaredField("descricao");

        boolean temColuna = descricao.isAnnotationPresent(Column.class);

        assertTrue(temColuna,"nome deveria estar com anotação @Column");
    }

    @Test
    void dutoanotacoesTipoProduto() throws NoSuchFieldException {
        Class produto = Produto.class;

        Field tipoProduto = produto.getDeclaredField("tipoProduto");

        boolean temColuna = tipoProduto.isAnnotationPresent(Column.class);

        assertTrue(temColuna,"nome deveria estar com anotação @Column");
    }




}