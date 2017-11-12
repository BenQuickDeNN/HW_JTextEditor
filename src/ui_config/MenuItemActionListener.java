package ui_config;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import mainpack.GlobalVar;

/**
 * ��ť�¼�������
 * @author Ben Quick
 *
 */
public class MenuItemActionListener{

	/**
	 * �����ļ��¼�
	 * @param globalVar
	 * @return
	 */
	public final static ActionListener getItemFileCreateClickActionListener(GlobalVar globalVar){
		ActionListener ItemFileCreateClick = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showSaveFileDialog("�½��ļ�", "����", "����", "�ļ�����ʧ�ܣ�", globalVar, "");
			}
		};
		return ItemFileCreateClick;
	}
	
	/**
	 * ���Ϊ�¼�
	 * @param globalVar
	 * @param FileContent
	 * @return
	 */
	public final static ActionListener getItemFileSaveAsClickActionListener(GlobalVar globalVar, String FileContent){
		ActionListener ItemFileSaveAsClickActionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showSaveFileDialog("���Ϊ", "����", "����", "�ļ�����ʧ�ܣ�", globalVar, FileContent);
			}
		};
		return ItemFileSaveAsClickActionListener;
	}
	
	/**
	 * ��ʾ�����ļ��Ի���
	 * @param title �Ի������
	 * @param approveButtonText �϶��ش�
	 * @param errorMessageTitle ������Ϣ����
	 * @param errorMessageContent ������Ϣ����
	 * @param globalVar ȫ�ֱ���
	 * @param FileContent �ļ�����
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
				if(JOptionPane.showConfirmDialog(null, "�ļ��Ѵ��ڣ��Ƿ񸲸ǣ�", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
					String f = fi.getAbsolutePath();  
					globalVar.setFilePath(f);
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
						
					}
				}else{
					showSaveFileDialog(title, approveButtonText, errorMessageTitle, errorMmessageContent, globalVar, FileContent);// ����һ��
				}
			}
		}
	}
}
