package com.formacion.bosonit.block6personcontrollers.controllers;

import com.formacion.bosonit.block6personcontrollers.models.Persona;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador/bean")
public class Controlador {

    Persona bean1;
    Persona bean2;
    Persona bean3;

    public Controlador(@Qualifier("bean1") Persona bean1, @Qualifier("bean2") Persona bean2, @Qualifier("bean3") Persona bean3) {
        this.bean1 = bean1;
        this.bean2 = bean2;
        this.bean3 = bean3;
    }

    @GetMapping("/{bean}")
    public Persona getBean(@PathVariable("bean") String bean) {
        Persona returnBean = null;
        switch (bean){
            case "bean1":
                returnBean = this.bean1;
                break;
            case "bean2":
                returnBean = this.bean2;
                break;
            case "bean3":
                returnBean = this.bean3;
                break;
        }
        return returnBean;
    }
}
