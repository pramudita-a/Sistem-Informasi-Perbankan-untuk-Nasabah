/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Koneksi.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import Model.varJenis;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author DELL
 */
public class DAO_Jenis implements DAO_Interface<varJenis>{
    Connection connection;
    public DAO_Jenis(){
        connection = Database.KoneksiDB();
    }
    
    String INSERT = "INSERT INTO Jenis (Kode_Rekening, Jenis_Rekening) VALUES(?,?)";
    String UPDATE = "UPDATE jenis set Jenis_Rekening=? WHERE Kode_Rekening=?";
    String DELETE = "DELETE FROM jenis WHERE Kode_Rekening=?";
    String SELECT = "SELECT * FROM jenis ";
    String CARI = "SELECT * FROM jenis WHERE Kode_Rekening=?";
    
    @Override
    public void insert(varJenis object) {
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement(CARI);
            st.setString(1, object.getvKodeRekening()); 
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                JOptionPane.showMessageDialog(null, "Data sudah pernah disimpan");
            }
            else{
                st = null;
                st= connection.prepareStatement(INSERT);
                st.setString(1,object.getvKodeRekening());
                st.setString(2,object.getvJenisRekening());
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di simpan");
            }
            st.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }    
    }

    @Override
    public void update(varJenis object) {
           PreparedStatement st = null;
        try{
                st = null;
                st= connection.prepareStatement(UPDATE);
                st.setString(1,object.getvJenisRekening());
                st.setString(2,object.getvKodeRekening());
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di ubah");
                st.close();
            }
        catch(Exception e){
            e.printStackTrace();
        }    
    }

    @Override
    public void delete(String koderekening) {
          PreparedStatement st = null;
        try{
                st = null;
                st= connection.prepareStatement(DELETE);
                st.setString(1, koderekening);
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                st.close();
            }
        catch(Exception e){
            e.printStackTrace();
        }    
    }    
    

    @Override
    public List<varJenis> getAll() {
        List<varJenis> list = null;
        PreparedStatement st = null;
        try{
                st = null;
                list = new ArrayList<varJenis>();
                st= connection.prepareStatement(SELECT);
                ResultSet rs = st.executeQuery();
                while (rs.next()){
                    varJenis objJns = new varJenis();
                    objJns.setvKodeRekening(rs.getString("Kode_Rekening"));
                    objJns.setvJenisRekening(rs.getString("Jenis_Rekening"));
                    list.add(objJns);
                }
                st.close();
            }
        catch(Exception e){
            e.printStackTrace();
        }    
        return list;
    }

    @Override
    public List<varJenis> getCari(String key) {
        List<varJenis> list = null;
        PreparedStatement st = null;
        try{
                st = null;
                list = new ArrayList<varJenis>();
                st = connection.prepareStatement(SELECT);
                st.setString(1, "%"+key+"%");
                ResultSet rs = st.executeQuery();
                while (rs.next()){
                    varJenis objJns = new varJenis();
                    objJns.setvKodeRekening(rs.getString("Kode_Rekening"));
                    objJns.setvJenisRekening(rs.getString("Jenis_Rekening"));
                    list.add(objJns);
                }
                st.close();
            }
        catch(Exception e){
            e.printStackTrace();
        }    
        return list;
    }
    
}
