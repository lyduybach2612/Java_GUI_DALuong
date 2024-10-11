package GLuong;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class GUI_updateNV extends JFrame {

    private final String[] diaChi = {"Hải Phòng", "Hà Nội", "Nam Định"};
    private final String[] headers = {"Mã nhân viên", "Họ tên", "Lương", "Địa chỉ"};
    private JTextField tf_maNV;
    private JTextField tf_hoTen;
    private JTextField tf_luong;
    private JComboBox<String> cb_diaChi;
    private JLabel lb_maNV;
    private JLabel lb_hoTen;
    private JLabel lb_luong;
    private JLabel lb_diaChi;
    private JButton btn_timKiem;
    private  JButton btn_capNhat;
    private DefaultTableModel model;
    private JPanel panel;
    private JTable table;
    private JButton btn_xoa;
    public GUI_updateNV(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("NV");
        this.setSize(700,700);
        this.setLayout(null);
        initializeComponent();
        this.setVisible(true);

    }

    public  void initializeComponent(){

        this.tf_maNV = new JTextField();
        this.tf_hoTen = new JTextField();
        this.tf_luong = new JTextField();
        this.cb_diaChi = new JComboBox<String>(diaChi);
        this.btn_timKiem = new JButton("Tìm kiếm");
        this.btn_capNhat = new JButton("Cập nhập");
        this.lb_maNV = new JLabel("Mã nhân viên");
        this.lb_hoTen = new JLabel("Họ tên");
        this.lb_luong = new JLabel("Lương");
        this.lb_diaChi = new JLabel("Địa chỉ");
        this.model = new DefaultTableModel(headers, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table = new JTable(model);
        this.panel = new JPanel(new GridLayout(1,1));
        this.panel.add(new JScrollPane(table));

        this.lb_maNV.setBounds(20,20,100,25);
        this.lb_hoTen.setBounds(20,55,100,25);
        this.lb_diaChi.setBounds(20,90,100,25);
        this.lb_luong.setBounds(20,125,100,25);

        this.tf_maNV.setBounds(129,20,200,25);
        this.tf_hoTen.setBounds(129,54,200,25);
        this.cb_diaChi.setBounds(129,89,200,25);
        this.tf_luong.setBounds(129,124,200,25);

        this.panel.setBounds(129,205,400,300);

        this.btn_timKiem.setBounds(228,160,100,25);

        this.btn_timKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XLLuong x = new XLLuong();
                try {
                    List<NhanVien> nvs = x.getNVbyMa(tf_maNV.getText());
                    model.setRowCount(0);
                    for(NhanVien nv : nvs){
                        String[] dataRow = {
                                nv.getMaNV(),
                                nv.getHoTen(),
                                nv.getDiaChi(),
                                String.valueOf(nv.getLuong())
                        };
                        model.addRow(dataRow);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.btn_capNhat.setBounds(129,160,100,25);
        this.btn_capNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhanVien nhanVien = new NhanVien(tf_maNV.getText(), tf_hoTen.getText(), (String) cb_diaChi.getSelectedItem(), Double.parseDouble(tf_luong.getText()));
                XLLuong x = new XLLuong();
                try {
                    x.updateNV(nhanVien, tf_maNV.getText());
                    model.setRowCount(0);
                    List<NhanVien> nvs = x.getNV();
                    for(NhanVien nv : nvs){
                        String[] dataRow = {
                                nv.getMaNV(),
                                nv.getHoTen(),
                                nv.getDiaChi(),
                                String.valueOf(nv.getLuong())
                        };
                        model.addRow(dataRow);
                    }
                    tf_maNV.setEditable(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowOpened(WindowEvent e) {

                XLLuong x = new XLLuong();
                try {
                    List<NhanVien> nvs = x.getNV();
                    for(NhanVien nv : nvs){
                        String[] dataRow = {
                                nv.getMaNV(),
                                nv.getHoTen(),
                                nv.getDiaChi(),
                                String.valueOf(nv.getLuong())
                        };
                        model.addRow(dataRow);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }

        });

        this.table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int row = table.getSelectedRow();
                if(row != -1){
                    tf_maNV.setText(model.getValueAt(row, 0).toString());
                    tf_hoTen.setText(model.getValueAt(row, 1).toString());
                    tf_luong.setText(model.getValueAt(row, 3).toString());
                    tf_maNV.setEditable(false);
                }

            }

        });

        this.add(tf_maNV);
        this.add(tf_hoTen);
        this.add(tf_luong);
        this.add(cb_diaChi);
        this.add(btn_timKiem);
        this.add(btn_capNhat);
        this.add(lb_maNV);
        this.add(lb_hoTen);
        this.add(lb_luong);
        this.add(lb_diaChi);
        this.add(panel);

    }





}
