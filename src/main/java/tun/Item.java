/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tun;

/**
 *
 * @author damian
 */
public class Item extends javax.swing.JPanel {

    /**
     * Creates new form Item
     */
    public Item() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIcon = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        checkbox = new javax.swing.JCheckBox();

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("available.png"))); // NOI18N

        lblTitle.setFont(new java.awt.Font("Ubuntu", 1, 11)); // NOI18N
        lblTitle.setText("mercadolibre");

        lblDescription.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        lblDescription.setText("ssh -L 5000:192.172.13.13:500 lberetta@192.168.1.199");

        checkbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle)
                    .addComponent(lblDescription))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(checkbox)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(checkbox)
                            .addComponent(lblIcon)
                            .addComponent(lblDescription))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkbox;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
	public javax.swing.JCheckBox getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(javax.swing.JCheckBox checkbox) {
		this.checkbox = checkbox;
	}

	public javax.swing.JLabel getLblDescription() {
		return lblDescription;
	}

	public void setLblDescription(javax.swing.JLabel lblDescription) {
		this.lblDescription = lblDescription;
	}

	public javax.swing.JLabel getLblIcon() {
		return lblIcon;
	}

	public void setLblIcon(javax.swing.JLabel lblIcon) {
		this.lblIcon = lblIcon;
	}

	public javax.swing.JLabel getLblTitle() {
		return lblTitle;
	}

	public void setLblTitle(javax.swing.JLabel lblTitle) {
		this.lblTitle = lblTitle;
	}
}