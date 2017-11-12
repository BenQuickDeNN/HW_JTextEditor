package mainpack;

import javax.swing.filechooser.FileSystemView;

/**
 * 全局变量
 * @author Ben Quick
 *
 */
public class GlobalVar {

	public GlobalVar(){
		initFilePath();
	}
	/**
	 * 当前文件路径
	 */
	private String FilePath;
	/**
	 * 初始化路径，默认为我的文档
	 */
	private void initFilePath(){
		FileSystemView fsv = FileSystemView.getFileSystemView();
		FilePath = fsv.getDefaultDirectory().getPath();
	}
	public String getFilePath(){return FilePath;}
	public void setFilePath(String FilePath){this.FilePath = FilePath;}
	
	/**
	 * 文件扩展名
	 */
	public final static String FileExtendsionName = "mydoc";
	/**
	 * 文件类型名
	 */
	public final static String FileTypeName = "自定义文档(*.mydoc)";
}
