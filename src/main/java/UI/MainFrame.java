package UI;

import BLL.CourseBLL;
import BLL.CourseInstructorBLL;
import BLL.CourseOnSiteBLL;
import BLL.CourseOnlineBLL;
import BLL.DepartmentBLL;
import BLL.PersonBLL;
import BLL.StudentGradeBLL;
import DAL.CourseInstructorDAL;
import DAL.entities.Course;
import DAL.entities.CourseInstructor;
import DAL.entities.CourseOnSite;
import DAL.entities.CourseOnline;
import DAL.entities.Department;
import DAL.entities.Person;
import DAL.entities.StudentGrade;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author lamquoc
 */
public class MainFrame extends javax.swing.JFrame {

    CardLayout cardLayout;
    CourseBLL courseBLL = new CourseBLL();
    CourseOnlineBLL courseOnlineBLL = new CourseOnlineBLL();
    CourseOnSiteBLL courseOnsiteBLL = new CourseOnSiteBLL();
    CourseInstructorBLL courseInstructorBLL = new CourseInstructorBLL();
    DepartmentBLL departmentBLL = new DepartmentBLL();
    PersonBLL personBLL = new PersonBLL();
    private int mouseX, mouseY;
    private int totalPage;

//Quản lí kết quả khóa học   
    private StudentGradeBLL sgBLL;
    private DefaultTableModel model3;

