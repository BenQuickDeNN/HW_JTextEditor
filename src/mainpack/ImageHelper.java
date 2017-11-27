package mainpack;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

public class ImageHelper {

	/**
     * ��ͼƬת����Base64����
     * @param image ������ͼƬ
     * @return
     */
    public static String convertImgToBase64(BufferedImage image, int width, int height){
        //��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
        byte[] data = null;
        //��ȡͼƬ�ֽ�����
        try 
        {
        	ByteArrayOutputStream output = new java.io.ByteArrayOutputStream();
        	ImageIO.write(image, "bmp", output);
        	output.flush();
        	data = output.toByteArray();
        	output.close();
        }catch (IOException e) 
        {
            e.printStackTrace();
        }finally {
			
		}
        return Base64.getEncoder().encodeToString(data);
    }
    
    /**
     * ���ֽ������ַ�������Base64���벢����ͼƬ
     * @param imgStr ͼƬ����
     * @param imgFilePath ����ͼƬȫ·����ַ
     * @return
     */
    public static BufferedImage convertBase64ToImg(String imgStr){
        //
        if (imgStr == null) return null;
        byte[] buff = null;
        try 
        {
            //Base64����
            buff = Base64.getDecoder().decode(imgStr);
            for(int i = 0; i < buff.length; ++i)
            {
                if(buff[i]<0)
                {
                	//�����쳣����
                    buff[i]+=256;
                }
            }
            
        }catch (Exception e) 
        {
        	e.printStackTrace();
        }finally {
			
		}
        BufferedImage image = null;
        try {
			image = ImageIO.read(new ByteArrayInputStream(buff));
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			
		}
        return image;
    }
    
    
}
