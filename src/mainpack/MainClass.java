package mainpack;

import javax.swing.JFrame;

import ui_config.FrameConfig;
import ui_config.WidgetConfig;

/**
 * Java �ı��༭�� ����
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
	 * ��ʼ��ȫ�ֱ���
	 */
	private void initialGlobalVar(){
		GlobalVarMain = new GlobalVar();
	}
	
	
	/***
	 * ��ʼ��ͼ�ν���
	 */
	private void initialFrame(){
		MainFrame = FrameConfig.createJFrame(FrameConfig.DefaultTitle, FrameConfig.DefaultWidth, FrameConfig.DefaultHeight, GlobalVarMain);
		WidgetConfig.allocateWidget(MainFrame, GlobalVarMain);
	}
	public static void main(String[] args) {
		new MainClass();
	}

}
