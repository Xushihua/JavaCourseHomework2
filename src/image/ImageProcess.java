package image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
A: Cut an image.
B: zoom out (shrink) an image.
C: zoom in (enlarge) an image.
*/
public class ImageProcess {
	public static void main(String[] args) {
		File input;
		File cutOutput;
		File shrinkOutput;
		try {
			input = new File("data/flower.jpg");
			cutOutput = new File("data/cutOutput.jpg");
			shrinkOutput = new File("data/shrinkOutput.jpg");
			String inputImage = "data/flower.jpg";
			String ouputImage = "data/shrinkOutput.jpg";
			ImageProcess.scaleImage(inputImage, ouputImage, 1.5, "jpg");
			// ImageProcess.cutImage(input, cutOutput);
			// ImageProcess.shrinkImage(input, shrinkOutput, 0.5);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

		}
	}

	public static void cutImage(File input, File output) throws IOException {
		BufferedImage image = ImageIO.read(input);
		int width = image.getWidth();
		int height = image.getHeight();
		int[] imageArray = new int[width * height];
		image.getRGB(0, 0, width, height, imageArray, 0, width);
		BufferedImage cut = new BufferedImage(width / 2, height, image.getType());
		cut.setRGB(0, 0, width / 2, height, imageArray, 0, width);
		ImageIO.write(cut, "jpg", output);
	}

	public static void scaleImage(String sourceImagePath, String destinationPath, double scale, String format) {
		File file = new File(sourceImagePath);
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(file);
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			width = parseDoubleToInt(width * scale);
			height = parseDoubleToInt(height * scale);
			Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics graphics = outputImage.getGraphics();
			graphics.drawImage(image, 0, 0, null);
			graphics.dispose();
			ImageIO.write(outputImage, format, new File(destinationPath));
		} catch (IOException e) {
			System.out.println("scaleImage方法压缩图片时出错了");
			e.printStackTrace();
		}

	}

	private static int parseDoubleToInt(double sourceDouble) {
		int result = 0;
		result = (int) sourceDouble;
		return result;
	}
}
