package mainpack;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;

/**
 * XML����
 * @author Ben Quick
 *
 */
public class XMLHelper {

	/**
	 * ����XML�ĵ�
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
