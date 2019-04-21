/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 *
 * @author vcalazans
 */
public class Editor {

    private File f;
    private String dir;
    private ID3V1_1 mp3;
    private static final String CHARSET = "ISO-8859-1";

    public Editor(String dir) throws FileNotFoundException {

        this.dir = dir;
        f = new File(dir);
        mp3 = new ID3V1_1();
    }

    /**
     * Este método, é responsavel por recuperar todas as marcações do arquivo e
     * retornar em um Objeto do tipo ID3v1_1
     *
     * @return
     * @throws IOException
     */
    public ID3V1_1 getMarcacoes() throws IOException {

        try (RandomAccessFile raf = new RandomAccessFile(dir, "rw")) {

            int pos = (int) (raf.length() - 128);
            raf.skipBytes(pos);

            byte[] abyCabecalho = new byte[3];
            if (raf.read(abyCabecalho) != 3) {
                throw new IOException("Erro ao ler cabecalho.");
            }
            String sCabecalho = new String(abyCabecalho, 0, Recursos.verificarBytes(abyCabecalho), CHARSET);

            if (sCabecalho.isEmpty()) {
                return null;
            }

            byte[] abyTitulo = new byte[30];
            if (raf.read(abyTitulo) != 30) {
                throw new IOException("Erro ao ler titulo.");
            }
            String sTitulo = new String(abyTitulo, 0, Recursos.verificarBytes(abyTitulo), CHARSET);

            byte[] abyArtista = new byte[30];
            if (raf.read(abyArtista) != 30) {
                throw new IOException("Erro ao ler artista.");
            }
            String sArtista = new String(abyArtista, 0, Recursos.verificarBytes(abyArtista), CHARSET);

            byte[] abyAlbum = new byte[30];
            if (raf.read(abyAlbum) != 30) {
                throw new IOException("Erro ao ler Album.");
            }
            String sAlbum = new String(abyAlbum, 0, Recursos.verificarBytes(abyAlbum), CHARSET);

            byte[] ano = new byte[4];
            if (raf.read(ano) != 4) {
                throw new IOException("Erro ao ler ano.");
            }
            String sAno = new String(ano, 0, Recursos.verificarBytes(ano), CHARSET);

            byte[] comentarios = new byte[28];
            if (raf.read(comentarios) != 28) {
                throw new IOException("Erro ao ler comentarios.");
            }
            String sComentarios = new String(comentarios, 0, Recursos.verificarBytes(comentarios), CHARSET);

            int flagDeTrilha = raf.read();
            int nroFaixa = raf.read();
            int genero = raf.read();

            mp3.setTituloMusica(sTitulo);
            mp3.setArtista(sArtista);
            mp3.setAlbum(sAlbum);
            mp3.setAno(sAno);
            mp3.setComentario(sComentarios);
            mp3.setFlagDeTrilha(flagDeTrilha);
            mp3.setNroFaixa(nroFaixa);
            mp3.setGenero(genero);

        }

        return mp3;
    }

    /**
     * Este método, tem por objetivo inserir marcações no arquivo.O mesmo pode
     * ser usado para alterar as marcações ja existentes.
     *
     * @param mp3
     * @throws IOException
     */
    public void inserirMarcacoes(ID3V1_1 mp3) throws IOException {

        try (RandomAccessFile raf = new RandomAccessFile(dir, "rw")) {

            int pos = (int) (raf.length() - 128);

            raf.skipBytes(pos);
            String tag = new String("TAG".getBytes(CHARSET), CHARSET);
            raf.writeBytes(Recursos.preencheEspacosEmBranco(tag, 3));

            String titulo = new String(Recursos.preencheEspacosEmBranco(mp3.getTituloMusica(), 30).getBytes(CHARSET), CHARSET);
            raf.writeBytes(titulo);

            String artista = new String(mp3.getArtista().getBytes(CHARSET), CHARSET);
            raf.writeBytes(Recursos.preencheEspacosEmBranco(artista, 30));

            String album = new String(mp3.getAlbum().getBytes(CHARSET), CHARSET);
            raf.writeBytes(Recursos.preencheEspacosEmBranco(album, 30));

            String ano = new String(mp3.getAno().getBytes(CHARSET), CHARSET);
            raf.writeBytes(Recursos.preencheEspacosEmBranco(ano, 4));

            String comentario = new String(mp3.getComentario().getBytes(CHARSET), CHARSET);
            raf.writeBytes(Recursos.preencheEspacosEmBranco(comentario, 28));

            byte flagDeTrilha;
            Integer flag = mp3.getFlagDeTrilha();
            flagDeTrilha = flag.byteValue();
            raf.write(flagDeTrilha);

            byte nroFaixa;
            Integer nro = mp3.getNroFaixa();
            nroFaixa = nro.byteValue();
            raf.write(nroFaixa);

            byte genero;
            Integer gen = mp3.getGenero();
            genero = gen.byteValue();
            raf.write(genero);

        }
    }

    /**
     * Este método remove todas as marcações do arquivo MP3.
     *
     * @throws IOException
     */
    public void removerMarcacoes() throws IOException {

        try (RandomAccessFile raf = new RandomAccessFile(dir, "rw")) {

            byte[] tag = new byte[3];
            byte[] titulo = new byte[30];
            byte[] artista = new byte[30];
            byte[] album = new byte[30];
            byte[] ano = new byte[4];
            byte[] comentario = new byte[28];
            byte flagDeTrilha = 0;
            byte nroFaixa = 0;
            byte genero = 0;

            int pos = (int) (raf.length() - 128);
            raf.skipBytes(pos);

            raf.write(tag);
            raf.write(titulo);
            raf.write(artista);
            raf.write(album);
            raf.write(ano);
            raf.write(comentario);
            raf.write(flagDeTrilha);
            raf.write(nroFaixa);
            raf.write(genero);
        }
    }

}
