package com.testejava.project.service;

import com.testejava.project.model.Cliente;
import com.testejava.project.util.MyResponse;

public interface IClienteService {

	/**
	 * Recuperar os dados do cliente por NOME e CPF de forma paginada
	 * @param nome
	 * @param cpf
	 * @param page
	 * @param size
	 * @return MyResponse
	 */
	public MyResponse<?> getCliente(String nome, String cpf, int page, int size);
	
	/**
	 * Recuperar os dados de todos os clientes de forma paginada.
	 * @return MyResponse
	 */
	public MyResponse<?> getClientes(int page, int size);
	
	/**
	 * Insere um novo cliente
	 * @param cliente
	 * @return MyResponse
	 */
	public MyResponse<?> saveCliente(Cliente cliente);
	
	/**
	 * Remove um cliente pelo CPF
	 * @param cpf
	 * @return MyResponse
	 */
	public MyResponse<?> deleteCliente(String cpf);
	
	/**
	 * Atualiza um cliente pelo ID
	 * @param id
	 * @param cliente
	 * @return MyResponse
	 */
	public MyResponse<?> updateCliente(Long id, Cliente cliente);
	
	/**
	 * Atualiza as informações do cliente
	 * @param id
	 * @param cpf
	 * @param nome
	 * @param dataNascimento
	 * @return MyResponse
	 */
	public MyResponse<?> updateCliente(Long id, String cpf, String nome, String dataNascimento);
}
