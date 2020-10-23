package com.bandtec.projetoindividual.controladores;

import com.bandtec.projetoindividual.dominios.Produto;
import com.bandtec.projetoindividual.dominios.Servico;
import com.bandtec.projetoindividual.repositorio.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.FormatterClosedException;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoRepository repository;


    ListaObj<Servico> listaObj = new ListaObj<Servico>(5);


    @GetMapping
    public ResponseEntity getServico(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity criarServico(@RequestBody Servico novoServico){
        repository.save(novoServico);
        listaObj.adiciona(novoServico);
        listaObj.gravaServico(listaObj,true);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getServicoEsp(@PathVariable int id){
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteServico(@PathVariable int id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/documento", produces = {"application/csv"})
    @ResponseBody
    public ResponseEntity getDocumento(){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Servico.csv");

        byte[] arquivo = null;

        String nomeDoArquivo = "Servico.csv";

        try{

            arquivo = Files.readAllBytes(Paths.get(nomeDoArquivo));

            return new ResponseEntity(arquivo,headers, HttpStatus.OK);

        }catch (FileNotFoundException errou){

            System.err.println("Arquivo não encontrado");
            System.exit(1);
            return ResponseEntity.notFound().build();

        }catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

}

