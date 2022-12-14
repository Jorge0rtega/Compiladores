package interfaces;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class Render extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        // TODO Auto-generated method stub
            setHorizontalAlignment(SwingConstants.CENTER);
            if(value instanceof JCheckBox){
                JCheckBox btn = (JCheckBox)value;
                return btn;
            }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
    
    
}