    /**
     * Creates new form MainFrame
     *
     * @throws java.sql.SQLException
     */
    public MainFrame() throws SQLException {
        initComponents();
        cardLayout = (CardLayout) (Content.getLayout());

        setIcons();
        hiddenLabel();

        fillData();

        OnlineCourse_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        OnsiteCourse_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Instructor_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        OnlineCourse_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    if (OnlineCourse_table.getSelectedRow() != -1) {
                        getKHDataFromRow(OnlineCourse_table);
                        Online_add_btn.setEnabled(false);
                    }
                }
            }
        });

        OnsiteCourse_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    if (OnsiteCourse_table.getSelectedRow() != -1) {
                        getKHDataFromRow(OnsiteCourse_table);
                        Onsite_add_btn.setEnabled(false);
                    }
                }
            }
        });

        Instructor_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    if (Instructor_table.getSelectedRow() != -1) {
                        getPCDataFromRow(Instructor_table);
                        Instructor_add_btn.setEnabled(false);
                    }
                }
            }
        });

        jTable3.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        jTable3.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedRow = jTable3.getSelectedRow();
                int selectedColumn = jTable3.getSelectedColumn();
                int rowCount = jTable3.getRowCount();

                // Move to the next row in the same column
                if (selectedRow < rowCount - 1) {
                    jTable3.changeSelection(selectedRow + 1, selectedColumn, false, false);
                    jTable3.editCellAt(selectedRow + 1, selectedColumn);
                    Component editor = jTable3.getEditorComponent();
                    if (editor != null) {
                        editor.requestFocusInWindow();
                    }
                }
                if (selectedRow == rowCount - 1) {
                    int currentPage = Integer.parseInt(KQKH_Page.getText());
                    if (currentPage < totalPage) {
                        KQKH_Page.setText(String.valueOf(currentPage + 1));
                        jTable3.changeSelection(0, selectedColumn, false, false);
                        jTable3.editCellAt(0, selectedColumn);
                        Component editor = jTable3.getEditorComponent();
                        if (editor != null) {
                            editor.requestFocusInWindow();
                        }
                        // Thực hiện load dữ liệu cho trang mới ở đây
                    }
                }

            }
        });

        ChangePage();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        LeftMenu = new javax.swing.JPanel();
        Panel_QLKH = new javax.swing.JPanel();
        Label_QLKH_icon = new javax.swing.JLabel();
        Label_QLKH = new javax.swing.JLabel();
        Panel_QLPC = new javax.swing.JPanel();
        Label_QLPC_icon = new javax.swing.JLabel();
        Label_QLPC = new javax.swing.JLabel();
        Panel_QLKQ = new javax.swing.JPanel();
        Label_QLKQ_icon = new javax.swing.JLabel();
        Label_QLKQ = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        CloseBtn = new javax.swing.JLabel();
        Content = new javax.swing.JPanel();
        QLKH = new javax.swing.JTabbedPane();
        OnlineCourse = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        OnlineCourse_table = new javax.swing.JTable();
        Online_search = new javax.swing.JTextField();
        Online_search_btn = new javax.swing.JButton();
        Online_previousPage = new javax.swing.JButton();
        Online_pagination = new javax.swing.JLabel();
        Online_nextPage = new javax.swing.JButton();
        Online_update_btn = new javax.swing.JButton();
        Online_add_btn = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        credits = new javax.swing.JLabel();
        Online_credits_tf = new javax.swing.JTextField();
        Online_title_tf = new javax.swing.JTextField();
        department = new javax.swing.JLabel();
        Online_delete_btn = new javax.swing.JButton();
        Online_clear_btn = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        url = new javax.swing.JLabel();
        url_tf = new javax.swing.JTextField();
        Department_Cbb1 = new javax.swing.JComboBox<>();
        Course_id1 = new javax.swing.JLabel();
        Online_page = new javax.swing.JTextField();
        Statistic_Online = new javax.swing.JButton();
        OnsiteCourse = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        OnsiteCourse_table = new javax.swing.JTable();
        Onsite_search = new javax.swing.JTextField();
        Onsite_search_btn = new javax.swing.JButton();
        Onsite_previousPage = new javax.swing.JButton();
        Onsite_pagination = new javax.swing.JLabel();
        Onsite_nextPage = new javax.swing.JButton();
        Onsite_update_btn = new javax.swing.JButton();
        Onsite_title = new javax.swing.JLabel();
        Onsite_credits = new javax.swing.JLabel();
        Onsite_add_btn = new javax.swing.JButton();
        Onsite_credits_tf = new javax.swing.JTextField();
        Onsite_title_tf = new javax.swing.JTextField();
        Onsite_department = new javax.swing.JLabel();
        Onsite_delete_btn = new javax.swing.JButton();
        Onsite_clear_btn = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        location = new javax.swing.JLabel();
        location_tf = new javax.swing.JTextField();
        days = new javax.swing.JLabel();
        days_tf = new javax.swing.JTextField();
        time = new javax.swing.JLabel();
        Department_Cbb2 = new javax.swing.JComboBox<>();
        Onsite_courseID = new javax.swing.JLabel();
        Onsite_page = new javax.swing.JTextField();
        Date datetime = new Date();

        SpinnerDateModel sm = new SpinnerDateModel(datetime, null, null, Calendar.HOUR_OF_DAY);
        jSpinner1 = new javax.swing.JSpinner(sm);
        Statistic_Onsite = new javax.swing.JButton();
        QLPC = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Instructor_table = new javax.swing.JTable();
        instructor_delete = new javax.swing.JButton();
        Search3 = new javax.swing.JTextField();
        CourseInstructor_search_btn = new javax.swing.JButton();
        PC_Pagination = new javax.swing.JLabel();
        PC_PreviousPage = new javax.swing.JButton();
        Instructor_update = new javax.swing.JButton();
        PC_NextPage = new javax.swing.JButton();
        Instructor_add_btn = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        PC_Page = new javax.swing.JTextField();
        PC_clear_btn = new javax.swing.JButton();
        title1 = new javax.swing.JLabel();
        Course_title_Cbb = new javax.swing.JComboBox<>();
        title2 = new javax.swing.JLabel();
        Instructor_Cbb = new javax.swing.JComboBox<>();
        Course_id = new javax.swing.JLabel();
        Instructor_id = new javax.swing.JLabel();
        Statistic_Instructor = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        QLKQKH = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtName3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton15 = new javax.swing.JButton();
        lbStudentID = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbCourseID = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        lbEnrollmentID = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbTitle = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        KQKH_Pagination = new javax.swing.JLabel();
        txtSearch3 = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        cbSelectCourse3 = new javax.swing.JComboBox<>();
        txtGrade = new javax.swing.JTextField();
        KQKH_Page = new javax.swing.JTextField();
        Statistic_StudentGrade = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        Background.setBackground(new java.awt.Color(255, 255, 255));

        LeftMenu.setBackground(new java.awt.Color(236, 88, 88));
        LeftMenu.setMinimumSize(new java.awt.Dimension(0, 10));

        Panel_QLKH.setBackground(new java.awt.Color(255, 120, 108));
        Panel_QLKH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Panel_QLKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Panel_QLKHMouseClicked(evt);
            }
        });

        Label_QLKH_icon.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        Label_QLKH_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        Label_QLKH.setFont(new java.awt.Font("Silom", 1, 18)); // NOI18N
        Label_QLKH.setForeground(new java.awt.Color(255, 227, 215));
        Label_QLKH.setText("QUẢN LÍ KHOÁ HỌC");

        javax.swing.GroupLayout Panel_QLKHLayout = new javax.swing.GroupLayout(Panel_QLKH);
        Panel_QLKH.setLayout(Panel_QLKHLayout);
        Panel_QLKHLayout.setHorizontalGroup(
            Panel_QLKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_QLKHLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(Label_QLKH_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(Label_QLKH, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Panel_QLKHLayout.setVerticalGroup(
            Panel_QLKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Label_QLKH_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(Panel_QLKHLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(Label_QLKH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Panel_QLPC.setBackground(new java.awt.Color(236, 88, 88));
        Panel_QLPC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Panel_QLPC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Panel_QLPCMouseClicked(evt);
            }
        });
        Panel_QLPC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Label_QLPC_icon.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        Label_QLPC_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Panel_QLPC.add(Label_QLPC_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 60));

        Label_QLPC.setFont(new java.awt.Font("Silom", 1, 18)); // NOI18N
        Label_QLPC.setForeground(new java.awt.Color(255, 227, 215));
        Label_QLPC.setText("QUẢN LÍ PHÂN CÔNG");
        Panel_QLPC.add(Label_QLPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 190, 40));

        Panel_QLKQ.setBackground(new java.awt.Color(236, 88, 88));
        Panel_QLKQ.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Panel_QLKQ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Panel_QLKQMouseClicked(evt);
            }
        });
        Panel_QLKQ.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Label_QLKQ_icon.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        Label_QLKQ_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Panel_QLKQ.add(Label_QLKQ_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 60));

        Label_QLKQ.setFont(new java.awt.Font("Silom", 1, 18)); // NOI18N
        Label_QLKQ.setForeground(new java.awt.Color(255, 227, 215));
        Label_QLKQ.setText("QUẢN LÍ KẾT QUẢ KH");
        Panel_QLKQ.add(Label_QLKQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 190, 40));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Kannada MN", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PROJECT1");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setMinimumSize(new java.awt.Dimension(50, 20));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 5));
        jSeparator1.setRequestFocusEnabled(false);
        jSeparator1.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout LeftMenuLayout = new javax.swing.GroupLayout(LeftMenu);
        LeftMenu.setLayout(LeftMenuLayout);
        LeftMenuLayout.setHorizontalGroup(
            LeftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_QLKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Panel_QLPC, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addComponent(Panel_QLKQ, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addGroup(LeftMenuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(LeftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LeftMenuLayout.setVerticalGroup(
            LeftMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftMenuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(Panel_QLKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_QLPC, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_QLKQ, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CloseBtn.setBackground(new java.awt.Color(255, 255, 255));
        CloseBtn.setFont(new java.awt.Font("Kannada MN", 1, 24)); // NOI18N
        CloseBtn.setForeground(new java.awt.Color(237, 84, 87));
        CloseBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CloseBtn.setText("X");
        CloseBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CloseBtnMouseClicked(evt);
            }
        });

        Content.setLayout(new java.awt.CardLayout());

        QLKH.setBackground(new java.awt.Color(255, 255, 255));
        QLKH.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                QLKHStateChanged(evt);
            }
        });

        OnlineCourse.setBackground(new java.awt.Color(255, 255, 255));

        OnlineCourse_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CourseID", "Title", "Credit", "Department", "URL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(OnlineCourse_table);
        if (OnlineCourse_table.getColumnModel().getColumnCount() > 0) {
            OnlineCourse_table.getColumnModel().getColumn(0).setMaxWidth(80);
            OnlineCourse_table.getColumnModel().getColumn(2).setMaxWidth(50);
            OnlineCourse_table.getColumnModel().getColumn(4).setHeaderValue("URL");
        }

        Online_search_btn.setBorder(null);
        Online_search_btn.setBorderPainted(false);
        Online_search_btn.setContentAreaFilled(false);
        Online_search_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Online_search_btn.setFocusPainted(false);
        Online_search_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Online_search_btnMouseClicked(evt);
            }
        });

        Online_previousPage.setText("<<");
        Online_previousPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Online_previousPageMouseClicked(evt);
            }
        });

        Online_pagination.setText("Pagination");

        Online_nextPage.setText(">>");
        Online_nextPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Online_nextPageMouseClicked(evt);
            }
        });

        Online_update_btn.setBackground(new java.awt.Color(236, 88, 88));
        Online_update_btn.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        Online_update_btn.setForeground(new java.awt.Color(255, 227, 215));
        Online_update_btn.setText("Update");
        Online_update_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Online_update_btnMouseClicked(evt);
            }
        });
        Online_update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Online_update_btnActionPerformed(evt);
            }
        });

        Online_add_btn.setBackground(new java.awt.Color(236, 88, 88));
        Online_add_btn.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        Online_add_btn.setForeground(new java.awt.Color(255, 227, 215));
        Online_add_btn.setText("Add");
        Online_add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Online_add_btnActionPerformed(evt);
            }
        });

        title.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        title.setText("Course Title:");

        credits.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        credits.setText("Credits:");

        Online_title_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Online_title_tfActionPerformed(evt);
            }
        });

        department.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        department.setText("Department:");

        Online_delete_btn.setBackground(new java.awt.Color(236, 88, 88));
        Online_delete_btn.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        Online_delete_btn.setForeground(new java.awt.Color(255, 227, 215));
        Online_delete_btn.setText("Delete");
        Online_delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Online_delete_btnActionPerformed(evt);
            }
        });

        Online_clear_btn.setBackground(new java.awt.Color(236, 88, 88));
        Online_clear_btn.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        Online_clear_btn.setForeground(new java.awt.Color(255, 227, 215));
        Online_clear_btn.setText("Clear");
        Online_clear_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Online_clear_btnMouseClicked(evt);
            }
        });
        Online_clear_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Online_clear_btnActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        url.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        url.setText("Url:");

        Department_Cbb1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select department" }));

        Course_id1.setText("Id");
        Course_id1.setEnabled(false);

        Statistic_Online.setBackground(new java.awt.Color(236, 88, 88));
        Statistic_Online.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Statistic_OnlineMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout OnlineCourseLayout = new javax.swing.GroupLayout(OnlineCourse);
        OnlineCourse.setLayout(OnlineCourseLayout);
        OnlineCourseLayout.setHorizontalGroup(
            OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OnlineCourseLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OnlineCourseLayout.createSequentialGroup()
                        .addComponent(Online_previousPage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Online_page, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Online_pagination)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Online_nextPage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Statistic_Online, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OnlineCourseLayout.createSequentialGroup()
                        .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(OnlineCourseLayout.createSequentialGroup()
                                .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(department)
                                    .addComponent(credits)
                                    .addComponent(title)
                                    .addComponent(url))
                                .addGap(30, 30, 30)
                                .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Online_title_tf)
                                    .addComponent(Online_credits_tf)
                                    .addComponent(Department_Cbb1, 0, 302, Short.MAX_VALUE)
                                    .addComponent(url_tf))
                                .addGap(54, 54, 54))
                            .addGroup(OnlineCourseLayout.createSequentialGroup()
                                .addComponent(Course_id1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OnlineCourseLayout.createSequentialGroup()
                                    .addComponent(Online_add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Online_update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(OnlineCourseLayout.createSequentialGroup()
                                    .addComponent(Online_search, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(Online_search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(OnlineCourseLayout.createSequentialGroup()
                                .addComponent(Online_delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(Online_clear_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(38, 38, 38))
        );
        OnlineCourseLayout.setVerticalGroup(
            OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OnlineCourseLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(OnlineCourseLayout.createSequentialGroup()
                        .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(OnlineCourseLayout.createSequentialGroup()
                                .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(title)
                                    .addComponent(Online_title_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(credits)
                                    .addComponent(Online_credits_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, OnlineCourseLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Online_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Online_search_btn))
                                .addGap(18, 18, 18)
                                .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Online_add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Online_update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(OnlineCourseLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Online_delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Online_clear_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(OnlineCourseLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(department)
                                    .addComponent(Department_Cbb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(url)
                                    .addComponent(url_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(Course_id1))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Statistic_Online, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(OnlineCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Online_pagination, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Online_nextPage)
                        .addComponent(Online_previousPage)
                        .addComponent(Online_page, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        Online_search.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(236, 88, 88)));

        QLKH.addTab("Online Course", OnlineCourse);

        OnsiteCourse.setBackground(new java.awt.Color(255, 255, 255));

        OnsiteCourse_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CourseID", "Title", "Credit", "Department", "Location", "Days", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(OnsiteCourse_table);
        if (OnsiteCourse_table.getColumnModel().getColumnCount() > 0) {
            OnsiteCourse_table.getColumnModel().getColumn(0).setMaxWidth(80);
            OnsiteCourse_table.getColumnModel().getColumn(2).setMaxWidth(50);
            OnsiteCourse_table.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        Onsite_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Onsite_searchActionPerformed(evt);
            }
        });

        Onsite_search_btn.setBorder(null);
        Onsite_search_btn.setBorderPainted(false);
        Onsite_search_btn.setContentAreaFilled(false);
        Onsite_search_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Onsite_search_btn.setFocusPainted(false);
        Onsite_search_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Onsite_search_btnMouseClicked(evt);
            }
        });
        Onsite_search_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Onsite_search_btnActionPerformed(evt);
            }
        });

        Onsite_previousPage.setText("<<");
        Onsite_previousPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Onsite_previousPageMouseClicked(evt);
            }
        });
        Onsite_previousPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Onsite_previousPageActionPerformed(evt);
            }
        });

        Onsite_pagination.setText("Pagination");

        Onsite_nextPage.setText(">>");
        Onsite_nextPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Onsite_nextPageMouseClicked(evt);
            }
        });

        Onsite_update_btn.setBackground(new java.awt.Color(236, 88, 88));
        Onsite_update_btn.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        Onsite_update_btn.setForeground(new java.awt.Color(255, 227, 215));
        Onsite_update_btn.setText("Update");
        Onsite_update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Onsite_update_btnActionPerformed(evt);
            }
        });

        Onsite_title.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        Onsite_title.setText("Course Title:");

        Onsite_credits.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        Onsite_credits.setText("Credits:");

        Onsite_add_btn.setBackground(new java.awt.Color(236, 88, 88));
        Onsite_add_btn.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        Onsite_add_btn.setForeground(new java.awt.Color(255, 227, 215));
        Onsite_add_btn.setText("Add");
        Onsite_add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Onsite_add_btnActionPerformed(evt);
            }
        });

        Onsite_title_tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Onsite_title_tfActionPerformed(evt);
            }
        });

        Onsite_department.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        Onsite_department.setText("Department:");

        Onsite_delete_btn.setBackground(new java.awt.Color(236, 88, 88));
        Onsite_delete_btn.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        Onsite_delete_btn.setForeground(new java.awt.Color(255, 227, 215));
        Onsite_delete_btn.setText("Delete");
        Onsite_delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Onsite_delete_btnActionPerformed(evt);
            }
        });

        Onsite_clear_btn.setBackground(new java.awt.Color(236, 88, 88));
        Onsite_clear_btn.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        Onsite_clear_btn.setForeground(new java.awt.Color(255, 227, 215));
        Onsite_clear_btn.setText("Clear");
        Onsite_clear_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Onsite_clear_btnMouseClicked(evt);
            }
        });
        Onsite_clear_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Onsite_clear_btnActionPerformed(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        location.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        location.setText("Location:");

        days.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        days.setText("Days:");

        time.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        time.setText("Time:");

        Department_Cbb2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select department" }));

        Onsite_courseID.setText("jLabel2");
        Onsite_courseID.setEnabled(false);

        JSpinner.DateEditor de = new JSpinner.DateEditor(jSpinner1, "HH:mm:ss");

        jSpinner1.setEditor(de);

        Statistic_Onsite.setBackground(new java.awt.Color(236, 88, 88));
        Statistic_Onsite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Statistic_OnsiteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout OnsiteCourseLayout = new javax.swing.GroupLayout(OnsiteCourse);
        OnsiteCourse.setLayout(OnsiteCourseLayout);
        OnsiteCourseLayout.setHorizontalGroup(
            OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OnsiteCourseLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OnsiteCourseLayout.createSequentialGroup()
                        .addComponent(Onsite_previousPage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Onsite_page, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Onsite_pagination)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Onsite_nextPage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Statistic_Onsite, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OnsiteCourseLayout.createSequentialGroup()
                        .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Onsite_department)
                            .addComponent(Onsite_credits)
                            .addComponent(Onsite_title)
                            .addComponent(location)
                            .addComponent(days)
                            .addComponent(time))
                        .addGap(30, 30, 30)
                        .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(days_tf, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(location_tf, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Department_Cbb2, javax.swing.GroupLayout.Alignment.LEADING, 0, 300, Short.MAX_VALUE)
                            .addComponent(Onsite_credits_tf, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Onsite_title_tf, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 5, Short.MAX_VALUE)
                        .addGap(52, 52, 52)
                        .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(OnsiteCourseLayout.createSequentialGroup()
                                    .addComponent(Onsite_add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Onsite_update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(OnsiteCourseLayout.createSequentialGroup()
                                    .addComponent(Onsite_search, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(Onsite_search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(OnsiteCourseLayout.createSequentialGroup()
                                .addComponent(Onsite_delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(Onsite_clear_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Onsite_courseID))))
                .addGap(38, 38, 38))
        );
        OnsiteCourseLayout.setVerticalGroup(
            OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OnsiteCourseLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(OnsiteCourseLayout.createSequentialGroup()
                        .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(OnsiteCourseLayout.createSequentialGroup()
                                .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Onsite_title)
                                    .addComponent(Onsite_title_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Onsite_credits)
                                    .addComponent(Onsite_credits_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, OnsiteCourseLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Onsite_search)
                                    .addComponent(Onsite_search_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Onsite_add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Onsite_update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(OnsiteCourseLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Onsite_delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Onsite_clear_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                                .addComponent(Onsite_courseID)
                                .addGap(12, 12, 12))
                            .addGroup(OnsiteCourseLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Onsite_department)
                                    .addComponent(Department_Cbb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(location)
                                    .addComponent(location_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(days)
                                    .addComponent(days_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(time)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(OnsiteCourseLayout.createSequentialGroup()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(OnsiteCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Onsite_pagination, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Onsite_nextPage)
                        .addComponent(Onsite_previousPage)
                        .addComponent(Onsite_page, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Statistic_Onsite, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        Online_search.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(236, 88, 88)));

        QLKH.addTab("Onsite Course", OnsiteCourse);

        Content.add(QLKH, "card1");

        QLPC.setBackground(new java.awt.Color(255, 255, 255));

        Instructor_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CourseID", "Title", "InstructorID", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(Instructor_table);
        if (Instructor_table.getColumnModel().getColumnCount() > 0) {
            Instructor_table.getColumnModel().getColumn(0).setMaxWidth(80);
            Instructor_table.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        instructor_delete.setBackground(new java.awt.Color(236, 88, 88));
        instructor_delete.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        instructor_delete.setForeground(new java.awt.Color(255, 227, 215));
        instructor_delete.setText("Delete");
        instructor_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                instructor_deleteMouseClicked(evt);
            }
        });

        CourseInstructor_search_btn.setBorder(null);
        CourseInstructor_search_btn.setBorderPainted(false);
        CourseInstructor_search_btn.setContentAreaFilled(false);
        CourseInstructor_search_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CourseInstructor_search_btn.setFocusPainted(false);
        CourseInstructor_search_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CourseInstructor_search_btnMouseClicked(evt);
            }
        });

        PC_Pagination.setText("Pagination");

        PC_PreviousPage.setText("<<");
        PC_PreviousPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PC_PreviousPageMouseClicked(evt);
            }
        });

        Instructor_update.setBackground(new java.awt.Color(236, 88, 88));
        Instructor_update.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        Instructor_update.setForeground(new java.awt.Color(255, 227, 215));
        Instructor_update.setText("Update");
        Instructor_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Instructor_updateMouseClicked(evt);
            }
        });

        PC_NextPage.setText(">>");
        PC_NextPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PC_NextPageMouseClicked(evt);
            }
        });

        Instructor_add_btn.setBackground(new java.awt.Color(236, 88, 88));
        Instructor_add_btn.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        Instructor_add_btn.setForeground(new java.awt.Color(255, 227, 215));
        Instructor_add_btn.setText("Add");
        Instructor_add_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Instructor_add_btnMouseClicked(evt);
            }
        });

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        PC_Page.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PC_PageActionPerformed(evt);
            }
        });

        PC_clear_btn.setBackground(new java.awt.Color(236, 88, 88));
        PC_clear_btn.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        PC_clear_btn.setForeground(new java.awt.Color(255, 227, 215));
        PC_clear_btn.setText("Clear");
        PC_clear_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PC_clear_btnMouseClicked(evt);
            }
        });
        PC_clear_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PC_clear_btnActionPerformed(evt);
            }
        });

        title1.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        title1.setText("Course Title:");

        Course_title_Cbb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select course" }));
        Course_title_Cbb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Course_title_CbbActionPerformed(evt);
            }
        });

        title2.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        title2.setText("Instructor:");

        Instructor_Cbb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select instructor" }));
        Instructor_Cbb.setEnabled(false);
        Instructor_Cbb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Instructor_CbbActionPerformed(evt);
            }
        });

        Course_id.setText("jLabel2");
        Course_id.setEnabled(false);

        Instructor_id.setText("jLabel2");
        Instructor_id.setEnabled(false);

        Statistic_Instructor.setBackground(new java.awt.Color(236, 88, 88));
        Statistic_Instructor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Statistic_InstructorMouseClicked(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 190, 152));

        jLabel7.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 227, 215));
        jLabel7.setText("QUẢN LÝ PHÂN CÔNG");
        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(337, 337, 337)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout QLPCLayout = new javax.swing.GroupLayout(QLPC);
        QLPC.setLayout(QLPCLayout);
        QLPCLayout.setHorizontalGroup(
            QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QLPCLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QLPCLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(QLPCLayout.createSequentialGroup()
                        .addGroup(QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(QLPCLayout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addGroup(QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Course_title_Cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Instructor_Cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(QLPCLayout.createSequentialGroup()
                                .addComponent(Course_id)
                                .addGap(38, 38, 38)
                                .addComponent(Instructor_id))
                            .addComponent(title2)
                            .addComponent(title1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(QLPCLayout.createSequentialGroup()
                                .addComponent(Search3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CourseInstructor_search_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(QLPCLayout.createSequentialGroup()
                                .addComponent(Instructor_add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(Instructor_update, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(QLPCLayout.createSequentialGroup()
                                .addComponent(instructor_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PC_clear_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(52, 52, 52))
                    .addGroup(QLPCLayout.createSequentialGroup()
                        .addComponent(PC_PreviousPage)
                        .addGap(18, 18, 18)
                        .addComponent(PC_Page, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(PC_Pagination)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PC_NextPage)
                        .addGap(449, 449, 449)
                        .addComponent(Statistic_Instructor, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(27, Short.MAX_VALUE))))
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        QLPCLayout.setVerticalGroup(
            QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QLPCLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QLPCLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(title1)
                            .addComponent(Course_title_Cbb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Instructor_Cbb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(QLPCLayout.createSequentialGroup()
                                .addGroup(QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(instructor_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PC_clear_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QLPCLayout.createSequentialGroup()
                                .addGroup(QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Course_id)
                                    .addComponent(Instructor_id))
                                .addGap(27, 27, 27))))
                    .addGroup(QLPCLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(QLPCLayout.createSequentialGroup()
                                .addGroup(QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CourseInstructor_search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Search3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Instructor_add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Instructor_update, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QLPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PC_PreviousPage)
                        .addComponent(PC_Pagination, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PC_NextPage)
                        .addComponent(PC_Page, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Statistic_Instructor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        Online_search.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(236, 88, 88)));

        Content.add(QLPC, "card2");

        QLKQKH.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 190, 152));

        jLabel6.setFont(new java.awt.Font("Menlo", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 227, 215));
        jLabel6.setText("QUẢN LÝ KẾT QUẢ KHOÁ HỌC");
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(275, 275, 275))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel13.setText("StudentID:");

        txtName3.setText("               ");

        jTable3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "EnrollmentID", "CourseID", "Title", "StudentID", "FirstName", "LastName", "Grade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setMaxWidth(90);
            jTable3.getColumnModel().getColumn(6).setMinWidth(120);
        }

        jButton15.setText("<<");
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton15MouseClicked(evt);
            }
        });

        lbStudentID.setText("               ");

        jLabel14.setText("Grade:");

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setText("EnrollmentID:");

        jLabel11.setText("Name:");

        lbCourseID.setText("               ");

        jButton17.setText(">>");
        jButton17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton17MouseClicked(evt);
            }
        });

        lbEnrollmentID.setText("               ");

        jLabel4.setText("CourseID:");

        lbTitle.setText("               ");

        btnAdd.setBackground(new java.awt.Color(236, 88, 88));
        btnAdd.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 227, 215));
        btnAdd.setText("Thêm");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        jLabel8.setText("Title:");

        btnDelete.setBackground(new java.awt.Color(236, 88, 88));
        btnDelete.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 227, 215));
        btnDelete.setText("Xoá");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        KQKH_Pagination.setText("Pagination");

        txtSearch3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearch3KeyReleased(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(236, 88, 88));
        btnEdit.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 227, 215));
        btnEdit.setText("Sửa");
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditMouseClicked(evt);
            }
        });

        cbSelectCourse3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selected Course" }));
        cbSelectCourse3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbSelectCourse3ItemStateChanged(evt);
            }
        });

        KQKH_Page.setText("KQKH_Page");
        KQKH_Page.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KQKH_PageActionPerformed(evt);
            }
        });

        Statistic_StudentGrade.setBackground(new java.awt.Color(236, 88, 88));
        Statistic_StudentGrade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Statistic_StudentGradeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout QLKQKHLayout = new javax.swing.GroupLayout(QLKQKH);
        QLKQKH.setLayout(QLKQKHLayout);
        QLKQKHLayout.setHorizontalGroup(
            QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(QLKQKHLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QLKQKHLayout.createSequentialGroup()
                        .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(QLKQKHLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lbStudentID, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                            .addGroup(QLKQKHLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lbCourseID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(QLKQKHLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lbEnrollmentID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(22, 22, 22)
                        .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(47, 47, 47)
                        .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(txtName3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(QLKQKHLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(QLKQKHLayout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(btnEdit)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete))
                    .addComponent(cbSelectCourse3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearch3))
                .addGap(40, 40, 40))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QLKQKHLayout.createSequentialGroup()
                .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(QLKQKHLayout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(KQKH_Page, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(KQKH_Pagination)
                        .addGap(18, 18, 18)
                        .addComponent(jButton17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Statistic_StudentGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(QLKQKHLayout.createSequentialGroup()
                        .addContainerGap(19, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        QLKQKHLayout.setVerticalGroup(
            QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QLKQKHLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QLKQKHLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(lbEnrollmentID))
                            .addComponent(txtSearch3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbCourseID)
                                .addComponent(jLabel4)
                                .addComponent(jLabel8)
                                .addComponent(lbTitle))
                            .addComponent(cbSelectCourse3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(QLKQKHLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAdd)
                                    .addComponent(btnEdit)
                                    .addComponent(btnDelete)))
                            .addGroup(QLKQKHLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbStudentID)
                                        .addComponent(jLabel11)
                                        .addComponent(txtName3))
                                    .addComponent(jLabel13))
                                .addGap(21, 21, 21)
                                .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jSeparator5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(QLKQKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(KQKH_Pagination, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton15)
                        .addComponent(jButton17)
                        .addComponent(KQKH_Page, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Statistic_StudentGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        Online_search.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(236, 88, 88)));

        Content.add(QLKQKH, "card3");

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(LeftMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(CloseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LeftMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(CloseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseBtnMouseClicked
        System.exit(0);
    }//GEN-LAST:event_CloseBtnMouseClicked

    private void Panel_QLKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Panel_QLKHMouseClicked
        cardLayout.show(Content, "card1");
        Panel_QLKH.setBackground(new Color(255, 120, 108));
        Panel_QLPC.setBackground(new Color(236, 88, 88));
        Panel_QLKQ.setBackground(new Color(236, 88, 88));

    }//GEN-LAST:event_Panel_QLKHMouseClicked

    private void Panel_QLPCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Panel_QLPCMouseClicked
        cardLayout.show(Content, "card2");
        Panel_QLKH.setBackground(new Color(236, 88, 88));
        Panel_QLPC.setBackground(new Color(255, 120, 108));
        Panel_QLKQ.setBackground(new Color(236, 88, 88));
        List<CourseInstructor> list = courseInstructorBLL.getListCourseInstructor(1);
        totalPage = courseInstructorBLL.getListCourseInstructorCount();
        PC_Pagination.setText(" / " + totalPage);
        PC_Page.setText("1");

        loadDataPC(list);

    }//GEN-LAST:event_Panel_QLPCMouseClicked

    private void Panel_QLKQMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Panel_QLKQMouseClicked
        cardLayout.show(Content, "card3");
        sgBLL = new StudentGradeBLL();
//        LoadStudentGradeTable(1);
//        KQKH_Page.setText("1");
        KQKH_Page.setText("1");
        PageKQKH(1);
        Panel_QLKH.setBackground(new Color(236, 88, 88));
        Panel_QLPC.setBackground(new Color(236, 88, 88));
        Panel_QLKQ.setBackground(new Color(255, 120, 108));
    }//GEN-LAST:event_Panel_QLKQMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int x = evt.getXOnScreen() - mouseX;
        int y = evt.getYOnScreen() - mouseY;
        setLocation(x, y);
    }//GEN-LAST:event_formMouseDragged

    private void Online_update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Online_update_btnActionPerformed
        if (OnlineCourse_table.getSelectedRow() != -1) {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật môn học không?",
                    "Xác nhận ", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                String CourseTitle = Online_title_tf.getText();
                int DepartmentID = getIdFromString2((String) Department_Cbb1.getSelectedItem());
                String Url = url_tf.getText();

                int Credits = Integer.parseInt(Online_credits_tf.getText());
                int id = Integer.parseInt(Course_id1.getText());
                if (courseBLL.updateCourse(CourseTitle, Credits, DepartmentID, id, Url)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                    PageKH(1);
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
                }
            } else {
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn khoá học");
        }
    }//GEN-LAST:event_Online_update_btnActionPerformed

    private void Online_add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Online_add_btnActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm môn học không?",
                "Xác nhận ", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            String CourseTitle = Online_title_tf.getText();
            int DepartmentID = getIdFromString2((String) Department_Cbb1.getSelectedItem());
            String Url = url_tf.getText();

            int Credits = Integer.parseInt(Online_credits_tf.getText());
            if (courseBLL.addCourse(CourseTitle, Credits, DepartmentID, Url)) {
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                PageKH(1);
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }
        } else {
            return;
        }
    }//GEN-LAST:event_Online_add_btnActionPerformed

    private void Online_title_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Online_title_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Online_title_tfActionPerformed

    private void Online_delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Online_delete_btnActionPerformed

        if (OnlineCourse_table.getSelectedRow() != -1) {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá môn học không?",
                    "Xác nhận ", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                int id;
                id = Integer.parseInt(Course_id1.getText());
                if (courseBLL.deleteCourse(id)) {
                    JOptionPane.showMessageDialog(null, "Xoá thành công");
                    PageKH(1);
                } else {
                    JOptionPane.showMessageDialog(null, "Xoá thất bại");
                }
            } else {
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn khoá học");
        }
    }//GEN-LAST:event_Online_delete_btnActionPerformed

    private void Online_clear_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Online_clear_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Online_clear_btnActionPerformed

    private void Onsite_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Onsite_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Onsite_searchActionPerformed

    private void Onsite_search_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Onsite_search_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Onsite_search_btnActionPerformed

    private void Onsite_previousPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Onsite_previousPageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Onsite_previousPageActionPerformed

    private void Onsite_update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Onsite_update_btnActionPerformed
        if (OnlineCourse_table.getSelectedRow() != -1) {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật môn học không?",
                    "Xác nhận ", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                String CourseTitle = Onsite_title_tf.getText();
                int DepartmentID = getIdFromString2((String) Department_Cbb2.getSelectedItem());

                String Location = location_tf.getText();
                String Days = days_tf.getText();

                Date data = (Date) jSpinner1.getValue();

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

                String Time = sdf.format(data);

                int Credits = Integer.parseInt(Onsite_credits_tf.getText());
                int id = Integer.parseInt(Onsite_courseID.getText());
                if (courseBLL.updateCourseOnsite(CourseTitle, Credits, DepartmentID, id, Location, Days, Time)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                    PageKH(1);
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
                }
            } else {
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn khoá học");
        }
    }//GEN-LAST:event_Onsite_update_btnActionPerformed

    private void Onsite_add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Onsite_add_btnActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm môn học không?",
                "Xác nhận ", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            String CourseTitle = Onsite_title_tf.getText();
            int DepartmentID = getIdFromString2((String) Department_Cbb2.getSelectedItem());

            String Location = location_tf.getText();
            String Days = days_tf.getText();

            Date data = (Date) jSpinner1.getValue();

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

            String Time = sdf.format(data);

            int Credits = Integer.parseInt(Onsite_credits_tf.getText());
            if (courseBLL.addCourseOnsite(CourseTitle, Credits, DepartmentID, Location, Days, Time)) {
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                PageKH(1);
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }
        } else {
            return;
        }
    }//GEN-LAST:event_Onsite_add_btnActionPerformed

    private void Onsite_title_tfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Onsite_title_tfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Onsite_title_tfActionPerformed

    private void Onsite_delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Onsite_delete_btnActionPerformed
        if (OnlineCourse_table.getSelectedRow() != -1) {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá môn học không?",
                    "Xác nhận ", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                int id;
                id = Integer.parseInt(Onsite_courseID.getText());
                if (courseBLL.deleteCourseOnsite(id)) {
                    JOptionPane.showMessageDialog(null, "Xoá thành công");
                    PageKH(1);
                } else {
                    JOptionPane.showMessageDialog(null, "Xoá thất bại");
                }
            } else {
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hãy chọn khoá học");
        }
    }//GEN-LAST:event_Onsite_delete_btnActionPerformed

    private void Onsite_clear_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Onsite_clear_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Onsite_clear_btnActionPerformed

    private void QLKHStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_QLKHStateChanged
        List<Course> course;
        if (QLKH.getSelectedIndex() == 0) {

            course = courseOnlineBLL.getAllOnlineCourse(1);
            Online_page.setText("1");
            totalPage = courseOnlineBLL.getAllOnlineCourseTotalPage();
            Online_pagination.setText(" / " + totalPage);
            loadDataKH(course, OnlineCourse_table);
        } else {
            course = courseOnsiteBLL.getAllOnsiteCourse(1);
            Onsite_page.setText("1");
            totalPage = courseOnsiteBLL.getAllOnsiteCourseTotalPage();
            Onsite_pagination.setText(" / " + totalPage);
            loadDataKH(course, OnsiteCourse_table);
        }
    }//GEN-LAST:event_QLKHStateChanged

    private void Online_clear_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Online_clear_btnMouseClicked
        if (OnlineCourse_table.getSelectedRow() != -1) {
            Online_add_btn.setEnabled(true);
            Course_id1.setText(null);
            Online_title_tf.setText(null);
            Online_credits_tf.setText(null);
            Department_Cbb1.setSelectedItem("Select department");
            url_tf.setText(null);
            OnlineCourse_table.clearSelection();
        }
    }//GEN-LAST:event_Online_clear_btnMouseClicked

    private void Onsite_clear_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Onsite_clear_btnMouseClicked
        if (OnsiteCourse_table.getSelectedRow() != -1) {
            Onsite_add_btn.setEnabled(true);
            Onsite_courseID.setText(null);
            Onsite_title_tf.setText(null);
            Onsite_credits_tf.setText(null);
            Department_Cbb2.setSelectedItem("Select department");
            location_tf.setText(null);
            days_tf.setText(null);

            Date defaultDate = new Date();
            jSpinner1.setValue(defaultDate);
            OnsiteCourse_table.clearSelection();
        }
    }//GEN-LAST:event_Onsite_clear_btnMouseClicked

    private void Online_previousPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Online_previousPageMouseClicked
        int page = Integer.parseInt(Online_page.getText());
        if (page > 1) {
            page = page - 1;
            Online_page.setText(page + "");
        } else {

        }
    }//GEN-LAST:event_Online_previousPageMouseClicked

    private void Online_nextPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Online_nextPageMouseClicked
        int page = Integer.parseInt(Online_page.getText());
        if (page < totalPage) {
            page = page + 1;
            Online_page.setText(page + "");
        } else {

        }
    }//GEN-LAST:event_Online_nextPageMouseClicked

    private void Onsite_previousPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Onsite_previousPageMouseClicked
        int page = Integer.parseInt(Onsite_page.getText());
        if (page > 1) {
            page = page - 1;
            Onsite_page.setText(page + "");
        } else {

        }
    }//GEN-LAST:event_Onsite_previousPageMouseClicked

    private void Onsite_nextPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Onsite_nextPageMouseClicked
        int page = Integer.parseInt(Onsite_page.getText());
        if (page < totalPage) {
            page = page + 1;
            Onsite_page.setText(page + "");
        } else {

        }
    }//GEN-LAST:event_Onsite_nextPageMouseClicked

    private void Onsite_search_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Onsite_search_btnMouseClicked
        Onsite_page.setText("1");
    }//GEN-LAST:event_Onsite_search_btnMouseClicked

    private void Online_search_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Online_search_btnMouseClicked
        Online_page.setText("1");
    }//GEN-LAST:event_Online_search_btnMouseClicked

    private void PC_clear_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PC_clear_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PC_clear_btnActionPerformed

    private void PC_PageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PC_PageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PC_PageActionPerformed

    private void PC_clear_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PC_clear_btnMouseClicked
        clearPC();
    }//GEN-LAST:event_PC_clear_btnMouseClicked

    private void Course_title_CbbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Course_title_CbbActionPerformed
        Instructor_Cbb.removeAllItems();
        Instructor_Cbb.addItem("Select instructor");
        if (Course_title_Cbb.getSelectedItem().equals("Select course")) {
        } else {
            String courseNameID = (String) Course_title_Cbb.getSelectedItem();
            Instructor_Cbb.setEnabled(true);
            List<Person> person = personBLL.getPersonNotInstructorOfCourse(Integer.parseInt(getIdFromString(courseNameID)));
            for (Person ps : person) {
                Instructor_Cbb.addItem(ps.getLastname() + " " + ps.getFirstname() + " - ID: " + ps.getPersonID());
            }
        }
    }//GEN-LAST:event_Course_title_CbbActionPerformed

    private void Instructor_add_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instructor_add_btnMouseClicked
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn phân công cho giảng viên này không?",
                "Xác nhận", JOptionPane.YES_OPTION);
        if (result == JOptionPane.NO_OPTION) {
            return;
        }
        // Lấy thông tin về khóa học và giảng viên được chọn
        String selectedCourse = (String) Course_title_Cbb.getSelectedItem();
        String selectedInstructor = (String) Instructor_Cbb.getSelectedItem();

        if (selectedCourse.equals("Select course") || selectedInstructor.equals("Select instructor")) {
            // Hiển thị thông báo hoặc xử lý lỗi nếu không có khóa học hoặc giảng viên được chọn
            return;
        }
        CourseInstructor ci = new CourseInstructor();

        ci.setInstructor(new Person()); // Khởi tạo đối tượng Instructor
        // Tách thông tin từ chuỗi selectedInstructor
        int personID = getIdFromString2(selectedInstructor);
        //int courseID = getIdFromString2(selectedCourse);
        String Title = extractTitle(selectedCourse);

        System.out.println("Debug - title: " + Title);
        ci.getInstructor().setPersonID(personID);
        ci.setTitle(Title);
        ci.getInstructor().setFirstname("");
        ci.getInstructor().setLastname("");
        ci.setCourseID(0);

        try {
            if (courseInstructorBLL.addCourseInstructor(ci)) {
                // Xử lý khi phân công thành công
                clearPC();
                updatePCTable();
                JOptionPane.showMessageDialog(null, "Thêm thành công");
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }

        } catch (Exception ex) {
            Logger.getLogger(CourseInstructorDAL.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_Instructor_add_btnMouseClicked

    private void Instructor_CbbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Instructor_CbbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Instructor_CbbActionPerformed

    private void Online_update_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Online_update_btnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Online_update_btnMouseClicked

    private void Instructor_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instructor_updateMouseClicked
        // TODO add your handling code here:
        int i = Instructor_table.getSelectedRow();

        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Hãy chọn lịch phân công");
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật lịch không?",
                    "Xác nhận ", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                CourseInstructor ci = new CourseInstructor();
                ci.setInstructor(new Person());
                String oldCourseTitle = Instructor_table.getModel().getValueAt(i, 1).toString();
                int oldPersonID = Integer.parseInt(Instructor_table.getModel().getValueAt(i, 2).toString());
                ci.setCourseID(0);
                ci.getInstructor().setFirstname("");
                ci.getInstructor().setLastname("");
                String selectedCourse = (String) Course_title_Cbb.getSelectedItem();
                String selectedInstructor = (String) Instructor_Cbb.getSelectedItem();
                int personID = getIdFromString2(selectedInstructor);
                String Title = extractTitle(selectedCourse);
                ci.setTitle(Title);
                ci.getInstructor().setPersonID(personID);

                if (courseInstructorBLL.updateCourseInstructor(ci, oldPersonID, oldCourseTitle)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                    clearPC();
                    updatePCTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại ");
                }

            }
        }
    }//GEN-LAST:event_Instructor_updateMouseClicked

    private void instructor_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_instructor_deleteMouseClicked
        // TODO add your handling code here:
        int i = Instructor_table.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Hãy chọn course cần xóa");
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phân công này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                String selectedCourse = (String) Course_title_Cbb.getSelectedItem();
                String selectedInstructor = (String) Instructor_Cbb.getSelectedItem();
                String CourseTitle = extractTitle(selectedCourse);

                // Lấy danh sách CourseInstructor từ BLL
                List<CourseInstructor> courseInstructors = courseInstructorBLL.getAllCourseInstructor();

                // Tìm PersonID tương ứng với giảng viên đã chọn
                int personID = findPersonID(courseInstructors, selectedInstructor);

                // Gọi hàm xóa từ BLL
                if (courseInstructorBLL.deleteCourseInstructor(personID, CourseTitle)) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công");
                    String search = Search3.getText();
                    clearPC();
                    updatePCTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa thất bại");
                }

            }
        }
    }//GEN-LAST:event_instructor_deleteMouseClicked

    private void CourseInstructor_search_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CourseInstructor_search_btnMouseClicked
        // TODO add your handling code here:
        updatePCTable();
    }//GEN-LAST:event_CourseInstructor_search_btnMouseClicked

    private void PC_PreviousPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PC_PreviousPageMouseClicked
        // TODO add your handling code here:
        int page = Integer.parseInt(PC_Page.getText());
        if (page > 1) {
            page = page - 1;
            PC_Page.setText(page + "");
        } else {
        }
    }//GEN-LAST:event_PC_PreviousPageMouseClicked

    private void PC_NextPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PC_NextPageMouseClicked
        // TODO add your handling code here:
        int page = Integer.parseInt(PC_Page.getText());
        if (page < totalPage) {
            page = page + 1;
            PC_Page.setText(page + "");
        } else {
        }
    }//GEN-LAST:event_PC_NextPageMouseClicked

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        // TODO add your handling code here:
        AddStudentGrade addStudentGrade = new AddStudentGrade();
        addStudentGrade.setVisible(true);
        addStudentGrade.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
