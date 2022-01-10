/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo2;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author YUDHA PANGESTU PRATAMA
 */
public class dbCRUD {
 
    String jdbcUrl  = "jdbc:mysql://localhost:3306/19630831_kesehatan";
    String username = "root";
    String password = "";
    
    
 public dbCRUD(){      }
 
  public dbCRUD(String url, String user, String pass){
     
     try(Connection Koneksi = DriverManager.getConnection(url, user, pass)) {
       Driver mysqldriver = new com.mysql.jdbc.Driver();  
       DriverManager.registerDriver(mysqldriver);
     
       System.out.println("Berhasil");
       }  catch (SQLException error){
       System.err.println(error.toString());
       }        
     }
  
  public Connection getKoneksi() throws SQLException{
      try{
          Driver mysqldriver = new com.mysql.jdbc.Driver();
          DriverManager.registerDriver(mysqldriver);
          
      }catch (SQLException e) {
        System.err.println(e.toString());
      }
      
      return DriverManager.getConnection(this.jdbcUrl, this.username, this.password);
  }
  
  public boolean duplicateKey(String table, String PrimaryKey, String value){
      boolean hasil = false;
      
      try{
          Statement sts = getKoneksi().createStatement();
          ResultSet rs  = sts.executeQuery("SELECT*FROM"+" "+table+" "+"WHERE"+" "+PrimaryKey+" ='"+value+"'");
   
          hasil = rs.next();
          
          rs.close();
          sts.close();
          getKoneksi().close();
      } catch (Exception e) { 
          System.err.println(e.toString());
      }
      
      return hasil;
  }

  // pasien
  public void simpanpasien(String kode_pasien, String nama_pasien, String umur, String jenis_kelamin, String alamat){
      try {
      if (duplicateKey("pasien","kode_pasien",kode_pasien) ) {  
          JOptionPane.showMessageDialog(null, "Kode Sudah Terdaftar");
      } else{
         String SQL = "INSERT INTO pasien (kode_pasien,nama_pasien,umur,jenis_kelamin,alamat)VALUE('"+kode_pasien+"','"+nama_pasien+"','"+umur+"','"+jenis_kelamin+"','"+alamat+"')";
         Statement perintah = getKoneksi().createStatement();
      
          perintah.executeUpdate(SQL);
          perintah.close();
          getKoneksi().close();
          
          JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
      }       
      } catch (Exception e){
         System.err.println(e.toString());  
      }
  }
  public void ubahpasien(String kode_pasien, String nama_pasien, String umur, String jenis_kelamin, String alamat){
      try{
      {
         String SQL ="UPDATE pasien  SET  kode_pasien='"+kode_pasien+"', nama_pasien='"+nama_pasien+"', umur='"+umur+"', jenis_kelamin='"+jenis_kelamin+"', alamat='"+alamat+"' WHERE kode_pasien='"+kode_pasien+"'";
         SQL = String.format(SQL,kode_pasien,nama_pasien,umur,jenis_kelamin,alamat);
         Statement perintah = getKoneksi().createStatement() ;
              
          perintah.executeUpdate(SQL);
          perintah.close();
          getKoneksi().close();
    
          JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
      }       
      } catch (Exception e){
         System.err.println(e.toString());  
      }
  }
  public void hapuspasien(String kode_pasien, String nama_pasien, String umur, String jenis_kelamin, String alamat){
      try{
      {
         String SQL ="DELETE FROM pasien WHERE kode_pasien='"+kode_pasien+"'"; 
         SQL = String.format(SQL,kode_pasien,nama_pasien,umur,jenis_kelamin,alamat);
         Statement perintah = getKoneksi().createStatement() ;
              
          perintah.executeUpdate(SQL);
          perintah.close();
          getKoneksi().close();
    
          JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
      }       
      } catch (Exception e){
        System.err.println(e.toString());  
      }
  }
  
