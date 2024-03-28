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
        // R: READ <=> SELECT
        // U: UPDATE <=> UPDATE 
        // D: DELETE <=> DELETE 
    
    // SELECT => 1 table 
    // 1 cau select k co dieu 
    // GET ALL => Hien thi tat ca 
    public ArrayList<GiangVien>getAll(){
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
        ArrayList<GiangVien>lists = new ArrayList<>();
        try(Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
                ) {
            // table => kieu du lieu ResultSet
           ResultSet rs = ps.executeQuery();
           // Khi kieu du lieu tra ve la 1 bang => executeQuery
           while(rs.next()){ // Kiem tra xem con co dong de doc tiep hay k 
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
    
    public GiangVien getOne(String ma123){
         String sql = """
                    SELECT ma, ten, loai, tuoi, bac, gioi_tinh
                     FROM QLGV.dbo.giang_vien
                     WHERE ma = ? 
                     """;
        try(Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
                ) {
            // Set gia tri cho dau hoi cham 
            ps.setObject(1, ma123);
//            ps.setObject(1, tuoi);
            // table => kieu du lieu ResultSet
           ResultSet rs = ps.executeQuery();
           // Khi kieu du lieu tra ve la 1 bang => executeQuery
           while(rs.next()){ // Kiem tra xem con co dong de doc tiep hay k 
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
    public static void main(String[] args) {
        System.out.println(new GiangVienRepository().getOne("TienNh21"));
    }
}
