package mainpack;

/**
 * 用于被保存的文件
 * @author Ben Quick
 *
 */
public class SaveFile {
	/**
	 * 文本内容
	 */
	private String TextContent;
	/**
	 * 设置文本内容
	 * @param TextContent
	 */
	public void setTextContent(String TextContent){
		this.TextContent = TextContent;
	}
	/**
	 * 获得文本内容
	 * @return
	 */
	public String getTextContent(){
		return TextContent;
	}
	/**
	 * 字体类型
	 */
	private String FontType;
	/**
	 * 设置字体类型
	 * @param FontType
	 */
	public void setFontType(String FontType){
		this.FontType = FontType;
	}
	/**
	 * 获取字体类型
	 * @return
	 */
	public String getFontType(){
		return FontType;
	}
	/**
	 * 图片base64码
	 */
	private String ImageBase64;
	/**
	 * 设置图片base64码
	 * @param ImageBase64
	 */
	public void setImageBase64(String ImageBase64){
		this.ImageBase64 = ImageBase64;
	}
	/**
	 * 获取图片base64码
	 * @return
	 */
	public String getImageBase64(){
		return ImageBase64;
	}
}