//                LoadStudentGradeTable(1);
//                KQKH_Page.setText("1");
                KQKH_Page.setText("1");
                PageKQKH(1);
                cbSelectCourse3.setSelectedItem("Selected Course");
            }
        });
    }//GEN-LAST:event_btnAddMouseClicked

    private void txtSearch3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearch3KeyReleased

        PageKQKH(1);
        KQKH_Page.setText("1");
    }//GEN-LAST:event_txtSearch3KeyReleased

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
        // TODO add your handling code here:
        try {
            boolean check = false;
            for (int i = 0; i < model3.getRowCount(); ++i) {
                int enrollmentID = Integer.parseInt(model3.getValueAt(i, 0).toString());
                float grade = 0.0f;
                try {
                    grade = Float.parseFloat(model3.getValueAt(i, 6).toString());
                } catch (NumberFormatException ex) {
                    // Xử lý nếu grade không phải là float
                    JOptionPane.showMessageDialog(null, "Grade must be a float value!");
                    continue;
                }
                if (grade >= 0.0 && grade <= 4.0) {
                    sgBLL.editStudenGrade(enrollmentID, grade);
                    cbSelectCourse3.setSelectedItem("Selected Course");
                    check = true;
                } else {
                    // Xử lý nếu grade không nằm trong khoảng từ 0.0 đến 4.0
                    JOptionPane.showMessageDialog(null, "Grade is incorrect for enrollment ID: " + enrollmentID);
                }
            }
            if (!check) {
                JOptionPane.showMessageDialog(null, "Update successful!");
                LoadStudentGradeTable(1);
                KQKH_Page.setText("1");
            }
        } catch (HeadlessException | NumberFormatException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEditMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        // TODO add your handling code here:
        int row = jTable3.getSelectedRow();

        int id = -1;
        id = Integer.parseInt(model3.getValueAt(row, 0).toString());
        if (id != -1) {
            JOptionPane.showMessageDialog(null, "Deleted successful");
            cbSelectCourse3.setSelectedItem("Selected Course");
            sgBLL.deleteStudentGrade(id);
        } else {
            JOptionPane.showMessageDialog(null, "The selected item does not exist");
        }
//        LoadStudentGradeTable(1);
//        KQKH_Page.setText("1");
        KQKH_Page.setText("1");
        PageKQKH(1);
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void cbSelectCourse3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbSelectCourse3ItemStateChanged
        // TODO add your handling code here:
        model3.setRowCount(0);
        String titleSG = null;
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            titleSG = (String) cbSelectCourse3.getSelectedItem();
        }
        ArrayList<StudentGrade> sgs = sgBLL.selectedCourse(titleSG);
        for (StudentGrade sg : sgs) {
            Object[] r = {sg.getEnrollmentID(), sg.getCourseID(), sg.getTitle(), sg.getStudentID(), sg.getFirstName(), sg.getLastName(), sg.getGarde()};
            model3.addRow(r);
        }
        if ("Selected Course".equals(titleSG)) {
//            LoadStudentGradeTable(1);
//            KQKH_Page.setText("1");
            KQKH_Page.setText("1");
            PageKQKH(1);
        }
    }//GEN-LAST:event_cbSelectCourse3ItemStateChanged

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        int row = jTable3.getSelectedRow();
        if (row != -1) {
            lbEnrollmentID.setText(model3.getValueAt(row, 0).toString());
            lbCourseID.setText(model3.getValueAt(row, 1).toString());
            lbTitle.setText(model3.getValueAt(row, 2).toString());
            lbStudentID.setText(model3.getValueAt(row, 3).toString());
            txtName3.setText(model3.getValueAt(row, 4).toString() + " " + model3.getValueAt(row, 5));
            txtGrade.setText(model3.getValueAt(row, 6).toString());
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseClicked
        int page = Integer.parseInt(KQKH_Page.getText());
        if (page > 1) {
            page = page - 1;
            KQKH_Page.setText(page + "");
        } else {
        }
    }//GEN-LAST:event_jButton15MouseClicked

    private void jButton17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MouseClicked
        int page = Integer.parseInt(KQKH_Page.getText());
        if (page < totalPage) {
            page = page + 1;
            KQKH_Page.setText(page + "");
        } else {
        }
    }//GEN-LAST:event_jButton17MouseClicked

    private void KQKH_PageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KQKH_PageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KQKH_PageActionPerformed

    private void Statistic_OnlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Statistic_OnlineMouseClicked

        Statistic_OnlineCourse statistic_OnlineCourse = new Statistic_OnlineCourse();

        statistic_OnlineCourse.setVisible(true);


    }//GEN-LAST:event_Statistic_OnlineMouseClicked

    private void Statistic_OnsiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Statistic_OnsiteMouseClicked
        Statistic_OnsiteCourse statistic_OnsiteCourse = new Statistic_OnsiteCourse();

        statistic_OnsiteCourse.setVisible(true);

    }//GEN-LAST:event_Statistic_OnsiteMouseClicked

    private void Statistic_InstructorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Statistic_InstructorMouseClicked
        Statistic_Instructor statistic_Instructor = new Statistic_Instructor();

        statistic_Instructor.setVisible(true);
    }//GEN-LAST:event_Statistic_InstructorMouseClicked

    private void Statistic_StudentGradeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Statistic_StudentGradeMouseClicked
        Statistic_StudentGrade statistic_StudentGrade = new Statistic_StudentGrade();

        statistic_StudentGrade.setVisible(true);
    }//GEN-LAST:event_Statistic_StudentGradeMouseClicked

