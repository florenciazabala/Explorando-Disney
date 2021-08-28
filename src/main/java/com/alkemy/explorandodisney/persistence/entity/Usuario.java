package com.alkemy.explorandodisney.persistence.entity;

import org.hibernate.annotations.ColumnTransformer;
import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(unique = true)
    private String mail;
    @Column(unique = true)
    private String usuario;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "Text")
    @ColumnTransformer(read = "AES_DECRYPT(UNHEX(clave), 'alkemy')", write = "HEX(AES_ENCRYPT(?, 'alkemy'))")
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
