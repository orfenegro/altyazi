package altyazi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class NameChanger extends JFrame {

	private JPanel contentPane;
	private JTextArea textField;
	StringBuffer Path2;
	private JScrollPane scrollPane;
	public static JTextField newTitle;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newTitle = new JTextField("Mass Subtitle Convertor by Ömer Faruk Durusoy");
					NameChanger frame = new NameChanger();
					
					
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public NameChanger(){
		this.setTitle(newTitle.getText());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1049, 766);	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnKlasrSe = new JButton("Klas\u00F6r Se\u00E7");
		contentPane.add(btnKlasrSe, BorderLayout.SOUTH);
		
		
		textField = new JTextArea("Dosyalarýnýz burada listenelecektir.\n Program alfa aþamasýnda olduðundan programcý programdan oluþacak zararlardan\n mesuliyet kabul etmemektedir.",15,10);
		contentPane.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		textField.setEditable(false);
		
		JButton btnBasla = new JButton("BASLA");
		contentPane.add(btnBasla, BorderLayout.EAST);
		Font font = new Font("Times New Roman", Font.BOLD, 20);
		textField.setFont(font);
		textField.setForeground(Color.BLUE);
		//scrollPane = new JScrollPane(textField);
		scrollPane = new JScrollPane(textField);
		add(scrollPane, BorderLayout.CENTER);
		operation islem = new operation();
		btnBasla.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				File dir= new File(Path2.toString());
                File files[]=dir.listFiles();
                for (File f: files) {
                    if (f.isDirectory()) {
                        try {
                            listDir(f.toString());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                    	change(f);                    
                    }

                }
				
			}
			public void listDir (String dir) throws IOException{
				File myfile= new File(dir);
				File files2[]=myfile.listFiles();
				for (File f: files2) {
					if (f.isDirectory()) {
                        listDir(f.toString());
					} else {
						change(f);
					}
				}
			}
			public void change (File f) {
				if(f.getName().lastIndexOf('.')>0) {
                    int lastIndex = f.getName().lastIndexOf('.');
                    String str = f.getName().substring(lastIndex);
                    if(str.equals(".txt") || str.equals(".sub") || str.equals(".srt")) {
                        try {
                          String sonuc = islem.koddegýstýr(f.getAbsolutePath());
                          textField.append(sonuc+"\n");
                        } catch (IOException e1) {
                          e1.printStackTrace();
                        }
                    }
				} 
			}
		});
			
		
		btnKlasrSe.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser jfcGetTextFiles =new JFileChooser("C:\\");
				jfcGetTextFiles.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jfcGetTextFiles.setAcceptAllFileFilterUsed(false);
				int iRtnVal= jfcGetTextFiles.showOpenDialog(null);
				FileReader frSelectedFile=null;
				 if (iRtnVal == JFileChooser.APPROVE_OPTION) {
					 File file = jfcGetTextFiles.getSelectedFile();	
					 Path2=new StringBuffer(file.toString());
					 textField.setText(file.toString());
					
		        }
			}
		});
		
	}
}
