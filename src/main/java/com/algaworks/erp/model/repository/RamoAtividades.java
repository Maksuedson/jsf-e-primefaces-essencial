package com.algaworks.erp.model.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.algaworks.erp.model.RamoAtividade;

public class RamoAtividades implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	public RamoAtividades() {
	}

	public RamoAtividades(EntityManager manager) {
		this.manager = manager;
	}
	
	@SuppressWarnings("unchecked")
	public List<RamoAtividade> pesquisar(String descricao){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<RamoAtividade> criteriaQuery = criteriaBuilder.createQuery(RamoAtividade.class);
		Root<RamoAtividade> root = criteriaQuery.from(RamoAtividade.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.like(root.get("descricao"), descricao + "%"));
		
		Query query = manager.createQuery(criteriaQuery);
		return query.getResultList();
	}
}