  //dokter
  public void simpandokter(String kode_dokter, String nama_dokter, String jenis_kelamin, String alamat, String kota, String telepon){
      try {
      if (duplicateKey("dokter","kode_dokter",kode_dokter) ) {  
          JOptionPane.showMessageDialog(null, "Kode Sudah Terdaftar");
      } else{
         String SQL = "INSERT INTO dokter (kode_dokter,nama_dokter,jenis_kelamin,alamat,kota,telepon)VALUE('"+kode_dokter+"','"+nama_dokter+"','"+jenis_kelamin+"','"+alamat+"','"+kota+"','"+telepon+"')";
         Statement perintah = getKoneksi().createStatement();
      
          perintah.executeUpdate(SQL);
          perintah.close();
          getKoneksi().close();
          
          JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
      }       
      } catch (Exception e){
         System.err.println(e.toString());  
      }
  }
  public void ubahdokter(String kode_dokter, String nama_dokter, String jenis_kelamin, String alamat, String kota, String telepon){
      try{
      {
         String SQL ="UPDATE dokter SET  kode_dokter='"+kode_dokter+"', nama_dokter='"+nama_dokter+"', jenis_kelamin='"+jenis_kelamin+"', alamat='"+alamat+"', kota='"+kota+"', telepon='"+telepon+"' WHERE kode_dokter='"+kode_dokter+"'";
         SQL = String.format(SQL,kode_dokter,nama_dokter,jenis_kelamin,alamat,kota,telepon);
         Statement perintah = getKoneksi().createStatement() ;
              
          perintah.executeUpdate(SQL);
          perintah.close();
          getKoneksi().close();
    
          JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
      }       
      } catch (Exception e){
         System.err.println(e.toString());  
      }
  }
  public void hapusdokter(String kode_dokter, String nama_dokter, String jenis_kelamin, String alamat, String kota, String telepon){
      try{
      {
         String SQL ="DELETE FROM dokter WHERE kode_dokter='"+kode_dokter+"'"; 
         SQL = String.format(SQL,kode_dokter,nama_dokter,jenis_kelamin,alamat,kota,telepon);
         Statement perintah = getKoneksi().createStatement() ;
              
          perintah.executeUpdate(SQL);
          perintah.close();
          getKoneksi().close();
    
          JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
      }       
      } catch (Exception e){
        System.err.println(e.toString());  
      }
  }

///perawat
  public void simpanperawat(String kode_perawat, String nama_perawat, String jenis_kelamin, String alamat, String kota, String telepon){
      try {
      if (duplicateKey("perawat","kode_perawat",kode_perawat) ) {  
          JOptionPane.showMessageDialog(null, "Kode Sudah Terdaftar");
      } else{
         String SQL = "INSERT INTO perawat (kode_perawat,nama_perawat,jenis_kelamin,alamat,kota,telepon)VALUE('"+kode_perawat+"','"+nama_perawat+"','"+jenis_kelamin+"','"+alamat+"','"+kota+"','"+telepon+"')";
         Statement perintah = getKoneksi().createStatement();
      
          perintah.executeUpdate(SQL);
          perintah.close();
          getKoneksi().close();
          
          JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
      }       
      } catch (Exception e){
         System.err.println(e.toString());  
      }
  }
  public void ubahperawat(String kode_perawat, String nama_perawat, String jenis_kelamin, String alamat, String kota, String telepon){
      try{
      {
         String SQL ="UPDATE perawat  SET  kode_perawat='"+kode_perawat+"', nama_perawat='"+nama_perawat+"', jenis_kelamin='"+jenis_kelamin+"', alamat='"+alamat+"', kota='"+kota+"', telepon='"+telepon+"' WHERE kode_perawat='"+kode_perawat+"'";
         SQL = String.format(SQL,kode_perawat,nama_perawat,jenis_kelamin,alamat,kota,telepon);
         Statement perintah = getKoneksi().createStatement() ;
              
          perintah.executeUpdate(SQL);
          perintah.close();
          getKoneksi().close();
    
          JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
      }       
      } catch (Exception e){
         System.err.println(e.toString());  
      }
  }
  public void hapusperawat(String kode_perawat, String nama_perawat, String jenis_kelamin, String alamat, String kota, String telepon){
      try{
      {
         String SQL ="DELETE FROM perawat WHERE kod_perawat='"+kode_perawat+"'"; 
         SQL = String.format(SQL,kode_perawat,nama_perawat,jenis_kelamin,alamat,kota,telepon);
         Statement perintah = getKoneksi().createStatement() ;
              
          perintah.executeUpdate(SQL);
          perintah.close();
          getKoneksi().close();
    
          JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
      }       
      } catch (Exception e){
        System.err.println(e.toString());  
      
      }
  }
  
