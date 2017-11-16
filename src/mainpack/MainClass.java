package mainpack;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import ui_config.FrameConfig;
import ui_config.MenuItemActionListener;
import ui_config.WidgetConfig;

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
		MainFrame.setLayout(new BorderLayout());
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
		MainFrame.add(TextPanel);
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
		MenuItem itemFileCreate = new MenuItem("�½�...");			menuFile.add(itemFileCreate);		itemFileCreate.addActionListener(ItemFileCreateClick);
		MenuItem itemFileOpen = new MenuItem("��...");				menuFile.add(itemFileOpen);			itemFileOpen.addActionListener(ItemFileOpenClickActionListener);
		MenuItem itemFileSave = new MenuItem("����");					menuFile.add(itemFileSave);			itemFileSave.addActionListener(ItemFileSaveClickActionListener);
		MenuItem itemFileSaveAs = new MenuItem("���Ϊ...");			menuFile.add(itemFileSaveAs);		itemFileSaveAs.addActionListener(ItemFileSaveAsClickActionListener);
		MenuItem itemFileSync = new MenuItem("ͬ������");				menuFile.add(itemFileSync);
		/* �༭�˵���Ŀ */
		MenuItem itemEditClear = new MenuItem("���");				menuEdit.add(itemEditClear);		itemEditClear.addActionListener(ItemEditClearClickActionListener);
		MenuItem itemEditInsertImage = new MenuItem("����ͼƬ...");	menuEdit.add(itemEditInsertImage);
		MenuItem itemEditFind = new MenuItem("����...");				menuEdit.add(itemEditFind);
		MenuItem itemEditReplace = new MenuItem("�滻...");			menuEdit.add(itemEditReplace);
		/* ���ò˵���Ŀ */
		MenuItem itemSettingFont = new MenuItem("����...");menuSetting.add(itemSettingFont);
		MenuItem itemSettingNetwork = new MenuItem("����...");menuSetting.add(itemSettingNetwork);
		
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
			isFirstSaved = true;
		}
	};
	/**
	 * ���Ϊ�¼�
	 */
	ActionListener ItemFileSaveAsClickActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			showSaveFileDialog("���Ϊ", "����", "����", "�ļ�����ʧ�ܣ�", "", false);
		}
	};
	/**
	 * �����ļ��¼�
	 */
	ActionListener ItemFileSaveClickActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(isFirstSaved){
				showSaveFileDialog("���Ϊ", "����", "����", "�ļ�����ʧ�ܣ�", "", false);
			}else{
				try{
					FileWriter out = new FileWriter(GlobalVarMain.getFilePath());
					out.write("");
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
	ActionListener ItemEditClearClickActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			TextPanel.setText("");
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
}
