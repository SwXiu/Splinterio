package main.java.com.cesur.splinterio.services.impl;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cesur.splinterio.models.Chat;
import com.cesur.splinterio.models.Incidence;
import com.cesur.splinterio.models.User;
import com.cesur.splinterio.models.dtos.ChatDTO;
import com.cesur.splinterio.repositories.ChatRepository;
import com.cesur.splinterio.repositories.IncidenceRepository;
import com.cesur.splinterio.repositories.UserRepository;
import com.cesur.splinterio.services.ChatService;

public class ChatServiceImpl implements ChatService {

    private static Map<String, List<String>> map = new HashMap<>();

    static {
        map.put("ofimatica",Arrays.asList("¿1. Reiniciaste el programa?", "¿2. Reiniciaste el ordenador?"));
    }
    @Autowired
    ChatRepository chatRepository;

    @Autowired
    UserRepository userRepository;

     @Autowired
    IncidenceRepository incidenceRepository;

    @Override
    public void addChat(String email, ChatDTO datos) {
        Optional<Incidence> optionalEntity = incidenceRepository.findById(datos.getId());
        Optional<User> user = userRepository.getUserByEmail(email);
        if(user.isPresent()){
            Chat chat = new Chat();
            chat.setMessage(datos.getMessage());
            chat.setAutor(user.getId());
            chat.setIncidence(optionalEntity);
            chatRepository.save(chat);
        }
    }

    @Override
    public String answerChat(String message) {
        message  = message.toLowerCase();
        
        for(Map.Entry<String, List<String>> responses : map.entrySet()) {
            String key = responses.getKey();
            List<String> value = responses.getValue();

            if(message.contains(key)) {
                for(int i = 0; i < value.length; i++) {
                    return value.get(i);
                }
            }
        }

        return "No entiendo lo que dices";
    }

    @Override
    public void closeChat(ChatDTO chat) {
        System.out.println("Adios, " + chat.getUserId());
    }

}
