package com.FinanGlobal.bancoemp.Repositorio;

import com.FinanGlobal.bancoemp.Entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}

