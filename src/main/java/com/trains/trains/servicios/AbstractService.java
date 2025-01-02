package com.trains.trains.servicios;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T, ID> {

    private final JpaRepository<T, ID> repository;

    public AbstractService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    public T guardar(T entidad) throws Exception {
        return repository.save(entidad);
    }

    public List<T> mostrarTodos() {
        return repository.findAll();
    }

    public Optional<T> encontrarPorId(ID id) {
        return repository.findById(id);
    }

    public void eliminar(ID id) {
        repository.deleteById(id);
    }

    public abstract T editar(ID id, T entidadEditada) throws Exception;
}