// Hàm setIcon cho các component    
    private void setIcons() {
        Label_QLKH_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/education.png")));
        Label_QLPC_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/speech.png")));
        Label_QLKQ_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/exam.png")));
        Online_search_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/loupe.png")));
        Onsite_search_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/loupe.png")));
        CourseInstructor_search_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/loupe.png")));
        Statistic_Online.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/statistics.png")));
        Statistic_Onsite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/statistics.png")));
        Statistic_Instructor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/statistics.png")));
        Statistic_StudentGrade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/statistics.png")));
    }

// Ẩn các label
    private void hiddenLabel() {
        Course_id1.setVisible(false);
        Onsite_courseID.setVisible(false);
        Course_id.setVisible(false);
        Instructor_id.setVisible(false);
    }

// Hàm lấy id từ chuỗi trong combobox 
    private int getIdFromString2(String nameID) {
        String[] parts = nameID.split("ID:");
        //System.out.println("Debug - nameID: " + nameID); // Thêm dòng này để xem giá trị của nameID

        if (parts.length == 2) {
            try {
                return Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

// Hàm lấy id từ chuỗi trong combobox    
    private String getIdFromString(String nameID) {
        String[] parts = nameID.split(":\\s*");

        if (parts.length == 2) {
            return parts[1];
        }
        return null;
    }

// Hàm lấy title từ chuỗi trong combobox
    public static String extractTitle(String fullTitle) {

        // Tách title từ chuỗi theo dấu gạch ngang và loại bỏ khoảng trắng
        String[] parts = fullTitle.split(" - ");
        if (parts.length >= 1) {
            return parts[0];
        } else {
            return null;  // Trả về null nếu không tìm thấy phần title
        }
    }

// Hàm cập nhật trang    
    private void ChangePage() {
        Online_page.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                int page = Integer.parseInt(Online_page.getText());
                PageKH(page);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                int page = Integer.parseInt(Online_page.getText());
                PageKH(page);
            }
        });
        Onsite_page.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                int page = Integer.parseInt(Onsite_page.getText());
                PageKH(page);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            public void insertUpdate(DocumentEvent e) {
                int page = Integer.parseInt(Onsite_page.getText());
                PageKH(page);
            }
        });
        PC_Page.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                int page = Integer.parseInt(PC_Page.getText());
                PagePC(page);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                int page = Integer.parseInt(PC_Page.getText());
                PagePC(page);
            }
        });
        KQKH_Page.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                int page = Integer.parseInt(KQKH_Page.getText());

                PageKQKH(page);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                int page = Integer.parseInt(KQKH_Page.getText());

                PageKQKH(page);
            }
        });
    }

    //======================
    //  QUẢN LÝ KHOÁ HỌC 
    //======================
