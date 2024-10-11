package GLuong;

import java.sql.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.List;

public class XLLuong {

    public Connection getCon() throws SQLException {

        String connectionString = "jdbc:sqlserver://" +
                "LAPTOP-RT1ETNCP\\LYDUYBACH;" +
                "database=DLLuong;" +
                "user=sa;" +
                "password=bach2612;" +
                "encrypt=true;" +
                "trustServerCertificate=true;";
        return DriverManager.getConnection(connectionString);

    }

    public List<NhanVien> getNV() throws SQLException {

        Connection connection = getCon();
        List<NhanVien> nvs = new ArrayList<NhanVien>();
        String query = "SELECT * FROM tbNhanvien";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            NhanVien nv = new NhanVien();
            nv.setMaNV(rs.getString(1));
            nv.setHoTen(rs.getString(2));
            nv.setDiaChi(rs.getString(3));
            nv.setLuong(rs.getDouble(4));
            nvs.add(nv);
        }
        return nvs;

    }

    public List<NhanVien> getNVbyMa(String ma) throws SQLException {

        Connection connection = getCon();
        List<NhanVien> nvs = new ArrayList<NhanVien>();
        String query = "SELECT * FROM tbNhanvien WHERE maNV = '" + ma + "'";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            NhanVien nv = new NhanVien();
            nv.setMaNV(rs.getString(1));
            nv.setHoTen(rs.getString(2));
            nv.setDiaChi(rs.getString(3));
            nv.setLuong(rs.getDouble(4));
            nvs.add(nv);
        }
        return nvs;

    }

    public void updateNV(NhanVien nv, String ma) throws SQLException {

        Connection connection = getCon();
        String query = String.format("UPDATE tbNhanvien SET maNV = ?, HoTen = ?, DiaChi = ?, Luong = ? WHERE maNV = %s", ma);
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, nv.getMaNV());
        stmt.setString(2, nv.getHoTen());
        stmt.setString(3, nv.getDiaChi());
        stmt.setDouble(4, nv.getLuong());
        if (stmt.executeUpdate() > 0) {
            System.out.println("Updated employee");
        }
        else {
            System.out.println("Failed to update employee");
        }

    }

}
