/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

/**
 *
 * @author Jorge Ortega
 */
public class ER_AFN {
    String ExprRegular;
    public AFN result;
    public AnalizLexico L;
    
    public ER_AFN(String sigma, AFD autAFD){
        ExprRegular=sigma;
        L = new AnalizLexico(ExprRegular, autAFD);
        
    }
    public ER_AFN(String sigma, String fileAFD, int identifAFD){
        ExprRegular=sigma;
        L = new AnalizLexico(ExprRegular, fileAFD, identifAFD);
    }
    public ER_AFN(String fileAFD, int identifAFD){
        L = new AnalizLexico(fileAFD, identifAFD);
    }
    public void setExpresion(String sigma){
        ExprRegular=sigma;
        L.setSigma(sigma);
    }
    public boolean IniCoversion(){
        int token;
        AFN f= new AFN();
        if(E(f)){
            token=L.yylex();
            if(token==0){
                this.result=f;
                return true;
            }
        }
        return false;
    }

    private boolean E(AFN f) {
        if(T(f)){
            if(Ep(f)){
               return true;
            }
        }
        return false;
    }

    private boolean Ep(AFN f) {
        int token;
        AFN f2 = new AFN();
        token =L.yylex();
        if(token== 10 ){//Or
            if(T(f2)){
                f.unirAFN(f2);
                if(Ep(f)){
                    return true;
                }
                
            }
            return false;
        }
        L.UndoToken();
        return true;
    }
    private boolean T(AFN f) {
        if(C(f)){
            if(Tp(f)){
               return true;
            }
        }
        return false;
    }
    private boolean Tp(AFN f) {
        int token;
        AFN f2 = new AFN();
        token =L.yylex();
        if(token== 10 ){//concatenar
            if(C(f2)){
                f.concatenar(f2);
                if(Tp(f)){
                    return true;
                }
                
            }
            return false;
        }
        L.UndoToken();
        return true;
    }
    private boolean C(AFN f) {
        if(F(f)){
            if(Cp(f)){
               return true;
            }
        }
        return false;
    }
    private boolean Cp(AFN f) {
        int token;
        AFN f2 = new AFN();
        token =L.yylex();
        switch(token){
            case 30: //cerradura transitiva
                f.cerraduraSuma();
                if(Cp(f)){
                    return true;
                }
                return false;
            case 40: //cerradura kleen
                f.cerraduraAsterisco();
                if(Cp(f)){
                    return true;
                }
                return false;
            case 50: //opcional
                f.opcional();
                if(Cp(f)){
                    return true;
                }
                return false;
                    
        }
        L.UndoToken();
        return true;
    }
    
    public boolean F(AFN f){
        int token;
        char simbolo1, simbolo2;
        token=L.yylex();
        switch(token){
            case 60: // (
                if(E(f)){
                    token =L.yylex();
                    if(token==70){
                        return true;
                    }
                }
                return false;
            case 80: // [
                token=L.yylex();
                if(token==110){ //simbolo
                    simbolo1=(L.yytext.charAt(0)=='\\')? L.yytext.charAt(1):  L.yytext.charAt(0);
                    token=L.yylex();
                    if (token==100){//guion
                        token=L.yylex();
                        if(token==110){//simbolo
                            simbolo2=(L.yytext.charAt(0)=='\\')? L.yytext.charAt(1):  L.yytext.charAt(0);
                            token=L.yylex();
                            if(token==90){ //   ]
                                f.CrearAFNBasico(simbolo1, simbolo2);
                                return true;
                            }
                        }
                        
                    }
                    
                }
                return false;
            case 110: //simbolo
                simbolo1=(L.yytext.charAt(0)=='\\')? L.yytext.charAt(1):  L.yytext.charAt(0);
                f.CrearAFNBasico(simbolo1);
                return true;
        }
        return false;
    }
}