package com.formacion.bosonit.block11uploaddownloadfiles.fichero;

import com.formacion.bosonit.block11uploaddownloadfiles.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class FicheroServiceImpl implements FicheroService{
    @Autowired
    FicheroRepository ficheroRepository;
    @Autowired
    StorageService storageService;

    @Override
    public FicheroDto addFichero(MultipartFile file, String tipo, String categoria) {
        if (!file.getOriginalFilename().split("\\.")[1].equals(tipo))
            throw new RuntimeException("MAL");

        storageService.store(file);

        FicheroDto input = new FicheroDto();

        input.setFilename(file.getOriginalFilename());
        input.setUpload_date(new Date());
        input.setCategory(categoria);

        return new FicheroDto(ficheroRepository.save(input.ficheroDtoToFichero()));
    }

    @Override
    public Resource getFicheroById(Integer id) {
        Fichero file = ficheroRepository.findById(id).orElseThrow();
        return storageService.loadAsResource(file.getFilename());
    }
}
