package ui_config;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import mainpack.GlobalVar;

/***
 * ��̬�࣬ ���ڴ���JFrame����
 * @author Ben Quick
 *
 */
public class FrameConfig {

	public final static String DefaultTitle = "TextEditor";
	public final static int DefaultWidth = 800;
	public final static int DefaultHeight = 600;
	/***
	 * ����JFrame
	 * @param title Frame�ı���
	 * @param width ���
	 * @param height �߶�
	 * @param globalVar ȫ�ֱ���
	 * @return �µ�JFrame����
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
