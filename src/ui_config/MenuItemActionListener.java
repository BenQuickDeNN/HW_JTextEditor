package ui_config;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import mainpack.GlobalVar;

/**
 * 按钮事件监听器
 * @author Ben Quick
 *
 */
public class MenuItemActionListener{

	/**
	 * 创建文件事件
	 * @param globalVar
	 * @return
	 */
	public final static ActionListener getItemFileCreateClickActionListener(GlobalVar globalVar){
		ActionListener ItemFileCreateClick = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showSaveFileDialog("新建文件", "创建", "错误", "文件创建失败！", globalVar, "");
			}
		};
		return ItemFileCreateClick;
	}
	/**
	 * 保存事件
	 * @param globalVar
	 * @param FileContent
	 * @return
	 */
	public final static ActionListener getItemFileSaveActionListener(GlobalVar globalVar, String FileContent){
		ActionListener ItemFileSaveClickActiongListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					FileWriter out = new FileWriter(globalVar.getFilePath());
					out.write(FileContent);
					out.close();
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "保存失败！", "错误", JOptionPane.ERROR_MESSAGE);
				}finally {
					
				}
			}
		};
		return ItemFileSaveClickActiongListener;
	}
	/**
	 * 另存为事件
	 * @param globalVar
	 * @param FileContent
	 * @return
	 */
	public final static ActionListener getItemFileSaveAsClickActionListener(GlobalVar globalVar, String FileContent){
		ActionListener ItemFileSaveAsClickActionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showSaveFileDialog("另存为", "保存", "错误", "文件保存失败！", globalVar, FileContent);
			}
		};
		return ItemFileSaveAsClickActionListener;
	}
	/**
	 * 打开文件事件
	 * @param globalVar
	 * @param text_to_show 将要显示的文本
	 * @return
	 */
	public final static ActionListener getItemFileOpenClickActionListener(GlobalVar globalVar, char[] text_to_show){
		ActionListener ItemFileOpenClickActionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser(globalVar.getFilePath());
				FileNameExtensionFilter filter = new FileNameExtensionFilter(GlobalVar.FileTypeName, GlobalVar.FileExtendsionName);  
			    jfc.setFileFilter(filter); 
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);  
				jfc.setDialogTitle("打开文件");
				jfc.setApproveButtonText("打开");
				
				if(jfc.showDialog(null,null) == JFileChooser.APPROVE_OPTION){
					
					try{
						FileReader in = new FileReader(jfc.getSelectedFile().getAbsolutePath());
						in.read(text_to_show);
						in.close();
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, "打开失败！", "错误", JOptionPane.ERROR_MESSAGE);
						System.err.println(e.getMessage());
					}finally {
						
					}
				}
				
			}
		};
		return ItemFileOpenClickActionListener;
	}
	
	/**
	 * 显示保存文件对话框
	 * @param title 对话框标题
	 * @param approveButtonText 肯定回答
	 * @param errorMessageTitle 错误消息标题
	 * @param errorMessageContent 错误消息内容
	 * @param globalVar 全局变量
	 * @param FileContent 文件内容
	 */
	private final static void showSaveFileDialog(String title, String approveButtonText, String errorMessageTitle, String errorMmessageContent, GlobalVar globalVar, String FileContent){
		JFileChooser jfc = new JFileChooser(globalVar.getFilePath());
		FileNameExtensionFilter filter = new FileNameExtensionFilter(GlobalVar.FileTypeName, GlobalVar.FileExtendsionName);  
	    jfc.setFileFilter(filter); 
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);  
		jfc.setDialogTitle(title);
		jfc.setApproveButtonText(approveButtonText);
		
		if(jfc.showDialog(null,null) == JFileChooser.APPROVE_OPTION){
			File fi = new File(jfc.getSelectedFile().getAbsolutePath());
			if(fi.exists()){
				if(JOptionPane.showConfirmDialog(null, "文件已存在，是否覆盖？", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
					String f = fi.getAbsolutePath();  
					globalVar.setFilePath(f);
					try{
						if(f.indexOf("." + GlobalVar.FileExtendsionName)==-1){f += "." + GlobalVar.FileExtendsionName;} // 补上扩展名
						FileWriter out = new FileWriter(f);  
						out.write(FileContent);  
						out.close();  
					}  
					catch(Exception e){
						JOptionPane.showMessageDialog(null, errorMmessageContent, errorMessageTitle, JOptionPane.ERROR_MESSAGE);
						System.err.println(e.getMessage());
					}finally {
						
					}
				}else{
					showSaveFileDialog(title, approveButtonText, errorMessageTitle, errorMmessageContent, globalVar, FileContent);// 再做一次
				}
			}
		}
	}
}
