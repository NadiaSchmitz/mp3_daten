import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;

public class Main {

	public static void main(String[] args) {
		
		long size = 0;
		byte bTAG[] = new byte[3];
		byte bTitel[] = new byte[30];
		byte bInterpret[] = new byte[30];
		byte bCDTitel[] = new byte[30];
		byte bJahr[] = new byte[4];
		byte bKommentar[] = new byte[30];
		byte bGenre = 0;
		
		JFileChooser file_mp3 = new JFileChooser();
		file_mp3.setCurrentDirectory(new File("."));
		file_mp3.showOpenDialog(null);
		
		try {
			File my_file = file_mp3.getSelectedFile();
			FileInputStream my_file_input_stream = new FileInputStream(my_file);
			
			size = my_file_input_stream.available();
			my_file_input_stream.skip(size - 128);
			
			my_file_input_stream.read(bTAG);
			String bTAG_str = new String(bTAG);
			
			my_file_input_stream.read(bTitel);
			String bTitel_str = new String(bTitel);
			
			my_file_input_stream.read(bInterpret);
			String bInterpret_str = new String(bInterpret);
			
			my_file_input_stream.read(bCDTitel);
			String bCDTitel_str = new String(bCDTitel);
			
			my_file_input_stream.read(bJahr);
			String bJahr_str = new String(bJahr);
			
			my_file_input_stream.read(bKommentar);
			String bKommentar_str = new String(bKommentar);
			
			bGenre = (byte)my_file_input_stream.read();
			
			System.out.println("MP3 Daten:");
			System.out.println("TAG: " + bTAG_str);
			System.out.println("Titel: " + bTitel_str);
			System.out.println("Interpret: " + bInterpret_str);
			System.out.println("CD: " + bCDTitel_str);
			System.out.println("Jahr: " + bJahr_str);
			System.out.println("Kommentar: " + bKommentar_str);
			System.out.println("Genre: " + bGenre);
			
			FileOutputStream my_file_output_stream = new FileOutputStream(my_file);
			
			bJahr_str = "1986";
			bJahr = bJahr_str.getBytes();
			for (int i = 0; i < 4; i++) {
				my_file_output_stream.write(bJahr[i]);
			}
			
			my_file_input_stream.read(bJahr);
			bJahr_str = new String(bJahr);
			
			System.out.println("\nMP3 neue Daten:");
			System.out.println("Jahr: " + bTAG_str);
			System.out.println("Jahr: " + bJahr_str);
			
			my_file_input_stream.close();
			my_file_output_stream.close();
			
		}
		catch (Exception e) {
			System.out.println("Fehler beim Einlesen.");
		}

	}

}
 