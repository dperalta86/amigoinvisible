package ar.com.dperalta.amigoinvisible.services;

import ar.com.dperalta.amigoinvisible.entities.Sorteo;
import ar.com.dperalta.amigoinvisible.entities.Usuario;
import ar.com.dperalta.amigoinvisible.entities.UsuarioSorteo;
import ar.com.dperalta.amigoinvisible.repositories.SorteoRepository;
import ar.com.dperalta.amigoinvisible.repositories.UsuarioRepository;
import ar.com.dperalta.amigoinvisible.repositories.UsuarioSorteoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AppService {
    @Autowired
    SorteoRepository sorteoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioSorteoRepository usuarioSorteoRepository;
    @Autowired
    EnvioEmailService envioEmailService;


    public long cargarJsonASorteo(String jsonString) {
        try {
            // Parsear JSON
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()); // --> Registro el modulo para manejar tipos de fechas y horas
            JsonNode rootNode = objectMapper.readTree(jsonString);
            JsonNode usersNode = rootNode.path("usuario");
            JsonNode sorteoNode = rootNode.path("sorteo");

            //Creo y guardo Sorteo
            Sorteo sorteo = objectMapper.treeToValue(sorteoNode, Sorteo.class);
            Sorteo savedSorteo = sorteoRepository.save(sorteo);

            //Creo y guardo los usuarios
            List<Usuario> usuarios = new ArrayList<>();
            for (JsonNode userNode : usersNode) {
                Usuario usuario = objectMapper.treeToValue(userNode, Usuario.class);
                usuarios.add(usuario);
            }
            usuarioRepository.saveAll(usuarios);

            //Mezclo aleatoriamente los participantes --> Algoritmo Shuffle
            Collections.shuffle(usuarios);

            // Prepara una lista de asignaciones que se va a completar en el bucle siguiente.
            List<UsuarioSorteo> asignaciones = new ArrayList<>();
            for (int i = 0; i < usuarios.size(); i++) {
                // Determina el "dador" actual y su amigo invisible correspondiente.
                Usuario giver = usuarios.get(i);
                Usuario receiver = usuarios.get((i + 1) % usuarios.size());
                // La operación `(i + 1) % participants.size()` asegura que el último participante
                // reciba al primero como amigo invisible, cerrando el círculo.

                // Crea una nueva asignación, asociando el dador con su amigo invisible.
                UsuarioSorteo asignacion = new UsuarioSorteo();
                asignacion.setUsuario(giver);  // Establece el participante que da el regalo.
                asignacion.setSorteado(receiver); // Establece a quién le da el regalo.
                asignacion.setSorteo(sorteo);       // Asocia esta asignación con el sorteo actual.

                // Agrega la asignación a la lista de asignaciones.
                asignaciones.add(asignacion);

                // Envía un correo a cada participante con su asignación.
                enviarEmail(giver, receiver, savedSorteo.getMensaje());
            }

            //Guarda todas las asignaciones en la base de datos de una sola vez.
            usuarioSorteoRepository.saveAll(asignaciones);

            return savedSorteo.getId_sorteo();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        /*catch (JsonProcessingException e) {
            throw new RuntimeException(e);
            */
        }

    }

    /**
     * Envía un correo electrónico a un participante con la información de su amigo invisible.
     *
     * @param giver    El participante que dará el regalo.
     * @param receiver El participante que recibirá el regalo.
     * @param mensaje  El mensaje editable que se enviará por email.
     */
    private void enviarEmail(Usuario giver, Usuario receiver, String mensaje) {

        String subject = "Amigo Invisible: ¡Te hemos asignado tu amigo!";

        String text = mensaje
                .replace("[NOMBRE]", giver.getNombre())
                .replace("[AMIGO_INVISIBLE]", receiver.getNombre());

/*

        String text = String.format("Hola %s,\n\nTu amigo invisible es: %s.\n¡No le digas a nadie!",
                giver.getNombre(), receiver.getNombre());

 */

        System.out.println(giver.getEmail());
        envioEmailService.sendEmail(giver.getEmail(), subject, text);
    }


    // Método para reenviar los correos a todos los participantes de un sorteo.
    public String resendEmails(Long sorteoId) {
        Sorteo sorteoEncontrado = sorteoRepository.findById(sorteoId)
                .orElseThrow(() -> new RuntimeException("Sorteo no encontrado"));

        List<UsuarioSorteo> usuarioSorteos = usuarioSorteoRepository.findBySorteo(sorteoEncontrado);

        for (UsuarioSorteo usuarioSorteo : usuarioSorteos) {
            enviarEmail(usuarioSorteo.getUsuario(), usuarioSorteo.getSorteado(), sorteoEncontrado.getMensaje());
        }
        return String.format("Se han renviado los mails correspondientes al sorteo %s.", sorteoEncontrado.getCodigoSorteo());
    }
}
