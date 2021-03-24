/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.p1jdk14;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergi
 */
public class AnalizadorDescendente {
    public static final int ID = 1;
    public static final int NUM = 2;
    public static final int PAR_A = 3;
    public static final int PAR_C = 4;
    public static final int SUMA = 5;
    public static final int POR = 6;
    public static final int FINCADENA = 0;
    private LexerSuma lexerSuma;

    private int token;
    private boolean noError = true;

private void avanzar() {
        try {
            token = lexerSuma.yylex();
        } catch (IOException ex) {
            System.out.println("error en el token: " + ex.getMessage());
        }
    }

 public void consumir(int tok) {
            if (tok == token) {
                    avanzar();
            } else {
                    //manejo errors
                    error(tok, token);
            }
    }

public AnalizadorDescendente(LexerSuma lexerSuma) {
            this.lexerSuma = lexerSuma;
            avanzar();
            E();
    }

private void   E() {
            switch(token) {
                    case ID: case NUM: case PAR_A:
                            T(); Ep();
                            break;
                    default:
                            error(token);
            }
            if (noError) {
                    System.out.println("cadena aceptada");
            }
    }

 private void Ep() {
            switch(token) {
                    case PAR_C: case FINCADENA:
                            /*lambda, no hago nada*/
                            break;
                    case SUMA:
                            consumir(SUMA); T(); Ep();
                            break;
                    default:
                            error(token);
            }
    }

    private void T() {
            switch(token) {
                    case ID: case NUM: case PAR_A:
                            F(); Tp();
                            break;
                    default:
                            error(token);
            }
    }

   private void Tp() {
            switch(token) {
                    case PAR_C: case SUMA: case FINCADENA :
                            /*lambda, no hago nada*/
                            break;
                    case POR:
                            consumir(POR); F(); Tp();
                            break;
                    default:
                            error(token);
            }
    }

    private void F() {
            switch(token) {
                    case ID:
                            consumir(ID);
                            break;
                    case NUM:
                            consumir(NUM);
                            break;
                    case PAR_A:
                            consumir(PAR_A); E(); consumir(PAR_C);
                            break;
                    default:
                            error(token);
            }
    }
    
    private void error(int token){
        System.out.println("Error en el index token: " + token);
        noError = false;
    }       
    private void error(int tok, int token){
        System.out.println("El token " + token + "no era el esperado. Token esperado: " + tok);
        noError = false;
    }
    
}
