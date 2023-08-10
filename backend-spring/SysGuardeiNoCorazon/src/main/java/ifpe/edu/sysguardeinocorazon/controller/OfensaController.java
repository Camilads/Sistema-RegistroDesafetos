package ifpe.edu.sysguardeinocorazon.controller;

import ifpe.edu.sysguardeinocorazon.model.entities.Desafeto;
import ifpe.edu.sysguardeinocorazon.model.entities.Ofensa;
import ifpe.edu.sysguardeinocorazon.model.repository.FacadeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/ofensa")
public class OfensaController {

    @PostMapping("/{desafeto_id}")
    public ResponseEntity<?> create(@RequestBody Ofensa ofensa, @PathVariable("desafeto_id") int desafetoId) {

        try {
            Desafeto des = FacadeRepository.getCurrentInstance().readDesafeto(desafetoId);

            ofensa.setDesafeto(des);

            if(des == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Desafeto não encontrado");
            }

            ofensa.setDataHora(new Timestamp(System.currentTimeMillis()));
            FacadeRepository.getCurrentInstance().createOfensa(ofensa);
            return ResponseEntity.status(HttpStatus.CREATED).body("Ofensa adicionada com sucesso");
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar ofensa") ;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ofensa> read(@PathVariable("id") int id) {
        try{
            Ofensa ofensa = FacadeRepository.getCurrentInstance().readOfensa(id);

            if(ofensa != null) {
                return ResponseEntity.ok(ofensa);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Ofensa>> readAll() {

        try {
            List<Ofensa> ofensas = FacadeRepository.getCurrentInstance().readAllOfensa();

            return ResponseEntity.ok(ofensas);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("desafeto/{desafeto_id}")
    public ResponseEntity<List<Ofensa>> readAllOfensaByDesafetoId(@PathVariable("desafeto_id") int desafetoId) {
        try {
            List<Ofensa> ofensas = FacadeRepository.getCurrentInstance().readAllOfensaByDesafetoId(desafetoId);
            return ResponseEntity.ok(ofensas);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
