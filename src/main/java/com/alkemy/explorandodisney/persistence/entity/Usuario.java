package com.alkemy.explorandodisney.persistence.entity;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(unique = true)
    private String mail;
    @Column(unique = true)
    private String usuario;
    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "Text")
   // @ColumnTransformer(read = "AES_DECRYPT(UNHEX(clave), 'alkemy')", write = "HEX(AES_ENCRYPT(?, 'alkemy'))")
    private String clave;

    public Usuario() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
