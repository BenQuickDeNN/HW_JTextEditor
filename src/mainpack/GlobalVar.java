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
	/* 
	 * Frame配置
	 *  */
	public final static String DefaultTitle = "TextEditor";
	public final static int DefaultWidth = 800;
	public final static int DefaultHeight = 600;
	/**
	 * 当前文件路径
	 */
	private String FilePath;
	/**
	 * 初始化路径，默认为我的文档
	 */
	private void initFilePath(){
		FileSystemView fsv = FileSystemView.getFileSystemView();
		FilePath = fsv.getDefaultDirectory().getPath() + "/新建自定义文档." + FileExtendsionName;
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
	/**
	 * 图片类型名
	 */
	public final static String ImageFileTypeName = "位图(*.bmp)";
	/**
	 * 图片文件扩展名
	 */
	public final static String ImageExtendsionName = "bmp";
	/**
	 * 发送控制字
	 */
	public final static String CTRL_SEND = "SEND";
	/**
	 * 读取控制字
	 */
	public final static String CTRL_GET = "GET";
}
