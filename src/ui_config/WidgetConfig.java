package ui_config;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import mainpack.GlobalVar;

/***
 * 组件设置
 * @author Ben Quick
 *
 */
public class WidgetConfig {

	/**
	 * 给指定框架分配组件
	 * @param frame 主框架
	 * @param globalVar 全局变量
	 */
	public static void allocateWidget(JFrame frame, GlobalVar globalVar){
		/* TextPanel */
		frame.add(initTextPane(globalVar));
		/* 菜单栏 */
		frame.setMenuBar(initMenuBar(globalVar));
		
		frame.validate();
	}
	
	/**
	 * 初始化文本编辑框
	 * @return 文本编辑框
	 */
	private static JTextPane initTextPane(GlobalVar globalVar){
		JTextPane TextPanel = new JTextPane();
		TextPanel.setEditable(true);
		TextPanel.setVisible(true);
		return TextPanel;
	}
	
	/**
	 * 初始化菜单栏
	 * @return 菜单栏
	 */
	private static MenuBar initMenuBar(GlobalVar globalVar){
		MenuBar MenuBarMain = new MenuBar();
		
		Menu menuFile = new Menu("文件");
		Menu menuEdit = new Menu("编辑");
		Menu menuSetting = new Menu("设置");
		/* 文件菜单项目  */
		MenuItem itemFileCreate = new MenuItem("新建...");menuFile.add(itemFileCreate);itemFileCreate.addActionListener(MenuItemActionListener.getItemFileCreateClickActionListener(globalVar));
		MenuItem itemFileOpen = new MenuItem("打开...");menuFile.add(itemFileOpen);
		MenuItem itemFileSave = new MenuItem("保存");menuFile.add(itemFileSave);
		MenuItem itemFileSaveAs = new MenuItem("另存为...");menuFile.add(itemFileSaveAs);itemFileSaveAs.addActionListener(MenuItemActionListener.getItemFileSaveAsClickActionListener(globalVar, ""));
		MenuItem itemFileSync = new MenuItem("同步到云");menuFile.add(itemFileSync);
		/* 编辑菜单项目 */
		MenuItem itemEditClear = new MenuItem("清空文本");menuEdit.add(itemEditClear);
		
		MenuBarMain.add(menuFile);
		MenuBarMain.add(menuEdit);
		MenuBarMain.add(menuSetting);
		
		return MenuBarMain;
	}
}
