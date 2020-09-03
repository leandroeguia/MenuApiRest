package com.lemon.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lemon.menu.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
