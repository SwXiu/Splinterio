package com.cesur.splinterio.services;

import com.cesur.splinterio.models.dtos.ChatDTO;

public interface ChatService {
    /**
     * 1.- comunicacion entre persona y chatbot (inicio del chat)
     * 2.- cerrar chatbot
     * 3.- recibe mensaje y devuelve respuesta (codigos de respuesta y pregunta)
     */
    void addChat(String email,ChatDTO chat);
    String answerChat(String message);
    String closeChat(chatDTO chat);
}
