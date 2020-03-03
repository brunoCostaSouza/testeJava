package com.testejava.project.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.testejava.project.util.Util;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "cpf", nullable = false)
	private String cpf;

	@Column(name = "data_nascimento", nullable = false)
	private Date dataNascimento;

	@Transient
	private int idade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = Util.retirarMascaraCpf(cpf);
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getIdade() {
		if (dataNascimento != null) {
			Calendar calen = Calendar.getInstance();
			int anoAtual = calen.get(Calendar.YEAR);
			calen.setTime(dataNascimento);
			int anoNascimento = calen.get(Calendar.YEAR);
			idade = anoAtual - anoNascimento;
		}
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public void replaceFields(Cliente cliente) {
		this.cpf = cliente.getCpf();
		this.dataNascimento = cliente.getDataNascimento();
		this.nome = cliente.getNome();
	}

	public void replaceFields(String nome, String cpf, Date dataNascimento) {
		if (nome != null && !nome.isBlank())
			this.nome = nome;
		if (cpf != null && !cpf.isBlank())
			this.cpf = cpf;
		if (dataNascimento != null)
			this.dataNascimento = dataNascimento;
	}
}
