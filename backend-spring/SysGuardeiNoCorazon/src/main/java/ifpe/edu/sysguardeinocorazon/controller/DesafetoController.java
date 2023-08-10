package ifpe.edu.sysguardeinocorazon.controller;

import ifpe.edu.sysguardeinocorazon.model.entities.Desafeto;
import ifpe.edu.sysguardeinocorazon.model.repository.FacadeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/desafeto")
public class DesafetoController {

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Desafeto desafeto) {

        try {
            FacadeRepository.getCurrentInstance().createDesafeto(desafeto);

            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro de desafeto realizado com sucesso");
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar desafeto") ;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Desafeto> read(@PathVariable("id") int id) {

        try {
            Desafeto desafeto = FacadeRepository.getCurrentInstance().readDesafeto(id);

            if(desafeto != null) {
                return ResponseEntity.ok(desafeto);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Desafeto>> readAll() {

        try {
            List<Desafeto> desafetos = FacadeRepository.getCurrentInstance().readAllDesafeto();

            return ResponseEntity.ok(desafetos);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
