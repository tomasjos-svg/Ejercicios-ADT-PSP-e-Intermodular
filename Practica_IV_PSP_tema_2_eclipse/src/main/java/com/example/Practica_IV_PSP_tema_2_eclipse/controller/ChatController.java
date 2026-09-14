package com.example.Practica_IV_PSP_tema_2_eclipse.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.Practica_IV_PSP_tema_2_eclipse.websocket.Mensaje;

@Controller
public class ChatController {

    @MessageMapping("/mensaje")
    @SendTo("/topic/mensajes")
    public Mensaje recibir(Mensaje mensaje) {
        return mensaje;
    }
}