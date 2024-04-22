package User.SeeOrder.Main;

import LoginRegister.Model.ModelUser;
import Test.DisplayImage.Main.DisplayImageFromDatabase;
import Test.DisplayImage.Main.DisplayImageFromDatabasePreview;
import Test.InsertResult.ServiceResult;
import User.Revision.Main.RevisionMain;
import User.SeeOrder.Rating.Main.RatingMain;
import User.SeeOrder.Rating.Service.ServiceRating;
import User.SeeOrder.Service.ServiceMyOrder;
import User.SeeOrder.Swing.Cell.CellActionRenderer;
import User.SeeOrder.Swing.Cell.TableActionCellEditor;
import User.SeeOrder.Swing.Cell.TableActionEvent;
import User.SeeOrder.Swing.CellActive.CellActiveRenderer;
import User.SeeOrder.Swing.CellActive.TableActionActiveEvent;
import User.SeeOrder.Swing.CellActive.TableActiveCellEditor;
import User.SeeOrder.Swing.CellCancelled.CellCancelledRenderer;
import User.SeeOrder.Swing.CellCancelled.TableActionCancelledEvent;
import User.SeeOrder.Swing.CellCancelled.TableCancelledCellEditor;
import User.SeeOrder.Swing.CellFinished.CellFinishedRenderer;
import User.SeeOrder.Swing.CellFinished.TableActionFinishedEvent;
import User.SeeOrder.Swing.CellFinished.TableFinishedCellEditor;
import User.SeeOrder.Swing.CellWaiting.CellWaitingRenderer;
import User.SeeOrder.Swing.CellWaiting.TableActionWaitingEvent;
import User.SeeOrder.Swing.CellWaiting.TableWaitingCellEditor;
import User.SendFootage.Main.FootageMain;
import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import video.MainVideoJ;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import jnafilechooser.api.JnaFileChooser;
import raven.alerts.MessageAlerts;
import connection.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import video.MainVideoJPreview;

public class SeeOrderMain extends javax.swing.JPanel {

    private MainVideoJ videoPlayer;
    private final ModelUser user;
    private ServiceRating serviceRating;

