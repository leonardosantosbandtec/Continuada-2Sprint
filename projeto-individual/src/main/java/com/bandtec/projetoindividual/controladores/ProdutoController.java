package com.bandtec.projetoindividual.controladores;

import com.bandtec.projetoindividual.dominios.Produto;
import com.bandtec.projetoindividual.repositorio.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.text.SimpleDateFormat;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Formatter;
import java.util.FormatterClosedException;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;



    ListaObj<Produto> listaObj = new ListaObj<Produto>(5);


    @GetMapping
    public ResponseEntity getProduto() {
        return


                ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity criarProduto(@RequestBody Produto novoProduto) {
        repository.save(novoProduto);
        listaObj.adiciona(novoProduto);
        listaObj.gravaProduto(listaObj,true);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getProdutoEsp(@PathVariable int id) {
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduto(@PathVariable int id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/documento", produces = {"application/csv"})
    @ResponseBody
    public ResponseEntity getDocumento(){




        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Produto.csv");

        byte[] arquivo = null;

        String nomeDoArquivo = "Produto.csv";



        try{

            arquivo = Files.readAllBytes(Paths.get(nomeDoArquivo));

            return new ResponseEntity(arquivo,headers,HttpStatus.OK);

        }catch (FileNotFoundException erro){

            System.err.println("Arquivo não encontrado");
            System.exit(1);
            return ResponseEntity.notFound().build();

        }catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }


}
