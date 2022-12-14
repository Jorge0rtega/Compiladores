package interfaces;
import compiladores.AFN;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

public class PBasico extends JDialog implements ActionListener{
    JLabel inst, inicio, fin, ind;
    JTextField caracter, caracterf;
    JButton aceptar, cancelar;
    JPanel arriba, centro, abajo;
    JCheckBox ascii;
    AFN a = new AFN();
    int index;
    Boolean correcto = false;
    
    public PBasico(java.awt.Frame parent, boolean model,int n){
        super(parent,model);
        index=n;
        ind = new JLabel("AFN ID. " + index + ":");
        inst = new JLabel("\n Coloca tu caracter(es): ");
        inicio = new JLabel("De: ");
        fin = new JLabel(" a: ");
        inst.setFont(new Font("Serif", Font.BOLD, 25));
        ind.setFont(new Font("Serif", Font.BOLD, 25));
        caracter = new JTextField(5);
        caracterf = new JTextField(5);
        ascii = new JCheckBox("Cogigo ASCII");
        ascii.setBounds(10, 10, 150, 30);
        aceptar = new JButton("Aceptar");
        cancelar = new JButton("Cancelar");
        aceptar.addActionListener(this);
        cancelar.addActionListener(this);
        arriba=new JPanel();
        abajo=new JPanel();
        centro=new JPanel();

        arriba.add(inst);
        centro.add(ind);
        centro.add(ascii);
        centro.add(inicio);centro.add(caracter);centro.add(fin);centro.add(caracterf);
        abajo.add(aceptar);abajo.add(cancelar);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add("North",arriba);
        getContentPane().add("Center",centro);
        getContentPane().add("South",abajo);
        setSize(600,400);
	    //setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e ){
        if(e.getSource()==aceptar){
            if(caracter.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "error al crear el AFN Básico");
                setVisible(false);
                setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
            }
            char c1,c2=0;
            if(!ascii.isSelected()){
                c1 = caracter.getText().charAt(0);
                if(!caracterf.getText().isEmpty())
                    c2 = caracterf.getText().charAt(0);
            }
            else{
                c1 = (char) Integer.parseInt(caracter.getText());
                if(!caracterf.getText().isEmpty())
                    c2 = (char) Integer.parseInt(caracterf.getText());
            }

            if(caracterf.getText().isEmpty())
                a = a.CrearAFNBasico(c1);
            else
                a = a.CrearAFNBasico(c1,c2);
            JOptionPane.showMessageDialog(this, " Se creo el AFN Básico " + index);
            index = index + 1;

            setVisible(false);
        }
        if(e.getSource()==cancelar){

            setVisible(false);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            //DO_NOTHING_ON_CLOSE, HIDE_ON_CLOSE, or DISPOSE_ON_CLOSE 
            //No EXIT_ON_CLOSE
        }
    }

    public AFN getafn(){
        return this.a;
    }

    public int getcont(){
        return this.index;
    }
}
