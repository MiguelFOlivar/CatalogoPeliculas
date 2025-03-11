package com.mf.JSP_Movie.service;

import com.mf.JSP_Movie.model.Usuario;
import com.mf.JSP_Movie.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;

    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build(); //or HS384.key() or HS512.key()

    //Autenticar usuario
    public boolean authemticate(String username, String password) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);

        if (usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();

            if (cryptPasswordEncoder.matches(password, usuario.getPassword()) && usuario.getFailedAttempts()<3){
                //Reseteamos los intentos fallidos en caso de un login exitoso
                usuario.setFailedAttempts(0);
                usuario.setEnabled(true);
                usuarioRepository.save(usuario);
                return true;
            }else {
                //incrementar intentos fallidos
                usuario.setFailedAttempts(usuario.getFailedAttempts()+1);
                usuarioRepository.save(usuario);
            }
        }
        return false;
    }

    @Transactional
    public String registrarUsuario(Usuario usuario) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(usuario.getUsername());
        if (usuarioOptional.isPresent()){
            return "El usuario ya existe";
        }
        usuario.setPassword(cryptPasswordEncoder.encode(usuario.getPassword()));
        usuario.setEnabled(true);
        usuario.setFailedAttempts(0);

        if(usuario.getRole() == null || usuario.getRole().isEmpty()){
            usuario.setRole("USER");
        }
        usuarioRepository.save(usuario);
        System.out.println("usuario registrado: " + usuario.getUsername());
        return "registro exitoso";
    }

    // Lógica para verificar si el correo ya está registrado
    @Transactional
    public String verificarEmail(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent()) {
            return "El correo electrónico ya está registrado.";
        }
        return "Correo electrónico disponible.";
    }

    //Generar token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.ES256, SECRET_KEY)
                .compact();
    }

    //Registrar usuario
    public Usuario registerUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    // Lógica para verificar el login y manejar los intentos fallidos
    @Transactional
    public String verificarLogin(Usuario usuario, String password) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByUsername(usuario.getUsername());

        if (!usuarioExistente.isPresent()) {
            return "Usuario no encontrado.";
        }

        Usuario usuarioBD = usuarioExistente.get();

        // Si el usuario está bloqueado, no permitir el login
        if (usuarioBD.getFailedAttempts() >= 3) {
            return "Cuenta bloqueada por múltiples intentos fallidos.";
        }

        // Verificar si la contraseña es correcta
        if (cryptPasswordEncoder.matches(password, usuarioBD.getPassword())) {
            // Si la contraseña es correcta, resetear los intentos fallidos
            usuarioBD.setFailedAttempts(0);
            usuarioBD.setEnabled(true); // Asegurar que la cuenta esté habilitada
            usuarioRepository.save(usuarioBD);
            return "Login exitoso.";
        } else {
            // Si la contraseña es incorrecta, incrementar los intentos fallidos
            usuarioBD.setFailedAttempts(usuarioBD.getFailedAttempts() + 1);
            if (usuarioBD.getFailedAttempts() >= 3) {
                usuarioBD.setEnabled(false); // Deshabilitar la cuenta si se superan los intentos
            }
            usuarioRepository.save(usuarioBD);
            return "Credenciales incorrectas. Intentos fallidos: " + usuarioBD.getFailedAttempts();
        }
    }

}
