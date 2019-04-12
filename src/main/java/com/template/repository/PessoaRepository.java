package com.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.template.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
