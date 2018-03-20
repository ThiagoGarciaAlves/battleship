package br.fatecsp.engsw3.battleship.controller;

import br.fatecsp.engsw3.battleship.model.Nave;
import br.fatecsp.engsw3.battleship.repository.NaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/nave")
public class NaveController {

    @Autowired
    private NaveRepository naveRepository;

    @PostMapping
    public @ResponseBody String addNave(@RequestParam String tipo, @RequestParam int tamanho) {
        Nave nave = new Nave();
        nave.setTipo(tipo);
        nave.setTamanho(tamanho);
        naveRepository.save(nave);
        return "Nave criada!";
    }

    @GetMapping
    public @ResponseBody Iterable<Nave> getAllNaves() {
        return naveRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public @ResponseBody Nave getNave(@PathVariable int id) {
        Optional<Nave> naveEncontrada = naveRepository.findById(id);
        return naveEncontrada.orElse(null);
    }

    @PutMapping(path = "{id}")
    public @ResponseBody String changeNave(@PathVariable int id, @RequestParam String tipo, @RequestParam int tamanho) {
        Optional<Nave> naveEncontrada = naveRepository.findById(id);
        if (naveEncontrada.isPresent()) {
            Nave nave = naveEncontrada.get();
            nave.setTipo(tipo);
            nave.setTamanho(tamanho);
            naveRepository.save(nave);
            return "Nave modificada!";
        } else {
            return "Nave não encontrada!";
        }
    }

    @DeleteMapping(path = "{id}")
    public @ResponseBody String removeNave(@PathVariable int id) {
        naveRepository.deleteById(id);
        return "Nave removida!";
    }

}