// Hàm ghi dữ liệu vào combobox 
    private void fillData() {
        List<Department> dpl = departmentBLL.getDepartmentList();
        Department_Cbb1.removeAll();
        for (Department dp : dpl) {
            String name = dp.getName() + " - ID: " + dp.getDepartmentID();
            Department_Cbb1.addItem(name);
            Department_Cbb2.addItem(name);
        }

        List<Course> list = courseBLL.getAllCourse2();
        Course_title_Cbb.removeAll();
        for (Course c : list) {
            String course = c.getTitle() + " - ID: " + c.getCourseID();
            Course_title_Cbb.addItem(course);
        }
    }

// Hàm ghi dữ liệu lên table khoá học
    private void loadDataKH(List<Course> courses, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Course c : courses) {
            if (c instanceof CourseOnline) {
                CourseOnline online = (CourseOnline) c;
                Object[] data = {c.getCourseID(), c.getTitle(), c.getCredits(), c.getDepartment().getName(), online.getURL()};
                model.addRow(data);
            } else {
                CourseOnSite onsite = (CourseOnSite) c;
                Object[] data = {c.getCourseID(), c.getTitle(), c.getCredits(), c.getDepartment().getName(), onsite.getLocation(), onsite.getDays(), onsite.getTime()};
                model.addRow(data);
            }
        }
    }

