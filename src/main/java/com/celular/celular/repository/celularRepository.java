package com.celular.celular.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.celular.celular.model.celularModel;

@Repository
public interface celularRepository extends JpaRepository<celularModel,UUID> {

}
