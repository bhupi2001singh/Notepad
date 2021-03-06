import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class notepad extends KeyAdapter  implements ActionListener, KeyListener
{
 
 static int active =0;
 static int fsize=17;
 
 JFrame frame1;
 JMenuBar npMenuBar;
 JMenu file, edit, format, view;
 JMenuItem newdoc, opendoc, exit, savedoc, saveasdoc, copydoc, pastedoc, remdoc,  fontfamily, fontstyle, fontsize, status;
 static JTextArea maintext;
 JTextField title;
 Font font1;
 JPanel bottom;
 JLabel details, pastecopydoc;
 JList familylist, stylelist, sizelist;
 JScrollPane sb;
 JMenu menuBackgroundColor,menuTextColor;
 JMenuItem iRed,iYellow,iGreen,iBlack,iWhite,iBlue,ired,iyellow,igreen,iblack,iwhite,iblue;
 String familyvalue[]={"Agency FB","Antiqua","Architect","Arial","Calibri","Comic Sans","Courier","Cursive","Impact","Serif"};
 String sizevalue[]={"5","10","15","20","25","30","35","40","45","50","55","60","65","70"};
 int [] stylevalue={ Font.PLAIN, Font.BOLD, Font.ITALIC };
 String [] stylevalues={ "PLAIN", "BOLD", "ITALIC" };
 String ffamily, fsizestr, fstylestr;
 int fstyle;
 int cl;
 int linecount;
 String tle ;
 String topicstitle = "";
 JScrollPane sp;
 
notepad(){

 frame1 = new JFrame("Notepad Fast");

 font1=new Font("Arial",Font.PLAIN,17);

 bottom = new JPanel();
 details = new JLabel();
 pastecopydoc = new JLabel();

 familylist = new JList(familyvalue);
 stylelist = new JList(stylevalues);
 sizelist = new JList(sizevalue);


 familylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 sizelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 stylelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

 bottom.add(details);

 maintext = new JTextArea();

 sp=new JScrollPane(maintext);
 title = new JTextField(100);

 sb = new JScrollPane(maintext);

 maintext.setMargin( new Insets(5,5,5,5) );

 maintext.setFont(font1);
 frame1.add(maintext);

 npMenuBar = new JMenuBar();

 file = new JMenu("File");
 edit = new JMenu("Edit");
 format = new JMenu("Format");
 view = new JMenu("View");

 newdoc = new JMenuItem("New Document");
 opendoc = new JMenuItem("Open Document");
 savedoc = new JMenuItem("Save Document");
 saveasdoc = new JMenuItem("Save As Document");
 exit = new JMenuItem("Exit");

 copydoc = new JMenuItem("Copy Document");
 remdoc = new JMenuItem("Remove Document");
 pastedoc = new JMenuItem("Paste Document");

 fontfamily = new JMenuItem("Set Font Family");
 fontstyle = new JMenuItem("Set Font Style");
 fontsize = new JMenuItem("Set Font Size");
 status = new JMenuItem("Status");

 file.add(newdoc);
 file.add(opendoc);
 file.add(savedoc);
 file.add(saveasdoc);
 file.add(exit);

 edit.add(copydoc);
 edit.add(pastedoc);
 edit.add(remdoc);

 format.add(fontfamily);
 format.add(fontstyle);
 format.add(fontsize);

 view.add(status);
 createFormatMenu();
 npMenuBar.add(file);
 npMenuBar.add(edit);
 npMenuBar.add(format);
 npMenuBar.add(view);
 
 frame1.setJMenuBar(npMenuBar);
 frame1.add(bottom, BorderLayout.SOUTH);

 newdoc.addActionListener(this);
 copydoc.addActionListener(this);
 pastedoc.addActionListener(this);
 remdoc.addActionListener(this);
 status.addActionListener(this);
 savedoc.addActionListener(this);
 saveasdoc.addActionListener(this);

 fontfamily.addActionListener(this);
 fontsize.addActionListener(this);
 fontstyle.addActionListener(this);

 exit.addActionListener(this);

 maintext.addKeyListener(this);

 frame1.setSize(600,600);     
 frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 frame1.setLocationRelativeTo(null);
 frame1.setVisible(true);
}

public void actionPerformed(ActionEvent ae)
{
 if(ae.getSource()== newdoc)
 {
 frame1.setTitle("New Document.txt");
 maintext.setText("");
 }

 else if (ae.getSource()== copydoc)
 {
  String texts= maintext.getText();
  pastecopydoc.setText(texts);
  JOptionPane.showMessageDialog(null, "Copy Text "+texts);
 }
 else if(ae.getSource()== remdoc)
 {
  maintext.setText("");
  JOptionPane.showMessageDialog(null, "Removed");
 }
 else if (ae.getSource() == pastedoc)
 {
  if(maintext.getText().length() != 0)
  {
   maintext.setText(maintext.getText());
  }
  else
  {
  maintext.setText(pastecopydoc.getText());
  }
 }
 else if(ae.getSource()== status)
 {
  try{
   if(active ==0)
   {
    File f = new File(tle+".txt");
    details.setText("Size: "+f.length());
   }
  }
  catch (Exception e)
  {
   
  }
 }
 else if (ae.getSource()== fontfamily)
  {

     JOptionPane.showConfirmDialog(null, familylist, "Choose Font Family", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
  ffamily=String.valueOf(familylist.getSelectedValue());
  font1=new Font(ffamily,fstyle,fsize);
  maintext.setFont(font1);
  }
 else if (ae.getSource()== fontstyle)
  {

     JOptionPane.showConfirmDialog(null, stylelist, "Choose Font Style", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
  fstyle=stylevalue[stylelist.getSelectedIndex()];
  font1=new Font(ffamily,fstyle,fsize);
  maintext.setFont(font1);
  }
 else if (ae.getSource()== fontsize)
  {

     JOptionPane.showConfirmDialog(null, sizelist, "Choose Font Size", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
  fsizestr=String.valueOf(sizelist.getSelectedValue());
  fsize =Integer.parseInt(fsizestr);
  font1=new Font(ffamily,fstyle,fsize);
  maintext.setFont(font1);
  }
 else if(ae.getSource()==exit)
  {
   frame1.dispose();
  }
 
 else if(ae.getSource()==iRed) {
	 changeColor("Red");
 }
 
else if(ae.getSource()==iYellow) {
	changeColor("Yellow");
 }
 
else if(ae.getSource()==iGreen) {
	changeColor("Green");
}
 
else if(ae.getSource()==iBlack) {
	changeColor("Black");
}
 
else if(ae.getSource()==iWhite) {
	changeColor("White");
}
 
else if(ae.getSource()==iBlue) {
	changeColor("Blue");
}
 

else if(ae.getSource()==ired) {
	changeColor("red");
}

else if(ae.getSource()==iyellow) {
	changeColor("yellow");
}

else if(ae.getSource()==igreen) {
	changeColor("green");
}

else if(ae.getSource()==iblack) {
	changeColor("black");
}

else if(ae.getSource()==iwhite) {
	changeColor("white");
}

else if(ae.getSource()==iblue) {
	changeColor("blue");
}

 
 
 
 
}


public void createFormatMenu() {
	
	
	menuTextColor=new JMenu("Text Color");
	format.add(menuTextColor);
	
	ired=new JMenuItem("Red");
	ired.addActionListener(this);
	ired.setActionCommand("red");
	menuTextColor.add(ired);
	
	iyellow=new JMenuItem("Yellow");
	iyellow.addActionListener(this);
	iyellow.setActionCommand("yellow");
	menuTextColor.add(iyellow);
	
	igreen=new JMenuItem("Green");
	igreen.addActionListener(this);
	igreen.setActionCommand("green");
	menuTextColor.add(igreen);
	
	iblack=new JMenuItem("Black");
	iblack.addActionListener(this);
	iblack.setActionCommand("black");
	menuTextColor.add(iblack);
	
	iwhite=new JMenuItem("White");
	iwhite.addActionListener(this);
	iwhite.setActionCommand("white");
	menuTextColor.add(iwhite);
	
	iblue=new JMenuItem("Blue");
	iblue.addActionListener(this);
	iblue.setActionCommand("blue");
	menuTextColor.add(iblue);
	
	menuBackgroundColor=new JMenu("Background Color");
	format.add(menuBackgroundColor);
	
	iRed=new JMenuItem("Red");
	iRed.addActionListener(this);
	iRed.setActionCommand("Red");
	menuBackgroundColor.add(iRed);
	
	iYellow=new JMenuItem("Yellow");
	iYellow.addActionListener(this);
	iYellow.setActionCommand("Yellow");
	menuBackgroundColor.add(iYellow);
	
	iGreen=new JMenuItem("Green");
	iGreen.addActionListener(this);
	iGreen.setActionCommand("Green");
	menuBackgroundColor.add(iGreen);
	
	iBlack=new JMenuItem("Black");
	iBlack.addActionListener(this);
	iBlack.setActionCommand("Black");
	menuBackgroundColor.add(iBlack);
	
	iWhite=new JMenuItem("White");
	iWhite.addActionListener(this);
	iWhite.setActionCommand("White");
	menuBackgroundColor.add(iWhite);
	
	iBlue=new JMenuItem("Blue");
	iBlue.addActionListener(this);
	iBlue.setActionCommand("Blue");
	menuBackgroundColor.add(iBlue);
	
}


public void changeColor(String col) {
	
	switch(col) {
	
	case "Red":
		notepad.maintext.setBackground(Color.RED);
		break;
	
	case "Yellow":
		notepad.maintext.setBackground(Color.YELLOW);
		break;
		
	case "Green":
		notepad.maintext.setBackground(Color.GREEN);
		break;
		
	case "Black":
		notepad.maintext.setBackground(Color.BLACK);
		break;
		
	case "White":
		notepad.maintext.setBackground(Color.WHITE);
		break;
	
	case "Blue":
		notepad.maintext.setBackground(Color.BLUE);
		break;
		
	case "red":
		notepad.maintext.setForeground(Color.RED);
		break;
	
	case "yellow":
		notepad.maintext.setForeground(Color.YELLOW);
		break;
		
	case "green":
		notepad.maintext.setForeground(Color.GREEN);
		break;
		
	case "black":
		notepad.maintext.setForeground(Color.BLACK);
		break;
		
	case "white":
		notepad.maintext.setForeground(Color.WHITE);
		break;
		
	case "blue":
		notepad.maintext.setForeground(Color.BLUE);
		break;
	}
}


public void keyTyped(KeyEvent ke){
 cl= maintext.getText().length();
 linecount = maintext.getLineCount();
 details.setText("Length: "+cl+" Line: "+linecount);
}

public static void main(String ar[]) 
{
  new notepad();
}
}
