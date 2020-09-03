package com.lemon.menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lemon.menu.exceptions.ResourceNotFoundException;
import com.lemon.menu.model.Menu;
import com.lemon.menu.repository.MenuRepository;

@RestController
@RequestMapping("/api/v1")
public class MenuController {
	
	@Autowired
	private MenuRepository menuRepository;
	

	
	//Obtener el listado de todos los menues
	@GetMapping("/menues")
	public List<Menu> getAllMenu(){
		return menuRepository.findAll();
	}
	
	//Creacion de menu
	@PostMapping("/menues")
	public Menu crearMenu(@Valid @RequestBody Menu menu) {
		return menuRepository.save(menu);
	}
	
	//Obtener menu por ID
	@GetMapping("/menues/{id}")
	public ResponseEntity<Menu> getMenuById (@PathVariable (value="id") long MenuId)
			throws ResourceNotFoundException {
		Menu menu = menuRepository.findById(MenuId)
				.orElseThrow(() -> new ResourceNotFoundException("Menu no encontrado para este ID :: " + MenuId));
		return ResponseEntity.ok().body(menu);
	}
	
	//Actualizar Menu
	@PutMapping("/menues/{id}")
	public ResponseEntity<Menu> updateEmployee(@PathVariable(value = "id") Long MenuId,
			@Valid @RequestBody Menu menuDetails) throws ResourceNotFoundException {
		Menu menu = menuRepository.findById(MenuId)
				.orElseThrow(() -> new ResourceNotFoundException("Menu no encontrado para este ID :: " + MenuId));

		menu.setName(menuDetails.getName());
		menu.setDescription(menuDetails.getDescription());
		final Menu updatedMenu = menuRepository.save(menu);
		return ResponseEntity.ok(updatedMenu);
	}

	
	//Borrar menu
	@DeleteMapping("/menues/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long MenuId)
			throws ResourceNotFoundException {
		Menu menu = menuRepository.findById(MenuId)
				.orElseThrow(() -> new ResourceNotFoundException("Menu no encontrado para este ID :: " + MenuId));

		menuRepository.delete(menu);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
