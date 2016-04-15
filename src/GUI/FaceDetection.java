////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Author: Taha Emara
//
// Youtube Cahnnel : http://www.youtube.com/user/omar0103637
// Facebook Page : https://www.facebook.com/IcPublishes
// Linkedin Profile : http://eg.linkedin.com/pub/taha-emara/a4/1ab/524/
// E-mail: : tahaemara.eng@gmail.com
//
//                   Real time face detection using OpenCV with Java
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import static org.opencv.objdetect.Objdetect.CASCADE_SCALE_IMAGE;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author Taha Emara
 */
public class FaceDetection extends javax.swing.JFrame {
///

    private DaemonThread myThread = null;
    int count = 0;
    VideoCapture webSource = null;
    Mat frame = new Mat();
    Mat grises = new Mat();
    MatOfByte mem = new MatOfByte();
    /**************************/
    String cara_haarcascades = "C:\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt2.xml";
    String cara_haarcascades_cuda = "C:\\opencv\\sources\\data\\haarcascades_cuda\\haarcascade_frontalface_alt2.xml";
    String cara_lbpcascades = "C:\\opencv\\sources\\data\\lbpcascades\\lbpcascade_frontalface.xml";
    /**************************/
    String ojo_haarcascades = "C:\\opencv\\sources\\data\\haarcascades\\haarcascade_eye_tree_eyeglasses.xml";
    /**************************/
    
