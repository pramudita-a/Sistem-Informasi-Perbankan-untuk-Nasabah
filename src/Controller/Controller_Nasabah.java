/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAO_Interface;
import DAO.DAO_Nasabah;
import Model.varNasabah;
import View.FrmNasabah;
import java.util.List;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author l
 */
public class Controller_Nasabah {
    FrmNasabah form;
    DAO_Interface<varNasabah> model;
    DAO_Nasabah model_internal;
    List<varNasabah> list;
    String[] header;
    
    //deklarasi kontruktor
    public Controller_Nasabah(FrmNasabah form){
    this.form = form;
    model = new DAO_Nasabah();
    model_internal = new DAO_Nasabah();
    list = model.getAll();
    header = new String[]{"No Rekening", "Nama Nasabah", "Jenis Rekeing", "Saldo" };
    form.getTblNasabah().setShowGrid(true);
    form.getTblNasabah().setShowVerticalLines(true);
    form.getTblNasabah().setGridColor(Color.blue);
    }
    
    public void reset(){
        form.getCboJenisRekening().setSelectedItem("Pilih");
        form.getTxtNoRekening().setText("");
        form.getTxtNamaNasabah().setText("");
        isicomboJenisRekening();
        form.getTxtSaldo().setText("");
        form.getTxtNoRekening().requestFocus();
        isiTabel();
    }
    
        public void isicomboJenisRekening(){
        form.getCboJenisRekening().removeAllItems();
        form.getCboJenisRekening().addItem("-Pilih-");
        for (varNasabah b : model_internal.isicomboJenis()){
            form.getCboJenisRekening().addItem(b.getvJenisRekening());
        }
    }
    
    public void isiTabel() {
        list = model.getAll();
        DefaultTableModel tblModel = new DefaultTableModel (new Object [][]{},header){
            public boolean isCellEditable(int rowIndex, int columnIndex){
            return false;
            }
        };
        Object[] data = new Object[header.length];
        for(varNasabah objNsb : list){
            data[0]=objNsb.getvNoRekening();
            data[1]=objNsb.getvNamaNasabah();
            data[2]=objNsb.getvJenisRekening();
            data[3]=objNsb.getvSaldo();            
            tblModel.addRow(data);
        }
        form.getTblNasabah().setModel(tblModel);
    }
    
    public void isiField(int row){
        form.getTxtNoRekening().setText(list.get(row).getvNoRekening());
        form.getTxtNamaNasabah().setText(list.get(row).getvNamaNasabah());
        form.getCboJenisRekening().setSelectedItem(list.get(row).getvJenisRekening());
        form.getTxtSaldo().setText(Integer.toString(list.get(row).getvSaldo()));
    }
   
    public void insert(){
        // membuat objek
        varNasabah objNsb = new varNasabah();
        
        // mengisi variabel objNsb dengan data yang dientri
        objNsb.setvNoRekening(form.getTxtNoRekening().getText());
        objNsb.setvNamaNasabah(form.getTxtNamaNasabah().getText());
        objNsb.setvJenisRekening(""+form.getCboJenisRekening().getSelectedItem());
        objNsb.setvSaldo (Integer.parseInt(form.getTxtSaldo().getText()));

        // menjalankan method insert yang ada di DAO_Nasabah
        model.insert(objNsb);
    }
    
    public void update(){
        // membuat objek
        varNasabah objNsb= new varNasabah();
        
        // mengisi variabel objNsb dengan data yang dientri
        objNsb.setvNoRekening(form.getTxtNoRekening().getText());
        objNsb.setvNamaNasabah(form.getTxtNamaNasabah().getText());
        objNsb.setvJenisRekening(""+form.getCboJenisRekening().getSelectedItem());
        objNsb.setvSaldo(Integer.parseInt(form.getTxtSaldo().getText()));
        
        // menjalankan method insert yang ada di DAO_Nasabah
        model.update(objNsb);
    }
    
    public void delete(){
     if(!form.getTxtNoRekening().getText().trim().isEmpty()){
         // mengisi variabel objNsb dengan data yang dientri
         String norekening = form.getTxtNoRekening().getText();
         
         // menjalankan method update yang ada di DAO_Nasabah
         model.delete(norekening);
     }
     else{
         JOptionPane.showMessageDialog(form, "Pilih data yang akan di hapus");
        }
    }
    
    public void isiTabelCari() {
        list = model.getCari(form.getTxtNoRekening().getText().trim());
        DefaultTableModel tblModel = new DefaultTableModel (new Object [][]{},header);
        Object[] data = new Object[header.length];
        for(varNasabah objNsb : list){
            data[0]=objNsb.getvNoRekening();
            data[1]=objNsb.getvNamaNasabah();
            data[2]=objNsb.getvJenisRekening();
            data[3]=objNsb.getvSaldo();
            tblModel.addRow(data);
        }
        form.getTblNasabah().setModel(tblModel);
    }
    
}