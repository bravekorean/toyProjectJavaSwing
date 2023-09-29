package Program;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import StockManagerment.Stock;
import StockManagerment.StockDAO;
import UserManagement.User;
import UserManagement.UserDAO;


public class Start extends JFrame implements ActionListener {
	   private JPanel inputPanel;
	   JPanel btnpanel = new JPanel();
	   JPanel userbtnpanel = new JPanel();
	   JPanel stockpanel = new JPanel();
	   JPanel userpanel = new JPanel();
	    private JLabel idLabel;
	    private JTextField idInput;
	    private JLabel passLabel;
	    private JPasswordField passInput; // 패스워드 입력 필드로 변경
	    private JButton loginButton;
	    private JTable table;
	    private JTable stocktable;
	 
    
    private JFrame jframe;
  
    private Connection con;
    
    
    // 이미지 출처 "https://kr.freepik.com/free-vector/erp-infographic_25106209.htm#query=erp&position=13&from_view=keyword&track=sph">
	public Start() {
		setTitle("Company ERP");
		setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	       
		LoginPanel();

	        setVisible(true);
	}

	public static void main(String[] args) {
		
		new Start();
	}
	
	void setVisibleFalse() {
		inputPanel.setVisible(false);
		stockpanel.setVisible(false);
		userpanel.setVisible(false);
		userbtnpanel.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 if (e.getSource() == loginButton) {
	            String username = idInput.getText();
	            char[] passwordChars = passInput.getPassword();
	            String password = new String(passwordChars);

		            // userDAO 클래스의 인스턴스 생성
		            UserDAO dao = new UserDAO();
	
		            // 여기서 데이터베이스 연결 및 로그인 검증 수행
		            ArrayList<User> userList = dao.getUserList();
	
		            boolean validUser = false;
		            boolean isAdmin = false;
	
		            for (User user : userList) {
		                if (user.getUserid().equals(username) && user.getUserpass().equals(password)) {
		                    validUser = true;
		                    if (user.getRole().equals("admin")) {
		                        isAdmin = true;
		                    }
		                    break;
		                }
		            }
	
		            if (validUser) {
		                // 로그인 성공 시 처리
		                if (isAdmin) {
		                    JOptionPane.showMessageDialog(this, "관리자로 로그인 성공!");
		                    setVisibleFalse();
		                    btnPanel(true);
		                } else {
		                    JOptionPane.showMessageDialog(this, "사용자로 로그인 성공!");
		                    setVisibleFalse();
		                    btnPanel(false);
		                }
		            } else {
		                // 로그인 실패 시 처리
		                JOptionPane.showMessageDialog(this, "로그인 실패. 유효하지 않은 아이디 또는 비밀번호.");
		            }
	        }
	}
	
	private void LoginPanel() {
		
		setLocationRelativeTo(null); // 화면 중앙에 표시
        setLayout(new BorderLayout());

        JPanel imagePanel = new JPanel();
     // 이미지 파일의 상대 경로 지정
        String imagePath = "images/Main.jpg";

        // 이미지 아이콘 생성
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(imagePath));
        
        JLabel images = new JLabel(imageIcon);
        images.setPreferredSize(new Dimension(300, 300));
        
     // ID 및 Password 입력 패널
        inputPanel = new JPanel();
        add(inputPanel, BorderLayout.CENTER);
        JPanel panel1 = new JPanel();
        idLabel = new JLabel("ID:");
        idInput = new JTextField(15);
        idInput.setColumns(10); // 아이디 필드 크기 설정
        passLabel = new JLabel("Password:");
        passInput = new JPasswordField(15);
        passInput.setColumns(10); // 패스워드 필드 크기 설정
        loginButton = new JButton("Login");
        
        panel1.setLayout(new GridLayout(3,2,5,5));
        panel1.add(idLabel);
        panel1.add(idInput);
        panel1.add(passLabel);
        panel1.add(passInput);
        panel1.add(new JLabel());
        panel1.add(loginButton);
    


        loginButton.addActionListener(this); // 로그인 버튼에 액션 리스너 등록

