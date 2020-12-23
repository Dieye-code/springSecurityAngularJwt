package sn.samaneCorporation.securiteJwtSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sn.samaneCorporation.securiteJwtSpring.entities.Compte;
import sn.samaneCorporation.securiteJwtSpring.repositories.CompteRepository;

@RestController
public class CompteController {
	
	@Autowired
	private CompteRepository compteRepository;
	
	@GetMapping("/api/COmpte")
	public List<Compte> listeCompte() {
		return this.compteRepository.findAll();
	}
	
	@PostMapping("/api/Compte/add")
	public Compte addCompte(@RequestBody Compte compte) {
		
		return this.compteRepository.save(compte);
	}
	
	@PostMapping("/api/Compte/update/{id}")
	public Compte editCompte(@PathVariable int id, @RequestBody Compte compte) {
		
		try {

			Compte c = this.compteRepository.findById(id).get();
			compte.setId(id);
			return this.compteRepository.saveAndFlush(compte);
		} catch (Exception e) {
			return null;
		}
	}

}
