/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tun;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author damian
 */
public class View extends javax.swing.JFrame implements KeyListener {

	private Main main;
	
    /**
     * Creates new form NewJFrame
     */
    public View(Main main) {
    	this.main = main;
        initComponents();
        lookAndField();
    }
    
    private void lookAndField(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void initComponents() {

    	addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    	
        textField = new javax.swing.JTextField();
        textField.setVisible(false);
        
        textField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
		        	main.borrarFiltro();
		        	getTextField().setVisible(false);
		        	getTextField().setText("");
		        	pack();
		        }else {
		        	String txt = ((JTextField)e.getSource()).getText();
		        	main.filtrar(txt);		        	
		        }
			}
			@Override
			public void keyPressed(KeyEvent e) {}
		});
        
        pnlContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textField.setBackground(new java.awt.Color(254, 253, 222));
        textField.setText("");

        pnlContainer.setBackground(new java.awt.Color(251, 251, 251));
        pnlContainer.setLayout(new javax.swing.BoxLayout(pnlContainer, javax.swing.BoxLayout.PAGE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textField, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>   

    // Variables declaration - do not modify                     
    private javax.swing.JPanel pnlContainer;
    private javax.swing.JTextField textField;
    // End of variables declaration                   

    public JPanel getPnlContainer() {
        return pnlContainer;
    }

    public void setPnlContainer(JPanel pnlContainer) {
        this.pnlContainer = pnlContainer;
    }

    public void keyPressed(KeyEvent e) {
    	main.jframeKeyPressed(e);
    }
    
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	public javax.swing.JTextField getTextField() {
		return textField;
	}

	public void setTextField(javax.swing.JTextField textField) {
		this.textField = textField;
	}
	
}