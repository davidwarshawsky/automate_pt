package image_mod;

import java.awt.Image;

import javax.swing.ImageIcon;


public class ChangeImageSize {
	public String path;
	
	public static void main(String[] args) {
		ChangeImageSize cis = new ChangeImageSize("hello.png");
	}
	
	public ChangeImageSize(String path){
		System.out.println(this.path);
		this.path = path;
		
	}
	
	public static ImageIcon resize(String path,int width , int height) {
		ImageIcon ii = new ImageIcon(path);
		Image i = ii.getImage();
		Image modifiedImage = i.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(modifiedImage);
	}
}
