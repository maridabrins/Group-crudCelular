package com.celular.celular.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.celular.celular.model.celularModel;
import com.celular.celular.repository.celularRepository;

@Service
public class CelularService {
	
	private celularRepository repository;
	
	
	//metodo para criar um celular na tabela
	public celularModel saveCelular(celularModel celular) {
		return repository.save(celular);
		
	}
	//metodo para listar todos os celulares cadastrados
		public List<celularModel> getCelular(){
			return repository.findAll();
		}
		
		//metodo para buscar por id
		public celularModel getCelularById(UUID id) {
			return repository.findById(id).get();
		}
		//método para atualizar por id
		public celularModel updateCelular(UUID id, celularModel celular) {
			celularModel celularAtualizado =  repository.findById(id).get();;
			celularAtualizado.setMarca(celular.getMarca());
			celularAtualizado.setCapacidadeBateria(celular.getBateria());
			celularAtualizado.setModelo(celular.getModelo());;
			celularAtualizado.setTamanhoTela(celular.getTamanhoTela());
			return repository.save(celular);
		}
		
		public void deleteCelular(UUID id) {
			repository.deleteById(id);
		}

}
