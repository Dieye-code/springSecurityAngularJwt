package sn.samaneCorporation.securiteJwtSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sn.samaneCorporation.securiteJwtSpring.entities.Client;
import sn.samaneCorporation.securiteJwtSpring.repositories.ClientRepository;

@RestController
@CrossOrigin("*")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	@GetMapping("/api/Client")
	public List<Client> listClient(){
		List<Client> lists = this.clientRepository.findAll();
//		lists.forEach(client->{
//			System.out.println(client.getId());
//		});
		return lists;
	}
	
	

	@PostMapping("/api/Client/add")
	public Client add(@RequestBody Client client) {
		return this.clientRepository.save(client);
	}

}
