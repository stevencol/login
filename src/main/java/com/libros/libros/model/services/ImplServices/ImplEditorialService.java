package com.libros.libros.model.services.ImplServices;

import com.libros.libros.model.entitys.Editorial;
import com.libros.libros.model.services.IServices.IEditorialService;
import com.libros.libros.repository.IEditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class ImplEditorialService implements IEditorialService {

    @Autowired
    IEditorialRepository editorialRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Editorial> getEditoriales() {
        return editorialRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Editorial findById(Long id) {
        return editorialRepository.findById(id).orElse(null);
    }

    @Override
    public void saveEditorial(Editorial editorial) {
        editorialRepository.save(editorial);
    }

    @Override
    public void deleteEditorial(Editorial editorial) {
        editorialRepository.deleteById(editorial.getId());
    }


}
