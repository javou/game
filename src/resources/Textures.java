package resources;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
public class Textures {

	private static HashMap<String, BufferedImage> textures;
	public static void init() {
		textures = new HashMap<String, BufferedImage>();
		File folder = new File("images");
		for(File file : folder.listFiles()) {
			try {
				textures.put(file.getName().replaceAll(".png",""), ImageIO.read(file));
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Exception reading: " + file.getName());
			}
		}	
	}
	public static BufferedImage getTexture(String name) {
		BufferedImage texture = textures.get(name);
		if(texture == null)
			texture = textures.get("error");
		return texture;
			
	}
}
