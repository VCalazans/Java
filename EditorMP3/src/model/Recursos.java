/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author vcalazans
 */
public class Recursos {

    /**
     * Este método recupera o tamanho real dos caracteres recuperados no array
     * de bytes.
     *
     * @param byString
     * @return
     */
    public static int verificarBytes(byte[] byString) {
        for (int i = 0; i < byString.length; i++) {
            if (byString[i] == 0) {
                return i;
            }
        }
        return byString.length;
    }

    /**
     * Preenche a quantidade de espaços em branco faltantes na String.
     *
     * @param str
     * @param qtdEspacos
     * @return
     * @throws IOException
     */
    public static String preencheEspacosEmBranco(String str, int qtdEspacos) throws IOException {

        if (str.length() > qtdEspacos) {
            throw new IOException("Entrada maior que " + qtdEspacos + " caracteres.");
        }

        for (int i = str.length(); i < qtdEspacos; i++) {
            str += " ";
        }

        return str;
    }

    /**
     * Recupera o último estado das dimensões da tela.
     * @param dir
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static Rectangle getPosTela(String dir) throws FileNotFoundException, IOException {

        Rectangle obj = new Rectangle();
        File f = new File(dir);

        try (FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            obj = (Rectangle) ois.readObject();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return obj;

    }

    /**
     * Grava as dimensões da tela no quando a mesma é fechada.
     * @param pos
     * @param dir
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void setPosTela(Rectangle pos, String dir) throws FileNotFoundException, IOException {

        File f = new File(dir);

        try (FileOutputStream fos = new FileOutputStream(f);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(pos);

        }

    }
}
