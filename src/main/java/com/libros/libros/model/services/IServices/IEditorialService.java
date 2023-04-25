package com.libros.libros.model.services.IServices;

import com.libros.libros.model.entitys.Editorial;

import java.util.List;

public interface IEditorialService {

    List<Editorial> getEditoriales();

    Editorial findById(Long id);

    void saveEditorial(Editorial editorial);

    void deleteEditorial(Editorial editorial);

}
