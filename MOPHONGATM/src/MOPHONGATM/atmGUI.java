package MOPHONGATM;






import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.print.DocFlavor.READER;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;


public class atmGUI extends JFrame implements ActionListener{
    JPanel p1,p2,p3,p4,p5,pThe,pNhanThe,pLoaiThe, pGT_a,pGT_b,pGT_c,pGT_d;
    JButton[] n;
    JLabel tba,tbb,tbc,xn;
    JButton enter,cancel,clear,admin;
    JButton la,lb,lc,ld,ra,rb,rc,rd,layThe,napThe;
    JPasswordField pin,pin1,pin2,pin3;
    JTextField loaiThe, guiTien, taiKhoan, guiTien2;
    JTextField ma,ten,mapin,mathe;
    JComboBox gt;
    Font font;
    String temp = "";
    boolean kt = true, ad = false;
    int chon = -1, khoa = 1, phim = 0, chon2 = 0;
    JLabel tc,td,te,tf,tg,th,ti,tm,mthe;
    JPanel menu;
	atmGUI()
	{
	    getContentPane().setLayout(null);
		setSize(700,700);
		setTitle("Chương trình mô phỏng MÁY ATM");
		font = new Font ("Time New Romances", Font.BOLD ,29 );
		tba = new JLabel("<html><p>Ngân Hàng ABC xin chào quý khách, vui lòng để thẻ vào khe bên dưới</p></html>");
		tba.setFont(font); tba.setForeground(Color.yellow); 
		tbb = new JLabel(">>>Chưa có thẻ<<<");
		tbc = new JLabel(""); tbc.setFont(font); tbc.setForeground(Color.yellow); 
		la = new JButton(); 
		lb = new JButton();
		lc = new JButton();
		ld = new JButton();
		ra = new JButton();
		rb = new JButton();
		rc = new JButton();
		rd = new JButton();
          
		
		
		n = new JButton[12];
		for (int i=0;i<10;i++)
			n[i] = new JButton(""+i);
		
		
		p1 = new JPanel(new GridLayout(4,3));
		for (int i=0;i<10;i++)
		p1.add(n[i]);
		p1.add(new JLabel());
		p1.setSize(250,200);
	    p1.setLocation(100,460);
	    
	    p2 = new JPanel(new GridLayout(4,1));
	    p2.add(la); p2.add(lb); p2.add(lc); p2.add(ld);
	    p2.setSize(100,400);
	    p2.setLocation(0, 30);
	    
	    
	    p3 = new JPanel(new GridLayout(4,1));
	    p3.add(ra); p3.add(rb); p3.add(rc); p3.add(rd);
	    p3.setSize(100,400);
	    p3.setLocation(600, 30);
	 
	    
	    p4 = new JPanel(new GridLayout(4,1));
	    p4.setSize(500,440);
	    p4.setLocation(100, 5);
	    p4.add(tba);
	    p4.add(tbc);
	    p4.setBackground(Color.blue);
	    
	    
	    p5 = new JPanel(new GridLayout(4,1));
	    enter = new JButton("ENTER"); enter.setEnabled(false);
	    cancel = new JButton("CANCEL"); cancel.setEnabled(false);
	    clear = new JButton("CLEAR"); clear.setEnabled(false);
	    admin = new JButton("admin"); admin.setEnabled(false);
	    p5.add(enter);
	    p5.add(cancel);
	    p5.add(admin);
	    p5.add(clear);
	    p5.setSize(80,200);
	    p5.setLocation(440,460);
	    p5.setBackground(Color.red);
	    
	    
	    pNhanThe = new JPanel(new GridLayout(1,2));
	    pNhanThe.setSize(180,30);
	    pNhanThe.setLocation(520,490);
	    layThe = new JButton("Lấy thẻ"); layThe.setVisible(false);
	    napThe = new JButton("Nạp thẻ");
	    pNhanThe.add(layThe);
	    pNhanThe.add(napThe);
	    
	    loaiThe = new JTextField(10); loaiThe.setText("Nhập mã thẻ"); loaiThe.setBackground(Color.pink);
	    pLoaiThe = new JPanel();
	    pLoaiThe.setSize(180,30);
	    pLoaiThe.setLocation(520,520);
	    pLoaiThe.add(loaiThe);
	    layThe.addActionListener(this);
	    napThe.addActionListener(this);
	    
	    for (int i=0;i<10;i++)
			n[i].addActionListener(this);
	    enter.addActionListener(this);
	    clear.addActionListener(this);
	    cancel.addActionListener(this);
		admin.addActionListener(this);
		
	    pThe = new JPanel();
	    pThe.add(tbb);
	    pThe.setSize(200,20);
	    pThe.setLocation(500,470);
	    pThe.setBackground(Color.cyan);
	    
	    la.addActionListener(this);
	    lb.addActionListener(this);
	    lc.addActionListener(this);
	    ld.addActionListener(this);
	    ra.addActionListener(this);
	    rb.addActionListener(this);
	    rc.addActionListener(this);
	    rd.addActionListener(this);
	    
	
	    tc = new JLabel("<html><p>Đổi mã</p> PIN</html>"); tc.setForeground(Color.yellow); tc.setFont(font);
	    td = new JLabel("<html><p>Số dư </p>tài khoản</html>"); td.setHorizontalAlignment(JLabel.RIGHT); td.setForeground(Color.yellow); td.setFont(font);
	    te = new JLabel("<html><p>In thông tin</p>cá nhân</html>"); te.setForeground(Color.yellow); te.setFont(font);
	    tf = new JLabel("<html><p>Gửi tiền vào</p>tài khoản</html>");tf.setHorizontalAlignment(JLabel.RIGHT);  tf.setForeground(Color.yellow); tf.setFont(font);
	    tg = new JLabel("<html><p>In lịch sử</p>giao dịch</html>"); tg.setForeground(Color.yellow); tg.setFont(font);
	    th = new JLabel("Chuyển khoản"); th.setHorizontalAlignment(JLabel.RIGHT); th.setForeground(Color.yellow); th.setFont(font);
	    ti = new JLabel("<html><p>Hủy bỏ </p>giao dịch</html>"); ti.setForeground(Color.yellow); ti.setFont(font);
	    tm = new JLabel("Rút tiền");tm.setHorizontalAlignment(JLabel.RIGHT);  tm.setForeground(Color.yellow); tm.setFont(font);
	    xn = new JLabel("Xác nhận"); xn.setFont(font); xn.setForeground(Color.yellow); 
	    mthe = new JLabel("Mã thẻ: "); mthe.setHorizontalAlignment(JLabel.RIGHT); mthe.setForeground(Color.yellow); mthe.setFont(font);
	    ma = new JTextField(); ma.setFont(font); ma.setBackground(Color.pink);
	    ten = new JTextField(); ten.setFont(font); ten.setBackground(Color.pink);
	    gt = new JComboBox(); gt.setFont(font); gt.setBackground(Color.pink);
	    gt.addItem("Nam"); gt.addItem("Nu");
	    mapin = new JTextField(); mapin.setFont(font); mapin.setBackground(Color.pink);
	    mathe = new JTextField(); mathe.setFont(font); mathe.setBackground(Color.pink);
	    pin = new JPasswordField(4); 
	    

	    pin1 = new JPasswordField();pin1.setFont(new Font("Time New Romances",Font.BOLD,40));pin1.setEditable(false);
	    pin2 = new JPasswordField();pin2.setFont(new Font("Time New Romances",Font.BOLD,40));pin2.setEditable(false);
	    pin3 = new JPasswordField();pin3.setFont(new Font("Time New Romances",Font.BOLD,40));pin3.setEditable(false);
	    guiTien = new JTextField(); guiTien.setFont(font);
	    taiKhoan = new JTextField(); taiKhoan.setFont(font);
	    guiTien2 = new JTextField(); guiTien2.setFont(font);guiTien2.setEditable(false);
	    
	    menu = new JPanel(new GridLayout(4,2)); 
	    menu.add(tc);menu.add(td);
	    menu.add(te);menu.add(tf);
	    menu.add(tg);menu.add(th);
	    menu.add(ti);menu.add(tm);
	    menu.setSize(500,440);
	    menu.setLocation(100, 5);
	    menu.setBackground(Color.blue);
	    
	    getContentPane().add(p1);
	    getContentPane().add(p2);
	    getContentPane().add(p3);
	    getContentPane().add(p4);
	    getContentPane().add(p5);
	    getContentPane().add(pThe);
	    getContentPane().add(pNhanThe);
	    getContentPane().add(pLoaiThe);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		atmExe user = new atmExe();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(layThe))
		{
			if (ad)
			{
		    menu.setVisible(false);
		    remove(menu);
			p4 = new JPanel(new GridLayout(4,1));
		    p4.setSize(500,440);
		    p4.setLocation(100, 5);
			p4.add(tba);
			p4.add(tbc);
			p4.setBackground(Color.blue);
			getContentPane().add(p4);
			kt = false;
			loaiThe.setText("Nhập mã thẻ");
			loaiThe.setEditable(true);
			tbc.setText("");
			enter.setEnabled(false);
		    cancel.setEnabled(false);
		    clear.setEnabled(false);
		    admin.setEnabled(false);
			napThe.setVisible(true);
			layThe.setVisible(false);
			tbb.setText(">>>Chưa có thẻ<<<");
			tba.setText("<html><p>Cám ơn quý khách đã sử dụng</p><p>dịch vụ của chúng tôi</p>Xin vui lòng để thẻ vào....</html>");
		    pin.setVisible(false);
		    temp = "";
			p4.remove(pin);
			ra.setText("");
			rb.setText("");
			ld.setText("");
			rd.setText("");
			chon = -1;
			}
			else
			{
				kt = false;
				loaiThe.setText("Nhập mã thẻ");
				loaiThe.setEditable(true);
				tbc.setText("");
				enter.setEnabled(false);
			    cancel.setEnabled(false);
			    clear.setEnabled(false);
			    admin.setEnabled(false);
				napThe.setVisible(true);
				layThe.setVisible(false);
				tbb.setText(">>>Chưa có thẻ<<<");
				tba.setText("<html><p>Cám ơn quý khách đã sử dụng</p><p>dịch vụ của chúng tôi</p>Xin vui lòng để thẻ vào....</html>");
			    pin.setVisible(false);
			    temp = "";
				p4.remove(pin);
				ra.setText("");
				rb.setText("");
				ld.setText("");
				rd.setText("");
				chon = -1;
			}
		    
		} else if (e.getSource().equals(napThe))
		{
			if ( loaiThe.getText().toString().compareTo("")==0 || loaiThe.getText().toString().compareTo("Nhập mã thẻ")==0) 
				{
				   loaiThe.setText("");
				   loaiThe.requestFocus();
				}		
			else
			{
			if (loaiThe.getText().toString().compareTo("AAA") == 0)
			{
			ad = true; 
			kt = true;
			admin.setEnabled(true);
			p4.setVisible(false);
			remove(p4);
			admin("<html><p>Xin chào quản trị viên ATM</p><html>");
			napThe.setVisible(false);
		   	layThe.setVisible(true);
		   	ma.setText("");
			ten.setText("");
			mapin.setText("");
			mathe.setText("");
			chon = 90;
			chon = -1;
			}
			else 
		     {
			 ad = false;
			 kt = true;
			 loaiThe.setEditable(false);
			 admin.setEnabled(true);
			 enter.setEnabled(true);
			 clear.setEnabled(true);
			 cancel.setEnabled(true);
			 tbc.setText("Nhập mã PIN");
			 napThe.setVisible(false);
		   	 layThe.setVisible(false);
			 tbb.setText(">>>Đã có thẻ<<<");
			 tba.setText("<html><p>Xin chào quý khách Hệ thống yêu cầu mã PIN </p></html>");
			 pin = new JPasswordField(4); pin.setEditable(false); pin.setFont(new Font("Time New Romances",Font.BOLD,40));
			 p4.add(pin);
			 ra.setText("Nhập");
			 rb.setText("Xóa");
			 ld.setText("<html><p>Hủy bỏ</p> giao dịch<html>");
			 temp = "";
			 chon = 0;
		     }
			}
		} else if (e.getSource().equals(n[0]))
		{
			if (kt)
			{
			 temp+=0;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
					if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[1]))
		{
			if (kt)
			{
			 temp+=1;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[2]))
		{
			if (kt)
			{
			 temp+=2;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[3]))
		{
			if (kt)
			{
			 temp+=3;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[4]))
		{
			if (kt)
			{
			 temp+=4;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[5]))
		{
			if (kt)
			{
			 temp+=5;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[6]))
		{
			if (kt)
			{
			 temp+=6;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[7]))
		{
			if (kt)
			{
			 temp+=7;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[8]))
		{
			if (kt)
			{
			 temp+=8;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(n[9]))
		{
			if (kt)
			{
			 temp+=9;
			 pin.setText(temp);
			
			 if (chon == 2)
			 {
				if (phim == 1) pin1.setText(temp);
			    if (phim == 2) pin2.setText(temp);
				if (phim == 3) pin3.setText(temp);
			 }
			 if (chon == 4)
			 {
				guiTien.setText(temp);
			 }
			 if (chon == 5)
			 {
				 if (phim == 1) guiTien.setText(temp);
				 if (phim == 2) taiKhoan.setText(temp);			
			 }
			 if (chon == 7)
				{
				 if (phim == 1) guiTien2.setText(temp);
				}
			}
		}else if (e.getSource().equals(enter))
		{
			
			if (kt)
			{
				if (chon == 0)
				{
					if (pin.getText().toString().length() == 4)
					{
					 if (atmExe.checkPin(pin.getText(),loaiThe.getText()))
					 {
						khoa = 1;
						chon = 1;
						p4.setVisible(false);
						remove(p4);
						  tc.setText("<html><p>Đổi mã</p> PIN</html>");
						    td.setText("<html><p>Số dư </p>tài khoản</html>");
						    te.setText("<html><p>In thông tin</p>cá nhân</html>");
						    tf.setText("<html><p>Gửi tiền vào</p>tài khoản</html>");
						    tg.setText("<html><p>In lịch sử</p>giao dịch</html>");
						    th.setText("Chuyển khoản");
						    ti.setText("<html><p>Hủy bỏ </p>giao dịch</html>");
						    tm.setText("Rút tiền");
						menu = new JPanel(new GridLayout(4,2)); 
						menu.add(tc);menu.add(td);
						menu.add(te);menu.add(tf);
						menu.add(tg);menu.add(th);
						menu.add(ti);menu.add(tm);
						menu.setSize(500,440);
						menu.setLocation(100, 5);
						menu.setBackground(Color.blue);
						
						getContentPane().add(menu);
						ld.setText("");
						ra.setText("");
						rb.setText("");
						guiTien.setText("");
						taiKhoan.setText("");
						try
						{
						ResultSet dl = atmExe.getDataAcc();
						dl.next(); tbb.setText(dl.getString("HOTEN").toString());
						}
						catch (Exception loi) {
							loi.printStackTrace();
						}
					 }else{
						if (khoa == 4) 
						{
							ld.setEnabled(false);	
							cancel.setEnabled(false);
						}
						tbc.setText("Sai mã PIN");
						if (khoa > 3) tbc.setText("<html><p>Bạn đã nhập sai quá 4 lần</p>hệ thống sẽ giữ thẻ</html>");
						pin.setText("");
						temp="";
						khoa++;
					 }
				   } else
					   {
					       tbc.setText("Mã PIN tối đa 4 kí tự");
					       temp="";
					   }
				}
			}
			
		}else if (e.getSource().equals(cancel))
		{
			
			if (kt)
			{ 
				pin1.setText("");
				pin2.setText("");
				pin3.setText("");
				if (chon == 1 || chon == 2 || chon == 3 || chon == 4 || chon == 5 || chon == 6 || chon == 7)
				{
					temp="";
					chon = -1;
					la.setText("");
					 lb.setText("");
					 lc.setText("");
					 ld.setText("");
					 ra.setText("");
					 rb.setText("");
					 rc.setText("");
					 rd.setText("");
					menu.setVisible(false);
					remove(menu);					
					kt = false;
					tbc.setText("");
					enter.setEnabled(false);
				    cancel.setEnabled(false);
				    clear.setEnabled(false);
				    admin.setEnabled(false);
					napThe.setVisible(false);
					layThe.setVisible(true);
					tbb.setText(">>>Chưa có thẻ<<<");
					tba.setText("<html><p>Cám ơn quý khách đã sử dụng</p><p>dịch vụ của chúng tôi</p>Xin vui lòng để thẻ vào....</html>");
				    temp = "";
					ra.setText("");
					rb.setText("");
					ld.setText("");
					layThe.setVisible(true);			
					p4 = new JPanel(new GridLayout(4,1));
					p4.setSize(500,440);
					p4.setLocation(100, 5);
					p4.add(tba);
					p4.add(tbc);
					p4.setBackground(Color.blue);
					getContentPane().add(p4);
					chon = -1;
				}
				else 
			 {
				kt = false;
				tbc.setText("");
				enter.setEnabled(false);
			    cancel.setEnabled(false);
			    clear.setEnabled(false);
			    admin.setEnabled(false);
				napThe.setVisible(false);
				layThe.setVisible(true);
				tbb.setText(">>>Chưa có thẻ<<<");
				tba.setText("<html><p>Cám ơn quý khách đã sử dụng</p><p>dịch vụ của chúng tôi</p>Xin vui lòng để thẻ vào....</html>");
			    pin.setVisible(false);
			    temp = "";
				p4.remove(pin);
				ra.setText("");
				rb.setText("");
				ld.setText("");
				layThe.setVisible(true);
			 }
			}
			
		}
		else if (e.getSource().equals(clear))
		{
			if (kt)
			{
				pin.setText("");
				temp="";
				tbc.setText("Nhập mã PIN");
			    if (chon == 2)
			    {
			    	pin1.setText("");
			    	pin2.setText("");
			    	pin3.setText("");
			    }
			    if (chon == 4)
			    {
			    	guiTien.setVisible(false);
					guiTien.setText("");
					guiTien.setVisible(true);
					temp="";
			    }
			    if (chon == 5)
				{
					guiTien.setVisible(false);
					guiTien.setText("");
					guiTien.setVisible(true);

					taiKhoan.setVisible(false);
					taiKhoan.setText("");
					taiKhoan.setVisible(true);
					temp="";
				}
				 if (chon == 7)
					{
					    guiTien2.setVisible(false);
						guiTien2.setText("");
						guiTien2.setVisible(true);
						temp="";
					}
			}
		}else if (e.getSource().equals(la))
		{
			if (kt)
			{ 
				if (chon == 1 || chon == 10)
				{
					menu.setVisible(false);
					remove(menu);
					tc.setText("Giao dịch khác");
					th.setText("Xác nhận");
					tm.setText("PIN cũ: ");
					td.setText("PIN mới: ");
					te.setText("Nhập lại PIN mới: ");
					menu = new JPanel(new GridLayout(4,2)); 
					menu.add(tc);menu.add(th);
				    menu.add(tm);menu.add(pin1);
				    menu.add(td);menu.add(pin2);
				    menu.add(te);menu.add(pin3);	    
				    menu.setSize(500,440);
				    menu.setLocation(100, 5);
				    menu.setBackground(Color.blue);
				    getContentPane().add(menu);
				    rb.setText("");
				    rc.setText("");
				    rd.setText("");
				    chon = 2;
				} else
				if (chon == 2)
				{
					la.setText("");
					 lb.setText("");
					 lc.setText("");
					 ld.setText("");
					 ra.setText("");
					 rb.setText("");
					 rc.setText("");
					 rd.setText("");
					 pin1.setText("");
					 pin2.setText("");
					 pin3.setText("");
					menu.setVisible(false);
					remove(menu);
					    tc.setText("<html><p>Đổi mã</p> PIN</html>");
					    td.setText("<html><p>Số dư </p>tài khoản</html>");
					    te.setText("<html><p>In thông tin</p>cá nhân</html>");
					    tf.setText("<html><p>Gửi tiền vào</p>tài khoản</html>");
					    tg.setText("<html><p>In lịch sử</p>giao dịch</html>");
					    th.setText("Chuyển khoản");
					    ti.setText("<html><p>Hủy bỏ </p>giao dịch</html>");
					    tm.setText("Rút tiền");
					menu = new JPanel(new GridLayout(4,2)); 
					menu.add(tc);menu.add(td);
					menu.add(te);menu.add(tf);
					menu.add(tg);menu.add(th);
					menu.add(ti);menu.add(tm);
					menu.setSize(500,440);
					menu.setLocation(100, 5);
					menu.setBackground(Color.blue);
					
					getContentPane().add(menu);
					ld.setText("");
					ra.setText("");
					rb.setText("");
					chon = 1;
				}
				if (chon == 3 || chon == 4 || chon == 5 || chon == 6)
				{
					la.setText("");
					 lb.setText("");
					 lc.setText("");
					 ld.setText("");
					 ra.setText("");
					 rb.setText("");
					 rc.setText("");
					 rd.setText("");
					 pin1.setText("");
					 pin2.setText("");
					 pin3.setText("");
					menu.setVisible(false);
					remove(menu);
					    tc.setText("<html><p>Đổi mã</p> PIN</html>");
					    td.setText("<html><p>Số dư </p>tài khoản</html>");
					    te.setText("<html><p>In thông tin</p>cá nhân</html>");
					    tf.setText("<html><p>Gửi tiền vào</p>tài khoản</html>");
					    tg.setText("<html><p>In lịch sử</p>giao dịch</html>");
					    th.setText("Chuyển khoản");
					    ti.setText("<html><p>Hủy bỏ </p>giao dịch</html>");
					    tm.setText("Rút tiền");
					menu = new JPanel(new GridLayout(4,2)); 
					menu.add(tc);menu.add(td);
					menu.add(te);menu.add(tf);
					menu.add(tg);menu.add(th);
					menu.add(ti);menu.add(tm);
					menu.setSize(500,440);
					menu.setLocation(100, 5);
					menu.setBackground(Color.blue);
					
					getContentPane().add(menu);
					ld.setText("");
					ra.setText("");
					rb.setText("");
					chon = 1;
				}
				
			if (chon == 8)	
			{
				guiTien.setText("");
				menu.setVisible(false);
				remove(menu);
				tc.setText("<html><p>Giao dịch</p>khác</html>");
				th.setText("Xác nhận");
				tf.setText("Nhập số tiền cần gửi");
				ti.setText("<html><p>Hủy bỏ </p>giao dịch</html>");
				tm.setText("Xóa");
				pGT_a = new JPanel(new GridLayout(1,2)); pGT_a.setBackground(Color.blue);
				pGT_a.add(tc);pGT_a.add(th);
				pGT_b = new JPanel(new GridLayout(1,1)); pGT_b.setBackground(Color.blue);
				pGT_b.add(tf);
				pGT_c = new JPanel(new GridLayout(1,1)); pGT_c.setBackground(Color.blue);
				pGT_c.add(guiTien); guiTien.setEditable(false);
				pGT_d = new JPanel(new GridLayout(1,2)); pGT_d.setBackground(Color.blue);
				pGT_d.add(ti); pGT_d.add(tm);
				menu = new JPanel(new GridLayout(4,2)); 
				menu.add(pGT_a);
				menu.add(pGT_b);
				menu.add(pGT_c);
				menu.add(pGT_d);
				menu.setSize(500,440);
				menu.setLocation(100, 5);
				menu.setBackground(Color.blue);
				getContentPane().add(menu);
				chon = 4;
				temp="";
			 }
				
				if (chon == 9)
				{
					menu.setVisible(false);
					remove(menu);
					tc.setText("<html><p>Giao dịch</p>khác</html>");
					th.setText("Xác nhận");
					tf.setText("Tiền gửi: ");
					td.setText("Tài khoản nhận");
					ti.setText("<html><p>Hủy bỏ </p>giao dịch</html>");
					tm.setText("Xóa");
					pGT_a = new JPanel(new GridLayout(1,2)); pGT_a.setBackground(Color.blue);
					pGT_a.add(tc);pGT_a.add(th);
					pGT_b = new JPanel(new GridLayout(1,2)); pGT_b.setBackground(Color.blue);
					pGT_b.add(tf);pGT_b.add(guiTien);guiTien.setEditable(false);
					pGT_c = new JPanel(new GridLayout(1,2)); pGT_c.setBackground(Color.blue);
					pGT_c.add(td); pGT_c.add(taiKhoan);taiKhoan.setEditable(false);
					pGT_d = new JPanel(new GridLayout(1,2)); pGT_d.setBackground(Color.blue);
					pGT_d.add(ti); pGT_d.add(tm);
					menu = new JPanel(new GridLayout(4,2)); 
					menu.add(pGT_a);
					menu.add(pGT_b);
					menu.add(pGT_c);
					menu.add(pGT_d);
					menu.setSize(500,440);
					menu.setLocation(100, 5);
					menu.setBackground(Color.blue);
					getContentPane().add(menu);
					chon = 5;
					temp="";
				}	
			if (chon == 99 || chon == 7)
			{
				guiTien2.setText("");
				phim = 0;
				menu.setVisible(false);
				remove(menu);
				xn.setVisible(false);
				tc.setText("Giao dịch khác");
				td.setText("100.000 VNĐ");
				tf.setText("200.000 VNĐ");	
				th.setText("500.000 VNĐ");
				ti.setText("Hủy bỏ giao dịch");
				tm.setText("Nhập số khác");
				menu = new JPanel(new GridLayout(4,2)); 
				menu.add(tc);menu.add(td);
				menu.add(guiTien2);menu.add(tf);
				menu.add(xn);menu.add(th);
				menu.add(ti);menu.add(tm);
				guiTien2.setBackground(Color.blue);
				menu.setSize(500,440);
				menu.setLocation(100, 5);
				menu.setBackground(Color.blue);
				getContentPane().add(menu);
				chon = 6;
			}
		}
			
				
		}
		else if (e.getSource().equals(lb))
		{
			if (kt)
			{
				if (chon == 1)
				{			 
					
					try
					{
					ResultSet kq =  atmExe.getDataAcc();
					kq.next();
					JOptionPane.showMessageDialog(null,"Mã khách hàng: "+kq.getString("MKH")+"\n" +
							"Họ tên: "+kq.getString("HOTEN")+"\nGiới tính: "+kq.getString("GIOITINH")+
							"\nSố dư tài khoản: "+kq.getString("TIEN")+"\nMã PIN: "+kq.getString("PIN")+"\nMã thẻ: "+kq.getString("MATHE")+"","Thông tin cá nhân",JOptionPane.INFORMATION_MESSAGE);
					}
					catch (Exception loi) {
						
					}
				}
				if (chon == 7)
				{
					phim = 1;
				}
				
			}
		}
		else if (e.getSource().equals(lc))
		{
			if (kt)
			{ 
				if (chon == 1)
				{
					try
					{
				    Calendar cal = Calendar.getInstance();	
					String tg = cal.get(Calendar.HOUR_OF_DAY) +" giờ "+ cal.get(Calendar.MINUTE)+ " phút ";
					ResultSet kq =  atmExe.getDataHis();
					kq.next();
					JOptionPane.showMessageDialog(null,"Mã khách hàng: "+kq.getString("MKH")+"\n" +
							"Họ tên: "+kq.getString("HOTEN")+"\nHôm nay: "+kq.getString("NGAYGD")+
							"\nThời gian xuất phiếu: "+tg+"\nTiền rút: "+kq.getString("TIENRUT")+
							"\nTiền gửi: "+kq.getString("TIENGUI")+
							"\nTiền chuyển: "+kq.getString("TIENCHUYEN")+"","Lịch sử giao dịch gần nhất",JOptionPane.INFORMATION_MESSAGE);
					}
					catch (Exception loi) {
						
					}
				}
				
				if (chon == 7 || chon == 99)
				{
					try
					{
						if (atmExe.getMonney(Long.parseLong(guiTien2.getText().toString())))
						{
							thongBao("<html><p>Xin vui lòng chờ trong giây lát.... Mời quý khách nhận tiền ở khe bên dưới</p></html>", 7);
							
						}else
						{
							thongBao(7);
							guiTien2.setText("4");
						}
					}
					catch (Exception loi) {
						thongBao("<html><p>Quý khách vui lòng kiểm tra nhập liệu</p></html>",7);
					}
				}
				
			}
		}
		else if (e.getSource().equals(ld))
		{
			
			if (kt)
			{
				if (chon == 0)
				{
					temp="";
					 la.setText("");
					 lb.setText("");
					 lc.setText("");
					 ld.setText("");
					 ra.setText("");
					 rb.setText("");
					 rc.setText("");
					 rd.setText("");
					kt = false;
					tbc.setText("");
					enter.setEnabled(false);
				    cancel.setEnabled(false);
				    clear.setEnabled(false);
				    admin.setEnabled(false);
					napThe.setVisible(false);
					layThe.setVisible(true);
					tbb.setText(">>>Chưa có thẻ<<<");
					tba.setText("<html><p>Cám ơn quý khách đã sử dụng</p><p>dịch vụ của chúng tôi</p>Xin vui lòng để thẻ vào....</html>");
				    pin.setVisible(false);
				    temp = "";
					p4.remove(pin);
					ra.setText("");
					rb.setText("");
					ld.setText("");
					layThe.setVisible(true);
				}
				if (chon == 1)
				{
					chon = -1;
					temp="";
					 la.setText("");
					 lb.setText("");
					 lc.setText("");
					 ld.setText("");
					 ra.setText("");
					 rb.setText("");
					 rc.setText("");
					 rd.setText("");
					menu.setVisible(false);
					remove(menu);
					
					kt = false;
					tbc.setText("");
					enter.setEnabled(false);
				    cancel.setEnabled(false);
				    clear.setEnabled(false);
				    admin.setEnabled(false);
					napThe.setVisible(false);
					layThe.setVisible(true);
					tbb.setText(">>>Chưa có thẻ<<<");
					tba.setText("<html><p>Cám ơn quý khách đã sử dụng</p><p>dịch vụ của chúng tôi</p>Xin vui lòng để thẻ vào....</html>");
				    temp = "";
					ra.setText("");
					rb.setText("");
					ld.setText("");
					layThe.setVisible(true);			
					p4 = new JPanel(new GridLayout(4,1));
					p4.setSize(500,440);
					p4.setLocation(100, 5);
					p4.add(tba);
					p4.add(tbc);
					p4.setBackground(Color.blue);
					getContentPane().add(p4);
					chon = -1;
				}
                if (chon == 3 || chon == 4 || chon == 5 || chon == 6 || chon == 7)
                {
                	temp="";
                	chon = -1;
                	la.setText("");
					 lb.setText("");
					 lc.setText("");
					 ld.setText("");
					 ra.setText("");
					 rb.setText("");
					 rc.setText("");
					 rd.setText("");
					menu.setVisible(false);
					remove(menu);					
					kt = false;
					tbc.setText("");
					enter.setEnabled(false);
				    cancel.setEnabled(false);
				    clear.setEnabled(false);
				    admin.setEnabled(false);
					napThe.setVisible(false);
					layThe.setVisible(true);
					tbb.setText(">>>Chưa có thẻ<<<");
					tba.setText("<html><p>Cám ơn quý khách đã sử dụng</p><p>dịch vụ của chúng tôi</p>Xin vui lòng để thẻ vào....</html>");
				    temp = "";
					ra.setText("");
					rb.setText("");
					ld.setText("");
					layThe.setVisible(true);			
					p4 = new JPanel(new GridLayout(4,1));
					p4.setSize(500,440);
					p4.setLocation(100, 5);
					p4.add(tba);
					p4.add(tbc);
					p4.setBackground(Color.blue);
					getContentPane().add(p4);
					chon = -1;
                }
                if (chon == 90)
				{
                 if (ma.getText().toString().compareTo("") == 0 || ma.getText().toString().compareTo("Nhập vào") == 0) ma.setText("Nhập vào");
                 else
                 {
                	if (mapin.getText().toString().length() == 4)
                	{     		
                		  try
                		  { 
                		   if (ten.getText().toString().compareTo("") == 0 || ten.getText().toString().compareTo("Nhập vào") == 0) ten.setText("Nhập vào");
                		   else  
                		   {  
                			if (mathe.getText().toString().length() == 3)
                			{	
                			  if (atmExe.ThemKH(ma.getText(), ten.getText(), gt.getSelectedItem().toString(), mapin.getText(), mathe.getText())){
                				ma.setText("Đã thêm");
            					ten.setText("Đã thêm");
            					mapin.setText("Đã thêm");          					
            					mathe.setText("Đã thêm");
                			  }else {
                				if (atmExe.LOI == 10) mathe.setText(atmExe.getLoi());
                				if (atmExe.LOI == 11) ma.setText(atmExe.getLoi());
                				if (atmExe.LOI == 12) ma.setText(atmExe.getLoi());
                				if (atmExe.LOI == 13) mapin.setText(atmExe.getLoi());
                				if (atmExe.LOI == 14) ma.setText(atmExe.getLoi());
                			  }
                			}else mathe.setText("Tối đa 3 kí tự");
                		   }
                		  }
                		  catch (Exception loi) {
						  }                  	
                	}
                	else 
                		{
                		  mapin.setText("Nhập 4 số");
                		  mapin.requestFocus();
                		}
                 }
				}
                
			}
		}
		else if (e.getSource().equals(ra))
		{
			
			if (kt)
			{ 
				if (chon == 0)
			 {		
				if (pin.getText().toString().length() == 4)
				{
			      if (atmExe.checkPin(pin.getText(),loaiThe.getText()))
				  {
					khoa = 1;
					chon = 1;
					p4.setVisible(false);
					remove(p4);
					tc.setText("<html><p>Đổi mã</p> PIN</html>");
					td.setText("<html><p>Số dư </p>tài khoản</html>");
					te.setText("<html><p>In thông tin</p>cá nhân</html>");
					tf.setText("<html><p>Gửi tiền vào</p>tài khoản</html>");
					tg.setText("<html><p>In lịch sử</p>giao dịch</html>");
					th.setText("Chuyển khoản");
					ti.setText("<html><p>Hủy bỏ </p>giao dịch</html>");
					tm.setText("Rút tiền");
					menu = new JPanel(new GridLayout(4,2)); 
					menu.add(tc);menu.add(td);
					menu.add(te);menu.add(tf);
					menu.add(tg);menu.add(th);
					menu.add(ti);menu.add(tm);
					menu.setSize(500,440);
					menu.setLocation(100, 5);
					menu.setBackground(Color.blue);
					
					getContentPane().add(menu);
					ld.setText("");
					ra.setText("");
					rb.setText("");
					guiTien.setText("");
					taiKhoan.setText("");
					try
					{
					ResultSet dl = atmExe.getDataAcc();
					dl.next(); tbb.setText(dl.getString("HOTEN").toString());
					}
					catch (Exception loi) {
						loi.printStackTrace();
					}
				   } else
				  {
					if (khoa == 4) 
					{		
						ld.setEnabled(false);	
						cancel.setEnabled(false);
					}
					tbc.setText("Sai mã PIN");
					if (khoa > 3) tbc.setText("<html><p>Bạn đã nhập sai quá 4 lần</p>hệ thống sẽ giữ thẻ</html>");
					khoa++;
					pin.setText("");
					temp="";
				  }
				} 
				else
				   {
				       tbc.setText("Mã PIN tối đa 4 kí tự");
				       temp="";
				   }	
			 }
				else
			if (chon == 2)
				{
				  if (pin.getText().toString() == "" || pin2.getText().toString() =="" || pin3.getText().toString() == "")
					thongBao("Kiểm tra lại khung nhập liệu",10);
				  else  if ( pin2.getText().toString().compareTo(pin3.getText().toString()) == 0) 
					{
						if (pin2.getText().toString().length() == 4)
						{
						   if (atmExe.changePin(pin1.getText().toString(), pin2.getText().toString()))
						   thongBao("Đã thay đổi Mã PIN",10);
							else  thongBao(10);
					    }
						else thongBao("Mã PIN tối đa 4 kí tự",10);
					}
					else thongBao("Mã PIN mới không trùng khớp",10);   
					
				}
			else
			if (chon == 1)
			{
				menu.setVisible(false);
				remove(menu);
				tc.setText("<html><p>Giao dịch</p>khác</html>");
				try
				{
					ResultSet kq = atmExe.getDataAcc();
					kq.next();
					td.setText("<html><p>Số dư:</p>"+kq.getString("TIEN")+" VNĐ</html>");
				}
				catch (Exception loi) {
					loi.printStackTrace();
				}
				te.setText("");
				tf.setText("");
				tg.setText("");
				th.setText("");
				ti.setText("<html><p>Hủy bỏ </p>giao dịch</html>");
				tm.setText("");
				menu = new JPanel(new GridLayout(4,2)); 
				menu.add(tc);menu.add(td);
				menu.add(te);menu.add(tf);
				menu.add(tg);menu.add(th);
				menu.add(ti);menu.add(tm);
				menu.setSize(500,440);
				menu.setLocation(100, 5);
				menu.setBackground(Color.blue);
				getContentPane().add(menu);
				chon = 3;
			  } else
			if (chon == 4)
			{
				try
				{
				   long tem = Long.parseLong(guiTien.getText().toString());
				   if (tem > 50000000) {
					   guiTien.setVisible(false);
					   guiTien.setText("");
					   guiTien.setVisible(true);
					   temp="";
					   thongBao("Máy chỉ nhận mỗi lần tối đa 50 triệu",8);
				   }
				   else
				   try
				   {
					if (atmExe.sendMoney(tem)) {
						   guiTien.setVisible(false);
						   guiTien.setText("");
						   guiTien.setVisible(true);
						   temp="";
						   thongBao("<html><p>Giao dịch thành công, Vui lòng</p>kiểm tra số dư tài khoản</html>",8);
					}
					else {
						   guiTien.setVisible(false);
						   guiTien.setText("");
						   guiTien.setVisible(true);
						   temp="";
						   thongBao(8);
						   
					}
				   }
				   catch (Exception loi) {
					loi.printStackTrace();
			  	   }
				}
				catch (Exception loi) {
					guiTien.setVisible(false);
					   guiTien.setText("");
					   guiTien.setVisible(true);
					   temp="";
					   thongBao("Xin vui lòng nhập thông tin",8);
				}
			}
			
			else
			if (chon == 6)
			{
				try
				{
					if (atmExe.getMonney(Long.parseLong("100000")))
					{
						thongBao("<html><p>Xin vui lòng chờ trong giây lát.... Mời quý khách nhận tiền ở khe bên dưới</p></html>",99);
					}else
					{
						thongBao(99);
					}
				}
				catch (Exception loi) {
					thongBao(99);
				}
			}
			else
			if (chon == 5)
			{
				try
				{
				   long tem = Long.parseLong(guiTien.getText().toString());
				   ResultSet kq = atmExe.getDataAcc();
				   kq.next();
				   if (tem > 20000000) {
					   thongBao("Limited 20 milion",9);
					   temp="";
				   }else
					   if (taiKhoan.getText().toString().compareTo(kq.getString("MKH").toString()) == 0)
					   {
						   thongBao("<html><p>Quý khách không thể gửi cho chính tài khoản của quý khách, Vui lòng kiểm tra lại</p></html>", 9);						
					   }		   
				   else
				   try
				   {
					if (atmExe.moveMoney(taiKhoan.getText().toString(), tem)) {
						thongBao("<html><p>Giao dịch thành công, quý khách vui lòng kiểm tra lại số dư</p></html>", 9);						
						temp="";
					}
					else {
						guiTien.setVisible(false);
						guiTien.setText("");
						guiTien.setVisible(true);
						taiKhoan.setVisible(false);
						taiKhoan.setText("");
						taiKhoan.setVisible(true);
						thongBao(9);
						temp="";
					}
				   }
				   catch (Exception loi) {
					loi.printStackTrace();
					thongBao(9);
			  	   }
				}
				catch (Exception loi) {
					thongBao("Vui lòng kiểm tra nhập liệu", 9);
					temp="";
				}
			}
			else
				if (chon == 90)
				{
					try
					{
					  if (ma.getText().toString().compareTo("") != 0)
					  {
						  if (ma.getText().toString().compareTo("Không tồn tại") != 0)
						  {
					      if (ma.getText().toString().compareTo("Nhập mã cần xóa") != 0)
							{
							  if (atmExe.XoaKH(ma.getText()))
							   {
								ma.setText("Đã xóa");
							   } else
							   {
								ma.setText("Không tồn tại");
							   }
							}
						  }
						  else ma.setText("Nhập mã cần xóa");
					  }else ma.setText("Nhập mã cần xóa");
					}
					catch (Exception loi) {
						loi.printStackTrace();
					}
				}
		  }
		}
		else if (e.getSource().equals(rb))
		{
			if (kt)
			{ 
				if (chon == 0)
				{
					pin.setText("");
					temp="";
					tbc.setText("Nhập mã PIN");
				}
				if (chon == 2)
				{
					phim = 1; temp="";
				}
				if (chon == 1)
				{
				    guiTien.setText("");
					menu.setVisible(false);
					remove(menu);
					tc.setText("<html><p>Giao dịch</p>khác</html>");
					th.setText("Xác nhận");
					tf.setText("Nhập số tiền cần gửi");
					ti.setText("<html><p>Hủy bỏ </p>giao dịch</html>");
					tm.setText("Xóa");
					pGT_a = new JPanel(new GridLayout(1,2)); pGT_a.setBackground(Color.blue);
					pGT_a.add(tc);pGT_a.add(th);
					pGT_b = new JPanel(new GridLayout(1,1)); pGT_b.setBackground(Color.blue);
					pGT_b.add(tf);
					pGT_c = new JPanel(new GridLayout(1,1)); pGT_c.setBackground(Color.blue);
					pGT_c.add(guiTien); guiTien.setEditable(false);
					pGT_d = new JPanel(new GridLayout(1,2)); pGT_d.setBackground(Color.blue);
					pGT_d.add(ti); pGT_d.add(tm);
					menu = new JPanel(new GridLayout(4,2)); 
					menu.add(pGT_a);
					menu.add(pGT_b);
					menu.add(pGT_c);
					menu.add(pGT_d);
					menu.setSize(500,440);
					menu.setLocation(100, 5);
					menu.setBackground(Color.blue);
					getContentPane().add(menu);
					chon = 4;
					temp="";
				}
				if (chon == 5) {
					phim = 1;
					temp="";
				}
				if (chon == 6)
				{
					try
					{
						if (atmExe.getMonney(Long.parseLong("200000")))
						{
							thongBao("<html><p>Xin vui lòng chờ trong giây lát.... Mời quý khách nhận tiền ở khe bên dưới</p></html>",99);
						}else
						{
							thongBao(99);
						}
					}
					catch (Exception loi) {
						thongBao(99);
					}
				}
				if (chon == 90)
				{
					ma.setText("");
					ten.setText("");					
					mapin.setText("");
					mathe.setText("");
				}
			}
		}
		else if (e.getSource().equals(rc))
		{
			if (kt)
			{ 
				if (chon == 2)
				{
					phim = 
						2;temp="";
				}
				if (chon == 4)
				{
					temp="";
				}
				if (chon == 1)
				{
					menu.setVisible(false);
					remove(menu);
					tc.setText("<html><p>Giao dịch</p>khác</html>");
					th.setText("Xác nhận");
					tf.setText("Tiền gửi: ");
					td.setText("Tài khoản nhận");
					ti.setText("<html><p>Hủy bỏ </p>giao dịch</html>");
					tm.setText("Xóa");
					pGT_a = new JPanel(new GridLayout(1,2)); pGT_a.setBackground(Color.blue);
					pGT_a.add(tc);pGT_a.add(th);
					pGT_b = new JPanel(new GridLayout(1,2)); pGT_b.setBackground(Color.blue);
					pGT_b.add(tf);pGT_b.add(guiTien);guiTien.setEditable(false);
					pGT_c = new JPanel(new GridLayout(1,2)); pGT_c.setBackground(Color.blue);
					pGT_c.add(td); pGT_c.add(taiKhoan);taiKhoan.setEditable(false);
					pGT_d = new JPanel(new GridLayout(1,2)); pGT_d.setBackground(Color.blue);
					pGT_d.add(ti); pGT_d.add(tm);
					menu = new JPanel(new GridLayout(4,2)); 
					menu.add(pGT_a);
					menu.add(pGT_b);
					menu.add(pGT_c);
					menu.add(pGT_d);
					menu.setSize(500,440);
					menu.setLocation(100, 5);
					menu.setBackground(Color.blue);
					getContentPane().add(menu);
					chon = 5;
					temp="";
				}
				if (chon == 5){
					phim = 2;
				    temp="";
				}
				if (chon == 6)
				{
					try
					{
						if (atmExe.getMonney(Long.parseLong("500000")))
						{
							thongBao("<html><p>Xin vui lòng chờ trong giây lát.... Mời quý khách nhận tiền ở khe bên dưới</p></html>",99);
						}else
						{
							thongBao(99);
						}
					}
					catch (Exception loi) {
						thongBao(99);
					}
				}
			}
		}
		else if (e.getSource().equals(rd))
		{
			if (kt)
			{
				if (chon == 2)
				{
					phim = 3;temp="";
				}

				if (chon == 4)
				{
					guiTien.setVisible(false);
					guiTien.setText("");
					guiTien.setVisible(true);
					temp="";
				}
				if (chon == 5)
				{
					guiTien.setVisible(false);
					guiTien.setText("");
					guiTien.setVisible(true);

					taiKhoan.setVisible(false);
					taiKhoan.setText("");
					taiKhoan.setVisible(true);
					temp="";
				}
				if (chon == 1)
				{
					guiTien2.setText("");
					phim = 0;
					menu.setVisible(false);
					remove(menu);
					xn.setVisible(false);
					tc.setText("Giao dịch khác");
					td.setText("100.000 VNĐ");
					tf.setText("200.000 VNĐ");	
					th.setText("500.000 VNĐ");
					ti.setText("Hủy bỏ giao dịch");
					tm.setText("Nhập số khác");
					menu = new JPanel(new GridLayout(4,2)); 
					menu.add(tc);menu.add(td);
					menu.add(guiTien2);menu.add(tf);
					menu.add(xn);menu.add(th);
					menu.add(ti);menu.add(tm);
					guiTien2.setBackground(Color.blue);
					menu.setSize(500,440);
					menu.setLocation(100, 5);
					menu.setBackground(Color.blue);
					getContentPane().add(menu);
					chon = 6;
				}else
				if (chon == 6)
				{
					guiTien2.setBackground(Color.pink);
		         	xn.setVisible(true);
		         	tc.setText("Trở về");
		         	td.setText("");
					tf.setText("");	
					th.setText("");
					tm.setText("Xóa");
					temp="";
					chon = 7;
				}else
					if (chon == 7)
					{
						guiTien2.setText("");
						temp="";
					}
					else if (chon == 90)
					{
						try
						{
						  ResultSet kq = atmExe.getDataAccFull();
						  kq.last();
						  int hang = kq.getRow();
						  String[] cot = {"Mã","Họ tên","Giới tính","PIN","Tiền","Mã Thẻ"};
						  Object[][] giaTri = new Object[hang][6];
						  kq.beforeFirst();
						  int i=0;
						  while(kq.next())
						  {
							  giaTri[i][0] = kq.getString("MKH");
							  giaTri[i][1] = kq.getString("HOTEN");
							  giaTri[i][2] = kq.getString("GIOITINH");
							  giaTri[i][3] = kq.getString("PIN");
							  giaTri[i][4] = kq.getString("TIEN");
							  giaTri[i][5] = kq.getString("MATHE");
							  i++;
						  }
						  JTable bang = new JTable(giaTri,cot);
						  JScrollPane js = new JScrollPane(bang);
						  
						  menu.setVisible(false);
						  remove(menu);
						  menu = new JPanel(); 
						  menu.add(js);
						  menu.setSize(500,440);
						  menu.setLocation(100, 5);
						  menu.setBackground(Color.blue);
						  getContentPane().add(menu);
						  rd.setText("Trở về");
						  ra.setText("");
						  rb.setText("");
						  ld.setText("");
						  chon = 900;
						}
						catch (Exception loi) {
							loi.printStackTrace();
						}
					}
					else if (chon == 900)
					{
						 chon = 90;
					     td.setText("Giới tính: ");
					     tf.setText("Mã PIN: ");
					     th.setText("Mã khách hàng: ");
					     tm.setText("Họ tên: ");
						 menu.setVisible(false);
						 remove(menu);
						 menu = new JPanel(new GridLayout(5,2));
						 menu.setSize(500,440);
						 menu.setLocation(100, 5);
						 menu.add(th); menu.add(ma);
						 menu.add(tm); menu.add(ten);
						 menu.add(td); menu.add(gt);
						 menu.add(tf); menu.add(mapin);
						 menu.add(mthe); menu.add(mathe);
						 menu.setBackground(Color.blue);  
						 ld.setText("<html><p>Thêm khách hàng</p></html>");
						 ra.setText("<html><p>Xóa khách hàng</p></html>");
						 rb.setText("<html><p>Xóa dữ liệu nhập</p></html>");
						 rd.setText("<html><p>Danh sách khách hàng</p></html>");
						 getContentPane().add(menu);
					}
			}
		}else if (e.getSource().equals(admin))
		{
			if (ad)
			{
				 chon = 90;
			     td.setText("Giới tính: ");
			     tf.setText("Mã PIN: ");
			     th.setText("Mã khách hàng: ");
			     tm.setText("Họ tên: ");
				 menu.setVisible(false);
				 remove(menu);
				 menu = new JPanel(new GridLayout(5,2));
				 menu.setSize(500,440);
				 menu.setLocation(100, 5);
				 menu.add(th); menu.add(ma);
				 menu.add(tm); menu.add(ten);
				 menu.add(td); menu.add(gt);
				 menu.add(tf); menu.add(mapin);
				 menu.add(mthe); menu.add(mathe);
				 menu.setBackground(Color.blue);  
				 ld.setText("<html><p>Thêm khách hàng</p></html>");
				 ra.setText("<html><p>Xóa khách hàng</p></html>");
				 rb.setText("<html><p>Xóa dữ liệu nhập</p></html>");
				 rd.setText("<html><p>Danh sách khách hàng</p></html>");
				 getContentPane().add(menu);
				
			}
			
		}
		
	}

