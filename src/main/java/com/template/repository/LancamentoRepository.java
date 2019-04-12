package com.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.template.model.Lancamento;
import com.template.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}