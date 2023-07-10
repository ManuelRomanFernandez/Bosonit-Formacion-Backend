package com.formacion.bosonit.block11uploaddownloadfiles.fichero;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FicheroService {
    FicheroDto addFichero(MultipartFile file, String tipo, String categoria);
    Resource getFicheroById(Integer id);
}
