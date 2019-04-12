package com.template.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.template.event.RecursoCriadoEvent;
import com.template.model.Categoria;
import com.template.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categotia, HttpServletResponse http) {
		Categoria categoriaSalva = categoriaRepository.save(categotia);

		publisher.publishEvent(new RecursoCriadoEvent(this, http, categoriaSalva.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {

		Categoria categoria = null;
		if(categoriaRepository.findById(codigo).isPresent()) {
			categoria = categoriaRepository.findById(codigo).get();
			return ResponseEntity.status(HttpStatus.OK).body(categoria) ;
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(categoria);
	}

}