        // 전체 화면에 컴포넌트 추가
        inputPanel.add(panel1, BorderLayout.CENTER);
        inputPanel.add(images, BorderLayout.NORTH);
   
	}
	
	
	
	private void btnPanel(boolean isAdmin) {
		
		  	btnpanel.setBackground(Color.gray);
		    JButton btn1 = new JButton("재고관리");
		    JButton btn2 = new JButton("유저관리");
		  
		    JButton btnClose = new JButton("닫기");

		    btnpanel.add(btn1);

		    if (isAdmin) {
		        btnpanel.add(btn2); // 관리자인 경우에만 유저 관리 버튼 추가
		    }
	  
		    btnpanel.add(btnClose);

		    btnActionListener listener = new btnActionListener();
		    btn1.addActionListener(listener);
		    btn2.addActionListener(listener);
		    
		    btnClose.addActionListener(listener);

		    add(btnpanel, BorderLayout.NORTH);
	}
	
	class btnActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String msg =  e.getActionCommand();
			JButton btn = (JButton) e.getSource();
			if (btn.getActionCommand().equals("닫기")) {
				JOptionPane.showMessageDialog(null, "종료합니다.");
				System.exit(0);
			
			}
			
			if (btn.getActionCommand().equals("재고관리")) {
				System.out.println("재고관리 ");
				StockPanel();
			}
			
			
			if (btn.getActionCommand().equals("유저관리")) {
				System.out.println("유저관리");
				Userpanel();	
			}
			
			
			
		}
		
	}
	

	
	class userActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String msg =  e.getActionCommand();
			JButton btn = (JButton) e.getSource();
			if (btn.getActionCommand().equals("등록")) {
				System.out.println("등록");
				new UserEditor().setVisible(true);
			}
			
			if (btn.getActionCommand().equals("수정")) {
				System.out.println("수정");
				new UpdateEditor().setVisible(true);
	                } 
			
						
			if (btn.getActionCommand().equals("삭제")) {
				System.out.println("삭제");
				new deleteEditor().setVisible(true);
			}
		
		
		}
	}
	

	
	private void Userpanel() {
		userpanel = new JPanel();
		// TODO Auto-generated method stub
		userpanel.setBackground(Color.blue);
		
		JTextField tfSearch = new JTextField(40);
		//-----------------------
		
		
		Vector<String> tableTitle = new Vector<String>();
		tableTitle.add("번호");
		tableTitle.add("아이디");
		tableTitle.add("비밀번호");
		tableTitle.add("이름");
		tableTitle.add("부서");
		tableTitle.add("권한");
		
		DefaultTableModel model = new DefaultTableModel(tableTitle, 0);
		
		table = new JTable(model);
		
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int srow = table.getSelectedRow();
				String usercode = (String) table.getValueAt(srow, 0);
				String id =  (String) table.getValueAt(srow, 1);
				String pwd = (String) table.getValueAt(srow, 2);
				String name = (String) table.getValueAt(srow, 3);
				String part = (String) table.getValueAt(srow, 4);
				String role = (String) table.getValueAt(srow, 5);
				
				System.out.println(usercode +  id + pwd  + name +  part +
						role);
			}});
		
		
		UserDAO dao = new UserDAO();
		ArrayList<User> userList = dao.getUserList();
		
		for(User u : userList) {
			Vector<String> v = new Vector<String>();
			v.add(Integer.toString(u.getUsercode()));
			v.add(u.getUserid());
			v.add(u.getUserpass());
			v.add(u.getUsername());
			v.add(u.getUserpart());
			v.add(u.getRole());
			
			model.addRow(v);
		}
		
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				 String val = tfSearch.getText();
	                TableRowSorter<TableModel> trs = new TableRowSorter<>(table.getModel());
	                table.setRowSorter(trs);
	                trs.setRowFilter(RowFilter.regexFilter(val));
			}
			
		});
		JPanel btnp = new JPanel();
		 JButton createbtn = new JButton("등록");
		 JButton updatebtn = new JButton("수정");
		 JButton deletebtn = new JButton("삭제");
		btnp.add(createbtn);
		btnp.add(updatebtn);
		btnp.add(deletebtn);
		  userActionListener listener = new userActionListener();
		   createbtn.addActionListener(listener);
		   updatebtn.addActionListener(listener);
		   deletebtn.addActionListener(listener);
		userpanel.add(btnp,BorderLayout.SOUTH);
		userpanel.add(table);
		userpanel.add(tfSearch);
		userpanel.add(new JScrollPane(table));
		
		
		
		
		
		//-------------------------
		add(userpanel);
