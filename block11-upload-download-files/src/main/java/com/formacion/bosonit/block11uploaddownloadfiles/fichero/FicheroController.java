package com.formacion.bosonit.block11uploaddownloadfiles.fichero;

import com.formacion.bosonit.block11uploaddownloadfiles.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.nio.file.Path;

@RestController
public class FicheroController {
    @Autowired
    FicheroService ficheroService;
    @Autowired
    StorageService storageService;

    @PostMapping("/upload/{tipo}")
    public ResponseEntity<FicheroDto> addFichero(
            @RequestBody MultipartFile file,
            @PathVariable String tipo,
            @RequestParam String categoria)
    {
        URI location = URI.create("/fichero");
        return ResponseEntity.created(location).body(ficheroService.addFichero(file, tipo, categoria));
    }

    @GetMapping("/setpath")
    public ResponseEntity<String> updateFileDirectory(
            @RequestParam String path
    ){
        storageService.setRootLocation(Path.of(path));
        return ResponseEntity.ok().body("El directorio se ha cambiado a " + path);
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> getFichero(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String filename
    ){
        try{
            Resource file = null;

            if (id != null){
                file = ficheroService.getFicheroById(id);
            }else if (filename != null){
                file = storageService.loadAsResource(filename);
            }

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
