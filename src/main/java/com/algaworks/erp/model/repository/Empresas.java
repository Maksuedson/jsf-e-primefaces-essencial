package com.algaworks.erp.model.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.algaworks.erp.model.Empresa;

public class Empresas implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	public Empresas() {
	}

	public Empresas(EntityManager manager) {
		this.manager = manager;
	}
	
	public Empresa porId(Long id) {
		return manager.find(Empresa.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Empresa> pesquisar(String nome){
		String jpql = "from Empresa where nomeFantasia like :nomeFantasia";
		
		Query query = manager.createQuery(jpql);
		query.setParameter("nomeFantasia", nome + "%");
		
		return query.getResultList();
	}
	
	public Empresa guardar(Empresa empresa) {
		return manager.merge(empresa);
	}
	
	public void remover(Empresa empresa) {
		empresa = porId(empresa.getId());
		manager.remove(empresa);
	}
}
