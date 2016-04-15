package GUI;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class example {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Imshow im = new Imshow("Video Preview"); 		 
                im.Window.setResizable(true);
		// -------------------------
		Mat imagen = new Mat();
		VideoCapture video = new VideoCapture(0);		
		// loop until VideoCamera is Available
		while (video.isOpened() == false)
			;
		// Bug Fix: Loop until initial image frames are empty
		while(imagen.empty()) {                    
                    video.retrieve(imagen);			
		}

		while (true) {			
                    video.retrieve(imagen);  
                    System.out.println(imagen.cols()+"\t"+imagen.rows());
//                    System.out.println(imagen.dump());                    
                    im.showImage(imagen);
                    
		}
	}

}
