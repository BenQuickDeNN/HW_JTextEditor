package mainpack;

/**
 * ���ڱ�������ļ�
 * @author Ben Quick
 *
 */
public class SaveFile {
	/**
	 * �ı�����
	 */
	private String TextContent;
	/**
	 * �����ı�����
	 * @param TextContent
	 */
	public void setTextContent(String TextContent){
		this.TextContent = TextContent;
	}
	/**
	 * ����ı�����
	 * @return
	 */
	public String getTextContent(){
		return TextContent;
	}
	/**
	 * ��������
	 */
	private String FontType;
	/**
	 * ������������
	 * @param FontType
	 */
	public void setFontType(String FontType){
		this.FontType = FontType;
	}
	/**
	 * ��ȡ��������
	 * @return
	 */
	public String getFontType(){
		return FontType;
	}
	/**
	 * ͼƬbase64��
	 */
	private String ImageBase64;
	/**
	 * ����ͼƬbase64��
	 * @param ImageBase64
	 */
	public void setImageBase64(String ImageBase64){
		this.ImageBase64 = ImageBase64;
	}
	/**
	 * ��ȡͼƬbase64��
	 * @return
	 */
	public String getImageBase64(){
		return ImageBase64;
	}
}
