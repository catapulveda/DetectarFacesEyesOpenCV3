package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
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
public class FaceDetectionSinReducir extends javax.swing.JFrame {
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
    String sonrisa_haarcascades = "C:\\opencv\\sources\\data\\haarcascades\\haarcascade_mcs_mouth.xml";
    
    CascadeClassifier faceDetector = new CascadeClassifier(cara_haarcascades);
    CascadeClassifier ojoDetector = new CascadeClassifier(ojo_haarcascades);
    CascadeClassifier bocaDetector = new CascadeClassifier(sonrisa_haarcascades);    
    
    MatOfRect faceDetections = new MatOfRect();
    MatOfRect mouthDetections = new MatOfRect();
    Mat reducida = new Mat();
    Imshow im = new Imshow("Video Preview"); 
///       
    public FaceDetectionSinReducir() {
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
        tomarfoto = new javax.swing.JCheckBox();
        valor = new javax.swing.JSlider();

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
            .addGap(0, 121, Short.MAX_VALUE)
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

        tomarfoto.setText("Tomar Foto");

        valor.setMajorTickSpacing(10);
        valor.setMinimum(1);
        valor.setMinorTickSpacing(100);
        valor.setPaintLabels(true);
        valor.setPaintTicks(true);
        valor.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tomarfoto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(valor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(tomarfoto))
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
                MatOfRect eyes = new MatOfRect();
                MatOfRect bocasDetectadas = new MatOfRect();
                Mat frame_gray = new Mat();

                if(!capture.isOpened()){
                    System.out.println("NO ESTA ABIERTA LA CAPUTRA");
                }else{
                    System.out.println("SE ABRIO LA CAPUTRA");
                }

                int num = 0;
                while(capture.read(frame)){
                    if(frame.empty()){
                        System.out.println("LA IMAGEN ESTA VACIA");
                        break;
                    }else{                              
                        Graphics g = jPanel1.getGraphics();
                        Imgproc.cvtColor(frame, frame_gray, Imgproc.COLOR_BGR2GRAY); 
                        Imgproc.equalizeHist(frame_gray, frame_gray);
                        double w = frame.width();
                        double h = frame.height();
                        //System.out.print("w = "+w);System.out.print(" - h = "+h);System.out.print("\t");System.out.print("cols = "+frame.cols());                System.out.println(" - rows = "+frame.rows());
                        //faceDetector.detectMultiScale(frame_gray, faces, 1.1, 2, 0|CASCADE_SCALE_IMAGE, new Size(w/16, h/16), new Size(w/8, h/8) );
                        faceDetector.detectMultiScale(frame_gray, faces, 1.3, 2, 0|CASCADE_SCALE_IMAGE, new Size(0, 0), new Size(w, h) );
                        Rect[] facesArray = faces.toArray();
                        System.out.println("FACES_ARRAY: "+facesArray.length);//CANTIDAD DE CARAS ENCONTRADAS
                        BufferedImage buff = null;                                                                       
                        Mat cara_reducida = new Mat();
                        Mat ojo = new Mat();
                        for (int i = 0; i < facesArray.length; i++) {
                           /*DETECTA Y DIBUJA UN ELIPSE A LO QUE CORRESPONDE LA CARA*/ 
                            Point center = new Point((facesArray[i].x + facesArray[i].width * 0.5), (facesArray[i].y + facesArray[i].height * 0.5));
                            Imgproc.ellipse(frame, center, new Size(facesArray[i].width * 0.5, facesArray[i].height * 0.5), 0, 0, 360, new Scalar(255, 0, 255), 4, 8, 0);
                           /*********************FIN****************/
                            Mat faceROI = frame_gray.submat(facesArray[i]);
                            ojoDetector.detectMultiScale(faceROI, eyes, 1.3, 2, 0|CASCADE_SCALE_IMAGE, new Size(30, 30), new Size(80, 80) );
                            try{
//                            faceROI = faceROI.submat((int) (faceROI.rows()*0.25), faceROI.rows(), 20, faceROI.cols()-20);
                            }catch(Exception e){}
                            if(tomarfoto.isSelected()){
                                Imgproc.resize(faceROI, cara_reducida, new Size(125, 150));
                                if(Imgcodecs.imwrite("nelson"+num+".png", cara_reducida)){
                                    num++;
                                    tomarfoto.setSelected(false);
                                }
                            }
                            /*************************************/
//                            Rect[] eyesArray = eyes.toArray();
//                            for(int j=0; j<eyesArray.length; j++){
//                                /*************************************/
//                                ojo = faceROI.submat(eyesArray[j]);
//                                try{
//                                Imgproc.medianBlur(ojo, ojo, 5);
//                                Imgproc.GaussianBlur(ojo, ojo, new Size(valor.getValue(), valor.getValue()), 100);
//                                }catch(Exception e){}
//                                Point center1 = new Point(facesArray[i].x + eyesArray[j].x + eyesArray[j].width * 0.5, 
//                                                          facesArray[i].y + eyesArray[j].y + eyesArray[j].height * 0.5);
//                                int radius = (int) Math.round((eyesArray[j].width + eyesArray[j].height) * 0.25);
//                                Imgproc.circle(frame, center1, radius, new Scalar(255, 0, 0), 4, 8, 0);
//                                /*************************************/
//                                Mat circles = new Mat();
//                                try{
//                                Imgproc.HoughCircles(ojo, circles, Imgproc.CV_HOUGH_GRADIENT, 1, 100);
//                                }catch(Exception e){}
//                                for(int k = 0; k < circles.cols(); k++) {
//                                    double[] circle = circles.get(0, k);
////                                    g.drawOval((int)circle[0] - (int)circle[2], (int)circle[1] - (int)circle[2], (int)circle[2] * 2, (int)circle[2] * 2);
//                                    int radio = (int) Math.round(((int)circle[2] * 2 + (int)circle[2] * 2) * 0.25);
//                                    Imgproc.circle(ojo, 
//                                            new Point( (int)circle[0] - (int)circle[2] , 
//                                                       (int)circle[1] - (int)circle[2]), 
//                                            radio, 
//                                            new Scalar(124, 214, 321));
//                                }
//                                break;
//                            }
                        }
                        try{
                            int alto = frame.rows();
                            int ancho = frame.cols();
                            Color bitMap[][] = new Color[ancho][alto];
                            for(int i = 0; i < alto; i++){
                                for(int j = 0; j < ancho; j++){
                                    Color pixel = bitMap[ i][ j];
                                    int promedio = (pixel.getBlue() + pixel.getGreen() + pixel.getRed()) / 3;
                                    if(promedio < 1.0){
                                        bitMap[ i][ j] = Color.BLACK;
                                    }else{
                                        bitMap[ i][ j] = Color.WHITE;
                                    }                                    
                                }
                            }
                            
                            BufferedImage imagen = new BufferedImage( ancho, alto, BufferedImage.TYPE_BYTE_BINARY );
                            for( int i = 0; i < alto; i++ ){
                                for( int j = 0; j < ancho; j++ ){
                                    imagen.setRGB( j, i, bitMap[ i ][ j ].getRGB() );
                                }
                            }
                            
                        //buff = im.toBufferedImage( (ojo==null)?frame:ojo );
                        //buff = im.toBufferedImage( frame );
                        buff = imagen;
                        if(g.drawImage(buff, 0, 0, jPanel1.getWidth(), jPanel1.getHeight() , 0, 0, buff.getWidth(), buff.getHeight(), null)){
                            
                        }
                        }catch(Exception e){}
//                        lbl.setIcon((buff!=null)?new ImageIcon(new ImageIcon(buff).getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT)):null);
//                        validate();
//                        repaint();
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
            java.util.logging.Logger.getLogger(FaceDetectionSinReducir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FaceDetectionSinReducir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FaceDetectionSinReducir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FaceDetectionSinReducir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FaceDetectionSinReducir().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox tomarfoto;
    private javax.swing.JSlider valor;
    // End of variables declaration//GEN-END:variables
}
