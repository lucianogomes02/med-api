package med.voli.medapi.usuario.repository;

import med.voli.medapi.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    UserDetails findByLogin(String login);
}
