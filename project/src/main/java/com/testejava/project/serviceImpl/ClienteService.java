package com.testejava.project.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.testejava.project.model.Cliente;
import com.testejava.project.repository.IClienteRepository;
import com.testejava.project.service.IClienteService;
import com.testejava.project.util.Message;
import com.testejava.project.util.MyResponse;
import com.testejava.project.util.Util;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	IClienteRepository mRepository;

	@Override
	public MyResponse<?> getCliente(String nome, String cpf, int page, int size) {
		MyResponse<List<Cliente>> response = new MyResponse<List<Cliente>>();
		response.setSuccess(true);
		response.setData(mRepository.search(nome, cpf, PageRequest.of(page, size)).getContent());
		return response;
	}

	@Override
	public MyResponse<?> getClientes(int page, int size) {
		MyResponse<List<Cliente>> res = new MyResponse<List<Cliente>>();
		res.setSuccess(true);
		res.setData(mRepository.findAll(PageRequest.of(page, size)).getContent());
		return res;
	}

	@Override
	public MyResponse<?> saveCliente(Cliente cliente) {
		MyResponse<Cliente> response = new MyResponse<Cliente>();

		if(!camposValidos(cliente, response)) {
			return response;
		}

		try {
			Cliente clienteSaved = mRepository.save(cliente);
			if (clienteSaved != null) {
				response.setSuccess(true);
				response.setMessage(Message.CLIENT_ADD_SUCCESS);
				response.setData(clienteSaved);
			}
		} catch (Exception e) {
			response.setMessage(Message.FILED_ADD_CLIENT);
		}
		return response;
	}

	@Override
	public MyResponse<?> updateCliente(Long id, Cliente cliente) {
		MyResponse<Cliente> response = new MyResponse<Cliente>();
		cliente.setId(id);
		if(!camposValidos(cliente, response)) {
			return response;
		}

		Cliente clienteAux = findClienteById(id, response);
		if (clienteAux != null) {
			clienteAux.replaceFields(cliente);
			saveCliente(response, clienteAux);
		}
		return response;
	}

	@Override
	public MyResponse<?> deleteCliente(String cpf) {
		MyResponse<Cliente> response = new MyResponse<Cliente>();
		Cliente cliente = mRepository.findByCpf(cpf);

		if (cliente != null) {
			try {
				mRepository.delete(cliente);
				response.setSuccess(true);
				response.setMessage(Message.SUCCESS_REMOVE_CLIENT);
			} catch (Exception e) {
				response.setMessage(Message.FAILED_REMOVE_CLIENT);
			}
			return response;
		} else {
			response.setMessage(String.format(Message.CLIENT_NOT_FOUND, cpf));
		}
		return response;
	}

	@Override
	public MyResponse<?> updateCliente(Long id, String cpf, String nome, String dataNascimento) {
		MyResponse<Cliente> response = new MyResponse<Cliente>();
		Date data = Util.convertData(dataNascimento);

		if (dataNascimento != null && !dataNascimento.isBlank() && data == null) {
			response.setMessage(Message.BIRTH_DATE_INVALID);
			return response;
		}

		if (cpf != null && !cpf.isBlank() && !Util.cpfValido(cpf)) {
			response.setMessage(Message.CPF_INVALID);
			return response;
		}

		Cliente clienteAux = findClienteById(id, response);
		if (clienteAux != null) {
			clienteAux.replaceFields(nome, cpf, data);
			saveCliente(response, clienteAux);
		}
		return response;
	}

	private Cliente findClienteById(Long id, MyResponse<Cliente> response) {
		try {
			return mRepository.findById(id).get();
		} catch (Exception e) {
			response.setMessage(String.format(Message.CLIENT_NOT_FOUND_ID, id.toString()));
		}
		return null;
	}

	private void saveCliente(MyResponse<Cliente> response, Cliente clienteAux) {
		try {
			mRepository.save(clienteAux);
			response.setSuccess(true);
			response.setMessage(Message.SUCCESS_UPDATE_CLIENT);
			response.setData(clienteAux);
		} catch (Exception e) {
			response.setMessage(Message.FAILED_UPDATE_CLIENT);
		}
	}
	
	private boolean camposValidos(Cliente cliente, MyResponse<Cliente> response) {
		
		if (!Util.cpfValido(cliente.getCpf())) {
			response.setMessage(Message.CPF_INVALID);
			return false;
		}

		Cliente clienteAux = mRepository.findByCpf(cliente.getCpf());
		if (clienteAux != null && cliente.getId() != clienteAux.getId()) {
			response.setMessage(Message.FAILED_CLIENT_EXIST);
			return false;
		}
		return true;
	}

}
