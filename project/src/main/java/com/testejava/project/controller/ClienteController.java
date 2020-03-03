package com.testejava.project.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testejava.project.model.Cliente;
import com.testejava.project.service.IClienteService;
import com.testejava.project.util.MyResponse;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService mService;

	@GetMapping
	public MyResponse<?> getClientes(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return mService.getClientes(page, size);
	}

	@GetMapping("/search")
	public MyResponse<?> search(@PathParam("nome") String nome, @PathParam("cpf") String cpf,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {

		return mService.getCliente(nome, cpf, page, size);
	}

	@PostMapping
	public MyResponse<?> save(@RequestBody Cliente cliente) {
		return mService.saveCliente(cliente);
	}

	@DeleteMapping("/{cpf}")
	public MyResponse<?> delete(@PathVariable("cpf") String cpf) {
		return mService.deleteCliente(cpf);
	}

	@PutMapping("/{id}")
	public MyResponse<?> update(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
		return mService.updateCliente(id, cliente);
	}
	
	@PatchMapping
	public MyResponse<?> updateCpf(
			@PathParam("id") Long id, 
			@RequestParam(value = "cpf", required = false) String cpf, 
			@RequestParam(value = "nome", required = false) String nome, 
			@RequestParam(value = "dataNascimento", required = false) String dataNascimento) {
		return mService.updateCliente(id, cpf, nome, dataNascimento);
	}

}