    CascadeClassifier faceDetector = new CascadeClassifier(cara_haarcascades);
    CascadeClassifier ojoDetector = new CascadeClassifier(ojo_haarcascades);
    MatOfRect faceDetections = new MatOfRect();
    Mat reducida = new Mat();
    Imshow im = new Imshow("Video Preview"); 
///       
    public FaceDetection() {
        initComponents();                
        
    }
    
    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (webSource.grab()) {
                        try {
                            webSource.retrieve(frame);
                            Graphics g = jPanel1.getGraphics();
                            Imgproc.cvtColor(frame, grises, Imgproc.COLOR_BGR2GRAY);
                            Imgproc.equalizeHist(grises, grises);
//                            Imgproc.resize(grises, reducida, new Size(g., 150));
                            faceDetector.detectMultiScale(grises, faceDetections, 1.3, 4, 0, new Size(30, 30), new Size(0, 0));  
                            for (Rect rect : faceDetections.toArray()) {
                               // System.out.println("ttt");
                                Imgproc.rectangle(frame, 
                                                new Point(rect.x, rect.y), 
                                                new Point(rect.x + rect.width, rect.y + rect.height),
                                                new Scalar(0, 255,0), 2);
                            }
                            //im.showImage(frame);
                            //Imgcodecs.imencode(".bmp", frame, mem);
                            //Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                            BufferedImage buff = im.toBufferedImage(frame);
                            if (g.drawImage(buff, 0, 0, jPanel1.getWidth(), jPanel1.getHeight() , 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                if (runnable == false) {
                                    System.out.println("Paused ..... ");
                                    this.wait();
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println("Error: "+ex);
                        }
                    }
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Pause");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        myThread.runnable = false;            // stop thread
        jButton2.setEnabled(false);   // activate start button 
        jButton1.setEnabled(true);     // deactivate stop button

        webSource.release();  // stop caturing fron cam


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

//        webSource = new VideoCapture(0); // video capture from default cam
//        myThread = new DaemonThread(); //create object of threat class
//        Thread t = new Thread(myThread);
//        t.setDaemon(true);
//        myThread.runnable = true;
//        t.start();                 //start thrad
//        jButton1.setEnabled(false);  // deactivate start button
//        jButton2.setEnabled(true);  //  activate stop button

        (new Thread(){
            public void run(){
                VideoCapture capture = new VideoCapture(0);
                Mat frame = new Mat();
                MatOfRect faces = new MatOfRect();
                Mat frame_gray = new Mat();

                if(!capture.isOpened()){
                    System.out.println("NO ESTA ABIERTA LA CAPUTRA");
                }else{
                    System.out.println("SE ABRIO LA CAPUTRA");
                }

                while(capture.read(frame)){
                    if(frame.empty()){
                        System.out.println("LA IMAGEN ESTA VACIA");
                        break;
                    }else{
                        Mat smallImg = new Mat();
                        Mat smallOrig = smallImg.clone();
                        Imgcodecs.imwrite("small Orig.png", smallOrig);
                        Imgcodecs.imwrite("smallOrig.png", smallOrig);
                        Graphics g = jPanel1.getGraphics();                        
                        Imgproc.cvtColor(frame, frame_gray, Imgproc.COLOR_BGR2GRAY);
                        Imgproc.resize(frame_gray, smallImg, new Size(frame.cols()/2, frame.rows()/2), 0, 0, Imgproc.INTER_LINEAR);
                        Imgproc.resize(frame, smallOrig, new Size(frame.cols()/2, frame.rows()/2), 0, 0, Imgproc.INTER_LINEAR);
                        Imgcodecs.imwrite("Normal.png", frame);
                        Imgcodecs.imwrite("Reducida.png", smallImg);
                        Imgproc.equalizeHist(smallImg, smallImg);
                        double w = frame.width();
                        double h = frame.height();
                        //System.out.print("w = "+w);System.out.print(" - h = "+h);System.out.print("\t");System.out.print("cols = "+frame.cols());                System.out.println(" - rows = "+frame.rows());
                        //faceDetector.detectMultiScale(frame_gray, faces, 1.1, 2, 0|CASCADE_SCALE_IMAGE, new Size(w/16, h/16), new Size(w/8, h/8) );
                        faceDetector.detectMultiScale(smallImg, faces, 1.1, 2, 0|CASCADE_SCALE_IMAGE, new Size(0, 0), new Size(w, h) );
                        Rect[] facesArray = faces.toArray();
                        System.out.println("FACES_ARRAY: "+facesArray.length);//CANTIDAD DE CARAS ENCONTRADAS
                        BufferedImage buff = null;                       
                        for (int i = 0; i < facesArray.length; i++) {
                           
                            Point center = new Point((facesArray[i].x + facesArray[i].width * 0.5), (facesArray[i].y + facesArray[i].height * 0.5));
                            Imgproc.ellipse(smallOrig, center, new Size(facesArray[i].width * 0.5, facesArray[i].height * 0.5), 0, 0, 360, new Scalar(255, 0, 255), 4, 8, 0);
                            
                            Mat faceROI = smallImg.submat(facesArray[i]);
                            MatOfRect eyes = new MatOfRect();
                            ojoDetector.detectMultiScale(faceROI, eyes, 1.1, 2, 0|CASCADE_SCALE_IMAGE, new Size(30, 30), new Size(80, 80) );
                            
                            Rect[] eyesArray = eyes.toArray();
                            for(int j=0; j<eyesArray.length; j++){
//                                Point eye_center = new Point(facesArray[i].x + eyesArray[j].x + eyesArray[j].width/2, facesArray[i].y + eyesArray[j].y + eyesArray[j].height/2);
//                                int radiuss = (int)Math.round( (eyesArray[j].width + eyesArray[j].height)*0.25 );
//                                Imgproc.circle(frame, eye_center, radiuss, new Scalar(255, 0, 255), 3, 8, 0);
                                /*****************************/
                                Point center1 = new Point(facesArray[i].x + eyesArray[j].x + eyesArray[j].width * 0.5, facesArray[i].y + eyesArray[j].y + eyesArray[j].height * 0.5);
                                int radius = (int) Math.round((eyesArray[j].width + eyesArray[j].height) * 0.25);
                                Imgproc.circle(smallOrig, center1, radius, new Scalar(255, 0, 0), 4, 8, 0);
//                                if(eyesArray.length==2){
//                                    Mat ojito = new Mat(smallOrig.submat(facesArray[i]), eyesArray[j]);
//                                    Imgproc.resize(ojito, ojito, new Size(frame.cols(), frame.rows()));
//                                    Imgcodecs.imwrite("Ojos"+j+".png", ojito);
//                                }                                        
                            }
                            //(g.drawImage(buff, 0, 0, jPanel1.getWidth(), jPanel1.getHeight() , 0, 0, buff.getWidth(), buff.getHeight(), null);                            
                        }
                        buff = im.toBufferedImage(smallOrig);
                        lbl.setIcon((buff!=null)?new ImageIcon(buff):null);
                        validate();
                        repaint();
                    }
                }
            }
        }).start();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FaceDetection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FaceDetection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FaceDetection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FaceDetection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FaceDetection().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl;
    // End of variables declaration//GEN-END:variables
}
