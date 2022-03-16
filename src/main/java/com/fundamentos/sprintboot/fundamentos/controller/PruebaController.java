package com.fundamentos.sprintboot.fundamentos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PruebaController {

    /**
     * @RequestMapping Con esta anotación se mapea este método para recibir las
     * solicitudes http.
     *
     *  @ResponseBody Se usa para responder un cuerpo en el retorno del método.
     */
    @RequestMapping
    @ResponseBody
    public ResponseEntity<String> function(){
        return new ResponseEntity<>("Hola desde controlador", HttpStatus.OK);
    }

}
