/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1jdk14;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

/**
 *
 * @author sergi
 */
public class Main {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("Escribir el contenido: ");
        String doc = scanner.nextLine();        
        try{
            Reader inputString = new StringReader(doc);
            BufferedReader reader = new BufferedReader(inputString);
            LexerSuma lexer = new LexerSuma(reader);
            AnalizadorDescendente parser = new AnalizadorDescendente(lexer);      
        }catch(Exception e){
            System.out.println("error al leer");
        }     
    }
    
}
