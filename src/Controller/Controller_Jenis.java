/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAO_Interface;
import DAO.DAO_Jenis;
import Model.varJenis;
import View.FrmJenis;
import java.util.List;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author l
 */
public class Controller_Jenis {
    FrmJenis form;
    DAO_Interface<varJenis> model;
    List<varJenis> list;
    String[] header;
    
    //deklarasi kontruktor
    public Controller_Jenis(FrmJenis form){
    this.form = form;
    model = new DAO_Jenis();
    list = model.getAll();
    header = new String[]{"Kode Rekening", "Jenis Rekening"};
    form.getTblJenisRekening().setShowGrid(true);
    form.getTblJenisRekening().setShowVerticalLines(true);
    form.getTblJenisRekening().setGridColor(Color.blue);
    }
    
    public void reset(){
        form.getTxtKodeRekening().setText("");
        form.getTxtJenisRekening().setText("");
        form.getTxtKodeRekening().requestFocus();
        isiTabel();
    }

    public void isiTabel() {
        list = model.getAll();
        DefaultTableModel tblModel = new DefaultTableModel (new Object [][]{},header){
            public boolean isCellEditable(int rowIndex, int columnIndex){
            return false;
            }
        };
        Object[] data = new Object[header.length];
        for(varJenis objJns : list){
            data[0]=objJns.getvKodeRekening();
            data[1]=objJns.getvJenisRekening();
            tblModel.addRow(data);
        }
        form.getTblJenisRekening().setModel(tblModel);
    }
    
    public void isiField(int row){
        form.getTxtKodeRekening().setText(list.get(row).getvKodeRekening());
        form.getTxtJenisRekening().setText(list.get(row).getvJenisRekening());
    }
   
    public void insert(){
        // membuat objek
        varJenis objJns = new varJenis();
        
        // mengisi variabel objJns dengan data yang dientri
        objJns.setvKodeRekening(form.getTxtKodeRekening().getText());
        objJns.setvJenisRekening(form.getTxtJenisRekening().getText());
        // menjalankan method insert yang ada di DAO_Jenis
        model.insert(objJns);
    }
    
    public void update(){
        // membuat objek
        varJenis objJns= new varJenis();
        
        // mengisi variabel objJenis dengan data yang dientri
        objJns.setvKodeRekening(form.getTxtKodeRekening().getText());
        objJns.setvJenisRekening(form.getTxtJenisRekening().getText());
      
        // menjalankan method insert yang ada di DAO_Jenis
        model.update(objJns);
    }
    
    public void delete(){
     if(!form.getTxtKodeRekening().getText().trim().isEmpty()){
         // mengisi variabel objJns dengan data yang dientri
         String koderekening = form.getTxtKodeRekening().getText();
         
         // menjalankan method update yang ada di DAO_Jenis
         model.delete(koderekening);
     }
     else{
         JOptionPane.showMessageDialog(form, "Pilih data yang akan di hapus");
        }
    }
    
    public void isiTabelCari() {
        list = model.getCari(form.getTxtKodeRekening().getText().trim());
        DefaultTableModel tblModel = new DefaultTableModel (new Object [][]{},header);
        Object[] data = new Object[header.length];
        for(varJenis objJns : list){
            data[0]=objJns.getvKodeRekening();
            data[1]=objJns.getvJenisRekening();
            tblModel.addRow(data);
        }
        form.getTblJenisRekening().setModel(tblModel);
    }
    
}