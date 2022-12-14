/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Ortega
 */
public class Compiladores {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    

////        calculadora
//        AFN a =new AFN();
//        a.CrearAFNBasico('0', '9');
//        AFN b =new AFN();
//        b.CrearAFNBasico('-');
//        AFN c =new AFN();
//        c.CrearAFNBasico('+');
//        AFN d =new AFN();
//        d.CrearAFNBasico('*');
//        AFN e =new AFN();
//        e.CrearAFNBasico('/');
//        AFN f =new AFN();
//        f.CrearAFNBasico('(');
//        AFN g =new AFN();
//        g.CrearAFNBasico(')');
//        AFN i =new AFN();
//        i.CrearAFNBasico(' ');
//        AFN h =new AFN();
//        h.UnionEspecialANFs(a, 70);//num
//        h.UnionEspecialANFs(b, 20);//-
//        h.UnionEspecialANFs(c, 10);//+
//        h.UnionEspecialANFs(d, 30);//*
//        h.UnionEspecialANFs(e, 40);// /
//        h.UnionEspecialANFs(f, 50);// (
//        h.UnionEspecialANFs(g, 60);// )
//        h.UnionEspecialANFs(i, 0);// Fin de cadena
//        h.ConvAFNaAFD("Calc");
//        EvaluadorExpr EE = new EvaluadorExpr("8+2*(10)", "Calc", 0);
//        System.out.println("EE = " + EE.iniEval());
//        System.out.println("V= "+ EE.result);
//        System.out.println("Post"+ EE.exprPost);//no funciona, pero no es tan necesaria
//                
//        Expresion regular
//        AFN a =new AFN();
//        a.CrearAFNBasico('a', 'z');
//        AFN o =new AFN();
//        o.CrearAFNBasico('A', 'Z');
//        AFN p =new AFN();
//        p.CrearAFNBasico('0', '9');
//        AFN q =new AFN();
//        q.CrearAFNBasico('.');
//        AFN u =new AFN();
//        u.CrearAFNBasico('\'');
//        AFN v =new AFN();
//        v.CrearAFNBasico('\n');//enter
//        AFN w =new AFN();
//        w.CrearAFNBasico('_');//enter
//        a.unirAFN(o);
//        a.unirAFN(p);
//        a.unirAFN(q);
//        a.unirAFN(u);
//        a.unirAFN(v);
//        a.unirAFN(w);
//        AFN b =new AFN();
//        b.CrearAFNBasico('|');
//        AFN c =new AFN();
//        c.CrearAFNBasico('&');
//        AFN d =new AFN();
//        d.CrearAFNBasico('+');
//        AFN e =new AFN();
//        e.CrearAFNBasico('*');
//        AFN f =new AFN();
//        f.CrearAFNBasico('?');
//        AFN g =new AFN();
//        g.CrearAFNBasico('(');
//        AFN i =new AFN();
//        i.CrearAFNBasico(')');
//        AFN j =new AFN();
//        j.CrearAFNBasico('[');
//        AFN k =new AFN();
//        k.CrearAFNBasico(']');
//        AFN l =new AFN();
//        l.CrearAFNBasico('-');
//        AFN m =new AFN();
//        m.CrearAFNBasico(' ');
//        AFN s =new AFN();
//        s.CrearAFNBasico('E');
//        AFN t =new AFN();
//        t.CrearAFNBasico('e');
//        AFN r =new AFN();
//        r.CrearAFNBasico('\\');
//        AFN a1 =new AFN();
//        a1.CrearAFNBasico('(');
//        AFN b1 =new AFN();
//        b1.CrearAFNBasico(')');
//        AFN c1 =new AFN();
//        c1.CrearAFNBasico('+');
//        AFN d1 =new AFN();
//        d1.CrearAFNBasico('*');
//        AFN e1 =new AFN();
//        e1.CrearAFNBasico('-');
//        AFN f1 =new AFN();
//        f1.CrearAFNBasico('/');
//        a1.unirAFN(a1);
//        a1.unirAFN(b1);      
//        a1.unirAFN(c1);
//        a1.unirAFN(d1);
//        a1.unirAFN(e1);
//        a1.unirAFN(f1);
//        r.concatenar(a1);
//        a.unirAFN(r);
//        a.unirAFN(m);
//        AFN h =new AFN();
//        
//        h.UnionEspecialANFs(b, 10);//and
//        h.UnionEspecialANFs(c, 20);//or
//        h.UnionEspecialANFs(d, 30);//suma
//        h.UnionEspecialANFs(e, 40);// kleen
//        h.UnionEspecialANFs(f, 50);// opcional
//        h.UnionEspecialANFs(g, 60);// (
//        h.UnionEspecialANFs(i, 70);// )
//        h.UnionEspecialANFs(j, 80);// [
//        h.UnionEspecialANFs(k, 90);// ]
//        h.UnionEspecialANFs(l, 100);// -
//        h.UnionEspecialANFs(a, 110);//simb
//        //h.UnionEspecialANFs(m, 0);// Fin de cadena
//        h.ConvAFNaAFD("ExpR");
//        
//        ER_AFN EA = new ER_AFN("[a-z]|[A-Z]&([a-z]|[A-Z]|[0-9])*", "ER", 1);
//        //ER_AFN EA = new ER_AFN("A+", "ER", 1);
//        //EA.IniCoversion();
//        EA.IniCoversion();
//        ER_AFN E1 = new ER_AFN("[0-9]+&(.&[0-9]+&((E|e)&[0-9]+)?)?", "ER", 2);
//        //EA.IniCoversion();
//        E1.IniCoversion();
//        ER_AFN E2 = new ER_AFN("\\+", "ER", 3);
//        //EA.IniCoversion();
//        E2.IniCoversion();
//        ER_AFN E3 = new ER_AFN("\\*", "ER", 4);
//        //EA.IniCoversion();
//        E3.IniCoversion();
//        ER_AFN E4 = new ER_AFN("\\(", "ER", 5);
//        //EA.IniCoversion();
//        E4.IniCoversion();
//        ER_AFN E5 = new ER_AFN("\\)", "ER", 6);
//        //EA.IniCoversion();
//        E5.IniCoversion();
//        
//        AFN test = new AFN();
//        test.UnionEspecialANFs(EA.result, 10);
//        test.UnionEspecialANFs(E1.result, 20);
////        test.UnionEspecialANFs(E2.result, 0);
////        test.UnionEspecialANFs(E3.result, 0);
////        test.UnionEspecialANFs(E4.result, 0);
////        test.UnionEspecialANFs(E5.result, 0);
//        test.ConvAFNaAFD("final");
        
        
        //gramatica de gramaticas
        // simbolo [a-z]|[A-Z]|\+|\-|\*|\/|\(|\)|'&([a-z]|[A-Z]|[0-9])*
        // FLECHA F&L&E&C&H&A
        // PC P&C
        // OR O&R
        // espacio 
        // enter
        
        String sigma="Foca FLECHA Foca OR Foca + Foca' PC\nperror FLECHA perro OR perro - perro' PC";
        GramGram GG=new GramGram(sigma, "GramGram", 0);
        GG.AnalizSatGG();
      
    }
    
}
