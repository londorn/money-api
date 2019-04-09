package com.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.template.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
