/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Jorge Ortega
 */
public class LL1 {

    public GramGram GG;
    AnalizLexico L;
    public int sigma;
    private Especiales c;
    public Integer[][] tabla;
    private String fileAFD;
    public ArrayList<String> auxPila = new ArrayList<String>();//para guardar los que semuestra en la interfaz
    public ArrayList<String> auxCadena = new ArrayList<String>();
    public ArrayList<String> auxNRegla = new ArrayList<String>();
    public LL1(String sigma, String fileAFD, int idAFD) {//FileAFD:GramGram
        GG = new GramGram(sigma, 0);
        GG.AnalizSatGG();
        this.fileAFD = fileAFD;

    }

    public ArrayList<String> first(ArrayList<Nodo> l) {
        ArrayList<String> R = new ArrayList<>();
        int i, j;
        Nodo n;
        R.clear();
        if (l.size() == 0) {
            HashSet<String> hashSet = new HashSet<String>(R);
            R.clear();
            R.addAll(hashSet);
            return R;
        }
        for (j = 0; j < l.size(); j++) {
            n = l.get(j);
            if (n.EdoTerminal || (n.simbolo.equals("epsilon"))) {
                R.add(n.simbolo);
                HashSet<String> hashSet = new HashSet<String>(R);
                R.clear();
                R.addAll(hashSet);
                return R;
            }
            //listaSimbolos[0] es no terminal
            for (i = 0; i < GG.numRaglas; i++) {
                if (GG.ArrReglas[i].nodos.get(0).simbolo.equals(n.simbolo)) {
                    continue;
                }
                if (GG.ArrReglas[i].ladoI.equals(n.simbolo)) {
                    R.addAll(first(GG.ArrReglas[i].nodos));
                }

            }
            if (R.contains("epsilon")) {
                if (j == (l.size()) - 1) {
                    continue;
                }
                R.remove(R.indexOf("epsilon"));
            } else {
                break;
            }
        }
        //para no repetir elementos
        HashSet<String> hashSet = new HashSet<String>(R);
        R.clear();
        R.addAll(hashSet);
        return R;
    }

    public ArrayList<String> follow(String simbNoTerm) {
        ArrayList<String> R = new ArrayList<>();
        ArrayList<String> aux = new ArrayList<>();
        ArrayList<Nodo> listaPost = new ArrayList<>();
        R.clear();
        int i, j, k;
        if (GG.ArrReglas[0].ladoI.equals(simbNoTerm)) {
            R.add("$");
        }
        for (i = 0; i < GG.numRaglas; i++) {
            for (j = 0; j < GG.ArrReglas[i].nodos.size(); j++) {
                if (GG.ArrReglas[i].nodos.get(j).simbolo.equals(simbNoTerm)) {
                    listaPost.clear();
                    for (k = j + 1; k < GG.ArrReglas[i].nodos.size(); k++) {
                        listaPost.add(GG.ArrReglas[i].nodos.get(k));
                    }
                    if (listaPost.size() == 0) {
                        if (!GG.ArrReglas[i].ladoI.equals(simbNoTerm)) {
                            R.addAll(follow(GG.ArrReglas[i].ladoI));
                        }
                        break;
                    }
                    aux.clear();
                    aux = first(listaPost);
                    if (aux.contains("epsilon")) {
                        aux.remove(aux.indexOf("epsilon"));
                        R.addAll(aux);
                        if (!GG.ArrReglas[i].ladoI.equals(simbNoTerm)) {
                            R.addAll(follow(GG.ArrReglas[i].ladoI));
                        }
                    } else {
                        R.addAll(aux);
                    }
                }
            }
        }
        HashSet<String> hashSet = new HashSet<String>(R);
        R.clear();
        R.addAll(hashSet);
        return R;
    }