    public SeeOrderMain(ModelUser user) {
        initComponents();

        this.user = user;

        setOpaque(false);
        tableActive.addTableStyle(jScrollPane7);
        tablePending.addTableStyle(jScrollPane8);
        tableFinished.addTableStyle(jScrollPane9);
        tableCancelled.addTableStyle(jScrollPane10);
        tableWaiting.addTableStyle(jScrollPane12);

        ServiceMyOrder.fetchActiveOrders((DefaultTableModel) tableActive.getModel(), user.getUserName());
        ServiceMyOrder.fetchPendingOrders((DefaultTableModel) tablePending.getModel(), user.getUserName());
        ServiceMyOrder.fetchWaitingOrders((DefaultTableModel) tableWaiting.getModel(), user.getUserName());
        ServiceMyOrder.fetchFinishedOrders((DefaultTableModel) tableFinished.getModel(), user.getUserName());
        ServiceMyOrder.fetchCancelledOrders((DefaultTableModel) tableCancelled.getModel(), user.getUserName());

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onPlay(int row) {
                int selectedRow = tablePending.getSelectedRow();
                if (selectedRow != -1) {
                    Object transactionNumber = tablePending.getValueAt(selectedRow, 0);
                    Object product = tablePending.getValueAt(selectedRow, 2); // Mengambil nilai kolom "Product"

                    if (product != null && product.toString().equalsIgnoreCase("Video Editing")) {
                        // Memainkan video dengan MainVideoJ
                        MainVideoJPreview videoPlayer = new MainVideoJPreview(transactionNumber.toString());
                        videoPlayer.playVideoFromDatabase();
                    } else if (product != null && (product.toString().equalsIgnoreCase("Design Graphic") || product.toString().equalsIgnoreCase("3D Modelling"))) {
                        // Menampilkan gambar dengan DisplayImageFromDatabase
                        DisplayImageFromDatabasePreview imageViewerP = new DisplayImageFromDatabasePreview(transactionNumber.toString());
                        imageViewerP.displayCurrentImage();
                    } else {
                        JOptionPane.showMessageDialog(SeeOrderMain.this, "Unsupported product type.");
                    }
                } else {
                    JOptionPane.showMessageDialog(SeeOrderMain.this, "Please select a row first.");
                }
            }

            @Override
            public void onRevision(int row) {
                int selectedRow = tablePending.getSelectedRow();
                if (selectedRow != -1) {
                    Object transactionNumber = tablePending.getValueAt(selectedRow, 0);
                    Object designerName = tablePending.getValueAt(selectedRow, 4);
                    Object productName = tablePending.getValueAt(selectedRow, 2);
                    Object level = tablePending.getValueAt(selectedRow, 3);
                    try {
                        RevisionMain revisionMain = new RevisionMain(); // Buat instance baru dari RevisionMain
                        revisionMain.setTransactionNumber(transactionNumber.toString()); // Tetapkan nomor transaksi
                        revisionMain.setDesignerName(designerName.toString()); // Tetapkan nama desainer
                        revisionMain.setProductName(productName.toString()); // Tetapkan nama produk
                        revisionMain.setLevel(level.toString()); // Tetapkan level
                        revisionMain.setLocationRelativeTo(null);
                        revisionMain.setVisible(true); // Tampilkan frame RevisionMain
                        revisionMain.requestFocus();
                    } catch (SQLException ex) {
                        Logger.getLogger(SeeOrderMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(SeeOrderMain.this, "Please select a row first.");
                }
            }

            @Override
            public void onRate(int row) {
                int selectedRow = tablePending.getSelectedRow();
                if (selectedRow != -1) {
                    Object transactionNumber = tablePending.getValueAt(selectedRow, 0);
                    Object designerName = tablePending.getValueAt(selectedRow, 4);
                    try {
                        // Periksa apakah transactionNumber sudah dinilai sebelumnya
                        if (serviceRating.isTransactionAlreadyRated(transactionNumber.toString())) {
                            MessageAlerts.getInstance().showMessage("Caution", "Already Rate This", MessageAlerts.MessageType.DEFAULT);
                        } else {
                            System.out.println("Rate on designer: " + designerName + " with transaction number: " + transactionNumber);

                            // Buat instansiasi RatingMain
                            RatingMain ratingMain = new RatingMain();

                            // Atur nilai transactionNumber dan designerName
                            ratingMain.setTransactionNumber(transactionNumber.toString());
                            ratingMain.setDesigner(designerName.toString());

                            // Tampilkan frame RatingMain
                            ratingMain.setVisible(true);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SeeOrderMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(SeeOrderMain.this, "Please select a row first.");
                }
            }
        };
        tablePending.getColumnModel().getColumn(8).setCellRenderer(new CellActionRenderer());
        tablePending.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditor(event));

        TableActionWaitingEvent eventW = new TableActionWaitingEvent() {
            @Override
            public void onNote(int row) {
                int selectedRow = tableWaiting.getSelectedRow();
                if (selectedRow != -1) {
                    Object transactionNumber = tableWaiting.getValueAt(selectedRow, 0);
                    Object designerName = tableWaiting.getValueAt(selectedRow, 4);
                    Object productName = tableWaiting.getValueAt(selectedRow, 2);
                    Object level = tableWaiting.getValueAt(selectedRow, 3);
                    Object username = tableWaiting.getValueAt(selectedRow, 1);

                    try {
                        // Query untuk mendapatkan email pengguna dari tabel user berdasarkan username
                        String queryUser = "SELECT email FROM user WHERE username = ?";
                        PreparedStatement statementUser = DatabaseConnection.getInstance().getConnection().prepareStatement(queryUser);
                        statementUser.setString(1, username.toString());
                        ResultSet resultSetUser = statementUser.executeQuery();

                        if (resultSetUser.next()) {
                            // Ambil email pengguna dari hasil kueri
                            String email = resultSetUser.getString("email");

                            // Buat instansiasi FootageMain
                            FootageMain footageMain = new FootageMain();

                            // Atur nilai transactionNumber, designerName, productName, level, dan email
                            footageMain.setTransactionNumber(transactionNumber.toString());
                            footageMain.setDesigner(designerName.toString());
                            footageMain.setProductName(productName.toString());
                            footageMain.setLevel(level.toString());
                            footageMain.setEmail(email);

                            // Tampilkan frame FootageMain
                            footageMain.setVisible(true);

                            System.out.println("Give Note on transaction number: " + transactionNumber);
                        } else {
                            System.out.println("User email not found for username: " + username);
                        }

                        // Tutup resources
                        resultSetUser.close();
                        statementUser.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(SeeOrderMain.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(SeeOrderMain.this, "Please select a row first.");
                }
            }
        };
        tableWaiting.getColumnModel().getColumn(8).setCellRenderer(new CellWaitingRenderer());
        tableWaiting.getColumnModel().getColumn(8).setCellEditor(new TableWaitingCellEditor(eventW));

        TableActionFinishedEvent eventF = new TableActionFinishedEvent() {
            @Override
            public void onPlay(int row) {
                int selectedRow = tableFinished.getSelectedRow();
                if (selectedRow != -1) {
                    Object transactionNumber = tableFinished.getValueAt(selectedRow, 0);
                    Object product = tableFinished.getValueAt(selectedRow, 2); // Mengambil nilai kolom "Product"

                    if (product != null && product.toString().equalsIgnoreCase("Video Editing")) {
                        // Memainkan video dengan MainVideoJ
                        MainVideoJ videoPlayer = new MainVideoJ(transactionNumber.toString());
                        videoPlayer.playVideoFromDatabase();
                    } else if (product != null && (product.toString().equalsIgnoreCase("Design Graphic") || product.toString().equalsIgnoreCase("3D Modelling"))) {
                        // Menampilkan gambar dengan DisplayImageFromDatabase
                        DisplayImageFromDatabase imageViewer = new DisplayImageFromDatabase(transactionNumber.toString());
                        imageViewer.displayImageFromDatabase();
                    } else {
                        JOptionPane.showMessageDialog(SeeOrderMain.this, "Unsupported product type.");
                    }
                } else {
                    JOptionPane.showMessageDialog(SeeOrderMain.this, "Please select a row first.");
                }
            }

            @Override
            public void onDownload(int row) {
                int selectedRow = tableFinished.getSelectedRow();
                if (selectedRow != -1) {
                    Object transactionNumber = tableFinished.getValueAt(selectedRow, 0);
                    Object product = tableFinished.getValueAt(selectedRow, 2); // Mengambil nilai kolom "Product"

                    if (product != null && product.toString().equalsIgnoreCase("Video Editing")) {
                        // Mendownload video dari database
                        try {
                            byte[] videoData = ServiceResult.getVideoFromDatabase(transactionNumber.toString());
                            if (videoData != null) {
                                // Memungkinkan pengguna memilih direktori penyimpanan
                                JnaFileChooser fileChooser = new JnaFileChooser();
                                fileChooser.setTitle("Choose directory to save video");
                                fileChooser.setMode(JnaFileChooser.Mode.Directories);
                                Window window = SwingUtilities.getWindowAncestor(SeeOrderMain.this);
                                boolean userSelection = fileChooser.showSaveDialog(window);

                                if (userSelection) {
                                    File directory = fileChooser.getSelectedFile();
                                    String fileName = transactionNumber.toString() + ".mp4";
                                    File file = new File(directory, fileName);

                                    // Simpan data video ke file di sistem file lokal
                                    FileOutputStream fos = new FileOutputStream(file);
                                    fos.write(videoData);
                                    fos.close();
                                    MessageAlerts.getInstance().showMessage("Download Success", "Video downloaded successfully.", MessageAlerts.MessageType.SUCCESS);
                                } else {
                                    MessageAlerts.getInstance().showMessage("Caution", "Download Cancelled", MessageAlerts.MessageType.DEFAULT);
                                }
                            } else {
                                MessageAlerts.getInstance().showMessage("Caution", "Failed to download video.", MessageAlerts.MessageType.ERROR);
                            }
                        } catch (SQLException | IOException e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(SeeOrderMain.this, "Failed to download video.");
                        }
                    } else if (product != null && (product.toString().equalsIgnoreCase("Design Graphic") || product.toString().equalsIgnoreCase("3D Modelling"))) {
                        // Mendownload gambar dari database
                        try {
                            byte[] imageData = ServiceResult.getImageFromDatabase(transactionNumber.toString());
                            if (imageData != null) {
                                // Memungkinkan pengguna memilih direktori penyimpanan
                                JnaFileChooser fileChooser = new JnaFileChooser();
                                fileChooser.setTitle("Choose directory to save image");
                                fileChooser.setMode(JnaFileChooser.Mode.Directories);
                                Window window = SwingUtilities.getWindowAncestor(SeeOrderMain.this);
                                boolean userSelection = fileChooser.showSaveDialog(window);

                                if (userSelection) {
                                    File directory = fileChooser.getSelectedFile();
                                    String fileName = transactionNumber.toString() + ".png"; // Atau ekstensi file yang sesuai
                                    File file = new File(directory, fileName);

                                    // Simpan data gambar ke file di sistem file lokal
                                    FileOutputStream fos = new FileOutputStream(file);
                                    fos.write(imageData);
                                    fos.close();
                                    MessageAlerts.getInstance().showMessage("Download Success", "Image downloaded successfully.", MessageAlerts.MessageType.SUCCESS);
                                } else {
                                    MessageAlerts.getInstance().showMessage("Caution", "Download Cancelled", MessageAlerts.MessageType.DEFAULT);
                                }
                            } else {
                                JOptionPane.showMessageDialog(SeeOrderMain.this, "Failed to download image.");
                            }
                        } catch (SQLException | IOException e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(SeeOrderMain.this, "Failed to download image.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(SeeOrderMain.this, "Unsupported product type.");
                    }
                } else {
                    JOptionPane.showMessageDialog(SeeOrderMain.this, "Please select a row first.");
                }
            }
        };
        tableFinished.getColumnModel().getColumn(8).setCellRenderer(new CellFinishedRenderer());
        tableFinished.getColumnModel().getColumn(8).setCellEditor(new TableFinishedCellEditor(eventF));

        TableActionCancelledEvent eventC = new TableActionCancelledEvent() {
            @Override
            public void onReason(int row) {
                int selectedRow = tableCancelled.getSelectedRow();
                if (selectedRow != -1) {
                    Object transactionNumber = tableCancelled.getValueAt(selectedRow, 0);
                    System.out.println("Reason Cancelled on transaction number: " + transactionNumber);
                } else {
                    JOptionPane.showMessageDialog(SeeOrderMain.this, "Please select a row first.");
                }
            }
        };
        tableCancelled.getColumnModel().getColumn(8).setCellRenderer(new CellCancelledRenderer());
        tableCancelled.getColumnModel().getColumn(8).setCellEditor(new TableCancelledCellEditor(eventC));

        TableActionActiveEvent eventA = new TableActionActiveEvent() {
            @Override
            public void onReceipt(int row) {
                int selectedRow = tableActive.getSelectedRow();
                if (selectedRow != -1) {
                    Object transactionNumber = tableActive.getValueAt(selectedRow, 0);
                    ServiceMyOrder.printReceipt(transactionNumber.toString());
                } else {
                    JOptionPane.showMessageDialog(SeeOrderMain.this, "Please select a row first.");
                }
            }
        };
        tableActive.getColumnModel().getColumn(8).setCellRenderer(new CellActiveRenderer());
        tableActive.getColumnModel().getColumn(8).setCellEditor(new TableActiveCellEditor(eventA));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new User.SeeOrder.Swing.RoundPanel();
        buttonDash1 = new User.SeeOrder.Swing.ButtonDash();
        materialTabbed1 = new User.SeeOrder.Swing.MaterialTabbed();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableActive = new User.SeeOrder.Swing.Table();
        jScrollPane11 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tableWaiting = new User.SeeOrder.Swing.Table();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablePending = new User.SeeOrder.Swing.Table();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tableFinished = new User.SeeOrder.Swing.Table();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tableCancelled = new User.SeeOrder.Swing.Table();
        jScrollPane13 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tableLate = new User.SeeOrder.Swing.Table();

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0, 50));

        buttonDash1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/SeeOrder/icon/hint.png"))); // NOI18N

        materialTabbed1.setForeground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0, 0));

        tableActive.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Transaction", "Username", "Product", "Level", "Designer", "Due On", "Amount", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tableActive);
        if (tableActive.getColumnModel().getColumnCount() > 0) {
            tableActive.getColumnModel().getColumn(1).setResizable(false);
            tableActive.getColumnModel().getColumn(2).setResizable(false);
            tableActive.getColumnModel().getColumn(3).setResizable(false);
            tableActive.getColumnModel().getColumn(4).setResizable(false);
            tableActive.getColumnModel().getColumn(5).setResizable(false);
            tableActive.getColumnModel().getColumn(6).setResizable(false);
            tableActive.getColumnModel().getColumn(6).setPreferredWidth(10);
            tableActive.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(204, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        materialTabbed1.addTab("Active", jScrollPane1);

        jScrollPane11.setBorder(null);
        jScrollPane11.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane11.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel8.setBackground(new java.awt.Color(0,0,0,0));

        tableWaiting.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Transaction", "Username", "Product", "Level", "Designer", "Due On", "Amount", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(tableWaiting);
        if (tableWaiting.getColumnModel().getColumnCount() > 0) {
            tableWaiting.getColumnModel().getColumn(0).setResizable(false);
            tableWaiting.getColumnModel().getColumn(1).setResizable(false);
            tableWaiting.getColumnModel().getColumn(2).setResizable(false);
            tableWaiting.getColumnModel().getColumn(3).setResizable(false);
            tableWaiting.getColumnModel().getColumn(4).setResizable(false);
            tableWaiting.getColumnModel().getColumn(5).setResizable(false);
            tableWaiting.getColumnModel().getColumn(6).setResizable(false);
            tableWaiting.getColumnModel().getColumn(6).setPreferredWidth(10);
            tableWaiting.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jScrollPane11.setViewportView(jPanel8);

        materialTabbed1.addTab("Waiting", jScrollPane11);

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0, 0));

        tablePending.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Transaction", "Username", "Product", "Level", "Designer", "Due On", "Amount", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tablePending);
        if (tablePending.getColumnModel().getColumnCount() > 0) {
            tablePending.getColumnModel().getColumn(0).setResizable(false);
            tablePending.getColumnModel().getColumn(1).setResizable(false);
            tablePending.getColumnModel().getColumn(2).setResizable(false);
            tablePending.getColumnModel().getColumn(3).setResizable(false);
            tablePending.getColumnModel().getColumn(4).setResizable(false);
            tablePending.getColumnModel().getColumn(5).setResizable(false);
            tablePending.getColumnModel().getColumn(6).setResizable(false);
            tablePending.getColumnModel().getColumn(6).setPreferredWidth(10);
            tablePending.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel3);

        materialTabbed1.addTab("Pending", jScrollPane2);

        jScrollPane5.setBorder(null);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0, 0));

        tableFinished.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Transaction", "Username", "Product", "Level", "Designer", "Due On", "Amount", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tableFinished);
        if (tableFinished.getColumnModel().getColumnCount() > 0) {
            tableFinished.getColumnModel().getColumn(0).setResizable(false);
            tableFinished.getColumnModel().getColumn(1).setResizable(false);
            tableFinished.getColumnModel().getColumn(2).setResizable(false);
            tableFinished.getColumnModel().getColumn(3).setResizable(false);
            tableFinished.getColumnModel().getColumn(4).setResizable(false);
            tableFinished.getColumnModel().getColumn(5).setResizable(false);
            tableFinished.getColumnModel().getColumn(6).setResizable(false);
            tableFinished.getColumnModel().getColumn(6).setPreferredWidth(10);
            tableFinished.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel6);

        materialTabbed1.addTab("Finished", jScrollPane5);

        jScrollPane6.setBorder(null);
        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel7.setBackground(new java.awt.Color(0,0,0,0));

        tableCancelled.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Transaction", "Username", "Product", "Level", "Designer", "Due On", "Amount", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(tableCancelled);
        if (tableCancelled.getColumnModel().getColumnCount() > 0) {
            tableCancelled.getColumnModel().getColumn(0).setResizable(false);
            tableCancelled.getColumnModel().getColumn(1).setResizable(false);
            tableCancelled.getColumnModel().getColumn(2).setResizable(false);
            tableCancelled.getColumnModel().getColumn(3).setResizable(false);
            tableCancelled.getColumnModel().getColumn(4).setResizable(false);
            tableCancelled.getColumnModel().getColumn(5).setResizable(false);
            tableCancelled.getColumnModel().getColumn(6).setResizable(false);
            tableCancelled.getColumnModel().getColumn(6).setPreferredWidth(10);
            tableCancelled.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(jPanel7);

        materialTabbed1.addTab("Cancelled", jScrollPane6);

        jScrollPane13.setBorder(null);
        jScrollPane13.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane13.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel9.setBackground(new java.awt.Color(0,0,0,0));

        tableLate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Transaction", "Username", "Product", "Level", "Designer", "Due On", "Amount", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane14.setViewportView(tableLate);
        if (tableLate.getColumnModel().getColumnCount() > 0) {
            tableLate.getColumnModel().getColumn(0).setResizable(false);
            tableLate.getColumnModel().getColumn(1).setResizable(false);
            tableLate.getColumnModel().getColumn(2).setResizable(false);
            tableLate.getColumnModel().getColumn(3).setResizable(false);
            tableLate.getColumnModel().getColumn(4).setResizable(false);
            tableLate.getColumnModel().getColumn(5).setResizable(false);
            tableLate.getColumnModel().getColumn(6).setResizable(false);
            tableLate.getColumnModel().getColumn(6).setPreferredWidth(10);
            tableLate.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jScrollPane13.setViewportView(jPanel9);

        materialTabbed1.addTab("Late", jScrollPane13);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonDash1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addComponent(buttonDash1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(materialTabbed1, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private User.SeeOrder.Swing.ButtonDash buttonDash1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private User.SeeOrder.Swing.MaterialTabbed materialTabbed1;
    private User.SeeOrder.Swing.RoundPanel roundPanel1;
    private User.SeeOrder.Swing.Table tableActive;
    private User.SeeOrder.Swing.Table tableCancelled;
    private User.SeeOrder.Swing.Table tableFinished;
    private User.SeeOrder.Swing.Table tableLate;
    private User.SeeOrder.Swing.Table tablePending;
    private User.SeeOrder.Swing.Table tableWaiting;
    // End of variables declaration//GEN-END:variables
}
