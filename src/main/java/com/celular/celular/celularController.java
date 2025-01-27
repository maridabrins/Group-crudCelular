package com.celular.celular;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.celular.celular.model.celularModel;
import com.celular.celular.repository.celularRepository;

@RestController
public class celularController {

    @Autowired
    private celularRepository repository;

 

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCelular(@RequestBody celularDto celular) {
        try {
            celularModel celularData = new celularModel(celular);
            repository.save(celularData);
            return ResponseEntity.ok("Celular cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Erro ao cadastrar celular: " + e.getMessage());
        }
    }

    @GetMapping("/celulares")
    public ResponseEntity<List<celularModel>> listarCelulares() {
       
            List<celularModel> celulares = repository.findAll();
            return ResponseEntity.ok(celulares);
        } 
    

    @GetMapping("/celulares/{id}")
    public ResponseEntity<?> obterCelular(@PathVariable String id) {
    
            UUID uuid = UUID.fromString(id);
            Optional<celularModel> celular = repository.findById(uuid);
            if (celular.isPresent()) {
                return ResponseEntity.ok(celular.get());
            }
			return null;
    }

    @PutMapping("/celulares/{id}")
    public ResponseEntity<?> editarCelular(@PathVariable String id, @RequestBody celularDto celularAtualizado) {
       
            UUID uuid = UUID.fromString(id);
            Optional<celularModel> celularExistente = repository.findById(uuid);
            if (celularExistente.isPresent()) {
                celularModel celular = celularExistente.get();
                celular.setMarca(celularAtualizado.getMarca());
                celular.setModelo(celularAtualizado.getModelo());
                celular.setTamanhoTela(celularAtualizado.getTamanhoTela());
                celular.setCapacidadeBateria(celularAtualizado.getCapacidadeBateria());
                repository.save(celular);
                return ResponseEntity.ok("Celular atualizado com sucesso!");
            } else {
                return ResponseEntity.status(404).body("Celular não encontrado");
           
    }
    }

    @DeleteMapping("/celulares/{id}")
    public ResponseEntity<?> deletarCelular(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Optional<celularModel> celular = repository.findById(uuid);
            if (celular.isPresent()) {
                repository.deleteById(uuid);
                return ResponseEntity.ok("Celular deletado com sucesso!");
            } else {
                return ResponseEntity.status(404).body("Celular não encontrado");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("ID inválido");
        }
    }
}
