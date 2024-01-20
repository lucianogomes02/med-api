package med.voli.medapi.repository.usuario;

import med.voli.medapi.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    UserDetails findByLogin(String login);
}