// Hàm lấy dữ liệu từ table khoá học 
    private void getKHDataFromRow(JTable table) {
        int selectedRow = table.getSelectedRow();

        if (QLKH.getSelectedIndex() == 0) {
            int CourseID = (int) table.getValueAt(selectedRow, 0);
            Course_id1.setText(CourseID + "");
            Course c = courseBLL.getCourseWithID(CourseID);
            Online_title_tf.setText(c.getTitle());
            Online_credits_tf.setText(c.getCredits() + "");
            Department_Cbb1.setSelectedItem(c.getDepartment().getName() + " - ID: " + c.getDepartment().getDepartmentID());
            url_tf.setText((String) table.getValueAt(selectedRow, 4));
        } else {
            int CourseID = (int) table.getValueAt(selectedRow, 0);
            Course c = courseBLL.getCourseWithID(CourseID);
            Onsite_courseID.setText(CourseID + "");
            Onsite_title_tf.setText(c.getTitle());
            Onsite_credits_tf.setText(c.getCredits() + "");
            Department_Cbb2.setSelectedItem(c.getDepartment().getName() + " - ID: " + c.getDepartment().getDepartmentID());
            location_tf.setText((String) table.getValueAt(selectedRow, 4));
            days_tf.setText((String) table.getValueAt(selectedRow, 5));
            jSpinner1.setValue(table.getValueAt(selectedRow, 6));
        }
    }

