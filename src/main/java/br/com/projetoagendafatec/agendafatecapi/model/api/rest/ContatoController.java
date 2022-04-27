// obs: irá funcionar como uma servlet, porém usando o REST
package br.com.projetoagendafatec.agendafatecapi.model.api.rest;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.com.projetoagendafatec.agendafatecapi.model.repository.ContatoRepository;
import br.com.projetoagendafatec.agendafatecapi.model.entity.Contato;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contatos")
@RequiredArgsConstructor
@CrossOrigin("*")

public class ContatoController {

    private final ContatoRepository repository;

    /* método save */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contato save(@RequestBody Contato contato){
        return repository.save(contato);
    }

//    /* método listar */
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<Contato> list(){
//        return repository.findAll();
//    }

    /* metodo delete */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.deleteById(id);
    }

    /* método update */
    @PatchMapping("{id}/favorito")
    public void favorite(@PathVariable Integer id){
        Optional<Contato> contato = repository.findById(id);
        contato.ifPresent( c -> {
            boolean favorito = c.getFavorito() == Boolean.TRUE;
            c.setFavorito(!favorito);
            repository.save(c);
        });
    }

    /* método para upload de foto */
    @PutMapping("{id}/foto")
    public byte[] addPhoto(@PathVariable Integer id, @RequestParam("foto") Part arquivo){
        Optional<Contato> contato = repository.findById(id);
        return contato.map( c -> {
            try {
                InputStream inputStream = arquivo.getInputStream(); /* inputStream pega um arquivo e transforma em bytes */
                byte[] bytes = new byte[(int) arquivo.getSize()];
                IOUtils.readFully(inputStream, bytes);
                c.setFoto(bytes);
                repository.save(c);
                inputStream.close();
                return bytes;
            } catch (IOException ex) {
                return null;
            }
        }).orElse(null);
    }

}
