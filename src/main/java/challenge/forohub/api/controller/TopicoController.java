package challenge.forohub.api.controller;

import challenge.forohub.api.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/topicos")//indicamos la ruta de acceso a la clase
public class TopicoController {

    @Autowired
    TopicoRepository topicoRepository;

    //Metodo que registra un topico
    @PostMapping
    public ResponseEntity<DtoRespuestaTopico> registrarTopico(@RequestBody @Valid DtoRegistroTopico dtoRegistroTopico, UriComponentsBuilder uriComponentsBuilder){

         Topico topico= topicoRepository.save(new Topico(dtoRegistroTopico));

         DtoRespuestaTopico dtoRespuestaTopico = new DtoRespuestaTopico(topico);

         URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
         return ResponseEntity.created(url).body(dtoRespuestaTopico);


    }


    //Metodo que retorna una lista de topicos
    @GetMapping
    public ResponseEntity<Page<DtoRespuestaTopico>> listadoTopicos(
            @PageableDefault(size = 8) Pageable paginacion,
            @RequestParam(required = false) NombreCurso curso,
            @RequestParam(required = false) Integer year) {

        Page<Topico> topicos;


        if (curso != null && year != null) {
            topicos = topicoRepository.findByCursoAndFecha(curso, year, paginacion);
        } else if (curso != null) {
            topicos = topicoRepository.findByCurso(curso, paginacion);
        } else if (year != null) {
            topicos = topicoRepository.findByFecha(year, paginacion);
        } else {
            topicos = topicoRepository.findAllByOrderByFechaDesc(paginacion);
        }

        if (topicos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(topicos.map(DtoRespuestaTopico::new));
    }

    //Metodo que retorna un topico por id
    @GetMapping("/{id}")
    public ResponseEntity<DtoRespuestaTopico> obtenerTopico(@PathVariable Long id) {

        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico =  new DtoRespuestaTopico(topico);
        return ResponseEntity.ok(datosTopico);

    }


    //Metodo que actualiza un topico
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DtoRespuestaTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DtoActualizarTopico dtoActualizarTopico) {
        Topico topico =topicoRepository.getReferenceById(id);
        topico.actualizarDatos(dtoActualizarTopico);
        return ResponseEntity.ok(new DtoRespuestaTopico(topico));
    }


    //Metodo que elimina un topico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarMedico(@PathVariable Long id){

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }


}