	public void thongBao(int c)
	{
		   temp="";
		   tc.setText("Trở về");
		   te.setText(atmExe.getLoi());
		   tg.setText("");
		   menu.setVisible(false);
		   remove(menu);
		   menu = new JPanel(new GridLayout(3,1));
		   menu.setSize(500,440);
		   menu.setLocation(100, 5);
		   menu.add(tc);
		   menu.add(te);
		   menu.add(tg);
		   menu.setBackground(Color.blue);  
		   getContentPane().add(menu);
		   chon = c;
	}
	public void thongBao(String tb, int c)
	{
		   temp="";
		   tc.setText("Trở về");
		   te.setText(tb);
		   tg.setText("");
		   menu.setVisible(false);
		   remove(menu);
		   menu = new JPanel(new GridLayout(3,1));
		   menu.setSize(500,440);
		   menu.setLocation(100, 5);
		   menu.add(tc);
		   menu.add(te);
		   menu.add(tg);
		   menu.setBackground(Color.blue);  
		   getContentPane().add(menu);
		   chon = c;
	}
	public void admin(String tb)
	{
		   temp="";
		   te.setText(tb);
		   tg.setText("");
		   menu.setVisible(false);
		   remove(menu);
		   menu = new JPanel(new GridLayout(2,1));
		   menu.setSize(500,440);
		   menu.setLocation(100, 5);
		   menu.add(te);
		   menu.add(tg);
		   menu.setBackground(Color.blue);  
		   getContentPane().add(menu);
	}
}
