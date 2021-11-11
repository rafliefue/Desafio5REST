package com.spring.nttdata.desafio3.web;


import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.nttdata.desafio3.entities.Cliente;
import com.spring.nttdata.desafio3.services.ClienteService;

@RestController
public class ClienteRestController {
	
	private ClienteService clienteService;
	
	@Autowired
	public ClienteRestController(final ClienteService clienteService) throws ClassNotFoundException {
		this.clienteService = clienteService;

	}

	//Index
	
	@GetMapping({"/"})
	public String welcome(final Map<String, Object> model) {

		return "index";
	}
	
	//Show Clientes
	
	@GetMapping(value = "/clientes")
	public List<Cliente> showClientes() {

		List<Cliente> lista = this.clienteService.findAll();
	
		return lista;
	}
	
	//Añadir Clientes
	
	@PostMapping(value = "/new")
	public List<Cliente> addCliente(@RequestBody Cliente newCliente) {
	
		this.clienteService.save(newCliente);
		
		//Devuelvo la lista actualizada
		
		List<Cliente> lista = this.clienteService.findAll();
		
		return lista;
	}
	
	//Borrar Clientes
	
	@DeleteMapping(value = "/delete/{name}")
	public List<Cliente> deleteCliente(@PathVariable String  name) {
	
		this.clienteService.delete(this.clienteService.findByName(name));
		
		//Devuelvo la lista actualizada
		
		List<Cliente> lista = this.clienteService.findAll();
		
		return lista;
	}
		
	//Buscar Clientes por Nombre
	
		@GetMapping(value = "/find/{name}")
		public Cliente findClientes(@PathVariable String  name) {
			
				return this.clienteService.findByName(name);
			
		}
		
}
