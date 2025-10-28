package com.FinanGlobal.bancoemp.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "t_usuario")
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    private Integer idUsuario;

    private String nombre;
    private String apellidos;

    @Column(name = "dni_ruc")
    private String dniRuc;

    private String sexo;
    private String correo;
    private String telefono;
    private String direccion;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    private String contrasena;

    @Column(name = "rol_usuario")
    private Integer rolUsuario;

    // Getters y setters (generarlos)
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDniRuc() { return dniRuc; }
    public void setDniRuc(String dniRuc) { this.dniRuc = dniRuc; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Integer getRolUsuario() { return rolUsuario; }
    public void setRolUsuario(Integer rolUsuario) { this.rolUsuario = rolUsuario; }
}
