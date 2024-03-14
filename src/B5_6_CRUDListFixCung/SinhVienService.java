/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package B5_6_CRUDListFixCung;

import java.util.ArrayList;

/**
 *
 * @author hangnt
 */
public class SinhVienService {

    // Xu Ly Logic cua toan bo project 
    private ArrayList<SinhVien> lists = new ArrayList<>();

    public SinhVienService() {
        // fake du lieu 5 ban ghi 
        lists.add(new SinhVien("He130461", "DungNA29", 10, "Ha noi", true));
        lists.add(new SinhVien("He130462", "DungNA30", 11, "Ha noi1", true));
        lists.add(new SinhVien("He130463", "DungNA31", 10, "Ha noi", false));
        lists.add(new SinhVien("He130464", "DungNA34", 11, "Ha noi2", true));
        lists.add(new SinhVien("He130465", "DungNA33", 10, "Ha noi", true));
    }

    public ArrayList<SinhVien> getAll() {
        return lists;
    }

}
