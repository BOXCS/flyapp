package Dashboard.Admin.Cell;

import Dashboard.Admin.Model.ModelDesigner;
import Dashboard.Admin.Model.ModelName;
import Dashboard.Admin.Service.ServiceDesigner;
import com.raven.table.TableCustom;
import com.raven.table.cell.TableCustomCell;
import com.raven.table.model.TableRowData;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import java.util.Date;

public class CellUserName extends TableCustomCell {
    
    private String pathImage;

    public CellUserName() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popup = new javax.swing.JPopupMenu();
        deleteImage = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmdSave = new javax.swing.JButton();
        image = new Dashboard.Swing.ImageAvatar();

        deleteImage.setText("jMenuItem1");
        deleteImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteImageActionPerformed(evt);
            }
        });
        popup.add(deleteImage);

        jLabel1.setText("UserName");

        jLabel2.setText("Image");

        cmdSave.setText("Save");

        image.setBorderSize(2);
        image.setBorderSpace(1);
        image.setGradientColor1(new java.awt.Color(63, 109, 217));
        image.setGradientColor2(new java.awt.Color(199, 42, 42));
        image.setImage(new javax.swing.ImageIcon(getClass().getResource("/Dashboard/icon/BG.png"))); // NOI18N
        image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imageMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdSave)
                        .addGap(0, 182, Short.MAX_VALUE))
                    .addComponent(txtName)
                    .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(cmdSave)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 2) {
            JFileChooser ch = new JFileChooser();
            ch.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    String name = f.getName().toLowerCase();
                    return f.isDirectory() || name.endsWith(".png") || name.endsWith(".jpg");
                }

                @Override
                public String getDescription() {
                    return "Image File";
                }
            });
            int opt = ch.showOpenDialog(this);
            if (opt == JFileChooser.APPROVE_OPTION) {
                pathImage = ch.getSelectedFile().getAbsolutePath();
                image.setImage(new ImageIcon(pathImage));
            }
        }
    }//GEN-LAST:event_imageMouseClicked

    private void imageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageMousePressed
        if (SwingUtilities.isRightMouseButton(evt)) {
            popup.show(image, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_imageMousePressed

    private void deleteImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteImageActionPerformed
        pathImage = "";
        image.setImage(null);
    }//GEN-LAST:event_deleteImageActionPerformed

    private void addEventSave(TableCustom table) {

        cmdSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.stopCellEditing();
                int row = getRow();
                ModelName name = (ModelName) table.getValueAt(row, 0);
                String Discord = table.getValueAt(row, 1).toString();
                String typeContent = table.getValueAt(row, 2).toString();
                String Details = table.getValueAt(row, 3).toString();
                Object date = table.getValueAt(row, 4);
                java.util.Date projectEndDate = null;

                // Validasi sel tanggal
                if (date instanceof java.util.Date) {
                    java.util.Date utilDate = new java.util.Date();
                    projectEndDate = new java.util.Date(utilDate.getTime());
                } else if (date instanceof java.util.Date) {
                    projectEndDate = (java.util.Date) date;
                } else {
                    // Sel tidak berisi tanggal yang valid
                    // Lakukan penanganan kesalahan atau berikan peringatan kepada pengguna
                    JOptionPane.showMessageDialog(null, "Invalid date in row " + row, "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Keluar dari metode jika sel tidak valid
                }
                ModelDesigner staff = (ModelDesigner) table.getModelData(row);
                ModelDesigner data = new ModelDesigner(staff.getDesignerID(), name, Discord, typeContent, Details, (Date) date);
                try {
                    ServiceDesigner serviceOrder = new ServiceDesigner();
                    if (staff.getDesignerID() == 0) {
                        // Insert
                        if (serviceOrder.isUserNameExists(name.getUserName())) {
                            JOptionPane.showMessageDialog(null, "Designer with UserName already exists!", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
                            return; // Keluar dari metode jika data sudah ada
                        }
                        serviceOrder.insertDesigner(data);
                        data.getName().setPath("Image");
                        table.updateModelData(row, data);
                    } else {
                        // Update
                        serviceOrder.updateDesigner(data);
                        data.getName().setPath("Image");
                        table.updateModelData(row, data);
                    }
                } catch (IOException | SQLException ae) {
                    System.err.println(ae);
                    data.getName().setPath("Image");
                    table.updateModelData(row, data);
                }
            }
        });
    }
    
    @Override
    public void setData(Object o) {
        if (o != null) {
            ModelName d = (ModelName) o;
            txtName.setText(d.getUserName());
            image.setImage(d.getProfile());
            pathImage = d.getPath();
        }
    }

    @Override
    public Object getData() {
        String UserName = txtName.getText().trim();
        return new ModelName(UserName, image.getImage(), pathImage);
    }

    @Override
    public Component createComponentCellRender(TableCustom table, TableRowData data, int row, int column) {
        ModelDesigner designer = (ModelDesigner) data;
        return new CellUserNameRender(designer.getName());
    }
    

    @Override
    public TableCustomCell createComponentCellEditor(TableCustom tc, TableRowData trd, Object o, int i, int i1) {
        CellUserName cell = new CellUserName();
        cell.setData(o);
        cell.addEventSave(tc);
        return cell;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdSave;
    private javax.swing.JMenuItem deleteImage;
    private Dashboard.Swing.ImageAvatar image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPopupMenu popup;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

}
