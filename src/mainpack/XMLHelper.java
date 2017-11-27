package mainpack;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * XML助手
 * @author Ben Quick
 *
 */
public class XMLHelper {

	/**
	 * 生成XML文档
	 * @param content 内容
	 * @return XML字符串
	 */
	public static String encodeXMLDoc(SaveFile content){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XMLEncoder xmlEncoder = new XMLEncoder(out);
		xmlEncoder.writeObject(content);
		xmlEncoder.close();
		String xmlString = out.toString();
		return xmlString;
	}
	/**
	 * 读取XML文档
	 * @param xmlContent 内容
	 * @return 文件对象
	 */
	public static SaveFile decodeXMLDoc(String xmlContent){
		ByteArrayInputStream in = new ByteArrayInputStream(xmlContent.getBytes());
		XMLDecoder xmlDecoder = new XMLDecoder(in);
		SaveFile saveFile = (SaveFile)xmlDecoder.readObject();
		xmlDecoder.close();
		return saveFile;
	}
}
