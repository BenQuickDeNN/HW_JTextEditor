package mainpack;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;

/**
 * XML助手
 * @author Ben Quick
 *
 */
public class XMLHelper {

	/**
	 * 生成XML文档
	 * @param content 
	 * @return
	 */
	public static String encodeXMLDoc(SaveFile content){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XMLEncoder xmlEncoder = new XMLEncoder(out);
		xmlEncoder.writeObject(content);
		xmlEncoder.close();
		String xmlString = out.toString();
		return xmlString;
	}
}
