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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
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
 * Java �ı��༭�� ����
 * @author Ben Quick in XJTU
 *
 */
public class MainClass {

	/**
	 * ��UI���
	 */
	JFrame MainFrame;
	/**
	 * �ı��༭��
	 */
	JTextPane TextPanel;
	/**
	 * ͼƬ��ʾ��
	 */
	ImagePanel imagePane;
	/**
	 * ȫ�ֱ���
	 */
	GlobalVar GlobalVarMain;
	/**
	 * �Ƿ��һ�α���
	 */
	boolean isFirstSaved = true;
	public MainClass() {
		initialGlobalVar();
		initialFrame();
		initialWidget();
	}

	public static void main(String[] args) {
		new MainClass();
	}
	
	
	/**
	 * ��ʼ��ȫ�ֱ���
	 */
	private void initialGlobalVar(){
		GlobalVarMain = new GlobalVar();
	}
	
	
	/***
	 * ��ʼ��ͼ�ν���
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
	 * ��ʼ�����
	 */
	private void initialWidget(){
		initTextPane();
		initImagePanel();
		initMenuBar();
	}
	/**
	 * ��ʼ���ı��༭��
	 * @return �ı��༭��
	 */
	private void initTextPane(){
		TextPanel = new JTextPane();
		TextPanel.setEditable(true);
		TextPanel.setVisible(true);
		TextPanel.setBounds(10, 10, 660, MainFrame.getHeight() - 20);
		TextPanel.setFont(new Font("����", Font.PLAIN, 18));
		MainFrame.add(TextPanel);
		isFirstSaved = true;
	}
	/**
	 * ��ʼ��ͼƬ��ʾ��
	 */
	private void initImagePanel(){
		imagePane = new ImagePanel(null);
		MainFrame.add(imagePane);
	}
	/**
	 * ��ʼ���˵���
	 * @return �˵���
	 */
	private void initMenuBar(){
		MenuBar MenuBarMain = new MenuBar();
		
		Menu menuFile = new Menu("�ļ�");
		Menu menuEdit = new Menu("�༭");
		Menu menuSetting = new Menu("����");
		/* �ļ��˵���Ŀ  */
		MenuItem itemFileCreate = new MenuItem("�½�...");			menuFile.add(itemFileCreate);			itemFileCreate.addActionListener(ItemFileCreateClick);
		MenuItem itemFileOpen = new MenuItem("��...");				menuFile.add(itemFileOpen);				itemFileOpen.addActionListener(ItemFileOpenClickActionListener);
		MenuItem itemFileSave = new MenuItem("����");					menuFile.add(itemFileSave);				itemFileSave.addActionListener(ItemFileSaveClickActionListener);
		MenuItem itemFileSaveAs = new MenuItem("���Ϊ...");			menuFile.add(itemFileSaveAs);			itemFileSaveAs.addActionListener(ItemFileSaveAsClickActionListener);
		MenuItem itemFileSync = new MenuItem("ͬ������");				menuFile.add(itemFileSync);				itemFileSync.addActionListener(ItemFileSyncClickActionListener);
		/* �༭�˵���Ŀ */
		MenuItem itemEditClear = new MenuItem("���");				menuEdit.add(itemEditClear);			itemEditClear.addActionListener(ItemEditClearClickActionListener);
		MenuItem itemEditInsertImage = new MenuItem("����ͼƬ...");	menuEdit.add(itemEditInsertImage);		itemEditInsertImage.addActionListener(ItemEditInsertImageActionListener);
		MenuItem itemEditFind = new MenuItem("����...");				menuEdit.add(itemEditFind);				itemEditFind.addActionListener(ItemEditFindClickActionListener);
		MenuItem itemEditReplace = new MenuItem("�滻...");			menuEdit.add(itemEditReplace);			itemEditReplace.addActionListener(ItemEditReplaceActionListener);
		/* ���ò˵���Ŀ */
		MenuItem itemSettingFont = new MenuItem("����...");			menuSetting.add(itemSettingFont);		itemSettingFont.addActionListener(ItemSettingFontActionListener);
		MenuItem itemSettingNetwork = new MenuItem("����...");		menuSetting.add(itemSettingNetwork);	itemSettingNetwork.addActionListener(ItemSettingNetworkActionListener);
		
		MenuBarMain.add(menuFile);
		MenuBarMain.add(menuEdit);
		MenuBarMain.add(menuSetting);
		
		MainFrame.setMenuBar(MenuBarMain);
	}
	
