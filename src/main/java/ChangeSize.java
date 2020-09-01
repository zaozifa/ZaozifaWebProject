import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;


public class ChangeSize {


    public static void main2(String[] args) throws Exception {
        //读取图片
        {
            File f = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\jw");
            File[] files = f.listFiles();
            for (File t : files) {
                if (t.getName().indexOf("png") < 0) {
                    continue;
                }
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(t));
                //字节流转图片对象
                Image bi = ImageIO.read(in);
                //获取图像的高度，宽度
                int height = bi.getHeight(null);
                int width = bi.getWidth(null);
                //构建图片流
                BufferedImage tag = new BufferedImage(width / 2, height / 2, BufferedImage.TYPE_4BYTE_ABGR);
                //绘制改变尺寸后的图
                tag.getGraphics().drawImage(bi, 0, 0, width / 2, height / 2, null);
                //输出流
                File destFile = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\jw\\"+t.getName().replaceAll("[.][^.]+$", "")+"2x.png");
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destFile));
                //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                //encoder.encode(tag);
                ImageIO.write(tag, "png", out);
                in.close();
                out.close();
            }

        }

        {
            File f = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\sw");
            File[] files = f.listFiles();
            for (File t : files) {
                if (t.getName().indexOf("png") < 0) {
                    continue;
                }
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(t));
                //字节流转图片对象
                Image bi = ImageIO.read(in);
                //获取图像的高度，宽度
                int height = bi.getHeight(null);
                int width = bi.getWidth(null);
                //构建图片流
                BufferedImage tag = new BufferedImage(width / 2, height / 2, BufferedImage.TYPE_4BYTE_ABGR);
                //绘制改变尺寸后的图
                tag.getGraphics().drawImage(bi, 0, 0, width / 2, height / 2, null);
                //输出流
                File destFile = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\sw\\"+t.getName().replaceAll("[.][^.]+$", "")+"2x.png");
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destFile));
                //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                //encoder.encode(tag);
                ImageIO.write(tag, "png", out);
                in.close();
                out.close();
            }

        }

        {
            File f = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\zjw");
            File[] files = f.listFiles();
            for (File t : files) {
                if (t.getName().indexOf("png") < 0) {
                    continue;
                }
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(t));
                //字节流转图片对象
                Image bi = ImageIO.read(in);
                //获取图像的高度，宽度
                int height = bi.getHeight(null);
                int width = bi.getWidth(null);
                //构建图片流
                BufferedImage tag = new BufferedImage(width / 2, height / 2, BufferedImage.TYPE_4BYTE_ABGR);
                //绘制改变尺寸后的图
                tag.getGraphics().drawImage(bi, 0, 0, width / 2, height / 2, null);
                //输出流
                File destFile = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\zjw\\"+t.getName().replaceAll("[.][^.]+$", "")+"2x.png");
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destFile));
                //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                //encoder.encode(tag);
                ImageIO.write(tag, "png", out);
                in.close();
                out.close();
            }

        }

        {
            File f = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\jjw");
            File[] files = f.listFiles();
            for (File t : files) {
                if (t.getName().indexOf("png") < 0) {
                    continue;
                }
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(t));
                //字节流转图片对象
                Image bi = ImageIO.read(in);
                //获取图像的高度，宽度
                int height = bi.getHeight(null);
                int width = bi.getWidth(null);
                //构建图片流
                BufferedImage tag = new BufferedImage(width / 2, height / 2, BufferedImage.TYPE_4BYTE_ABGR);
                //绘制改变尺寸后的图
                tag.getGraphics().drawImage(bi, 0, 0, width / 2, height / 2, null);
                //输出流
                File destFile = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\jjw\\"+t.getName().replaceAll("[.][^.]+$", "")+"2x.png");
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destFile));
                //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                //encoder.encode(tag);
                ImageIO.write(tag, "png", out);
                in.close();
                out.close();
            }

        }
    }
}