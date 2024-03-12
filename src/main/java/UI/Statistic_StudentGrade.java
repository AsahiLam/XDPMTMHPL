/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import BLL.CourseBLL;
import BLL.StudentGradeBLL;
import DAL.entities.Course;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Tính
 */
public class Statistic_StudentGrade extends javax.swing.JFrame implements ActionListener {
    
    private final CourseBLL cBLL = new CourseBLL();
    private final StudentGradeBLL stBLL = new StudentGradeBLL();
    private List<SimpleEntry<Double,Integer>> list;
    
    /**
     * Creates new form AddStudentGrade
     */
    public Statistic_StudentGrade() {
        initComponents();
        
        fillCourse();
        
        Chart_Panel2.setBackground(Color.red);
        Course_Cbb.addActionListener(this);
        
        list = stBLL.statistic_StudentGrade(getNameFromString((String)Course_Cbb.getSelectedItem()));
        
        showBarChart(list);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void showBarChart(List<SimpleEntry<Double, Integer>> list) {
        DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
        
        for (SimpleEntry<Double, Integer> stt : list) {
            barChartData.setValue(stt.getValue(),"Grade", stt.getKey());
        }

        JFreeChart barChart = ChartFactory.createBarChart("Phổ điểm của môn học "+getNameFromString2((String) Course_Cbb.getSelectedItem()),"Grade", "Amount", barChartData, PlotOrientation.VERTICAL, true, true, false);

        CategoryPlot barC = barChart.getCategoryPlot();
        
        barC.setRangeGridlinePaint(Color.ORANGE);
        barC.setBackgroundPaint(Color.WHITE);
        ChartPanel barPanel = new ChartPanel(barChart);
        barPanel.setPreferredSize(new Dimension(500, 300));
        
        
        Chart_Panel2.removeAll();
        Chart_Panel2.setLayout(new BorderLayout());

        Chart_Panel2.add(barPanel, BorderLayout.CENTER);

        Chart_Panel2.revalidate();
        repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        list = stBLL.statistic_StudentGrade(getNameFromString((String)Course_Cbb.getSelectedItem()));
        showBarChart(list);
    }
    
    private String getNameFromString2(String nameID) {
        String[] parts = nameID.split("- ID:");
        //System.out.println("Debug - nameID: " + nameID); // Thêm dòng này để xem giá trị của nameID

        if (parts.length == 2) {
            try {
                return parts[0].trim();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    private int getNameFromString(String nameID) {
        String[] parts = nameID.split("- ID:");
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
    
    private void fillCourse() {
        List<Course> listC = cBLL.getAllCourse2();
        Course_Cbb.removeAllItems();
        for (Course ps : listC) {
            
            Course_Cbb.addItem(ps.getTitle() + " - ID: " + ps.getCourseID());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Course_Cbb = new javax.swing.JComboBox<>();
        Chart_Panel2 = new javax.swing.JPanel(new BorderLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(500, 300));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống kê kết quả khoá học", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Helvetica Neue", 3, 13), new java.awt.Color(204, 0, 51))); // NOI18N

        jLabel1.setText("Course");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(Course_Cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Course_Cbb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        Chart_Panel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout Chart_Panel2Layout = new javax.swing.GroupLayout(Chart_Panel2);
        Chart_Panel2.setLayout(Chart_Panel2Layout);
        Chart_Panel2Layout.setHorizontalGroup(
            Chart_Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 576, Short.MAX_VALUE)
        );
        Chart_Panel2Layout.setVerticalGroup(
            Chart_Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(Chart_Panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Chart_Panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Chart_Panel2;
    private javax.swing.JComboBox<String> Course_Cbb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}