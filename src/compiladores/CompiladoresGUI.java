package compiladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Jorge Ortega
 */
public class CompiladoresGUI extends JFrame implements ActionListener{
    JMenu AFNs;
    JMenuBar Menu;
    JMenuItem Basico, Unir, Concatenar, CSuma, CMul, Opcional, ERAFN, UnionAL, Convertir, Analizar, calculadora;
    AFN A[];
    AFD D[];
    int cont=0, contd=0;
    
    public CompiladoresGUI(){
        setLayout(null);
        A = new AFN[100];
        D = new AFD[100];
        cont = 0;
        contd = 0;
        Menu = new JMenuBar();
        setJMenuBar(Menu);
        AFNs = new JMenu("AFNs");
        Menu.add(AFNs);
        Basico = new JMenuItem("Básico");
        AFNs.add(Basico);
        Unir = new JMenuItem("Unir");
        AFNs.add(Unir);
        Concatenar = new JMenuItem("Concatenar");
        AFNs.add(Concatenar);
        CSuma = new JMenuItem("Cerradura +");
        AFNs.add(CSuma);
        CMul = new JMenuItem("Cerradura *");
        AFNs.add(CMul);
        Opcional = new JMenuItem("Opcional");
        AFNs.add(Opcional);
        ERAFN = new JMenuItem("ER->AFN");
        AFNs.add(ERAFN);
        UnionAL = new JMenuItem("Unión para Analizador Léxico");
        AFNs.add(UnionAL);
        Convertir = new JMenuItem("Convertir AFN a AFD");
        AFNs.add(Convertir);
        Analizar = new JMenuItem("Analizar una Cadena");
        AFNs.add(Analizar);
        calculadora = new JMenuItem("Calculadora");
        AFNs.add(calculadora);
        Basico.addActionListener(this);
        Unir.addActionListener(this);
        Concatenar.addActionListener(this);
        CSuma.addActionListener(this);
        CMul.addActionListener(this);
        Opcional.addActionListener(this);
        ERAFN.addActionListener(this);
        UnionAL.addActionListener(this);
        Convertir.addActionListener(this);
        Analizar.addActionListener(this);
        calculadora.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e ){
        if(e.getSource()==Basico){
            PBasico B = new PBasico(new javax.swing.JFrame(),true,cont);
            B.setVisible(true);
            A[cont]=B.getafn();
            cont=B.getcont();
        }
        if(e.getSource()==Unir){            
            PUnion B = new PUnion(new javax.swing.JFrame(),true,A,cont);
            B.setVisible(true);
            A[cont]=B.getafn();
            cont=B.getcont();
            }
        if(e.getSource()==Concatenar){
            PConcatenar B = new PConcatenar(new javax.swing.JFrame(),true,A,cont);
            B.setVisible(true);
            A[cont]=B.getafn();
            cont=B.getcont();
        }
        if(e.getSource()==CSuma){
            PSuma B = new PSuma(new javax.swing.JFrame(),true,A,cont);
            B.setVisible(true);
            A[cont]=B.getafn();
            cont=B.getcont();
        }
        if(e.getSource()==CMul){
            PAsterisco B = new PAsterisco(new javax.swing.JFrame(),true,A,cont);
            B.setVisible(true);
            A[cont]=B.getafn();
            cont=B.getcont();
        }
        if(e.getSource()==Opcional){
            POpcional B = new POpcional(new javax.swing.JFrame(),true,A,cont);
            B.setVisible(true);
            A[cont]=B.getafn();
            cont=B.getcont();
        }
//        if(e.getSource()==ERAFN){
//            ERafn B = new ERafn(new javax.swing.JFrame(),true,cont);
//            B.setVisible(true);
//            A[cont]=B.getafn();
//            cont=B.getcont();
//        }
        if(e.getSource()==UnionAL){            
            UnirAFNs B = new UnirAFNs(new javax.swing.JFrame(),true,A,cont);
            B.setVisible(true);
            A[cont]=B.getafn();
            cont=B.getcont();}
        if(e.getSource()==Convertir){
            converAFNaAFD B = new converAFNaAFD(new javax.swing.JFrame(),true,A,cont, contd);
            B.setVisible(true);
            D[cont]=B.getafd();
            contd=B.getcont();
        }
//        if(e.getSource()==Analizar){
//            PAnalisisLexico B = new PAnalisisLexico(new javax.swing.JFrame(),true, D, contd);
//            B.setVisible(true);
//            //D[cont]=B.getafd();
//            //contd=B.getcont();
//        }
//        if(e.getSource()==Probar){}
        
        if(e.getSource()==Analizar){   
            PAnalizLex B = new PAnalizLex(new javax.swing.JFrame(),true,cont, A);
            B.setVisible(true);
            System.out.println("B.guardar = " + B.guardar);
            if(B.guardar){
                A=B.f;
                cont=B.cont;
                System.out.println("cont = " + cont);
            }
            
        }
        if(e.getSource()==calculadora){
            PCalculadora calcu = new PCalculadora(new javax.swing.JFrame(),true);
            calcu.setVisible(true);
        }
    }
    
    
    public static void main(String[] args) {
        CompiladoresGUI C = new CompiladoresGUI();
        C.setBounds(10, 20, 800, 600);
        C.setVisible(true);
        C.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*AFN a1 = new AFN();
        a1.CrearAFNBasico('A');
        AFN a2 = new AFN();
        AFN a3 = new AFN();
        a2.CrearAFNBasico('B', 'Z');
        for(Estado e: a1.EdosAcept){
            System.out.println(e.IdEstado);
        }
        for(Estado e: a2.EdosAcept){
            System.out.println(e.IdEstado);
        }
        System.out.println(a1.EdosAFN);
        System.out.println(a2.EdosAFN);
        a1.cerraduraSuma();
        System.out.println(a1.Alfabeto);
        System.out.println(a1.EdosAFN);
        for(Estado e: a1.EdosAcept){
            System.out.println(e.IdEstado);
        }
        System.out.println("Id de los estados del AFN final");
        for(Estado e: a1.EdosAFN){
            System.out.println(e.IdEstado);
        }*/
        
    }
    
}