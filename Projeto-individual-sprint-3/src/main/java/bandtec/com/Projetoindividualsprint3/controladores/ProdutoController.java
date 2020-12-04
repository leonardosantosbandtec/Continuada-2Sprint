package bandtec.com.Projetoindividualsprint3.controladores;

import bandtec.com.Projetoindividualsprint3.dominios.Produto;
import bandtec.com.Projetoindividualsprint3.repositorio.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Service
@RequestMapping("/produto")
public class ProdutoController {

        @Autowired
        private ProdutoRepository repository;

        private Map<UUID,Produto> resultados = new HashMap<>();
        private Map<UUID,Integer> id = new HashMap<>();


        FilaObj<Produto> filaObj =  new FilaObj<Produto>(100);
        PilhaObj<Produto> pilhaObj = new PilhaObj<Produto>(100);
        ListaObj<Produto> listaObj = new ListaObj<Produto>(100);

        @GetMapping("/verificarBanco")
        public ResponseEntity totalRequisicaoCadastros() {
                return ResponseEntity.ok(repository.findAll());
        }



        //desfaz a ultima requisição
        @GetMapping("/desfazerReq")
        public ResponseEntity desfazerReq() {
                pilhaObj.pop();
                System.out.println(filaObj.ultimoFila());
                return ResponseEntity.accepted().body("Requisição desfeita");
        }

        //Enfileira a nova requisição cadastrada e retorna o identificador da requisição UUID
        @PostMapping("/enfileirarReq")
        public ResponseEntity enfileirarReq(@RequestBody Produto novoProduto) {

                UUID identificador = UUID.randomUUID();

                resultados.put(identificador, novoProduto);
                id.put(identificador,novoProduto.getCodProc());
                filaObj.insert(novoProduto);
                pilhaObj.push(novoProduto);


                return ResponseEntity.accepted().header("identificador",
                        identificador.toString()).header("Tempo-segundo","90").build();
        }

        //Faz o tratamento da requisição de cadastro consultando a lista de 1 em 1 minuto
        //e cadastrando caso tenha algum na fila
        @Scheduled(fixedRate = 60000)
        public void AgendamentoDeMalhaFila(){
                        if (!filaObj.isEmpty()) {
                                Produto produto = filaObj.poll();
                                listaObj.adiciona(produto);
                                repository.save(produto);
                        }
        }

        //consulta se a requisição foi realizada ultilizando UUID como parametro para identifcarmos a
        //requisição referente ao UUID...
        @GetMapping("/consultaList/{identificador}")
        public ResponseEntity consultarList(@PathVariable UUID identificador){


                        if (repository.existsById(id.get(identificador))){

                                listaObj.removePeloIndice(id.get(identificador));

                                return ResponseEntity.accepted().body("Requisição encerrada");


                        }else{

                                return ResponseEntity.ok().body("Requisição em andamento \n"+
                                        resultados.get(identificador)) ;
                        }


        }

        @DeleteMapping("/deletar/{id}")
        public ResponseEntity deleteUma(@PathVariable int id){
                if (repository.existsById(id)){
                        repository.deleteById(id);
                        return ResponseEntity.ok().build();
                }else{
                        return ResponseEntity.notFound().build();
                }
        }



        @PostMapping("/completo")
        public ResponseEntity enviar(@RequestParam("arquivo") MultipartFile arquivo)
                throws IOException {

                        if (arquivo.isEmpty()){
                                return ResponseEntity.badRequest().body("Arquivo não enviado!");
                        }

                        System.out.println("Recebendo um arquivo de tipo: " + arquivo.getContentType());

                        byte[] conteudo = arquivo.getBytes();

                        Path path = Paths.get(arquivo.getOriginalFilename());
                        Files.write(path, conteudo);

                        LeArquivo le = new LeArquivo();

                        le.leArquivo(arquivo.getOriginalFilename());

                        return ResponseEntity.created(null).build();
                }
        }

