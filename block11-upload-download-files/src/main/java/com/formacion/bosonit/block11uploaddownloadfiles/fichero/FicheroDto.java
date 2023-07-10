package com.formacion.bosonit.block11uploaddownloadfiles.fichero;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FicheroDto {
    Integer id;
    String filename;
    Date upload_date;
    String category;

    public FicheroDto (Fichero fichero){
        this.id = fichero.getId();
        this.filename = fichero.getFilename();
        this.upload_date = fichero.getUpload_date();
        this.category = fichero.getCategory();
    }

    public Fichero ficheroDtoToFichero(){
        return new Fichero(
                this.getId(),
                this.getFilename(),
                this.getUpload_date(),
                this.getCategory()
        );
    }
}
