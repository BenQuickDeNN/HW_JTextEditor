package mainpack;

import javax.swing.filechooser.FileSystemView;

/**
 * ȫ�ֱ���
 * @author Ben Quick
 *
 */
public class GlobalVar {

	public GlobalVar(){
		initFilePath();
	}
	/**
	 * ��ǰ�ļ�·��
	 */
	private String FilePath;
	/**
	 * ��ʼ��·����Ĭ��Ϊ�ҵ��ĵ�
	 */
	private void initFilePath(){
		FileSystemView fsv = FileSystemView.getFileSystemView();
		FilePath = fsv.getDefaultDirectory().getPath();
	}
	public String getFilePath(){return FilePath;}
	public void setFilePath(String FilePath){this.FilePath = FilePath;}
	
	/**
	 * �ļ���չ��
	 */
	public final static String FileExtendsionName = "mydoc";
	/**
	 * �ļ�������
	 */
	public final static String FileTypeName = "�Զ����ĵ�(*.mydoc)";
}
