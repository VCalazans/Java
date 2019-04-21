/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author vcalazans
 */
public class ID3V1_1 {
    
    private String cabecalho;
    private String tituloMusica;
    private String aritsta;
    private String album;
    private String ano;
    private String comentario;
    private int flagDeTrilha;
    private int nroFaixa;   
    private int genero;
    
    public ID3V1_1(){
        
    }
    
    public void setCabecalho(String cabecalho){
        this.cabecalho = cabecalho;
    }
    
    public String getCabecalho(){
        return cabecalho;
    }
                             
    public void setTituloMusica(String musica){
        this.tituloMusica = musica;
    }
    
    public String getTituloMusica(){
        return tituloMusica;
    }
    
    public void setArtista(String artista){
        this.aritsta = artista;
    }
    
    public String getArtista(){
        return aritsta;
    }
    
    public void setAlbum(String album){
        this.album = album;
    }
    
    public String getAlbum(){
        return album;
    }
    
    public void setAno(String ano){
        this.ano = ano;
    }
    
    public String getAno(){
        return ano;
    }
    
    public void setComentario(String comentario){
        this.comentario = comentario;
    }
    
    public String getComentario(){
        return comentario;
    }
    
    public void setFlagDeTrilha(int flag){
        this.flagDeTrilha = flag;
    }
    
    public int getFlagDeTrilha(){
        return flagDeTrilha;
    }
    
    public void setNroFaixa(int faixa){
        this.nroFaixa = faixa;
    }
    
    public int getNroFaixa(){
        return nroFaixa;
    }
    
    public void setGenero(int genero){
        this.genero = genero;
    }
    
    public int getGenero(){
        return genero;
    }
    
}
