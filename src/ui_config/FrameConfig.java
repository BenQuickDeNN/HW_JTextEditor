package ui_config;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import mainpack.GlobalVar;

/***
 * 静态类， 用于创建JFrame对象
 * @author Ben Quick
 *
 */
public class FrameConfig {

	public final static String DefaultTitle = "TextEditor";
	public final static int DefaultWidth = 800;
	public final static int DefaultHeight = 600;
	/***
	 * 创建JFrame
	 * @param title Frame的标题
	 * @param width 宽度
	 * @param height 高度
	 * @param globalVar 全局变量
	 * @return 新的JFrame对象
	 */
	public static JFrame createJFrame(String title, int width, int height, GlobalVar globalVar){
		JFrame FrameObject = new JFrame(title);
		FrameObject.setLayout(new BorderLayout());
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (ScreenSize.width - width) / 2;
		int y = (ScreenSize.height - height) / 2;
		FrameObject.setBounds(x, y, width, height);
		FrameObject.setVisible(true);
		FrameObject.addWindowListener(new WindowListener(){

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
		return FrameObject;
	}
}
