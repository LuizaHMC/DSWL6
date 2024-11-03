package com.projeto.dto;

import java.util.List;

import com.projeto.model.Tarefa;

public interface IDTO<T> {
    void save(T obj);
    void delete(int id);
    List<T> list();
    T getById(int id);
	List<Tarefa> listByProjeto(int projetoId);
}