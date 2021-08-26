package com.alkemy.explorandodisney.persistence.entity.enums;


public enum MovieGenre {
    COMEDIA("comedia"),
    ACCION_AVENTURAS("accion/ aventuras"),
    FANTASIA("fantasia"),
    INFANTIL("infantil"),
    DOCUMENTAL("documental"),
    CIENCIA_FICCION("ciencia ficcion"),
    DRAMA("drama");

    private String valor;

    MovieGenre(String valor) {
        this.valor = valor;
    }


    public String getValor() {
        return valor;
    }



}
