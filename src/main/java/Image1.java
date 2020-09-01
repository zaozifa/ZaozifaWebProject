import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Image1 {

     private void setAlpha(String os) {
            /**
             * 增加测试项
             * 读取图片，绘制成半透明,修改像素
             */
            try {
              ImageIcon imageIcon = new ImageIcon(os);
              BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(),imageIcon.getIconHeight()
                  , BufferedImage.TYPE_4BYTE_ABGR);
              Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
              g2D.drawImage(imageIcon.getImage(), 0, 0,
                                   imageIcon.getImageObserver());
              //循环每一个像素点，改变像素点的Alpha值
              int alpha = 100;
              for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
                for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
//                    if (j1 <= 26 && j2 >= 180) {
//                    if (j1 <= 26 && j2 >= 159) {
//                    if (j1 <= 20 && j2 <= 20) {
//                    if (j1 >= 185 && j2 <= 26) {
                    if (j1 <= 15) {

                        int pixel = bufferedImage.getRGB(j2, j1);
                        int[]   rgb = new int[3];
//                        rgb[0] = (pixel & 0xff0000) >> 16;
//                        rgb[1] = (pixel & 0xff00) >> 8;
//                        rgb[2] = (pixel & 0xff);

                        rgb[0] = 0;
                        rgb[1] = 0;
                        rgb[2] = 0;
                        System.out.println("i=" + j1 + ",j=" + j2 + ":(" + rgb[0] + ","
                                + rgb[1] + "," + rgb[2] + ")");

//                        pixel = ( (alpha + 1) << 24) | (pixel & 0x00ffffff);
                        bufferedImage.setRGB(j2, j1, 0);
                    }

                }
              }
              g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());

              //生成图片为PNG
              ImageIO.write(bufferedImage, "png",  new File(os));
            }
            catch (Exception e) {
              e.printStackTrace();
            }
          }
     public static void main(String[] args) throws Exception {
        int x = 0;
        Image1 rc = new Image1();
//         {
//             File f = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\jw");
//             File[] files = f.listFiles();
//             for (File t : files) {
//                 if (t.getName().indexOf("png") < 0) {
//                     continue;
//                 }
////                 System.out.println(t.getAbsolutePath());
////                 break;
//                 rc.setAlpha(t.getAbsolutePath());
//             }
//
//         }

//         {
//             File f = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\jjw");
//             File[] files = f.listFiles();
//             for (File t : files) {
//                 if (t.getName().indexOf("png") < 0) {
//                     continue;
//                 }
////                 System.out.println(t.getAbsolutePath());
////                 break;
//                 rc.setAlpha(t.getAbsolutePath());
//             }
//
//         }
//
         {
             File f = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\zjw");
             File[] files = f.listFiles();
             for (File t : files) {
                 if (t.getName().indexOf("png") < 0) {
                     continue;
                 }
//                 System.out.println(t.getAbsolutePath());
//                 break;
                 rc.setAlpha(t.getAbsolutePath());
             }

         }
//
//         {
//             File f = new File("C:\\Users\\zhangxinwei\\IdeaProjects\\ZaozifaWebProject\\webPage\\img\\sw");
//             File[] files = f.listFiles();
//             for (File t : files) {
//                 if (t.getName().indexOf("png") < 0) {
//                     continue;
//                 }
////                 System.out.println(t.getAbsolutePath());
////                 break;
//                 rc.setAlpha(t.getAbsolutePath());
//             }
//
//         }

    }

}