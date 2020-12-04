package bandtec.com.Projetoindividualsprint3.dominios;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity
@Table(name = "tbl_produto")
public class Produto {

    @Id
    private Integer codProc;

    @Column(nullable = false, length = 20)
    private String nome;

    @Min(1)
    private Double preco;

    @Min(0)
    private Integer unidade;

    @Column(nullable = false, length = 60)
    private String descricao;

    @Column(nullable = false, length = 15)
    private String tipoProduto;



    public Integer getCodProc() {
        return codProc;
    }

    public void setCodProc(Integer codProc) {
        this.codProc = codProc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getUnidade() {
        return unidade;
    }

    public void setUnidade(Integer unidade) {
        this.unidade = unidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
}
