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
     * 将图片转换成Base64编码
     * @param image 待处理图片
     * @return
     */
    public static String convertImgToBase64(BufferedImage image, int width, int height){
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        //读取图片字节数组
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
     * 对字节数组字符串进行Base64解码并生成图片
     * @param imgStr 图片数据
     * @param imgFilePath 保存图片全路径地址
     * @return
     */
    public static BufferedImage convertBase64ToImg(String imgStr){
        //
        if (imgStr == null) return null;
        byte[] buff = null;
        try 
        {
            //Base64解码
            buff = Base64.getDecoder().decode(imgStr);
            for(int i = 0; i < buff.length; ++i)
            {
                if(buff[i]<0)
                {
                	//调整异常数据
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