  ///ruang
  public void simpanruang(String kode_ruang, String nama_ruang, String kode_perawat ){
      try {
      if (duplicateKey("ruang","kode_ruang",kode_ruang) ) {  
          JOptionPane.showMessageDialog(null, "Kode Sudah Terdaftar");
      } else{
         String SQL = "INSERT INTO ruang (kode_ruang,nama_ruang,kode_perawat)VALUE('"+kode_ruang+"','"+nama_ruang+"','"+kode_perawat+"')";
         Statement perintah = getKoneksi().createStatement();
      
          perintah.executeUpdate(SQL);
          perintah.close();
          getKoneksi().close();
          
          JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
      }       
      } catch (Exception e){
         System.err.println(e.toString());  
      }
  } 
  public void ubahruang(String kode_ruang, String nama_ruang, String kode_perawat ){
      try{
      {
         String SQL ="UPDATE ruang  SET nama_ruang='"+nama_ruang+"', kode_perawat='"+kode_perawat+"' WHERE kode_ruang='"+kode_ruang+"'";
         SQL = String.format(SQL,kode_ruang,nama_ruang,kode_perawat);
         Statement perintah = getKoneksi().createStatement() ;
              
          perintah.executeUpdate(SQL);
          perintah.close();
          getKoneksi().close();
    
          JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
      }       
      } catch (Exception e){
         System.err.println(e.toString());  
      }
  }
  public void hapusruang(String kode_ruang, String nama_ruang, String kode_perawat ){
      try{
      {
         String SQL ="DELETE FROM ruang WHERE kode_ruang='"+kode_ruang+"'"; 
         SQL = String.format(SQL,kode_ruang,nama_ruang,kode_perawat);
         Statement perintah = getKoneksi().createStatement() ;
              
          perintah.executeUpdate(SQL);
          perintah.close();
          getKoneksi().close();
    
          JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
      }       
      } catch (Exception e){
        System.err.println(e.toString());  
      
      }
  }
 
  // pasien prepareStatement
  public void simpanpasienp(String kode_pasien, String nama_pasien, String umur, String jenis_kelamin, String alamat){
        try{
             if (duplicateKey("pasien","kode_pasien",kode_pasien)){
                JOptionPane.showMessageDialog(null, "Kode sudah terdaftar");
        } else{
                String SQL = "INSERT INTO pasien (kode_pasien,nama_pasien,umur,jenis_kelamin,alamat) VALUE (?,?,?,?,?)";
                PreparedStatement simpan = getKoneksi().prepareStatement(SQL);
                simpan.setString(1, kode_pasien);
                simpan.setString(2, nama_pasien);
                simpan.setString(3, umur);
                simpan.setString(4, jenis_kelamin);
                simpan.setString(5, alamat);

                simpan.executeUpdate();
                System.out.println("Data berhasil disimpan");
                simpan.close();
                getKoneksi().close();
            }
    } catch (Exception e){
        System.out.println(e.toString());
    }
}
  public void ubahpasienp(String kode_pasien, String nama_pasien, String umur, String jenis_kelamin, String alamat){
        try{
            {
                String SQL ="UPDATE pasien SET nama_pasien=?, umur=?, jenis_kelamin=?, alamat=? WHERE kode_pasien=?";
                SQL = String.format(SQL,kode_pasien,nama_pasien,umur,jenis_kelamin,alamat);
                PreparedStatement simpan = getKoneksi().prepareStatement(SQL);
                
                simpan.setString(1, kode_pasien);
                simpan.setString(2, nama_pasien);
                simpan.setString(3, umur);
                simpan.setString(4, jenis_kelamin);
                simpan.setString(5, alamat);
        
                simpan.executeUpdate();
                System.out.println("Data berhasil diubah");
                simpan.close();
                getKoneksi().close();
            }
    } catch (Exception e){
        System.out.println(e.toString());
    } 
}
  public void hapuspasienp(String kode_pasien, String nama_pasien, String umur, String jenis_kelamin, String alamat){
        try{
            {
                String SQL ="DELETE FROM pasien WHERE kode_pasien=?"; 
                PreparedStatement simpan = getKoneksi().prepareStatement(SQL);   
                simpan.setString(1, kode_pasien);
                simpan.executeUpdate();
                
                System.out.println("Data berhasil dihapus");
                simpan.close();
                getKoneksi().close();
            }
    } catch (Exception e){
        System.out.println(e.toString());
    }
        
     
}
    