    public void crearTabla() {
        Integer[][] auxTabla = new Integer[GG.vn.size() + 1][GG.vt.size() + 1]; //guardar temporalmente la tabla, es mas uno por el $
        ArrayList<String> aux = new ArrayList<String>(); //guardar el resultafo de los first y follow
        for (int i = 0; i < GG.numRaglas; i++) {//recorrer todas las reglas
            if (GG.ArrReglas[i].nodos.get(0).simbolo.equals("epsilon")) {//si el lado derecho de la regla contiene epsilon
                aux = follow(GG.ArrReglas[i].ladoI);//se busca el follow
                for (int j = 0; j < aux.size(); j++) {
                    if (!(aux.get(j).equals("epsilon") || aux.get(j).equals("$"))) {  //si es diferente de epsilon o $
                        auxTabla[GG.vn.indexOf(GG.ArrReglas[i].ladoI)][GG.vt.indexOf(aux.get(j))] = i;   //se coloca en la casilla el numero de la regla                  
                    } else if (aux.get(j).equals("$")) {//si es igual a pesos 
                        auxTabla[GG.vn.indexOf(GG.ArrReglas[i].ladoI)][GG.vt.size()] = i;//se pone el numero de la reglaen la ultima casilla                     
                    }
                }
            } else {//si el lado derecho no contiene epsilon
                aux = first(GG.ArrReglas[i].nodos);//se guarda el fisrt
                for (int j = 0; j < aux.size(); j++) {//se recorre el resultado
                    if (!aux.get(j).equals("epsilon")) {//si es diferente a epsilon se guarda el numero de la regla
                        auxTabla[GG.vn.indexOf(GG.ArrReglas[i].ladoI)][GG.vt.indexOf(aux.get(j))] = i;//se utilizan los indices del arreglo de no terminales y el de los terminales
                    }
                }
            }
        }
        for (int i = 0; i < GG.vn.size() + 1; i++) {//se reocrre la tabla a las casillas sin regla asignada se les pone -1
            for (int j = 0; j < GG.vt.size() + 1; j++) {
                if (auxTabla[i][j] == null) {
                    auxTabla[i][j] = -1;
                }
            }
        }
        auxTabla[GG.vn.size()][GG.vt.size()] = 10;//a la casilla de aceptar se le pone 10
        tabla = auxTabla;//se copia la tabla
    }

    public boolean analizCadena(String cadena) {
        String aux = "";
        ArrayList<String> sigmaDiv = new ArrayList<String>();//para dividir la cadena en una lista
        int tkn, columna, fila, nRegla = 0;
        Stack pila = new Stack();//la pila para guarda

        String accion = "";//para debug, muestra la accion
        L = new AnalizLexico(cadena, fileAFD, 0);//analizador lexico para la cadena
        //separa la cadena en una lista
        tkn = L.yylex();
        aux = L.yytext;
        while (tkn != 0) {
            if (tkn != 2000) {//espacio
                sigmaDiv.add(aux);
            }
            tkn = L.yylex();
            aux = L.yytext;
        }
        sigmaDiv.add("$");//agrega $ a la lista de sigma
        pila.add("$"); //agrega $ a la pila
        pila.add(GG.ArrReglas[0].ladoI);//agrega el inicio de la gramatica de gramaticas
        auxPila.add(pila.toString());
        while (sigmaDiv.size() != 0) {//se termina de analizar toda la cadena
            if (sigmaDiv.get(0).equals("$")) {//si es $ se le asigna la ultima columna
                columna = GG.vt.size();
            } else {//sino se localiza su columna
                columna = GG.vt.indexOf(sigmaDiv.get(0));
            }
            fila = GG.vn.indexOf(pila.pop());//se empieza haciendo un pop para encontrar la fila
            nRegla = tabla[fila][columna];//se localiza en la tabla y se toma el numero de la regla
            auxNRegla.add(""+nRegla);
            if (nRegla == -1) {//en caso de no haber regla la cadena es invalida
                return false;
            }
            while (fila != -1) {//se itera hasta que lo que este en la pila sea un terminal
                for (int j = GG.ArrReglas[nRegla].nodos.size() - 1; j >= 0; j--) {
                    pila.push(GG.ArrReglas[nRegla].nodos.get(j).simbolo);
                }
                auxPila.add(pila.toString());
                accion = pila.lastElement().toString();
                if (sigmaDiv.get(0).equals("$")) {
                    columna = GG.vt.size();
                } else {
                    columna = GG.vt.indexOf(sigmaDiv.get(0));
                }
                fila = GG.vn.indexOf(pila.pop());//si es -1 es porque es un terminal
                //auxPila.add(pila.toString());
                if (accion.equals("epsilon")) {//si la accion es epsilon se hace otro pop
                    fila = GG.vn.indexOf(pila.pop());
                    //auxPila.add(pila.toString());
                }
                
                if (fila != -1) {//si encuentra una fila
                    nRegla = tabla[fila][columna];//se toma el numero de regla
                    auxNRegla.add(""+nRegla);
                    if (nRegla == -1) {
                        return false;//en caso contrario regresa falso
                    }
                }
                auxCadena.add(sigmaDiv.toString());
            }
            auxNRegla.add("POP");
            auxCadena.add(sigmaDiv.toString());
            sigmaDiv.remove(0);//se termino de analizar el elemento 
            auxPila.add(pila.toString());
        }

        if (pila.size() == 0 && sigmaDiv.size() == 0) {//si la pila y la cadena analizar estan vacias
            return true;//la cadena es verdadera
        } else {
            return false;
        }

    }

    public void setFileAFD(String fileAFD) {
        this.fileAFD = fileAFD;
    }
    
}