//		add(userbtnpanel);
		setVisibleFalse();
		userpanel.setVisible(true);
		userbtnpanel.setVisible(true);
		
	}

	class UserEditor extends JFrame implements ActionListener {
        private JTextField idTextField;
        private JTextField passwordTextField;
        private JTextField nameTextField;
        private JTextField partTextField;
        private JTextField roleTextField;

        public UserEditor() {
            setTitle("회원가입");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel editorPanel = new JPanel();
            editorPanel.setLayout(new GridLayout(6, 2));

            JLabel lblId = new JLabel("아이디:");
            idTextField = new JTextField(20);
            JLabel lblPassword = new JLabel("비밀번호:");
            passwordTextField = new JTextField(20);
            JLabel lblName = new JLabel("이름:");
            nameTextField = new JTextField(20);
            JLabel lblPart = new JLabel("부서:");
            partTextField = new JTextField(20);
            JLabel lblRole = new JLabel("권한:");
            roleTextField = new JTextField(20);

            JButton saveButton = new JButton("저장");
            saveButton.addActionListener(this);

            editorPanel.add(lblId);
            editorPanel.add(idTextField);
            editorPanel.add(lblPassword);
            editorPanel.add(passwordTextField);
            editorPanel.add(lblName);
            editorPanel.add(nameTextField);
            editorPanel.add(lblPart);
            editorPanel.add(partTextField);
            editorPanel.add(lblRole);
            editorPanel.add(roleTextField);
            editorPanel.add(new JLabel()); // 빈 라벨
            editorPanel.add(saveButton);

            add(editorPanel);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("저장")) {
                // 사용자 입력 받기
                String id = idTextField.getText();
                String password = passwordTextField.getText();
                String name = nameTextField.getText();
                String part = partTextField.getText();
                String role = roleTextField.getText();

                // 데이터베이스에 사용자 등록
                User newUser = new User(id, password, name, part, role);
                UserDAO dao = new UserDAO();
                dao.insertUser(newUser);

                // 테이블에 새로운 사용자 정보 추가
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                Vector<String> newRow = new Vector<>();
                newRow.add(Integer.toString(newUser.getUsercode()));
                newRow.add(newUser.getUserid());
                newRow.add(newUser.getUserpass());
                newRow.add(newUser.getUsername());
                newRow.add(newUser.getUserpart());
                newRow.add(newUser.getRole());
                model.addRow(newRow);

                // 필드 초기화
                idTextField.setText("");
                passwordTextField.setText("");
                nameTextField.setText("");
                partTextField.setText("");
                roleTextField.setText("");

                // 현재 창 닫기
                dispose();
                
                ArrayList<User> userList = dao.getUserList();
                model.setRowCount(0); // 테이블 초기화
                for (User user : userList) {
                    Vector<String> rowData = new Vector<>();
                    rowData.add(Integer.toString(user.getUsercode()));
                    rowData.add(user.getUserid());
                    rowData.add(user.getUserpass());
                    rowData.add(user.getUsername());
                    rowData.add(user.getUserpart());
                    rowData.add(user.getRole());
                    model.addRow(rowData);
                }
            }
 
        }
	} // UserEditor end -- 
	
	class UpdateEditor extends JFrame implements ActionListener {
		   private JTextField idTextField;
	        private JTextField passwordTextField;
	        private JTextField nameTextField;
	        private JTextField partTextField;
	        private JTextField roleTextField;
	        
	        public UpdateEditor() {
	        	
	        	setTitle("사용자 정보 편집");
	            setSize(400, 300);
	            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            
	            JPanel editorPanel = new JPanel();
	            editorPanel.setLayout(new GridLayout(6, 2));
	            
	            JLabel lblPassword = new JLabel("비밀번호:");
	            passwordTextField = new JTextField(20);
	            JLabel lblName = new JLabel("이름:");
	            nameTextField = new JTextField(20);
	            JLabel lblPart = new JLabel("부서:");
	            partTextField = new JTextField(20);
	            JLabel lblRole = new JLabel("권한:");
	            roleTextField = new JTextField(20);
	            JLabel lblId = new JLabel("기존 아이디:");
	            idTextField = new JTextField(20);
	            
	            JButton saveButton = new JButton("수정");
	            saveButton.addActionListener(this);
	           
	            editorPanel.add(lblPassword);
	            editorPanel.add(passwordTextField);
	            editorPanel.add(lblName);
	            editorPanel.add(nameTextField);
	            editorPanel.add(lblPart);
	            editorPanel.add(partTextField);
	            editorPanel.add(lblRole);
	            editorPanel.add(roleTextField);
	            editorPanel.add(lblId);
	            editorPanel.add(idTextField);
	            editorPanel.add(new JLabel()); // 빈 라벨
	            editorPanel.add(saveButton);

	            add(editorPanel);
	        	
	        }
	        
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("수정")) {
			     
                String id = idTextField.getText();
                String password = passwordTextField.getText();
                String name = nameTextField.getText();
                String part = partTextField.getText();
                String role = roleTextField.getText();
                
                UserDAO dao = new UserDAO();
                ArrayList<User> userList = dao.getUserList();

                boolean userFound = false;
                
                for (User user : userList) {
                    if (user.getUserid().equals(id)) {
                        // 사용자를 찾았을 때 업데이트 수행
                        user.setUserpass(password);
                        user.setUsername(name);
                        user.setUserpart(part);
                        user.setRole(role);

                        // 데이터베이스에 수정된 사용자 정보를 업데이트
                        dao.updateUser(user);

                        // 사용자 정보가 성공적으로 업데이트되면 메시지 표시
                        JOptionPane.showMessageDialog(this, "사용자 정보가 수정되었습니다.");

                        // 필드 초기화 및 창 닫기
                        idTextField.setText("");
                        passwordTextField.setText("");
                        nameTextField.setText("");
                        partTextField.setText("");
                        roleTextField.setText("");
                        dispose();
                        
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        ArrayList<User> userList1 = dao.getUserList();
                        model.setRowCount(0); // 테이블 초기화
                        for (User user1 : userList1) {
                            Vector<String> rowData = new Vector<>();
                            rowData.add(Integer.toString(user1.getUsercode()));
                            rowData.add(user1.getUserid());
                            rowData.add(user1.getUserpass());
                            rowData.add(user1.getUsername());
                            rowData.add(user1.getUserpart());
                            rowData.add(user1.getRole());
                            model.addRow(rowData);
                        }

                        userFound = true;
                        break;
                    }
                }

                if (!userFound) {
                    // 사용자를 찾지 못했을 때 경고 메시지 표시
                    JOptionPane.showMessageDialog(this, "해당 아이디를 가진 사용자가 존재하지 않습니다.");
                }
			} //Update Action end -- 
		}
		
	} // Update Editor end -- 
	
	
	class deleteEditor extends JFrame implements ActionListener {
		
		private JTextField idTextField;
		
		public deleteEditor() {
		   	setTitle("사용자 정보 삭제");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
            JPanel editorPanel = new JPanel();
            
            JLabel lblId = new JLabel("기존 아이디:");
            idTextField = new JTextField(20);
            JButton deleteButton = new JButton("삭제");
            editorPanel.add(lblId);
            editorPanel.add(idTextField);
            editorPanel.add(deleteButton);
            deleteButton.addActionListener(this);

            
            add(editorPanel);
           
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("삭제")) {
				String id = idTextField.getText();
				
				try {
					UserDAO dao = new UserDAO();
					
					dao.deleteUser(id);
					
					JOptionPane.showMessageDialog(this, "사용자 정보가 삭제되었습니다.");
					
					// 필드 초기화 및 창 닫기
                    idTextField.setText("");
                    
                    dispose();
                    
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    ArrayList<User> userList = dao.getUserList();
                    model.setRowCount(0); // 테이블 초기화
                    for (User user : userList) {
                        Vector<String> rowData = new Vector<>();
                        rowData.add(Integer.toString(user.getUsercode()));
                        rowData.add(user.getUserid());
                        rowData.add(user.getUserpass());
                        rowData.add(user.getUsername());
                        rowData.add(user.getUserpart());
                        rowData.add(user.getRole());
                        model.addRow(rowData);
                    }
					
					
					
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(this, "해당 아이디를 가진 사용자가 존재하지 않습니다.");
					exception.getStackTrace();
				}
				
			}
		} // delete Action end --
		
	} // Delete Editor end --
	
	
	// 여기까지가 UserManager 
	// 여기부터 StockManager 
	
	private void StockPanel() {
		stockpanel = new JPanel();
		stockpanel.setBackground(Color.green);
		
		
		JTextField tfSearch = new JTextField(40);
		//-----------------------
		
		
		Vector<String> tableTitle = new Vector<String>();
		tableTitle.add("번호");
		tableTitle.add("이름");
		tableTitle.add("재고");
		tableTitle.add("가격");
		
		
		DefaultTableModel model = new DefaultTableModel(tableTitle, 0);
		
		stocktable = new JTable(model);
		
		stocktable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int srow = stocktable.getSelectedRow();
				String procode = (String) stocktable.getValueAt(srow, 0);
				String name =  (String) stocktable.getValueAt(srow, 1);
				String stock = (String) stocktable.getValueAt(srow, 2);
				String price = (String) stocktable.getValueAt(srow, 3);
			
				
				System.out.println(procode + name +  stock + price);
			}});
		
		
		StockDAO dao = new StockDAO();
		ArrayList<Stock> userList = dao.getUserList();
		
		for(Stock u : userList) {
			Vector<String> v = new Vector<String>();
			v.add(Integer.toString(u.getProid()));
			v.add(u.getProname());
			v.add(Integer.toString(u.getStock()));
			v.add(Integer.toString(u.getPrice()));
			
			model.addRow(v);
		}
		
		
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				 String val = tfSearch.getText();
	                TableRowSorter<TableModel> trs = new TableRowSorter<>(stocktable.getModel());
	                stocktable.setRowSorter(trs);
	                trs.setRowFilter(RowFilter.regexFilter(val));
			}
			
		});
		JPanel btnp = new JPanel();
		 JButton createbtn = new JButton("등록");
		 JButton updatebtn = new JButton("수정");
		 JButton deletebtn = new JButton("삭제");
		btnp.add(createbtn);
		btnp.add(updatebtn);
		btnp.add(deletebtn);
		  stockActionListener listener = new stockActionListener();
		   createbtn.addActionListener(listener);
		   updatebtn.addActionListener(listener);
		   deletebtn.addActionListener(listener);
		stockpanel.add(btnp,BorderLayout.SOUTH);
		stockpanel.add(stocktable);
		stockpanel.add(tfSearch);
		stockpanel.add(new JScrollPane(stocktable));
		
		
		
		
		
		//-------------------------
		
		add(stockpanel);
		setVisibleFalse();
		stockpanel.setVisible(true);
		
	}
	
	class stockActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String msg =  e.getActionCommand();
			JButton btn = (JButton) e.getSource();
			if (btn.getActionCommand().equals("등록")) {
				System.out.println("재품등록");
				new StockEditor().setVisible(true);
			}
			
			if (btn.getActionCommand().equals("수정")) {
				System.out.println("재고수정");
				new StockUpdate().setVisible(true);
	                } 
			
						
			if (btn.getActionCommand().equals("삭제")) {
				System.out.println("재고삭제");
				new StockDelete().setVisible(true);
			}
		} // Action end
		
	} // stockAction end -- 
	
	
	class StockEditor extends JFrame implements ActionListener {
        private JTextField nameTextField;
        private JTextField stockTextField;
        private JTextField priceTextField;

		public  StockEditor() {
			
			 setTitle("제품등록");
	            setSize(400, 300);
	            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	            JPanel editorPanel = new JPanel();
	            editorPanel.setLayout(new GridLayout(6, 2));

	            JLabel lblName = new JLabel("이름:");
	            nameTextField = new JTextField(20);
	            JLabel lblstock = new JLabel("재고:");
	            stockTextField = new JTextField(20);
	            JLabel lblprice = new JLabel("가격:");
	            priceTextField = new JTextField(20);
	            JButton saveButton = new JButton("저장");
	            saveButton.addActionListener(this);

	          
	          
	            editorPanel.add(lblName);
	            editorPanel.add(nameTextField);
	            editorPanel.add(lblstock);
	            editorPanel.add(stockTextField);
	            editorPanel.add(lblprice);
	            editorPanel.add(priceTextField);
	            editorPanel.add(new JLabel()); // 빈 라벨
	            editorPanel.add(saveButton);

	            add(editorPanel);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("저장")) {
             
                String name = nameTextField.getText();
                String stockText = stockTextField.getText();
                String priceText = priceTextField.getText();
                
                int stock = Integer.parseInt(stockText);
                int price = Integer.parseInt(priceText);

                // 데이터베이스에 사용자 등록
                Stock newStock = new Stock(name,stock,price);
                StockDAO dao = new StockDAO();
                dao.insertStock(newStock);

                // 테이블에 새로운 사용자 정보 추가
                DefaultTableModel model = (DefaultTableModel) stocktable.getModel();
                Vector<String> newRow = new Vector<>();
                newRow.add(Integer.toString(newStock.getProid()));
                newRow.add(newStock.getProname());
                newRow.add(Integer.toString(newStock.getStock()));
                newRow.add(Integer.toString(newStock.getPrice()));
                
                
                model.addRow(newRow);

                // 필드 초기화
              
                nameTextField.setText("");
                stockTextField.setText("");
                priceTextField.setText("");
                
             

                // 현재 창 닫기
                dispose();
                
                ArrayList<Stock> stockList = dao.getUserList();
                model.setRowCount(0); // 테이블 초기화
                for (Stock stock1 : stockList) {
                    Vector<String> rowData = new Vector<>();
                    rowData.add(Integer.toString(stock1.getProid()));
                    rowData.add(stock1.getProname());
                    rowData.add(Integer.toString(stock1.getStock()));
                    rowData.add(Integer.toString(stock1.getPrice()));
                 
                    model.addRow(rowData);
                }
		} // Stock Editor Action end
		
		}
	} // StockEditor end 

	
	class StockUpdate extends JFrame implements ActionListener {
		private JTextField idTextField;
		 private JTextField nameTextField;
	        private JTextField stockTextField;
	        private JTextField priceTextField;
	        
		public StockUpdate() {
			setTitle("사용자 정보 편집");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
            JPanel editorPanel = new JPanel();
            editorPanel.setLayout(new GridLayout(6, 2));
            
           
            JLabel lblName = new JLabel("이름:");
            nameTextField = new JTextField(20);
            JLabel lblStock = new JLabel("재고:");
            stockTextField = new JTextField(20);
            JLabel lblPrice = new JLabel("가격:");
            priceTextField = new JTextField(20);
            JLabel lblId = new JLabel("제품 번호 :");
            idTextField = new JTextField(20);
            
            JButton saveButton = new JButton("수정");
            saveButton.addActionListener(this);
           
       
            editorPanel.add(lblName);
            editorPanel.add(nameTextField);
            editorPanel.add(lblStock);
            editorPanel.add(stockTextField);
            editorPanel.add(lblPrice);
            editorPanel.add(priceTextField);
            editorPanel.add(lblId);
            editorPanel.add(idTextField);
            editorPanel.add(new JLabel()); // 빈 라벨
            editorPanel.add(saveButton);

            add(editorPanel);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("수정")) {
			     
                String idText = idTextField.getText();
                String name = nameTextField.getText();
                String stockText = stockTextField.getText();
                String priceText= priceTextField.getText();
                
                int id = Integer.parseInt(idText);
                int stock = Integer.parseInt(stockText);
                int price = Integer.parseInt(priceText);
                
                StockDAO dao = new StockDAO();
                ArrayList<Stock> StockList = dao.getUserList();

                boolean stockFound = false;
                
                for (Stock stock1 : StockList) {
                    if (stock1.getProid() == id ) {
                        // 사용자를 찾았을 때 업데이트 수행
                       
                        stock1.setProname(name);
                        stock1.setStock(stock);
                        stock1.setPrice(price);

                        // 데이터베이스에 수정된 사용자 정보를 업데이트
                        dao.updateStockInfo(name,stock,price,id);

                        // 사용자 정보가 성공적으로 업데이트되면 메시지 표시
                        JOptionPane.showMessageDialog(this, "제품 정보가 수정되었습니다.");

                        // 필드 초기화 및 창 닫기
                        idTextField.setText("");
                        nameTextField.setText("");
                        stockTextField.setText("");
                        priceTextField.setText("");
                        dispose();
                        
                        DefaultTableModel model = (DefaultTableModel) stocktable.getModel();
                        ArrayList<Stock> stockList1 = dao.getUserList();
                        model.setRowCount(0); // 테이블 초기화
                        for (Stock stock2 : stockList1) {
                            Vector<String> rowData = new Vector<>();
                            rowData.add(Integer.toString(stock2.getProid()));
                            rowData.add(stock2.getProname());
                            rowData.add(Integer.toString(stock2.getStock()));                            
                            rowData.add(Integer.toString(stock2.getPrice()));
                         
                            
                           
                            model.addRow(rowData);
                        }

                        stockFound = true;
                        break;
                    }
                }

                if (!stockFound) {
                    // 사용자를 찾지 못했을 때 경고 메시지 표시
                    JOptionPane.showMessageDialog(this, "해당 번호를 가진 제품이 없습니다.");
                }
		} // StockUpdateAction end 
		}
		
	} // StockUpdate end 
	
	class StockDelete extends JFrame implements ActionListener {
		
		private JTextField idTextField;
		
		public StockDelete() {
		 	setTitle("사용자 정보 삭제");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
            JPanel editorPanel = new JPanel();
            
            JLabel lblId = new JLabel("제품 번호:");
            idTextField = new JTextField(20);
            JButton deleteButton = new JButton("삭제");
            editorPanel.add(lblId);
            editorPanel.add(idTextField);
            editorPanel.add(deleteButton);
            deleteButton.addActionListener(this);

            
            add(editorPanel);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("삭제")) {
				String idText = idTextField.getText();
				
				int id = Integer.parseInt(idText);
				
				
				try {
					StockDAO dao = new StockDAO();
					
					dao.deleteStock(id);
					
					JOptionPane.showMessageDialog(this, "사용자 정보가 삭제되었습니다.");
					
					// 필드 초기화 및 창 닫기
                    idTextField.setText("");
                    
                    dispose();
                    
                    DefaultTableModel model = (DefaultTableModel) stocktable.getModel();
                    ArrayList<Stock> stockList1 = dao.getUserList();
                    model.setRowCount(0); // 테이블 초기화
                    for (Stock stock2 : stockList1) {
                        Vector<String> rowData = new Vector<>();
                        rowData.add(Integer.toString(stock2.getProid()));
                        rowData.add(stock2.getProname());
                        rowData.add(Integer.toString(stock2.getStock()));                            
                        rowData.add(Integer.toString(stock2.getPrice()));
                        model.addRow(rowData);
                    }
					
					
					
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(this, "해당 번호가 없습니다.");
					exception.getStackTrace();
				}
				
			}
		}
		
	}
	
	
	
} // Start end -- 


	
	