	/*
	 * �˵��¼���Ӧ
	 */
	/**
	 * �½��ļ��¼�
	 */
	ActionListener ItemFileCreateClick = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			TextPanel.setText("");
			TextPanel.setFont(new Font("����", Font.PLAIN, 18));
			isFirstSaved = true;
		}
	};
	/**
	 * ���Ϊ�¼�
	 */
	ActionListener ItemFileSaveAsClickActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			SaveFile saveFile = new SaveFile();
			saveFile.setTextContent(TextPanel.getText());
			saveFile.setImageBase64(ImageHelper.convertImgToBase64(imagePane.getImage(), imagePane.width, imagePane.height));
			saveFile.setFontType(TextPanel.getFont().getFontName());
			showSaveFileDialog("���Ϊ", "����", "����", "�ļ�����ʧ�ܣ�", XMLHelper.encodeXMLDoc(saveFile), false);
		}
	};
	/**
	 * �����ļ��¼�
	 */
	ActionListener ItemFileSaveClickActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			SaveFile saveFile = new SaveFile();
			saveFile.setTextContent(TextPanel.getText());
			saveFile.setImageBase64(ImageHelper.convertImgToBase64(imagePane.getImage(), imagePane.width, imagePane.height));
			saveFile.setFontType(TextPanel.getFont().getFontName());
			if(isFirstSaved){
				showSaveFileDialog("���Ϊ", "����", "����", "�ļ�����ʧ�ܣ�", XMLHelper.encodeXMLDoc(saveFile), false);
			}else{
				try{
					FileWriter out = new FileWriter(GlobalVarMain.getFilePath());
					out.write(XMLHelper.encodeXMLDoc(saveFile));
					out.close();
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
				}finally {
					
				}
			}
		}
	};
	/**
	 * ���ļ�
	 */
	ActionListener ItemFileOpenClickActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser jfc = new JFileChooser(GlobalVarMain.getFilePath());
			FileNameExtensionFilter filter = new FileNameExtensionFilter(GlobalVar.FileTypeName, GlobalVar.FileExtendsionName);  
		    jfc.setFileFilter(filter); 
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);  
			jfc.setDialogTitle("���ļ�");
			jfc.setApproveButtonText("��");
			
			if(jfc.showDialog(null,null) == JFileChooser.APPROVE_OPTION){
				if(!jfc.getSelectedFile().getAbsolutePath().isEmpty()){
					File file = new File(jfc.getSelectedFile().getAbsolutePath());
					if(file.exists()){
						try{
							GlobalVarMain.setFilePath(jfc.getSelectedFile().getAbsolutePath());
							/* �ļ����� */
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
							TextPanel.setText(content);
						}catch (Exception e) {
							JOptionPane.showMessageDialog(null, "��ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
							System.err.println(e.getMessage());
						}finally {
							isFirstSaved = false;
						}
					}else{
						JOptionPane.showMessageDialog(null, "�ļ������ڣ�", "����", JOptionPane.ERROR_MESSAGE);
						actionPerformed(arg0);// ����һ��
					}
				}else{
					JOptionPane.showMessageDialog(null, "�������ļ�����", "�ļ�������Ϊ��", JOptionPane.WARNING_MESSAGE);
					actionPerformed(arg0);// ����һ��
				}
			}			
		}
	};
	/**
	 * ͬ��
	 */
	ActionListener ItemFileSyncClickActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	/**
	 * �������
	 */
	ActionListener ItemEditClearClickActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			TextPanel.setText("");
		}
	};
	/**
	 * ����
	 */
	ActionListener ItemEditFindClickActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			/* ���������Ӵ��� */
			JFrame FindFrame = new JFrame("����");
			int width = 300;
			int height = 50;
			Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
			FindFrame.setResizable(false);
			FindFrame.setLayout(null);
			FindFrame.setBounds((Screensize.width - width) / 2, (Screensize.height - height) / 2, width, height);
			
			TextField keyWordText = new TextField();
			keyWordText.setBounds(0, 0, 200, 20);
			
			Button buttonFind = new Button("������һ��");
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
						JOptionPane.showMessageDialog(null, "δ���ҵ���һ������", "", JOptionPane.ERROR_MESSAGE);
					}else{
						TextPanel.requestFocusInWindow();// ���ý���
						TextPanel.select(textIndex1, textIndex2);
					}
				}
			});
		}
	};
	/**
	 * �滻
	 */
	ActionListener ItemEditReplaceActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			/* ���������Ӵ��� */
			JFrame FindFrame = new JFrame("�滻");
			int width = 300;
			int height = 80;
			Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
			FindFrame.setResizable(false);
			FindFrame.setLayout(null);
			FindFrame.setBounds((Screensize.width - width) / 2, (Screensize.height - height) / 2, width, height);
			
			Label keyLabel = new Label("�������ݣ�");
			keyLabel.setBounds(0, 0, 60, 20);
			TextField keyWordText = new TextField();
			keyWordText.setBounds(60, 0, 140, 20);
			Label replaceLabel = new Label("�滻Ϊ��");
			
			replaceLabel.setBounds(0, 30, 60, 20);
			TextField replaceWordText = new TextField();
			replaceWordText.setBounds(60, 30, 140, 20);
			
			Button buttonReplace = new Button("�滻��һ��");
			buttonReplace.setBounds(200, 0, 100, 20);
			Button buttonReplaceAll = new Button("�滻ȫ��");
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
						JOptionPane.showMessageDialog(null, "δ���ҵ���һ������", "", JOptionPane.ERROR_MESSAGE);
					}else{
						TextPanel.requestFocusInWindow();// ���ý���
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
					TextPanel.requestFocusInWindow();// ���ý���
					TextPanel.setText(allText);
					JOptionPane.showMessageDialog(null, "�滻���", "", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
	};
	/**
	 * ��ʾ�����ļ��Ի���
	 * @param title �Ի������
	 * @param approveButtonText �϶��ش�
	 * @param errorMessageTitle ������Ϣ����
	 * @param errorMessageContent ������Ϣ����
	 * @param FileContent �ļ�����
	 */
	private  void showSaveFileDialog(String title, String approveButtonText, String errorMessageTitle, String errorMmessageContent, String FileContent, boolean firstSaved){
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
					if(JOptionPane.showConfirmDialog(null, "�ļ��Ѵ��ڣ��Ƿ񸲸ǣ�", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						String f = fi.getAbsolutePath();  
						GlobalVarMain.setFilePath(f);
						try{
							if(f.indexOf("." + GlobalVar.FileExtendsionName)==-1){f += "." + GlobalVar.FileExtendsionName;} // ������չ��
							FileWriter out = new FileWriter(f);  
							out.write(FileContent);  
							out.close();  
						}  
						catch(Exception e){
							JOptionPane.showMessageDialog(null, errorMmessageContent, errorMessageTitle, JOptionPane.ERROR_MESSAGE);
							System.err.println(e.getMessage());
						}finally {
							isFirstSaved = firstSaved;
						}
					}else{
						showSaveFileDialog(title, approveButtonText, errorMessageTitle, errorMmessageContent, FileContent, firstSaved);// ����һ��
					}
				}
			}else{
				JOptionPane.showMessageDialog(null, "�������ļ�����", "�ļ�������Ϊ��", JOptionPane.WARNING_MESSAGE);
				showSaveFileDialog(title, approveButtonText, errorMessageTitle, errorMmessageContent, FileContent, firstSaved);// ����һ��
			}
		}
	}

	/**
	 * ����ͼƬ
	 */
	ActionListener ItemEditInsertImageActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser jfc = new JFileChooser(GlobalVarMain.getFilePath());
			FileNameExtensionFilter filter = new FileNameExtensionFilter(GlobalVar.ImageFileTypeName, GlobalVar.ImageExtendsionName);  
		    jfc.setFileFilter(filter); 
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);  
			jfc.setDialogTitle("����ͼƬ");
			jfc.setApproveButtonText("��");
			
			if(jfc.showDialog(null,null) == JFileChooser.APPROVE_OPTION){
				if(!jfc.getSelectedFile().getAbsolutePath().isEmpty()){
					File file = new File(jfc.getSelectedFile().getAbsolutePath());
					if(file.exists()){
						try{
							BufferedImage image = ImageIO.read(file);
							imagePane.setImage(image);
							imagePane.repaint();
						}catch (Exception e) {
							JOptionPane.showMessageDialog(null, "��ʧ�ܣ�", "����", JOptionPane.ERROR_MESSAGE);
							System.err.println(e.getMessage());
						}finally {
						}
					}else{
						JOptionPane.showMessageDialog(null, "�ļ������ڣ�", "����", JOptionPane.ERROR_MESSAGE);
						actionPerformed(arg0);// ����һ��
					}
				}else{
					JOptionPane.showMessageDialog(null, "�������ļ�����", "�ļ�������Ϊ��", JOptionPane.WARNING_MESSAGE);
					actionPerformed(arg0);// ����һ��
				}
			}
		}
	};
	
	/**
	 * ��������
	 */
	ActionListener ItemSettingFontActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFrame FontFrame = new JFrame("ѡ������");
			FontFrame.setResizable(false);
			int width = 320;
			int height = 100;
			Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
			FontFrame.setBounds((Screensize.width - width) / 2, (Screensize.height - height) / 2, width, height);
			FontFrame.setLayout(null);
			
			String[] FontType = {"����", "����"};
			JList<String> FontList = new JList<String>(FontType);
			FontList.setBounds(10,10,300,50);
			FontList.setVisible(true);
			FontList.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					switch (FontList.getSelectedValue()) {
					case "����":
						TextPanel.setFont(new Font("����", Font.PLAIN, 18));
						break;
					case "����":
						TextPanel.setFont(new Font("����", Font.BOLD, 20));
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
	 * ��������
	 */
	ActionListener ItemSettingNetworkActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	/**
	 * �Զ���ͼƬ��
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
