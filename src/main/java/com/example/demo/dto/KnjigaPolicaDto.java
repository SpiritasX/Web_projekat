package com.example.demo.dto;

public class KnjigaPolicaDto {
    private Long idKnjige;
    private Long idPolice;
    private RecenzijaDto dto;

    public KnjigaPolicaDto(Long idKnjige, Long idPolice, RecenzijaDto dto) {
        this.idKnjige = idKnjige;
        this.idPolice = idPolice;
        this.dto = dto;
    }

    public Long getIdKnjige() {
        return idKnjige;
    }

    public void setIdKnjige(Long idKnjige) {
        this.idKnjige = idKnjige;
    }

    public Long getIdPolice() {
        return idPolice;
    }

    public void setIdPolice(Long idPolice) {
        this.idPolice = idPolice;
    }

    public RecenzijaDto getRecenzijaDto() {
        return dto;
    }

    public void setRecenzijaDto(RecenzijaDto dto) {
        this.dto = dto;
    }
}