// Hàm cập nhật trang quản lý khoá học 
    private void PageKH(int page) {
        if (QLKH.getSelectedIndex() == 0) {
            List<Course> course;
            if (Online_search.getText() == null || Online_search.getText().isEmpty() || Online_search.getText().isBlank()) {
                course = courseOnlineBLL.getAllOnlineCourse(page);
                totalPage = courseOnlineBLL.getAllOnlineCourseTotalPage();
            } else {
                String search = Online_search.getText();
                course = courseOnlineBLL.getCourseWithInfo(search, page);
                totalPage = courseOnlineBLL.getCourseWithInfoTotalPage(search);
            }
            Online_pagination.setText(" / " + totalPage);
            loadDataKH(course, OnlineCourse_table);
        } else {
            List<Course> course;
            if (Onsite_search.getText() == null || Onsite_search.getText().isEmpty() || Onsite_search.getText().isBlank()) {
                course = courseOnsiteBLL.getAllOnsiteCourse(page);

                totalPage = courseOnsiteBLL.getAllOnsiteCourseTotalPage();

                System.out.println(totalPage);
            } else {
                String search = Onsite_search.getText();
                course = courseOnsiteBLL.getCourseWithInfo(search, page);
                totalPage = courseOnsiteBLL.getCourseWithInfoTotalPage(search);
                System.out.println(totalPage);
            }
            Onsite_pagination.setText(" / " + totalPage);
            loadDataKH(course, OnsiteCourse_table);
        }
    }

    //======================
    //  QUẢN LÝ PHÂN CÔNG
    //======================
// Hàm tìm Instructor
    private int findPersonID(List<CourseInstructor> courseInstructors, String selectedInstructor) {
        for (CourseInstructor ci : courseInstructors) {
            String instructorName = ci.getInstructor().getLastname() + " " + ci.getInstructor().getFirstname();
            if (instructorName.equals(selectedInstructor)) {
                return ci.getInstructor().getPersonID();
            }
        }
        return -1; // Trả về giá trị không hợp lệ nếu không tìm thấy
    }

// Hàm cập nhật Instructor combobox
    private void fillInstructor(CourseInstructor ci) {
        List<Person> person = personBLL.getPersonNotInstructorOfCourse(ci.getCourseID());
        Instructor_Cbb.removeAllItems();
        Instructor_Cbb.addItem(ci.getInstructor().getLastname() + " " + ci.getInstructor().getFirstname());
        for (Person ps : person) {
            Instructor_Cbb.addItem(ps.getLastname() + " " + ps.getFirstname() + " - ID: " + ps.getPersonID());
        }
    }

// Hàm ghi dữ liệu lên table phân công
    private void loadDataPC(List<CourseInstructor> list) {
        DefaultTableModel model = (DefaultTableModel) Instructor_table.getModel();
        model.setRowCount(0);

        for (CourseInstructor ci : list) {
            Object[] data = {ci.getCourseID(), ci.getTitle(), ci.getInstructor().getPersonID(), ci.getInstructor().getLastname() + " " + ci.getInstructor().getFirstname()};
            model.addRow(data);
        }
        int rowCount = model.getRowCount();
        // Nếu có ít nhất một dòng mới được thêm
        if (model.getRowCount() > rowCount) {
            // Chọn dòng cuối cùng (dòng mới thêm)
            int rowIndex = model.getRowCount() - 1;
            Instructor_table.setRowSelectionInterval(rowIndex, rowIndex);

            // Cuộn đến dòng được chọn
            Instructor_table.scrollRectToVisible(Instructor_table.getCellRect(rowIndex, 0, true));
        }
    }

// Hàm cập nhật dữ liệu table phân công    
    private void updatePCTable() {
        String search = Search3.getText();
        if (search.isBlank() || search.isEmpty() || search == null) {
            List<CourseInstructor> updatedList = courseInstructorBLL.getListCourseInstructor(1);  // Thay thế bằng phương thức lấy danh sách từ BLL của bạn
            totalPage = courseInstructorBLL.getListCourseInstructorCount();
            PC_Pagination.setText(" / " + totalPage);
            PC_Page.setText("1");
            loadDataPC(updatedList);
        } else {
            List<CourseInstructor> updatedList = courseInstructorBLL.getCourseInstructorWithInfo(search, 1);
            totalPage = courseInstructorBLL.getCourseInstructorWithInfoCount(search);
            PC_Pagination.setText(" / " + totalPage);
            PC_Page.setText("1");
            loadDataPC(updatedList);
        }
    }

