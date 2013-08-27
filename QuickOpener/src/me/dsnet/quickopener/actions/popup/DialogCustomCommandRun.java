/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dsnet.quickopener.actions.popup;

import com.sessonad.oscommands.commands.Commands;
import me.dsnet.quickopener.PathFinder;
import me.dsnet.quickopener.QuickMessages;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.ImageUtilities;

/**
 *
 * @author SessonaD
 */
public class DialogCustomCommandRun extends javax.swing.JDialog {

    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    public static final int CHARSNUMBER = 80;
    private String command;
    private final String cmdos=Commands.getPlatform().getOperatingSystem().getShellPrefix();
    private final String currentFile=PathFinder.getActivePath(null,false);
    private final String currentFolder = PathFinder.getActivePath(null,true);
    private final String relativeFile = PathFinder.getRelativeActivePath(null,false);
    private final String relativeFolder = PathFinder.getRelativeActivePath(null,true);

    /**
     * Creates new form DialogCustomCommand
     */
    public DialogCustomCommandRun(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jLabel4.setText("(for your OS is: \'"+cmdos+"\')");
        jTable2.setModel(new PropertyTableModel("command"));
        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(400);
        jTable2.getColumnModel().getColumn(0).setMinWidth(100);
        cmdTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {checkParameters();}            
            @Override
            public void removeUpdate(DocumentEvent e) {checkParameters();}
            @Override
            public void insertUpdate(DocumentEvent e) {checkParameters();}
        });
        
        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
    }
    
    private void checkParameters() {
        String text=cmdTextField.getText();
        boolean p1 = text.contains("${param1}");
        p1label.setEnabled(p1);
        p1text.setEnabled(p1);
        fileParamButton1.setEnabled(p1);
        if(!p1)p1text.setText("");
        boolean p2 = text.contains("${param2}");
        p2label.setEnabled(p2);
        p2text.setEnabled(p2);
        fileParamButton2.setEnabled(p2);
        if(!p2)p2text.setText("");
        boolean p3 = text.contains("${param3}");
        p3label.setEnabled(p3);
        p3text.setEnabled(p3);
        fileParamButton3.setEnabled(p3);
        if(!p3)p3text.setText("");
        boolean p4 = text.contains("${param4}");
        p4label.setEnabled(p4);
        p4text.setEnabled(p4);
        fileParamButton4.setEnabled(p4);
        if(!p4)p4text.setText("");
        boolean p5 = text.contains("${param5}");
        p5label.setEnabled(p5);
        p5text.setEnabled(p5);
        fileParamButton5.setEnabled(p5);
        if(!p5)p5text.setText("");
        boolean p6 = text.contains("${param6}");
        p6label.setEnabled(p6);
        p6text.setEnabled(p6);
        fileParamButton6.setEnabled(p6);
        if(!p6)p6text.setText("");

    }
    
    public void setCommand(){
        command = cmdTextField.getText();
        if(command.contains("${param1}"))command=command.replace("${param1}", p1text.getText());
        if(command.contains("${param2}"))command=command.replace("${param2}", p2text.getText());
        if(command.contains("${param3}"))command=command.replace("${param3}", p3text.getText());
        if(command.contains("${param4}"))command=command.replace("${param4}", p4text.getText());
        if(command.contains("${param5}"))command=command.replace("${param5}", p5text.getText());
        if(command.contains("${param6}"))command=command.replace("${param6}", p6text.getText()); 
        if(command.contains("${currentFile}") && currentFile!=null && !currentFile.isEmpty())command=command.replace("${currentFile}", currentFile);  
        if(command.contains("${currentFolder}") && currentFolder!=null && !currentFolder.isEmpty())command=command.replace("${currentFolder}", currentFolder); 
        if(command.contains("${relativeFile}") && relativeFile!=null && !relativeFile.isEmpty())command=command.replace("${relativeFile}", relativeFile);  
        if(command.contains("${relativeFolder}") && relativeFolder!=null && !relativeFolder.isEmpty())command=command.replace("${relativeFolder}", relativeFolder);  
    }

    public String getCommand(){
        return command;
    }
    
    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        cmdTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        p1label = new javax.swing.JLabel();
        p2label = new javax.swing.JLabel();
        p3label = new javax.swing.JLabel();
        p1text = new javax.swing.JTextField();
        p2text = new javax.swing.JTextField();
        p3text = new javax.swing.JTextField();
        fileParamButton1 = new javax.swing.JButton();
        fileParamButton2 = new javax.swing.JButton();
        fileParamButton3 = new javax.swing.JButton();
        fileParamButton4 = new javax.swing.JButton();
        p4text = new javax.swing.JTextField();
        p4label = new javax.swing.JLabel();
        p5label = new javax.swing.JLabel();
        p5text = new javax.swing.JTextField();
        fileParamButton5 = new javax.swing.JButton();
        p6label = new javax.swing.JLabel();
        p6text = new javax.swing.JTextField();
        fileParamButton6 = new javax.swing.JButton();

        setTitle(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.title")); // NOI18N
        setIconImage(ImageUtilities.loadImage("me/dsnet/quickopener/icons/run.png"));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        okButton.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.okButton.text")); // NOI18N
        okButton.setFocusPainted(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, cmdTextField, org.jdesktop.beansbinding.ELProperty.create("${not empty text}"), okButton, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.cancelButton.text")); // NOI18N
        cancelButton.setFocusPainted(false);
        cancelButton.setRequestFocusEnabled(false);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        cmdTextField.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.cmdTextField.text")); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.jLabel1.text")); // NOI18N
        jLabel1.setRequestFocusEnabled(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/me/dsnet/quickopener/icons/run48.png"))); // NOI18N
        jLabel2.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.jLabel2.text")); // NOI18N

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.setFillsViewportHeight(true);
        jTable2.setFocusable(false);
        jTable2.setRequestFocusEnabled(false);
        jTable2.setShowVerticalLines(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel3.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.jLabel3.text")); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/me/dsnet/quickopener/icons/help.png"))); // NOI18N
        jLabel10.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.jLabel10.text")); // NOI18N
        jLabel10.setToolTipText("<html><a href=\"#\">Click on any command to set the input box.</a><br/>\n<br/>\nYou can customize the your preferred commands in:<br/>\n<a href=\"#\">Tools > Options > Miscellaneous > QuickOpener</a>\n</html>"); // NOI18N

        jCheckBox2.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.jCheckBox2.text")); // NOI18N
        jCheckBox2.setFocusPainted(false);
        jCheckBox2.setFocusable(false);
        jCheckBox2.setRequestFocusEnabled(false);
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jLabel4.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.jLabel4.text")); // NOI18N

        jCheckBox1.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.jCheckBox1.text")); // NOI18N
        jCheckBox1.setFocusPainted(false);
        jCheckBox1.setFocusable(false);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox3.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.jCheckBox3.text")); // NOI18N
        jCheckBox3.setFocusPainted(false);
        jCheckBox3.setFocusable(false);
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        p1label.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        p1label.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.p1label.text")); // NOI18N
        p1label.setEnabled(false);

        p2label.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        p2label.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.p2label.text")); // NOI18N
        p2label.setEnabled(false);

        p3label.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        p3label.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.p3label.text")); // NOI18N
        p3label.setEnabled(false);

        p1text.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.p1text.text")); // NOI18N
        p1text.setEnabled(false);
        p1text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p1textActionPerformed(evt);
            }
        });

        p2text.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.p2text.text")); // NOI18N
        p2text.setEnabled(false);
        p2text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p2textActionPerformed(evt);
            }
        });

        p3text.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.p3text.text")); // NOI18N
        p3text.setEnabled(false);

        fileParamButton1.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        fileParamButton1.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.fileParamButton1.text")); // NOI18N
        fileParamButton1.setToolTipText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.fileParamButton1.toolTipText")); // NOI18N
        fileParamButton1.setEnabled(false);
        fileParamButton1.setFocusPainted(false);
        fileParamButton1.setRequestFocusEnabled(false);
        fileParamButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileParamButton1ActionPerformed(evt);
            }
        });

        fileParamButton2.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        fileParamButton2.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.fileParamButton2.text")); // NOI18N
        fileParamButton2.setToolTipText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.fileParamButton2.toolTipText")); // NOI18N
        fileParamButton2.setEnabled(false);
        fileParamButton2.setFocusPainted(false);
        fileParamButton2.setRequestFocusEnabled(false);
        fileParamButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileParamButton2ActionPerformed(evt);
            }
        });

        fileParamButton3.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        fileParamButton3.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.fileParamButton3.text")); // NOI18N
        fileParamButton3.setToolTipText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.fileParamButton3.toolTipText")); // NOI18N
        fileParamButton3.setEnabled(false);
        fileParamButton3.setFocusPainted(false);
        fileParamButton3.setRequestFocusEnabled(false);
        fileParamButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileParamButton3ActionPerformed(evt);
            }
        });

        fileParamButton4.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        fileParamButton4.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.fileParamButton4.text")); // NOI18N
        fileParamButton4.setToolTipText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.fileParamButton4.toolTipText")); // NOI18N
        fileParamButton4.setEnabled(false);
        fileParamButton4.setFocusPainted(false);
        fileParamButton4.setRequestFocusEnabled(false);
        fileParamButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileParamButton4ActionPerformed(evt);
            }
        });

        p4text.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.p4text.text")); // NOI18N
        p4text.setEnabled(false);

        p4label.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        p4label.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.p4label.text")); // NOI18N
        p4label.setEnabled(false);

        p5label.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        p5label.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.p5label.text")); // NOI18N
        p5label.setEnabled(false);

        p5text.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.p5text.text")); // NOI18N
        p5text.setEnabled(false);

        fileParamButton5.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        fileParamButton5.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.fileParamButton5.text")); // NOI18N
        fileParamButton5.setToolTipText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.fileParamButton5.toolTipText")); // NOI18N
        fileParamButton5.setEnabled(false);
        fileParamButton5.setFocusPainted(false);
        fileParamButton5.setRequestFocusEnabled(false);
        fileParamButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileParamButton5ActionPerformed(evt);
            }
        });

        p6label.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        p6label.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.p6label.text")); // NOI18N
        p6label.setEnabled(false);

        p6text.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.p6text.text")); // NOI18N
        p6text.setEnabled(false);

        fileParamButton6.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        fileParamButton6.setText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.fileParamButton6.text")); // NOI18N
        fileParamButton6.setToolTipText(org.openide.util.NbBundle.getMessage(DialogCustomCommandRun.class, "DialogCustomCommandRun.fileParamButton6.toolTipText")); // NOI18N
        fileParamButton6.setEnabled(false);
        fileParamButton6.setFocusPainted(false);
        fileParamButton6.setRequestFocusEnabled(false);
        fileParamButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileParamButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(p3label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p3text, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileParamButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p1label)
                            .addComponent(p2label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p1text, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p2text, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fileParamButton1)
                            .addComponent(fileParamButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(p4label, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(p5label, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(p6label, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(p4text, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileParamButton4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(p5text, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileParamButton5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(p6text, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileParamButton6)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p4text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileParamButton4)
                    .addComponent(p4label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileParamButton5)
                    .addComponent(p5text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p5label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p6label)
                    .addComponent(p6text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileParamButton6))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p1text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p1label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fileParamButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p2text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileParamButton2)
                    .addComponent(p2label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p3text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileParamButton3)
                    .addComponent(p3label)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdTextField)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(0, 0, 0))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                .addComponent(okButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(okButton)
                    .addComponent(cancelButton)
                    .addComponent(jLabel10))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                .addGap(350, 350, 350)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox3, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.Alignment.CENTER))
                .addGap(12, 12, 12))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cancelButton, jCheckBox1, jCheckBox2, jCheckBox3, jLabel10, jLabel4, okButton});

        getRootPane().setDefaultButton(okButton);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        setCommand();
        String msg=QuickMessages.CONFIRM_COMMAND_PREFIX+getCommand()+QuickMessages.CONFIRM_COMMAND_SUFFIX;
        NotifyDescriptor d = new NotifyDescriptor.Confirmation(msg,"Confirm",NotifyDescriptor.OK_CANCEL_OPTION);
        if(NotifyDescriptor.OK_OPTION == DialogDisplayer.getDefault().notify(d)){
            doClose(RET_OK);
        }
    }//GEN-LAST:event_okButtonActionPerformed
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        if (evt.getClickCount() == 1) {
            final int thisrow=jTable2.getSelectedRow();
            if (-1 != thisrow){
                final int row = jTable2.getRowSorter().convertRowIndexToModel(thisrow);
                String path = (String) jTable2.getModel().getValueAt(row, 1);
                cmdTextField.setText(path);
            }
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void p1textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p1textActionPerformed
        
    }//GEN-LAST:event_p1textActionPerformed

    private void p2textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p2textActionPerformed
        
    }//GEN-LAST:event_p2textActionPerformed

    private void fileParamButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileParamButton1ActionPerformed
        DialogueFileSelector dialogue = new DialogueFileSelector(null, true);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int x = (screenSize.width - dialogue.getWidth()) / 2;
        final int y = (screenSize.height - dialogue.getHeight()) / 2;
        dialogue.setLocation(x, y);
        dialogue.setVisible(true);

        String userCommand = (dialogue.getReturnStatus()==DialogCustomTerminal.RET_OK)?dialogue.getCommand():null;
        p1text.setText(userCommand);
    }//GEN-LAST:event_fileParamButton1ActionPerformed

    private void fileParamButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileParamButton2ActionPerformed
        DialogueFileSelector dialogue = new DialogueFileSelector(null, true);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int x = (screenSize.width - dialogue.getWidth()) / 2;
        final int y = (screenSize.height - dialogue.getHeight()) / 2;
        dialogue.setLocation(x, y);
        dialogue.setVisible(true);

        String userCommand = (dialogue.getReturnStatus()==DialogCustomTerminal.RET_OK)?dialogue.getCommand():null;
        p2text.setText(userCommand);
    }//GEN-LAST:event_fileParamButton2ActionPerformed

    private void fileParamButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileParamButton3ActionPerformed
        DialogueFileSelector dialogue = new DialogueFileSelector(null, true);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int x = (screenSize.width - dialogue.getWidth()) / 2;
        final int y = (screenSize.height - dialogue.getHeight()) / 2;
        dialogue.setLocation(x, y);
        dialogue.setVisible(true);

        String userCommand = (dialogue.getReturnStatus()==DialogCustomTerminal.RET_OK)?dialogue.getCommand():null;
        p3text.setText(userCommand);
    }//GEN-LAST:event_fileParamButton3ActionPerformed

    private void fileParamButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileParamButton4ActionPerformed
        DialogueFileSelector dialogue = new DialogueFileSelector(null, true);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int x = (screenSize.width - dialogue.getWidth()) / 2;
        final int y = (screenSize.height - dialogue.getHeight()) / 2;
        dialogue.setLocation(x, y);
        dialogue.setVisible(true);

        String userCommand = (dialogue.getReturnStatus()==DialogCustomTerminal.RET_OK)?dialogue.getCommand():null;
        p4text.setText(userCommand);
    }//GEN-LAST:event_fileParamButton4ActionPerformed

    private void fileParamButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileParamButton5ActionPerformed
        DialogueFileSelector dialogue = new DialogueFileSelector(null, true);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int x = (screenSize.width - dialogue.getWidth()) / 2;
        final int y = (screenSize.height - dialogue.getHeight()) / 2;
        dialogue.setLocation(x, y);
        dialogue.setVisible(true);

        String userCommand = (dialogue.getReturnStatus()==DialogCustomTerminal.RET_OK)?dialogue.getCommand():null;
        p5text.setText(userCommand);
    }//GEN-LAST:event_fileParamButton5ActionPerformed

    private void fileParamButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileParamButton6ActionPerformed
        DialogueFileSelector dialogue = new DialogueFileSelector(null, true);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int x = (screenSize.width - dialogue.getWidth()) / 2;
        final int y = (screenSize.height - dialogue.getHeight()) / 2;
        dialogue.setLocation(x, y);
        dialogue.setVisible(true);

        String userCommand = (dialogue.getReturnStatus()==DialogCustomTerminal.RET_OK)?dialogue.getCommand():null;
        p6text.setText(userCommand);
    }//GEN-LAST:event_fileParamButton6ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if (cmdos==null) return;
        boolean value = jCheckBox2.isSelected();
        String text= cmdTextField.getText();
        if(value && text!=null && !text.startsWith(cmdos)){
            text=cmdos + text;
        }else if(!value && text!=null && text.startsWith(cmdos)){
            text=text.replaceAll(cmdos, "");
        }
        cmdTextField.setText(text);
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        boolean value = jCheckBox1.isSelected();
        String text= cmdTextField.getText();
        if(value && !text.contains("${currentFile}"))
            text=text + " ${currentFile}";  
        else if(!value && text.contains("${currentFile}"))
            text=text.replaceAll("\\$\\{currentFile\\}", ""); 
        cmdTextField.setText(text);
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        boolean value = jCheckBox3.isSelected();
        String text= cmdTextField.getText();
        if(value && !text.contains("${currentFolder}"))
            text=text + " ${currentFolder}";  
        else if(!value && text.contains("${currentFolder}"))
            text=text.replaceAll("\\$\\{currentFolder\\}", "");  
        cmdTextField.setText(text);
    }//GEN-LAST:event_jCheckBox3ActionPerformed
    
    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DialogCustomCommandRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogCustomCommandRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogCustomCommandRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogCustomCommandRun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                DialogCustomCommandRun dialog = new DialogCustomCommandRun(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField cmdTextField;
    private javax.swing.JButton fileParamButton1;
    private javax.swing.JButton fileParamButton2;
    private javax.swing.JButton fileParamButton3;
    private javax.swing.JButton fileParamButton4;
    private javax.swing.JButton fileParamButton5;
    private javax.swing.JButton fileParamButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel p1label;
    private javax.swing.JTextField p1text;
    private javax.swing.JLabel p2label;
    private javax.swing.JTextField p2text;
    private javax.swing.JLabel p3label;
    private javax.swing.JTextField p3text;
    private javax.swing.JLabel p4label;
    private javax.swing.JTextField p4text;
    private javax.swing.JLabel p5label;
    private javax.swing.JTextField p5text;
    private javax.swing.JLabel p6label;
    private javax.swing.JTextField p6text;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
    private int returnStatus = RET_CANCEL;
}
