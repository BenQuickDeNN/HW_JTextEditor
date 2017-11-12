package ui_config;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import mainpack.GlobalVar;

/***
 * �������
 * @author Ben Quick
 *
 */
public class WidgetConfig {

	/**
	 * ��ָ����ܷ������
	 * @param frame �����
	 * @param globalVar ȫ�ֱ���
	 */
	public static void allocateWidget(JFrame frame, GlobalVar globalVar){
		/* TextPanel */
		frame.add(initTextPane(globalVar));
		/* �˵��� */
		frame.setMenuBar(initMenuBar(globalVar));
		
		frame.validate();
	}
	
	/**
	 * ��ʼ���ı��༭��
	 * @return �ı��༭��
	 */
	private static JTextPane initTextPane(GlobalVar globalVar){
		JTextPane TextPanel = new JTextPane();
		TextPanel.setEditable(true);
		TextPanel.setVisible(true);
		return TextPanel;
	}
	
	/**
	 * ��ʼ���˵���
	 * @return �˵���
	 */
	private static MenuBar initMenuBar(GlobalVar globalVar){
		MenuBar MenuBarMain = new MenuBar();
		
		Menu menuFile = new Menu("�ļ�");
		Menu menuEdit = new Menu("�༭");
		Menu menuSetting = new Menu("����");
		/* �ļ��˵���Ŀ  */
		MenuItem itemFileCreate = new MenuItem("�½�...");menuFile.add(itemFileCreate);itemFileCreate.addActionListener(MenuItemActionListener.getItemFileCreateClickActionListener(globalVar));
		MenuItem itemFileOpen = new MenuItem("��...");menuFile.add(itemFileOpen);
		MenuItem itemFileSave = new MenuItem("����");menuFile.add(itemFileSave);
		MenuItem itemFileSaveAs = new MenuItem("���Ϊ...");menuFile.add(itemFileSaveAs);itemFileSaveAs.addActionListener(MenuItemActionListener.getItemFileSaveAsClickActionListener(globalVar, ""));
		MenuItem itemFileSync = new MenuItem("ͬ������");menuFile.add(itemFileSync);
		/* �༭�˵���Ŀ */
		MenuItem itemEditClear = new MenuItem("����ı�");menuEdit.add(itemEditClear);
		
		MenuBarMain.add(menuFile);
		MenuBarMain.add(menuEdit);
		MenuBarMain.add(menuSetting);
		
		return MenuBarMain;
	}
}
