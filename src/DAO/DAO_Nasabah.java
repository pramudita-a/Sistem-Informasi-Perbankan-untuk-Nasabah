/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Koneksi.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import Model.varNasabah;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author DELL
 */
public class DAO_Nasabah implements DAO_Interface<varNasabah>{
    Connection connection;
    public DAO_Nasabah(){
        connection = Database.KoneksiDB();
    }
    
    String INSERT = "INSERT INTO nasabah (No_Rekening, Nama_Nasabah, Jenis_Rekening, Saldo) VALUES(?,?,?,?)";
    String UPDATE = "UPDATE nasabah set Nama_Nasabah=?, Jenis_Rekening=?, Saldo=? WHERE No_Rekening=?";
    String DELETE = "DELETE FROM nasabah WHERE No_Rekening=?";
    String SELECT = "SELECT * FROM nasabah ";
    String CARI = "SELECT * FROM nasabah WHERE No_Rekening=?";
    
    public List<varNasabah> isicomboJenis(){
        PreparedStatement statement;
        List<varNasabah> list = null;
        try{
            list = new ArrayList();
            statement = connection.prepareStatement("SELECT DISTINCT Jenis_Rekening FROM Jenis ORDER BY Jenis_Rekening");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                varNasabah b = new varNasabah();
                b.setvJenisRekening(rs.getString("Jenis_Rekening"));
                list.add(b);
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void insert(varNasabah object) {
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement(CARI);
            st.setString(1, object.getvNoRekening()); 
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                JOptionPane.showMessageDialog(null, "Data sudah pernah disimpan");
            }
            else{
                st = null;
                st= connection.prepareStatement(INSERT);
                st.setString(1,object.getvNoRekening());
                st.setString(2,object.getvNamaNasabah());
                st.setString(3,object.getvJenisRekening());
                st.setInt(4,object.getvSaldo());
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
    public void update(varNasabah object) {
           PreparedStatement st = null;
        try{
                st = null;
                st= connection.prepareStatement(UPDATE);
                st.setString(1,object.getvNamaNasabah());
                st.setString(2,object.getvJenisRekening());
                st.setInt(3,object.getvSaldo());
                st.setString(4,object.getvNoRekening());
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di ubah");
                st.close();
            }
        catch(Exception e){
            e.printStackTrace();
        }    
    }

    @Override
    public void delete(String norekening) {
          PreparedStatement st = null;
        try{
                st = null;
                st= connection.prepareStatement(DELETE);
                st.setString(1, norekening);
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                st.close();
            }
        catch(Exception e){
            e.printStackTrace();
        }    
    }    
    

    @Override
    public List<varNasabah> getAll() {
        List<varNasabah> list = null;
        PreparedStatement st = null;
        try{
                st = null;
                list = new ArrayList<varNasabah>();
                st= connection.prepareStatement(SELECT);
                ResultSet rs = st.executeQuery();
                while (rs.next()){
                    varNasabah objJns = new varNasabah();
                    objJns.setvNoRekening(rs.getString("No_Rekening"));
                    objJns.setvNamaNasabah(rs.getString("Nama_Nasabah"));
                    objJns.setvJenisRekening(rs.getString("Jenis_Rekening"));
                    objJns.setvSaldo(rs.getInt("Saldo"));
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
    public List<varNasabah> getCari(String key) {
        List<varNasabah> list = null;
        PreparedStatement st = null;
        try{
                st = null;
                list = new ArrayList<varNasabah>();
                st = connection.prepareStatement(SELECT);
                st.setString(1, "%"+key+"%");
                ResultSet rs = st.executeQuery();
                while (rs.next()){
                    varNasabah objJns = new varNasabah();
                    objJns.setvNoRekening(rs.getString("No_Rekening"));
                    objJns.setvNamaNasabah(rs.getString("Nama_Nasabah"));
                    objJns.setvJenisRekening(rs.getString("Jenis_Rekening"));
                    objJns.setvSaldo(rs.getInt("Saldo"));
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
