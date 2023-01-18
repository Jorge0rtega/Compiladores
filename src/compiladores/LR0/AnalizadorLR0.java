/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores.LR0;

import compiladores.AnalizLexico;
import compiladores.GramGram;
import compiladores.Nodo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Jorge Ortega
 */
public class AnalizadorLR0 {
    public Inf_IrA[] ResultadoIrA;
    public int idAutGram;
    GramGram DescRecG;
    public int NumRenglonesIrA;
    public AnalizLexico LexGram;
    public String Gram;
    public String Sigma;
    public int[][] TablaLR0;
    public SimbTerm[] vt;
    public String[] vt2;
    public int[] vt3;
    public String[] vn;
    public HashSet<String> V;
    
    public AnalizadorLR0(String CadGramatica, String ArchAFDLexic){
        Gram=CadGramatica;
        DescRecG=new GramGram(CadGramatica, 5001);
        LexGram=new AnalizLexico(ArchAFDLexic, 5000);
    }
    public AnalizadorLR0(String CadGramatica){
        Gram=CadGramatica;
        DescRecG=new GramGram(CadGramatica, 5001);
    }
    public HashSet<ItemLR0> Mover_LR0(HashSet<ItemLR0> C, String Simbolo){
        HashSet<ItemLR0> R= new HashSet<ItemLR0>();
        ItemLR0 aux= new ItemLR0();
        List<Nodo> lista= new ArrayList<Nodo>();
        Nodo n;
        R.clear();
        for(ItemLR0 I: C){
            lista=DescRecG.ArrReglas[I.NumRegla].nodos;
            if(I.PosPunto<lista.size()){
                n=lista.get(I.PosPunto);
                if(n.simbolo.equals(Simbolo))
                    R.add(new ItemLR0(I.NumRegla, I.PosPunto+1));
            }
        }
        return R;
    }
}
