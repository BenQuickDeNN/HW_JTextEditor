package mainpack;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Java 文本编辑器 主类
 * @author Ben Quick in XJTU
 *
 */
public class MainClass {

	/**
	 * 主UI框架
	 */
	JFrame MainFrame;
	/**
	 * 文本编辑框
	 */
	JTextPane TextPanel;
	/**
	 * 图片显示板
	 */
	ImagePanel imagePane;
	/**
	 * 全局变量
	 */
	GlobalVar GlobalVarMain;
	/**
	 * 是否第一次保存
	 */
	boolean isFirstSaved = true;
	/**
	 * 服务器IP地址
	 */
	String ServerIP = "127.0.0.1";
	/**
	 * 服务器应用程序端口
	 */
	int ServerPort = 4700;
	/**
	 * 文件名
	 */
	String FileName = "新建文档." + GlobalVar.FileExtendsionName;
	public MainClass() {
		initialGlobalVar();
		initialFrame();
		initialWidget();
	}

	public static void main(String[] args) {
		new MainClass();
	}
	
	
	/**
	 * 初始化全局变量
	 */
	private void initialGlobalVar(){
		GlobalVarMain = new GlobalVar();
	}
	
	
	/**
	 * 初始化图形界面
	 */
	private void initialFrame(){
		MainFrame = new JFrame(GlobalVar.DefaultTitle);
		MainFrame.setLayout(null);
		MainFrame.setResizable(false);
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (ScreenSize.width - GlobalVar.DefaultWidth) / 2;
		int y = (ScreenSize.height - GlobalVar.DefaultHeight) / 2;
		MainFrame.setBounds(x, y, GlobalVar.DefaultWidth, GlobalVar.DefaultHeight);
		MainFrame.setVisible(true);
		MainFrame.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	/**
	 * 初始化组件
	 */
	private void initialWidget(){
		initTextPane();
		initImagePanel();
		initMenuBar();
	}
	/**
	 * 初始化文本编辑框
	 * @return 文本编辑框
	 */
	private void initTextPane(){
		TextPanel = new JTextPane();
		TextPanel.setEditable(true);
		TextPanel.setVisible(true);
		TextPanel.setBounds(10, 10, 660, MainFrame.getHeight() - 20);
		TextPanel.setFont(new Font("宋体", Font.PLAIN, 18));
		MainFrame.add(TextPanel);
		isFirstSaved = true;
	}
	/**
	 * 初始化图片显示板
	 */
	private void initImagePanel(){
		imagePane = new ImagePanel(null);
		MainFrame.add(imagePane);
	}
	/**
	 * 初始化菜单栏
	 * @return 菜单栏
	 */
	private void initMenuBar(){
		MenuBar MenuBarMain = new MenuBar();
		
		Menu menuFile = new Menu("文件");
		Menu menuEdit = new Menu("编辑");
		Menu menuSetting = new Menu("设置");
		/* 文件菜单项目  */
		MenuItem itemFileCreate = new MenuItem("新建...");			menuFile.add(itemFileCreate);			itemFileCreate.addActionListener(ItemFileCreateClick);
		MenuItem itemFileOpen = new MenuItem("打开...");				menuFile.add(itemFileOpen);				itemFileOpen.addActionListener(ItemFileOpenClickActionListener);
		MenuItem itemFileSave = new MenuItem("保存");					menuFile.add(itemFileSave);				itemFileSave.addActionListener(ItemFileSaveClickActionListener);
		MenuItem itemFileSaveAs = new MenuItem("另存为...");			menuFile.add(itemFileSaveAs);			itemFileSaveAs.addActionListener(ItemFileSaveAsClickActionListener);
		MenuItem itemFileSync = new MenuItem("同步到云");				menuFile.add(itemFileSync);				itemFileSync.addActionListener(ItemFileSyncClickActionListener);
		MenuItem itemFileGet = new MenuItem("从云中读取文件");			menuFile.add(itemFileGet);				itemFileGet.addActionListener(ItemFileGetActionListener);
		/* 编辑菜单项目 */
		MenuItem itemEditClear = new MenuItem("清空");				menuEdit.add(itemEditClear);			itemEditClear.addActionListener(ItemEditClearClickActionListener);
		MenuItem itemEditInsertImage = new MenuItem("插入图片...");	menuEdit.add(itemEditInsertImage);		itemEditInsertImage.addActionListener(ItemEditInsertImageActionListener);
		MenuItem itemEditClearImage = new MenuItem("清空图片");		menuEdit.add(itemEditClearImage);		itemEditClearImage.addActionListener(ItemEditClearImageActionListener);
		MenuItem itemEditFind = new MenuItem("查找...");				menuEdit.add(itemEditFind);				itemEditFind.addActionListener(ItemEditFindClickActionListener);
		MenuItem itemEditReplace = new MenuItem("替换...");			menuEdit.add(itemEditReplace);			itemEditReplace.addActionListener(ItemEditReplaceActionListener);
		/* 设置菜单项目 */
		MenuItem itemSettingFont = new MenuItem("字体...");			menuSetting.add(itemSettingFont);		itemSettingFont.addActionListener(ItemSettingFontActionListener);
		MenuItem itemSettingNetwork = new MenuItem("网络...");		menuSetting.add(itemSettingNetwork);	itemSettingNetwork.addActionListener(ItemSettingNetworkActionListener);
		
		MenuBarMain.add(menuFile);
		MenuBarMain.add(menuEdit);
		MenuBarMain.add(menuSetting);
		
		MainFrame.setMenuBar(MenuBarMain);
	}
	
	/*
	 * 菜单事件响应
	 */
	/**
	 * 新建文件事件
	 */
	ActionListener ItemFileCreateClick = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			TextPanel.setText("");
			TextPanel.setFont(new Font("宋体", Font.PLAIN, 18));
			isFirstSaved = true;
			FileName = "新建文档." + GlobalVar.FileExtendsionName;
			imagePane.setImage(null);
			imagePane.repaint();
		}
	};
	/**
	 * 另存为事件
	 */
	ActionListener ItemFileSaveAsClickActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			showSaveFileDialog("另存为", "保存", "错误", "文件保存失败！", false);
		}
	};
	/**
	 * 保存文件事件
	 */
	ActionListener ItemFileSaveClickActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {

			if(isFirstSaved){
				showSaveFileDialog("另存为", "保存", "错误", "文件保存失败！", false);
			}else{
				try{
					FileWriter out = new FileWriter(GlobalVarMain.getFilePath());
					SaveFile saveFile = new SaveFile();
					saveFile.setTextContent(TextPanel.getText());
					saveFile.setImageBase64(ImageHelper.convertImgToBase64(imagePane.getImage(), imagePane.width, imagePane.height));
					saveFile.setFontType(TextPanel.getFont().getFontName());
					saveFile.setIPAddress(ServerIP);
					saveFile.setPort(ServerPort);
					saveFile.setFileName(FileName);
					out.write(XMLHelper.encodeXMLDoc(saveFile));
					out.close();
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "保存失败！", "错误", JOptionPane.ERROR_MESSAGE);
				}finally {
					
				}
			}
		}
	};
	/**
	 * 打开文件
	 */
	ActionListener ItemFileOpenClickActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser jfc = new JFileChooser(GlobalVarMain.getFilePath());
			FileNameExtensionFilter filter = new FileNameExtensionFilter(GlobalVar.FileTypeName, GlobalVar.FileExtendsionName);  
		    jfc.setFileFilter(filter); 
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);  
			jfc.setDialogTitle("打开文件");
			jfc.setApproveButtonText("打开");
			
			if(jfc.showDialog(null,null) == JFileChooser.APPROVE_OPTION){
				if(!jfc.getSelectedFile().getAbsolutePath().isEmpty()){
					File file = new File(jfc.getSelectedFile().getAbsolutePath());
					if(file.exists()){
						try{
							GlobalVarMain.setFilePath(jfc.getSelectedFile().getAbsolutePath());
							/* 文件读入 */
							FileInputStream readStream = new FileInputStream(file);
							InputStreamReader contentReader = new InputStreamReader(readStream);
							BufferedReader bufferedReader = new BufferedReader(contentReader);
							String line;
							String content = "";
							while((line = bufferedReader.readLine()) != null){
								content += line;
							}
							bufferedReader.close();
							contentReader.close();
							readStream.close();
							SaveFile saveFile = XMLHelper.decodeXMLDoc(content);
							switch (saveFile.getFontType()) {
							case "宋体":
								TextPanel.setFont(new Font("宋体", Font.PLAIN, 18));
								break;
							case "黑体":
								TextPanel.setFont(new Font("黑体", Font.BOLD, 20));
							default:
								break;
							}
							TextPanel.setText(saveFile.getTextContent());
							imagePane.setImage(ImageHelper.convertBase64ToImg(saveFile.getImageBase64()));
							imagePane.repaint();
							ServerIP = saveFile.getIPAddress();
							ServerPort = saveFile.getPort();
						}catch (Exception e) {
							JOptionPane.showMessageDialog(null, "打开失败！", "错误", JOptionPane.ERROR_MESSAGE);
							System.err.println(e.getMessage());
						}finally {
							isFirstSaved = false;
						}
					}else{
						JOptionPane.showMessageDialog(null, "文件不存在！", "错误", JOptionPane.ERROR_MESSAGE);
						actionPerformed(arg0);// 再做一次
					}
				}else{
					JOptionPane.showMessageDialog(null, "请输入文件名！", "文件名不能为空", JOptionPane.WARNING_MESSAGE);
					actionPerformed(arg0);// 再做一次
				}
			}			
		}
	};
	/**
	 * 同步
	 */
	ActionListener ItemFileSyncClickActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new ConnectThread().start();
		}
	};
	/**
	 * 执行连接服务器的线程
	 * @author Ben Quick
	 *
	 */
	class ConnectThread extends Thread{
		public void run(){
			SaveFile saveFile = new SaveFile();
			saveFile.setTextContent(TextPanel.getText());
			saveFile.setImageBase64(ImageHelper.convertImgToBase64(imagePane.getImage(), imagePane.width, imagePane.height));
			saveFile.setFontType(TextPanel.getFont().getFontName());
			saveFile.setIPAddress(ServerIP);
			saveFile.setPort(ServerPort);
			saveFile.setFileName(FileName);
			String transferString = XMLHelper.encodeXMLDoc(saveFile);// 待传送字符串
			try {
				Socket socket = new Socket(ServerIP, ServerPort);
				OutputStream outputStream = socket.getOutputStream();
				PrintWriter fileWriter = new PrintWriter(outputStream, true);
				fileWriter.print(GlobalVar.CTRL_SEND + "\r\n" + saveFile.getFileName() + "\r\n" + transferString + "\r\n");// 向服务端发送数据
				fileWriter.close();
				outputStream.close();
				socket.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				
			}
		}
	}
	/**
	 * 从云中读取文件事件
	 */
	ActionListener ItemFileGetActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFrame FileNameFrame = new JFrame("请输入您要获取的文件名");
			int width = 300;
			int height = 70;
			Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
			FileNameFrame.setResizable(false);
			FileNameFrame.setLayout(null);
			FileNameFrame.setBounds((Screensize.width - width) / 2, (Screensize.height - height) / 2, width, height);
			
			TextField FileNameText = new TextField();
			FileNameText.setBounds(10, 10, 220, 20);
			FileNameText.setVisible(true);
			FileNameFrame.add(FileNameText);
			
			Button buttonConfirm = new Button("获取");
			buttonConfirm.setBounds(240, 10, 50, 20);
			buttonConfirm.setVisible(true);
			buttonConfirm.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					FileName = FileNameText.getText();
					new GETThread().start();
				}
			});
			FileNameFrame.add(buttonConfirm);
			
			FileNameFrame.setVisible(true);
		}
	};
	/**
	 * 执行获取文件的线程
	 * @author Ben Quick
	 *
	 */
	class GETThread extends Thread{
		public void run(){
			
			try {
				Socket socket = new Socket(ServerIP, ServerPort);
				OutputStream outputStream = socket.getOutputStream();
				PrintWriter fileWriter = new PrintWriter(outputStream, false);
				System.err.println(FileName);
				fileWriter.print(GlobalVar.CTRL_GET + "\r\n" + FileName + "\r\n");// 向服务端发送数据
				fileWriter.flush();
				
				/* 获取服务端发送的内容 */
				InputStream inputStream = socket.getInputStream();
				InputStreamReader contentReader = new InputStreamReader(inputStream);
				
				BufferedReader lineReader = new BufferedReader(contentReader);
				String line = "";
				String content = "";
				while((line = lineReader.readLine()) != null){
					content += line;
				}
				lineReader.close();
				contentReader.close();
				inputStream.close();
				
				fileWriter.close();
				outputStream.close();
				socket.close();
				
				SaveFile saveFile = XMLHelper.decodeXMLDoc(content);
				switch (saveFile.getFontType()) {
				case "宋体":
					TextPanel.setFont(new Font("宋体", Font.PLAIN, 18));
					break;
				case "黑体":
					TextPanel.setFont(new Font("黑体", Font.BOLD, 20));
				default:
					break;
				}
				TextPanel.setText(saveFile.getTextContent());
				imagePane.setImage(ImageHelper.convertBase64ToImg(saveFile.getImageBase64()));
				imagePane.repaint();
				ServerIP = saveFile.getIPAddress();
				ServerPort = saveFile.getPort();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				
			}
		}
	}
	/**
	 * 清空文字
	 */
	ActionListener ItemEditClearClickActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			TextPanel.setText("");
		}
	};
	/**
	 * 查找
	 */
	ActionListener ItemEditFindClickActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			/* 弹出查找子窗体 */
			JFrame FindFrame = new JFrame("查找");
			int width = 300;
			int height = 50;
			Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
			FindFrame.setResizable(false);
			FindFrame.setLayout(null);
			FindFrame.setBounds((Screensize.width - width) / 2, (Screensize.height - height) / 2, width, height);
			
			TextField keyWordText = new TextField();
			keyWordText.setBounds(0, 0, 200, 20);
			
			Button buttonFind = new Button("查找下一个");
			buttonFind.setBounds(200, 0, 100, 20);
			
			FindFrame.add(keyWordText);
			FindFrame.add(buttonFind);
			FindFrame.setVisible(true);
			
			buttonFind.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int selectIndex = TextPanel.getSelectionEnd();
					int textIndex1 = TextPanel.getText().indexOf(keyWordText.getText(), selectIndex);
					int textIndex2 = textIndex1 + keyWordText.getText().length();
					System.out.println("selectIndex = " + selectIndex + ", textIndex1 = " + textIndex1 + ", textIndex2 = " + textIndex2);
					if(textIndex1 == -1 || textIndex2 == -1){
						JOptionPane.showMessageDialog(null, "未能找到下一个内容", "", JOptionPane.ERROR_MESSAGE);
					}else{
						TextPanel.requestFocusInWindow();// 设置焦点
						TextPanel.select(textIndex1, textIndex2);
					}
				}
			});
		}
	};
	/**
	 * 替换
	 */
	ActionListener ItemEditReplaceActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			/* 弹出查找子窗体 */
			JFrame FindFrame = new JFrame("替换");
			int width = 300;
			int height = 80;
			Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
			FindFrame.setResizable(false);
			FindFrame.setLayout(null);
			FindFrame.setBounds((Screensize.width - width) / 2, (Screensize.height - height) / 2, width, height);
			
			Label keyLabel = new Label("查找内容：");
			keyLabel.setBounds(0, 0, 60, 20);
			TextField keyWordText = new TextField();
			keyWordText.setBounds(60, 0, 140, 20);
			Label replaceLabel = new Label("替换为：");
			
			replaceLabel.setBounds(0, 30, 60, 20);
			TextField replaceWordText = new TextField();
			replaceWordText.setBounds(60, 30, 140, 20);
			
			Button buttonReplace = new Button("替换下一个");
			buttonReplace.setBounds(200, 0, 100, 20);
			Button buttonReplaceAll = new Button("替换全部");
			buttonReplaceAll.setBounds(200, 30, 100, 20);
			
			FindFrame.add(keyLabel);
			FindFrame.add(replaceLabel);
			FindFrame.add(keyWordText);
			FindFrame.add(replaceWordText);
			FindFrame.add(buttonReplace);
			FindFrame.add(buttonReplaceAll);
			FindFrame.setVisible(true);
			
			buttonReplace.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int selectIndex = TextPanel.getSelectionEnd();
					int textIndex1 = TextPanel.getText().indexOf(keyWordText.getText(), selectIndex);
					int textIndex2 = textIndex1 + keyWordText.getText().length();
					System.out.println("selectIndex = " + selectIndex + ", textIndex1 = " + textIndex1 + ", textIndex2 = " + textIndex2);
					if(textIndex1 == -1 || textIndex2 == -1){
						JOptionPane.showMessageDialog(null, "未能找到下一个内容", "", JOptionPane.ERROR_MESSAGE);
					}else{
						TextPanel.requestFocusInWindow();// 设置焦点
						TextPanel.select(textIndex1, textIndex2);
						TextPanel.replaceSelection(replaceWordText.getText());
						TextPanel.select(textIndex1, textIndex1 + replaceWordText.getText().length());
					}
				}
			});
			buttonReplaceAll.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String allText = TextPanel.getText();
					allText = allText.replace(keyWordText.getText(), replaceWordText.getText());
					TextPanel.requestFocusInWindow();// 设置焦点
					TextPanel.setText(allText);
					JOptionPane.showMessageDialog(null, "替换完成", "", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
	};
	/**
	 * 显示保存文件对话框
	 * @param title 对话框标题
	 * @param approveButtonText 肯定回答
	 * @param errorMessageTitle 错误消息标题
	 * @param errorMessageContent 错误消息内容
	 * @param FileContent 文件内容
	 */
	private  void showSaveFileDialog(String title, String approveButtonText, String errorMessageTitle, String errorMmessageContent, boolean firstSaved){
		JFileChooser jfc = new JFileChooser(GlobalVarMain.getFilePath());
		FileNameExtensionFilter filter = new FileNameExtensionFilter(GlobalVar.FileTypeName, GlobalVar.FileExtendsionName);  
	    jfc.setFileFilter(filter); 
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);  
		jfc.setDialogTitle(title);
		jfc.setApproveButtonText(approveButtonText);
		
		if(jfc.showDialog(null,null) == JFileChooser.APPROVE_OPTION){
			if(!jfc.getSelectedFile().getAbsolutePath().isEmpty()){
				File fi = new File(jfc.getSelectedFile().getAbsolutePath());
				if(fi.exists()){
					if(JOptionPane.showConfirmDialog(null, "文件已存在，是否覆盖？", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						String f = fi.getAbsolutePath();  
						GlobalVarMain.setFilePath(f);
						try{
							if(f.indexOf("." + GlobalVar.FileExtendsionName)==-1){f += "." + GlobalVar.FileExtendsionName;} // 补上扩展名
							FileWriter out = new FileWriter(f); 
							FileName = fi.getName();
							SaveFile saveFile = new SaveFile();
							saveFile.setTextContent(TextPanel.getText());
							saveFile.setImageBase64(ImageHelper.convertImgToBase64(imagePane.getImage(), imagePane.width, imagePane.height));
							saveFile.setFontType(TextPanel.getFont().getFontName());
							saveFile.setIPAddress(ServerIP);
							saveFile.setPort(ServerPort);
							saveFile.setFileName(FileName);
							out.write(XMLHelper.encodeXMLDoc(saveFile));  
							out.close();  
						}  
						catch(Exception e){
							JOptionPane.showMessageDialog(null, errorMmessageContent, errorMessageTitle, JOptionPane.ERROR_MESSAGE);
							System.err.println(e.getMessage());
						}finally {
							isFirstSaved = firstSaved;
						}
					}else{
						showSaveFileDialog(title, approveButtonText, errorMessageTitle, errorMmessageContent, firstSaved);// 再做一次
					}
				}
			}else{
				JOptionPane.showMessageDialog(null, "请输入文件名！", "文件名不能为空", JOptionPane.WARNING_MESSAGE);
				showSaveFileDialog(title, approveButtonText, errorMessageTitle, errorMmessageContent, firstSaved);// 再做一次
			}
		}
	}

	/**
	 * 插入图片
	 */
	ActionListener ItemEditInsertImageActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser jfc = new JFileChooser(GlobalVarMain.getFilePath());
			FileNameExtensionFilter filter = new FileNameExtensionFilter(GlobalVar.ImageFileTypeName, GlobalVar.ImageExtendsionName);  
		    jfc.setFileFilter(filter); 
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);  
			jfc.setDialogTitle("插入图片");
			jfc.setApproveButtonText("打开");
			
			if(jfc.showDialog(null,null) == JFileChooser.APPROVE_OPTION){
				if(!jfc.getSelectedFile().getAbsolutePath().isEmpty()){
					File file = new File(jfc.getSelectedFile().getAbsolutePath());
					if(file.exists()){
						try{
							BufferedImage image = ImageIO.read(file);
							imagePane.setImage(image);
							imagePane.repaint();
						}catch (Exception e) {
							JOptionPane.showMessageDialog(null, "打开失败！", "错误", JOptionPane.ERROR_MESSAGE);
							System.err.println(e.getMessage());
						}finally {
						}
					}else{
						JOptionPane.showMessageDialog(null, "文件不存在！", "错误", JOptionPane.ERROR_MESSAGE);
						actionPerformed(arg0);// 再做一次
					}
				}else{
					JOptionPane.showMessageDialog(null, "请输入文件名！", "文件名不能为空", JOptionPane.WARNING_MESSAGE);
					actionPerformed(arg0);// 再做一次
				}
			}
		}
	};
	/**
	 * 清空图片
	 */
	ActionListener ItemEditClearImageActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			imagePane.setImage(null);
			imagePane.repaint();
		}
	};
	
	/**
	 * 设置字体
	 */
	ActionListener ItemSettingFontActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFrame FontFrame = new JFrame("选择字体");
			FontFrame.setResizable(false);
			int width = 320;
			int height = 100;
			Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
			FontFrame.setBounds((Screensize.width - width) / 2, (Screensize.height - height) / 2, width, height);
			FontFrame.setLayout(null);
			
			String[] FontType = {"宋体", "黑体"};
			JList<String> FontList = new JList<String>(FontType);
			FontList.setBounds(10,10,300,50);
			FontList.setVisible(true);
			FontList.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					switch (FontList.getSelectedValue()) {
					case "宋体":
						TextPanel.setFont(new Font("宋体", Font.PLAIN, 18));
						break;
					case "黑体":
						TextPanel.setFont(new Font("黑体", Font.BOLD, 20));
					default:
						break;
					}
				}
			});
			
			JScrollPane scrollPane = new JScrollPane(FontList);
			scrollPane.setBounds(FontList.getBounds());
			scrollPane.setVisible(true);
			
			
			FontFrame.add(scrollPane);
			FontFrame.setVisible(true);
		}
	};
	/**
	 * 设置网络
	 */
	ActionListener ItemSettingNetworkActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFrame NetFrame = new JFrame("网络设置");
			NetFrame.setResizable(false);
			int width = 320;
			int height = 100;
			Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
			NetFrame.setBounds((Screensize.width - width) / 2, (Screensize.height - height) / 2, width, height);
			NetFrame.setLayout(null);
			
			Label labelIP = new Label("服务器地址：");
			labelIP.setBounds(10, 10, 100, 20);
			TextField textIP = new TextField();
			textIP.setBounds(110, 10, 200, 20);
			textIP.setText(ServerIP);
			textIP.addTextListener(new TextListener() {
				
				@Override
				public void textValueChanged(TextEvent e) {
					ServerIP = textIP.getText();
				}
			});
			
			Label labelPort = new Label("通信端口：");
			labelPort.setBounds(10, 40, 100, 20);
			TextField textPort = new TextField();
			textPort.setText(ServerPort + "");
			textPort.setBounds(110, 40, 200, 20);
			textPort.addTextListener(new TextListener() {
				
				@Override
				public void textValueChanged(TextEvent e) {
					ServerPort = Integer.parseInt(textPort.getText());
				}
			});
			
			NetFrame.add(labelIP);
			NetFrame.add(textIP);
			NetFrame.add(labelPort);
			NetFrame.add(textPort);
			NetFrame.setVisible(true);
		}
	};
	
	/**
	 * 自定义图片框
	 * @author Ben Quick
	 *
	 */
	class ImagePanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		BufferedImage image;
		final int width = 100;
		final int height = 100;
		public ImagePanel(BufferedImage image){
			this.image = image;
			this.setBounds(680, 100, width, height);
			this.setBackground(Color.blue);
		}
		public void setImage(BufferedImage image){
			this.image = image;
		}
		public BufferedImage getImage(){
			return image;
		}
		public void paint(Graphics g){
			try{
				if(image != null) g.drawImage(image, 0, 0, width, height, null);
				else{
					g.setColor(Color.blue);
					g.fillRect(0, 0, width, height);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				
			}
		}
	}
	
}