    // dokter prepareStatement
  public void simpandokterp(String  kode_dokter, String nama_dokter, String jenis_kelamin, String alamat, String kota, String telepon){
        try{
             if (duplicateKey("dokter","kode_dokter",kode_dokter)){
                JOptionPane.showMessageDialog(null, "Kode sudah terdaftar");
        } else{
                String SQL = "INSERT INTO dokter (kode_dokter,nama_dokter,jenis_kelamin,alamat,kota,telepon) VALUE (?,?,?,?,?,?)";
                PreparedStatement simpan = getKoneksi().prepareStatement(SQL);
                simpan.setString(1, kode_dokter);
                simpan.setString(2, nama_dokter);
                simpan.setString(3, jenis_kelamin);
                simpan.setString(4, alamat);
                simpan.setString(5, kota);
                simpan.setString(6, telepon);

                simpan.executeUpdate();
                System.out.println("Data berhasil disimpan");
                simpan.close();
                getKoneksi().close();
            }
    } catch (Exception e){
        System.out.println(e.toString());
    }
}
  public void ubahdokterp(String kode_dokter, String nama_dokter, String jenis_kelamin, String alamat, String kota, String telepon){
        try{
            {
                String SQL ="UPDATE dokter SET nama_dokter=?, jenis_kelamin=?, alamat=?, kota?, telepon? WHERE kode_dokter=?";
                SQL = String.format(SQL,kode_dokter,nama_dokter,jenis_kelamin,alamat,kota,telepon);
                PreparedStatement simpan = getKoneksi().prepareStatement(SQL);
                simpan.setString(1, kode_dokter);
                simpan.setString(2, nama_dokter);
                simpan.setString(3, jenis_kelamin);
                simpan.setString(4, alamat);
                simpan.setString(5, kota);
                simpan.setString(6, telepon);
                
                simpan.executeUpdate();
                System.out.println("Data berhasil diubah");
                simpan.close();
                getKoneksi().close();
            }
    } catch (Exception e){
        System.out.println(e.toString());
    } 
}
  public void hapusdokterp(String kode_dokter, String nama_dokter, String jenis_kelamin, String alamat, String kota, String telepon){
        try{
            {
                String SQL ="DELETE FROM dokter WHERE kode_dokter=?"; 
                PreparedStatement simpan = getKoneksi().prepareStatement(SQL);   
                simpan.setString(1, kode_dokter);
                simpan.executeUpdate();
                
                System.out.println("Data berhasil dihapus");
                simpan.close();
                getKoneksi().close();
            }
    } catch (Exception e){
        System.out.println(e.toString());
    }
        
     
}
  