// Hàm lấy dữ liệu từ dòng đã chọn trong table phân công 
    private void getPCDataFromRow(JTable table) {
        int selectedRow = table.getSelectedRow();
        CourseInstructor ci = courseInstructorBLL.getCourseInstructorByIDs((int) table.getValueAt(selectedRow, 0), (int) table.getValueAt(selectedRow, 2));
        String nameID = table.getValueAt(selectedRow, 1) + " - ID: " + table.getValueAt(selectedRow, 0);

        Course_title_Cbb.setSelectedItem(nameID);
        Course_title_Cbb.setEnabled(false);

        Course_id.setText(table.getValueAt(selectedRow, 0) + "");

        Instructor_id.setText(table.getValueAt(selectedRow, 2) + "");

        fillInstructor(ci);

        Instructor_Cbb.setSelectedItem(table.getValueAt(selectedRow, 3));
        Instructor_Cbb.setEnabled(true);
    }

// Hàm tạo mới trang quản lý phân công   
    private void clearPC() {
        Instructor_add_btn.setEnabled(true);
        Course_title_Cbb.setEnabled(true);
        Course_title_Cbb.setSelectedIndex(0);
        Instructor_Cbb.setEnabled(false);
        Instructor_Cbb.removeAllItems();
        Instructor_Cbb.addItem("Select instructor");
    }

// Hàm cập nhật trang cho quản lý phân công    
    private void PagePC(int page) {

        List<CourseInstructor> course;
        String search3 = Search3.getText();
        if (search3 == null || search3.isEmpty() || search3.isBlank()) {
            course = courseInstructorBLL.getListCourseInstructor(page);
            totalPage = courseInstructorBLL.getListCourseInstructorCount();
        } else {
            course = courseInstructorBLL.getCourseInstructorWithInfo(search3, page);
            totalPage = courseInstructorBLL.getCourseInstructorWithInfoCount(search3);
        }
        PC_Pagination.setText(" / " + totalPage);
        loadDataPC(course);
    }

    //======================
    //  QUẢN LÝ KẾT QUẢ 
    //======================
//Quản lí kết quả khóa học   
    public void LoadStudentGradeTable(int page) {
        int numofrecords = 30;

        model3 = (DefaultTableModel) jTable3.getModel();
        model3.setRowCount(0);

        ArrayList<StudentGrade> sgList = sgBLL.getSGs();

        int startIndex = (page - 1) * numofrecords;
        int endIndex = Math.min(startIndex + numofrecords, sgList.size());

        for (int i = startIndex; i < endIndex; i++) {
            StudentGrade sg = sgList.get(i);
            Object[] row = {sg.getEnrollmentID(), sg.getCourseID(), sg.getTitle(), sg.getStudentID(), sg.getFirstName(), sg.getLastName(), sg.getGarde()};
            model3.addRow(row);
        }

        totalPage = (int) Math.ceil((double) sgList.size() / numofrecords);
        KQKH_Pagination.setText(" / " + totalPage);

        List<Course> cs = new CourseBLL().getAllCourse();
        for (Course c : cs) {
            cbSelectCourse3.addItem(c.getTitle());
        }
    }

// Hàm cập nhật trang cho quản lý phân công    
    private void PageKQKH(int page) {
        int numofrecords = 30;
        String keyword = txtSearch3.getText().toLowerCase();
        if (!keyword.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);

            ArrayList<StudentGrade> sgList = sgBLL.getSGs();
            int count = 0;

            for (StudentGrade sg : sgList) {
                String enrollmentID = String.valueOf(sg.getEnrollmentID()).toLowerCase();
                String courseID = String.valueOf(sg.getCourseID());
                String title = sg.getTitle().toLowerCase();
                String studentID = String.valueOf(sg.getStudentID());
                String firstName = sg.getFirstName().toLowerCase();
                String lastName = sg.getLastName().toLowerCase();
                String grade = String.valueOf(sg.getGarde());

                if (enrollmentID.contains(keyword) || courseID.contains(keyword) || title.contains(keyword) || studentID.contains(keyword) || firstName.contains(keyword) || lastName.contains(keyword) || grade.contains(keyword)) {
                    if (count >= (page - 1) * numofrecords && count < page * numofrecords) {
                        Object[] row = {enrollmentID, courseID, title, studentID, firstName, lastName, grade};
                        model.addRow(row);
                    }
                    count++;
                }
                totalPage = (int) Math.ceil((double) count / numofrecords);
                KQKH_Pagination.setText(" / " + totalPage);
            }
        } else {
            LoadStudentGradeTable(page);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainFrame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JLabel CloseBtn;
    private javax.swing.JPanel Content;
    private javax.swing.JButton CourseInstructor_search_btn;
    private javax.swing.JLabel Course_id;
    private javax.swing.JLabel Course_id1;
    private javax.swing.JComboBox<String> Course_title_Cbb;
    private javax.swing.JComboBox<String> Department_Cbb1;
    private javax.swing.JComboBox<String> Department_Cbb2;
    private javax.swing.JComboBox<String> Instructor_Cbb;
    private javax.swing.JButton Instructor_add_btn;
    private javax.swing.JLabel Instructor_id;
    private javax.swing.JTable Instructor_table;
    private javax.swing.JButton Instructor_update;
    private javax.swing.JTextField KQKH_Page;
    private javax.swing.JLabel KQKH_Pagination;
    private javax.swing.JLabel Label_QLKH;
    private javax.swing.JLabel Label_QLKH_icon;
    private javax.swing.JLabel Label_QLKQ;
    private javax.swing.JLabel Label_QLKQ_icon;
    private javax.swing.JLabel Label_QLPC;
    private javax.swing.JLabel Label_QLPC_icon;
    private javax.swing.JPanel LeftMenu;
    private javax.swing.JPanel OnlineCourse;
    private javax.swing.JTable OnlineCourse_table;
    private javax.swing.JButton Online_add_btn;
    private javax.swing.JButton Online_clear_btn;
    private javax.swing.JTextField Online_credits_tf;
    private javax.swing.JButton Online_delete_btn;
    private javax.swing.JButton Online_nextPage;
    private javax.swing.JTextField Online_page;
    private javax.swing.JLabel Online_pagination;
    private javax.swing.JButton Online_previousPage;
    private javax.swing.JTextField Online_search;
    private javax.swing.JButton Online_search_btn;
    private javax.swing.JTextField Online_title_tf;
    private javax.swing.JButton Online_update_btn;
    private javax.swing.JPanel OnsiteCourse;
    private javax.swing.JTable OnsiteCourse_table;
    private javax.swing.JButton Onsite_add_btn;
    private javax.swing.JButton Onsite_clear_btn;
    private javax.swing.JLabel Onsite_courseID;
    private javax.swing.JLabel Onsite_credits;
    private javax.swing.JTextField Onsite_credits_tf;
    private javax.swing.JButton Onsite_delete_btn;
    private javax.swing.JLabel Onsite_department;
    private javax.swing.JButton Onsite_nextPage;
    private javax.swing.JTextField Onsite_page;
    private javax.swing.JLabel Onsite_pagination;
    private javax.swing.JButton Onsite_previousPage;
    private javax.swing.JTextField Onsite_search;
    private javax.swing.JButton Onsite_search_btn;
    private javax.swing.JLabel Onsite_title;
    private javax.swing.JTextField Onsite_title_tf;
    private javax.swing.JButton Onsite_update_btn;
    private javax.swing.JButton PC_NextPage;
    private javax.swing.JTextField PC_Page;
    private javax.swing.JLabel PC_Pagination;
    private javax.swing.JButton PC_PreviousPage;
    private javax.swing.JButton PC_clear_btn;
    private javax.swing.JPanel Panel_QLKH;
    private javax.swing.JPanel Panel_QLKQ;
    private javax.swing.JPanel Panel_QLPC;
    private javax.swing.JTabbedPane QLKH;
    private javax.swing.JPanel QLKQKH;
    private javax.swing.JPanel QLPC;
    private javax.swing.JTextField Search3;
    private javax.swing.JButton Statistic_Instructor;
    private javax.swing.JButton Statistic_Online;
    private javax.swing.JButton Statistic_Onsite;
    private javax.swing.JButton Statistic_StudentGrade;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JComboBox<String> cbSelectCourse3;
    private javax.swing.JLabel credits;
    private javax.swing.JLabel days;
    private javax.swing.JTextField days_tf;
    private javax.swing.JLabel department;
    private javax.swing.JButton instructor_delete;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton17;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lbCourseID;
    private javax.swing.JLabel lbEnrollmentID;
    private javax.swing.JLabel lbStudentID;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel location;
    private javax.swing.JTextField location_tf;
    private javax.swing.JLabel time;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    private javax.swing.JTextField txtGrade;
    private javax.swing.JLabel txtName3;
    private javax.swing.JTextField txtSearch3;
    private javax.swing.JLabel url;
    private javax.swing.JTextField url_tf;
    // End of variables declaration//GEN-END:variables
}
