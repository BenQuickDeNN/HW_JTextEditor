package mainpack;

import javax.swing.JFrame;

import ui_config.FrameConfig;
import ui_config.WidgetConfig;

/**
 * Java 文本编辑器 主类
 * @author Ben Quick in XJTU
 *
 */
public class MainClass {

	JFrame MainFrame;
	GlobalVar GlobalVarMain;
	public MainClass() {
		initialGlobalVar();
		initialFrame();
	}

	
	
	/**
	 * 初始化全局变量
	 */
	private void initialGlobalVar(){
		GlobalVarMain = new GlobalVar();
	}
	
	
	/***
	 * 初始化图形界面
	 */
	private void initialFrame(){
		MainFrame = FrameConfig.createJFrame(FrameConfig.DefaultTitle, FrameConfig.DefaultWidth, FrameConfig.DefaultHeight, GlobalVarMain);
		WidgetConfig.allocateWidget(MainFrame, GlobalVarMain);
	}
	public static void main(String[] args) {
		new MainClass();
	}

}