  //perawat prepareStatement
  public void simpanperawatp(String kode_perawat, String nama_perawat, String jenis_kelamin, String alamat, String kota, String telepon){
        try{
             if (duplicateKey("perawat","kode_perawat",kode_perawat)){
                JOptionPane.showMessageDialog(null, "Kode sudah terdaftar");
        } else{
                String SQL = "INSERT INTO perawat (kode_perawat,nama_perawat,jenis_kelamin,alamat,kota,telepon) VALUE (?,?,?,?,?,?)";
                PreparedStatement simpan = getKoneksi().prepareStatement(SQL);
                simpan.setString(1, kode_perawat);
                simpan.setString(2, nama_perawat);
                simpan.setString(3, jenis_kelamin);
                simpan.setString(4, alamat);
                simpan.setString(5, kota);
                simpan.setString(6, telepon);

                simpan.executeUpdate();
                System.out.println("Data berhasil disimpan");
                simpan.close();
                getKoneksi().close();
            }
    } catch (Exception e){
        System.out.println(e.toString());
    }
}
  public void ubahperawatp(String kode_perawat, String nama_perawat, String jenis_kelamin, String alamat, String kota, String telepon){
        try{
            {
                String SQL ="UPDATE  SET nama_perawat=?, jenis_kelamin=?, alamat=?, kota?, telepon? WHERE kode_perawat=?";
                SQL = String.format(SQL,nama_perawat,jenis_kelamin,alamat,kota,telepon);
                PreparedStatement simpan = getKoneksi().prepareStatement(SQL);
                
                simpan.setString(1, kode_perawat);
                simpan.setString(2, nama_perawat);
                simpan.setString(3, jenis_kelamin);
                simpan.setString(4, alamat);
                simpan.setString(5, kota);
                simpan.setString(6, telepon);

                simpan.executeUpdate();
                System.out.println("Data berhasil diubah");
                simpan.close();
                getKoneksi().close();
            }
    } catch (Exception e){
        System.out.println(e.toString());
    } 
}
  public void hapusperawatp(String kode_perawat, String nama_perawat, String jenis_kelamin, String alamat, String kota, String telepon){
        try{
            {
                String SQL ="DELETE FROM perawat WHERE kode_perawat=?"; 
                PreparedStatement simpan = getKoneksi().prepareStatement(SQL);   
                simpan.setString(1, kode_perawat);
                simpan.executeUpdate();
                
                System.out.println("Data berhasil dihapus");
                simpan.close();
                getKoneksi().close();
            }
    } catch (Exception e){
        System.out.println(e.toString());
    }
        
     
}
  
  //ruang prepareStatement
  public void simpanruangp(String kode_ruang, String nama_ruang, String kode_perawat){
        try{
         if (duplicateKey("ruang","kode_ruang",kode_ruang) ) {  
                JOptionPane.showMessageDialog(null, "Kode sudah terdaftar");
        } else{
                String SQL = "INSERT INTO ruang (kode_ruang,nama_ruang,kode_perawat) VALUE (?,?,?)";
                PreparedStatement simpan = getKoneksi().prepareStatement(SQL);
                simpan.setString(1, kode_ruang);
                simpan.setString(2, nama_ruang);
                simpan.setString(3, kode_perawat);
                
                simpan.executeUpdate();
                System.out.println("Data berhasil disimpan");
                simpan.close();
                getKoneksi().close();
            }
    } catch (Exception e){
        System.out.println(e.toString());
    }
}
  public void ubahruangp(String kode_ruang, String nama_ruang, String kode_perawat){
        try{
            {
                String SQL ="UPDATE ruang SET nama_ruang=?, kode_perawat=?  WHERE kode_ruang=?";
                SQL = String.format(SQL,kode_ruang,nama_ruang,kode_perawat);
                PreparedStatement simpan = getKoneksi().prepareStatement(SQL);
                
                simpan.setString(1, kode_ruang);
                simpan.setString(2, nama_ruang);
                simpan.setString(3, kode_perawat);

                simpan.executeUpdate();
                System.out.println("Data berhasil diubah");
                simpan.close();
                getKoneksi().close();
            }
    } catch (Exception e){
        System.out.println(e.toString());
    } 
}
  public void hapusruangp(String kode_ruang, String nama_ruang, String kode_perawat){
        try{
            {
                String SQL ="DELETE FROM ruang WHERE kode_ruang=?"; 
                PreparedStatement simpan = getKoneksi().prepareStatement(SQL);   
                simpan.setString(1, kode_ruang);
                simpan.executeUpdate();
                
                System.out.println("Data berhasil dihapus");
                simpan.close();
                getKoneksi().close();
            }
    } catch (Exception e){
        System.out.println(e.toString());
    }   
}
  
  
}