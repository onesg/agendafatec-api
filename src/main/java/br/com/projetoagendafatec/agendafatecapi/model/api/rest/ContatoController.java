// obs: irá funcionar como uma servlet, porém usando o REST
package br.com.projetoagendafatec.agendafatecapi.model.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.com.projetoagendafatec.agendafatecapi.model.repository.ContatoRepository;
import br.com.projetoagendafatec.agendafatecapi.model.entity.Contato;
import java.util.List;

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

    /* método listar */
    @GetMapping
    public List<Contato> list(){
        return repository.findAll();
    }

    /* metodo delete */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.deleteById(id);
    }

}
