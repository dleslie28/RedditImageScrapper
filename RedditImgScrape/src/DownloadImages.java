import java.io.FileOutputStream;
import java.io.IOException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DownloadImages {
	
	private static String IMAGE_DESTINATION_FOLDER = "C:/Users/johndoe/Desktop/JavaResources/Reddit_Images";	
	
	private static void downloadImage(String strImageUrl) {
		String strImageName = strImageUrl.substring(strImageUrl.lastIndexOf("/")+1, strImageUrl.lastIndexOf("?"));
		System.out.println("Saving: " + strImageName + " from: " + strImageUrl);
		
		try {
			//accesses the URL passed in
			URL urlImage = new URL(strImageUrl);
			
			//reads in the information from URL image
			InputStream in = urlImage.openStream();
			
			//creates buffer for image
			byte[] buffer = new byte[4096];
			int n;
			
			OutputStream os = new FileOutputStream(IMAGE_DESTINATION_FOLDER + "/" + strImageName); 
			
			while((n = in.read(buffer)) != -1) {
				os.write(buffer,0,n);
			}
			
			os.close();
			in.close();
			
			System.out.println("Image saved");
					
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Document document = Jsoup.connect("https://www.reddit.com/r/EarthPorn/").get();			
			Elements imageElements = document.select(".ImageBox-image.media-element");
			
			for(int i = 0; i <= 5; i++ ) {
				String strImageUrl = imageElements.get(i).attr("abs:src");
				if( strImageUrl.charAt(8) == 'e'){
					continue;		
				}
				downloadImage(strImageUrl);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
