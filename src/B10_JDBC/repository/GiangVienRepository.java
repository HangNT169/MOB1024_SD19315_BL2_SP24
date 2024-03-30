/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package B10_JDBC.repository;

import B10_JDBC.config.DBConnect;
import B10_JDBC.entity.GiangVien;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hangnt
 */
public class GiangVienRepository {
    // repository là tầng trao đổi trực tiếp 
    // với CSDL 
    // CRUD: 
    // C: CREATE <=> INSERT INTO 
    // R: READ <=> SELECT => excute Query => 1 danh sach 
    // U: UPDATE <=> UPDATE => excuteUpdate => Tra ra so dong thuc hien thanh cong 
    // D: DELETE <=> DELETE 

    // SELECT => 1 table 
    // 1 cau select k co dieu 
    // GET ALL => Hien thi tat ca 
    public ArrayList<GiangVien> getAll() {
        // B1: Viet cau SQL 
        String sql = """
                     SELECT  ten, loai, tuoi, bac, gioi_tinh, ma
                     FROM QLGV.dbo.giang_vien;
                     """;
        // B2: Mo cong ket noi => Xay ra ngoai le => nem vao try catch
        // try..with..resource
        // try(nhung doi tuong nao can dong ket noi ){
        //            
        // }catch(ten loi){
        //     // ngoai le
        // }
        // B3: Tao ArrayList 
        ArrayList<GiangVien> lists = new ArrayList<>();
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            // table => kieu du lieu ResultSet
            ResultSet rs = ps.executeQuery();
            // Khi kieu du lieu tra ve la 1 bang => executeQuery
            while (rs.next()) { // Kiem tra xem con co dong de doc tiep hay k 
                // B4: Tao doi tuong giang vien 
                GiangVien gv = new GiangVien();
                gv.setMaGV(rs.getString(6));
                gv.setLoai(rs.getString(2));
                gv.setTuoi(rs.getInt(3));
                gv.setTen(rs.getString(1));
                gv.setBac(rs.getInt(4));
                gv.setGioiTinh(rs.getBoolean(5));
                // B5: Add vao list 
                lists.add(gv);
            }
        } catch (Exception e) {
            e.printStackTrace(); // nem loi khi xay ra 
        }
        return lists;
    }

    public GiangVien getOne(String ma123) {
        String sql = """
                    SELECT ma, ten, loai, tuoi, bac, gioi_tinh
                     FROM QLGV.dbo.giang_vien
                     WHERE ma = ? 
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            // Set gia tri cho dau hoi cham 
            ps.setObject(1, ma123);
//            ps.setObject(1, tuoi);
            // table => kieu du lieu ResultSet
            ResultSet rs = ps.executeQuery();
            // Khi kieu du lieu tra ve la 1 bang => executeQuery
            while (rs.next()) { // Kiem tra xem con co dong de doc tiep hay k 
                // B4: Tao doi tuong giang vien 
                GiangVien gv = new GiangVien();
                gv.setMaGV(rs.getString(1));
                gv.setLoai(rs.getString(3));
                gv.setTen(rs.getString(2));
                gv.setBac(rs.getInt(5));
                gv.setTuoi(rs.getInt(4));
                gv.setGioiTinh(rs.getBoolean(6));
                return gv;
            }
        } catch (Exception e) {
            e.printStackTrace(); // nem loi khi xay ra 
        }
        return null;
    }

    public ArrayList<GiangVien> timKiemGiangVienTheoKhoangTuoi(int tuoiMin, int tuoiMax) {
        // B1: Viet cau SQL 
        String sql = """
                     SELECT * 
                     FROM giang_vien
                     WHERE tuoi >= ? 
                     AND tuoi <= ?
                     """;
        ArrayList<GiangVien> lists = new ArrayList<>();
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tuoiMin);
            ps.setObject(2, tuoiMax);
            // table => kieu du lieu ResultSet
            ResultSet rs = ps.executeQuery();
            // Khi kieu du lieu tra ve la 1 bang => executeQuery
            while (rs.next()) { // Kiem tra xem con co dong de doc tiep hay k 
                // B4: Tao doi tuong giang vien 
                GiangVien gv = new GiangVien();
                gv.setMaGV(rs.getString(6));
                gv.setLoai(rs.getString(2));
                gv.setTuoi(rs.getInt(3));
                gv.setTen(rs.getString(1));
                gv.setBac(rs.getInt(4));
                gv.setGioiTinh(rs.getBoolean(5));
                // B5: Add vao list 
                lists.add(gv);
            }
        } catch (Exception e) {
            e.printStackTrace(); // nem loi khi xay ra 
        }
        return lists;
    }

    // Add => CREATE => Insert into 
    public boolean add(GiangVien gv) {
        // B1: Viet cau SQL 
        String sql = """
                    INSERT INTO QLGV.dbo.giang_vien
                    (ma, ten, loai, tuoi, bac, gioi_tinh)
                    VALUES(?,?,?,?,?,?);
                     """;
        int check = 0; // false 
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, gv.getMaGV());
            ps.setObject(2, gv.getTen());
            ps.setObject(3, gv.getLoai());
            ps.setObject(4, gv.getTuoi());
            ps.setObject(6, gv.isGioiTinh());
            ps.setObject(5, gv.getBac());
            check = ps.executeUpdate(); // Tra ra so dong duoc add thanh cong 
        } catch (Exception e) {
            e.printStackTrace(); // nem loi khi xay ra 
        }
        return check > 0;
    }

    // DELETE <=> Delete  <=> Delete
     public boolean delete(String ma) {
        // B1: Viet cau SQL 
        String sql = """
                    DELETE FROM QLGV.dbo.giang_vien
                    WHERE ma=?;
                     """;
        int check = 0; // false 
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ma);
            check = ps.executeUpdate(); // Tra ra so dong duoc add thanh cong 
        } catch (Exception e) {
            e.printStackTrace(); // nem loi khi xay ra 
        }
        return check > 0;
    }

      // UPDATE <=> UPDATE  <=> UPDATE
     public boolean update(String ma, GiangVien newGiangVien) {
        // B1: Viet cau SQL 
        String sql = """
                    UPDATE giang_vien
                        SET ten= ? , loai = ?, tuoi = ? , bac = ?, gioi_tinh = ?
                    WHERE ma = ?;
                     """;
        int check = 0; // false 
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, newGiangVien.getTen());
            ps.setObject(2, newGiangVien.getLoai());
            ps.setObject(3, newGiangVien.getTuoi());
            ps.setObject(5, newGiangVien.isGioiTinh());
            ps.setObject(4, newGiangVien.getTuoi());
            ps.setObject(6, ma);
            check = ps.executeUpdate(); // Tra ra so dong duoc add thanh cong 
        } catch (Exception e) {
            e.printStackTrace(); // nem loi khi xay ra 
        }
        return check > 0;
    }

    public static void main(String[] args) {
//        System.out.println(new GiangVienRepository().getOne("TienNh21"));
        System.out.println(new GiangVienRepository().getAll());

//        GiangVien gv = new GiangVien("12312", "fsadf", "fsfd", 10, 10, true);
//        System.out.println(new GiangVienRepository().add(gv));
    }
}
