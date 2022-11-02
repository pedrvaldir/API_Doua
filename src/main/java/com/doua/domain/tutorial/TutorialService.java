package com.doua.domain.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorialService {

    @Autowired
    private TutorialRepository repositorio;

    public void setRepository(TutorialRepository repository) {
        this.repositorio = repository;
    }

    public Iterable<Tutorial> getTutoriais() {
        return repositorio.findAll();
    }

    public Tutorial save(Tutorial tutorial) {

      return repositorio.save(tutorial);

    }

    public void delete(Long id) {
        repositorio.deleteById(id);
    }
}
