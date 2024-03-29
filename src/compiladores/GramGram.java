/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Ortega
 */
public class GramGram {
    public String Gramatica;
    public AnalizLexico L;
    public ElemArreglo[] ArrReglas=new ElemArreglo[100];
    public int numRaglas=0;
    public ArrayList<String> vt = new ArrayList<>();
    public ArrayList<String> vn = new ArrayList<>();
    Especiales c= new Especiales();
    
    public GramGram(String sigma, int idAFD){
       Gramatica=sigma;
       L=new AnalizLexico(Gramatica, "GramGram", idAFD);
    }
    
    public boolean AnalizSatGG(){
        int token;
        if(G()){
            token=L.yylexEspace();
            if(token==0){
                encontrarTyNT();         
                return true;
            }
        }
        return false;
    }   

    private boolean G() {
        return conjReglas();
    }

    private boolean conjReglas() {
        if(listaReglas()){
            if(conjReglasP()){
                return true;
            }
        }
        return false;
    }

    private boolean listaReglas() {
        int token;
        String strLadoIzq;
        token= L.yylexEspace();
        if(token==10){//simbolo
            strLadoIzq=L.yytext;
            token=L.yylexEspace();
            while(token==c.ESPACIO){//quitar espacios
                token=L.yylexEspace();
            }
            while(token==10){//tomar la plabra completa
                strLadoIzq=strLadoIzq + L.yytext;
                token=L.yylexEspace();
            }
            while(token==c.ESPACIO){//quitar espacios
                token=L.yylexEspace();
            }
            if(token==20){//flecha
                if(listaLadosDerechos(strLadoIzq)){
                    token=L.yylexEspace();
                    while(token==c.ESPACIO){//quitar espacios
                        token=L.yylexEspace();
                    }
                    if(token==30){//PC
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean conjReglasP() {
        ClassEstadoAnalizLexico edoLexic;
        edoLexic=L.getEdoAnalizLexico();
        if(listaReglas()){
            if(conjReglasP()){
                return true;
            }
            return false;
        }
        L.SetEdoAnalizLexico(edoLexic);
        return true;
    }

    private boolean listaLadosDerechos(String strLadoIzq) {
        if(ladoDerecho(strLadoIzq)){
            if(listaLadosDerechosP(strLadoIzq))
                return true;
        }
        return false;
    }



    private boolean listaLadosDerechosP(String strLadoIzq) {
        int token;
        token=L.yylexEspace();
        while(token==c.ESPACIO){//quitar espacios
                token=L.yylexEspace();
        }
        if(token==40){//OR
            if(ladoDerecho(strLadoIzq)){
                if(listaLadosDerechosP(strLadoIzq)){
                    return true;
                }                
            }
            return false;
        }
        L.UndoToken();
        return true;
    }
    
    private boolean ladoDerecho(String strLadoIzq) {
        int token;
        ArrayList<Nodo> l=new ArrayList<Nodo>();
        Nodo nodo=new Nodo();
        token=L.yylexEspace();
        while(token==c.ESPACIO){//quitar espacios
                token=L.yylexEspace();
        }
        if(token==10){//simbolo
            nodo.simbolo=L.yytext;
            token=L.yylexEspace();
            while(token!=c.ESPACIO){//tomar la plabra completa
                nodo.simbolo=nodo.simbolo + L.yytext;
                token=L.yylexEspace();
            }
            L.UndoToken();
            if(ladoDerechoP(l)){
                l.add(0,nodo);
                ArrReglas[numRaglas]=new ElemArreglo();
                ArrReglas[numRaglas].ladoI=strLadoIzq;
                ArrReglas[numRaglas++].nodos=l;
                return true;            
            }
        }
        return false;
    }

    private boolean ladoDerechoP(List<Nodo> l) {
        int token;
        Nodo n;
        token=L.yylexEspace();
        while(token==c.ESPACIO){//quitar espacios
                token=L.yylexEspace();
        }
        if(token==10){
            n=new Nodo();
            n.simbolo=L.yytext;
            token=L.yylexEspace();
            while(token!=c.ESPACIO){//tomar la plabra completa
                n.simbolo=n.simbolo+L.yytext;
                token=L.yylexEspace();
            }
            L.UndoToken();
            if(ladoDerechoP(l)){
                l.add(0,n);
                return true;
            }
            return false;
        }
        L.UndoToken();
        return true;
    }
    private void encontrarTyNT(){
        //encuentra los no terminales
        for(int i=0; ArrReglas[i]!=null;i++){
            if(!vn.contains(ArrReglas[i].ladoI)){//aun no esta en los no terminales
                vn.add(ArrReglas[i].ladoI);
            }        
        }
        //encuentra los terminales para el arreglo
        
        for(int i=0; ArrReglas[i]!=null;i++)
            for(int j=0; j<ArrReglas[i].nodos.size();j++){
                    if(!vn.contains(ArrReglas[i].nodos.get(j).simbolo)){//no esta en los no terminales
                        ArrReglas[i].nodos.get(j).EdoTerminal=true;
                        if(!vt.contains(ArrReglas[i].nodos.get(j).simbolo)){//aun no esta en los terminales
                            vt.add(ArrReglas[i].nodos.get(j).simbolo);
                        }
                    }
                         
            }
        vt.remove(vt.indexOf("epsilon"));
    }
    
}
