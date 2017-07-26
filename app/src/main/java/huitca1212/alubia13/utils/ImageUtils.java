package huitca1212.alubia13.utils;

public class ImageUtils {

	public static String generateImageResizeUrl(String imageUrl, int width) {
		return "http://rjapps.x10host.com/resize_image.php?url=" + imageUrl + "&width=" + width;
	}
}
