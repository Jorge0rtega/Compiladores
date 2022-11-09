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
public class EvaluadorExpr {
    String expresion;
    public float result;
    public String exprPost;
    public AnalizLexico L;
    
    public EvaluadorExpr(String sigma, AFD autAFD){
        expresion=sigma;
        L=new AnalizLexico(expresion, autAFD);
    }
    public EvaluadorExpr(String sigma, String fileAFD, int identifAFD){
        expresion=sigma;
        L=new AnalizLexico(expresion, fileAFD, identifAFD);
    }
    public EvaluadorExpr(String fileAFD, int identifAFD){
        L=new AnalizLexico(fileAFD, identifAFD);
    }
    public void  setExpresion(String sigma){
        expresion=sigma;
        L.setSigma(sigma);
    }
    public boolean iniEval(){
        float[] v= new float[1];
        int token; 
        String postfijo="";
        
        v[0]=0;
        if(E(v, postfijo)){
            token =L.yylex();
            if(token==0){
                result=v[0];
                exprPost=postfijo;
                return true;
            }
        }
        return false;
    }

    private boolean E(float[] v, String postfijo) {
        if(T(v, postfijo)){
            if(Ep(v, postfijo)){
                return true;
            }
        }
        return false;
    }

    private boolean Ep(float[] v, String postfijo) {
        int token;
        float[] v2= new float[1];
        v2[0]=0;
        String Post2="";
        token =L.yylex();
        if(token ==10 ||token ==20){//+ o -
            if(T(v2, Post2)){
                v[0]=v[0]+(token==10 ? v2[0] :- v2[0]);
                postfijo=postfijo+" "+Post2+" "+(token==10 ? "+": "-");
                if(Ep(v, postfijo)){
                    return true;
                }
            }
            return false;
        }
        L.UndoToken();
        return true;
    }
    
    private boolean T(float[] v, String postfijo) {
        if(F(v, postfijo)){
            if(Tp(v, postfijo)){
                return true;
            }
        }
        return false;
    }

    private boolean Tp(float[] v, String postfijo) {
        int token;
        float[] v2=new float[1];
        v2[0]=0;
        String Post2="";
        token =L.yylex();
        if(token ==30 ||token ==40){//* o /
            if(F(v2, Post2)){
                v[0]=v[0]*(token==30 ? v2[0] : 1/v2[0]);
                postfijo=postfijo+" "+Post2+" "+(token==10 ? "*": "/");
                if(Tp(v, postfijo)){
                    return true;
                }
            }
            return false;
        }
        L.UndoToken();
        return true;
    }

    private boolean F(float[] v, String postfijo) {
        int token;
        token=L.yylex();
        switch(token){
            case 50: // (
                if(E(v, postfijo)){
                    token=L.yylex();
                    if(token==60){
                        return true;
                    }
                }
                return false;
            case 70: //num
                v[0]= Float.parseFloat(L.yytext);
                postfijo=L.yytext;
                return true;
        }
        return false;
    }
    
    
    
}
