package opengl;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;
import com.jogamp.opengl.util.gl2.GLUT; 
import java.lang.Math;


public class Lab5 extends GLJPanel implements GLEventListener, KeyListener{

	/**
	 * A main routine to create and show a window that contains a
	 * panel of type Lab5.  The program ends when the user closes the 
	 * window.
	 * 
	 */
	
	private static void licz() {
		int N=12;
	    int R=55;
	    int[] xPoints = new int[N];
	    int[] yPoints = new int[N];
	    for(int i=0;i<N;i++)
	    {
	        xPoints[i]= (int)(R * Math.cos((Math.PI/2 + 2*Math.PI*i) /N));
	        yPoints[i]= (int)(R * Math.sin((Math.PI/2 + 2*Math.PI*i) /N));
	        System.out.println(xPoints[i]*0.01);
	        System.out.println(yPoints[i]*0.01);
	        System.out.println();
	    }
		
	}
	
	public static void main(String[] args) {
		licz();
		
		JFrame window = new JFrame("Some Objects in 3D");
		Lab5 panel = new Lab5();
		window.setContentPane(panel);
		window.pack();
		window.setResizable(false);
		window.setLocation(50,50);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	/**
	 * Constructor for class Lab4.
	 */
	public Lab5() {
		super( new GLCapabilities(null) ); // Makes a panel with default OpenGL "capabilities".
		setPreferredSize( new Dimension(700,700) );
		addGLEventListener(this); // This panel will respond to OpenGL events.
		addKeyListener(this);  // The panel will respond to key events.
	}

	
	//------------------- TODO: Complete this section! ---------------------

	private int objectNumber=1;        // Which object to draw (1 ,2, 3, 4, 5, or 6)?
	                                     //   (Controlled by number keys.)

	
	
	private boolean useAnaglyph = false; // Should anaglyph stereo be used?
	
	

	//    (Controlled by space bar.)

	private int rotateX = 0;    // Rotations of the cube about the axes.
	private int rotateY = 0;    //   (Controlled by arrow, PageUp, PageDown keys;
	private int rotateZ = 0;    //    Home key sets all rotations to 0.)

	private GLUT glut = new GLUT(); // An object for drawing GLUT shapes.


		
	
		
	/**
	 * The method that draws the current object, with its modeling transformation.
	 */
	private void draw(GL2 gl2) {

		gl2.glRotatef(rotateZ,0,0,1);   // Apply rotations to complete object.
		gl2.glRotatef(rotateY,0,1,0);
		gl2.glRotatef(rotateX,1,0,0);

		// TODO: Draw the currently selected object, number 1, 2, 3, 4, 5, or 6.
		// (Objects should lie in the cube with x, y, and z coordinates in the
		// range -5 to 5.)
		

		
	//PO NACIŒNIÊCIU 1 LUB 2 RYSUJE SIÊ WYBRANA FIGURA!!!	
		
	switch(objectNumber) 
	{
	case 1:
		gl2.glClear (gl2.GL_COLOR_BUFFER_BIT | gl2.GL_DEPTH_BUFFER_BIT);
		gl2.glColor3f(0, 1, 0);
		float degtorads,theta,z,a,b,r;
		degtorads = (float) (Math.PI/180.0);
		z=0.0f; a = 0.5f; b= 0.1f;
		gl2.glRotatef(20.0f, 0.0f, 1.0f, 0.0f);
		gl2.glBegin(gl2.GL_LINE_STRIP);
		for(theta = 0.0f;theta <1500.0f;theta+=1.0) 
		{
			r= (float) (a*Math.exp(b*theta*degtorads));
			gl2.glVertex3f((float)(r*Math.cos(theta*degtorads)),(float)( r*Math.sin(theta*degtorads)), z);
			z+=0.005;
			
		}
		gl2.glEnd();
		break;
	case 2:
		gl2.glClear (gl2.GL_COLOR_BUFFER_BIT | gl2.GL_DEPTH_BUFFER_BIT);
		gl2.glBegin(gl2.GL_TRIANGLE_FAN);

		//PUNKT WSPOLNY 12 TROJKATOW
		gl2.glVertex3f(0.0f,1.0f,0.0f);
		
		//WSPOLRZEDNE 12 PUNKTOW z podprogramu licz() i pomnozone razy 10
		gl2.glVertex3f(5.4f,-4.0f,0.7f);
		gl2.glVertex3f(4.3f,-4.0f,3.3f);
		gl2.glVertex3f(2.1f,-4.0f,5f);
		gl2.glVertex3f(-0.7f,-4.0f,5.4f);
		gl2.glVertex3f(-3.3f,-4.0f,4.3f);
		gl2.glVertex3f(-5f,-4.0f,2.1f);
		gl2.glVertex3f(-5.4f,-4.0f,-0.7f);
		gl2.glVertex3f(-4.3f,-4.0f,-3.3f);
		gl2.glVertex3f(-2.1f,-4.0f,-5f);
		gl2.glVertex3f(0.7f,-4.0f,-5.4f);
		gl2.glVertex3f(3.3f,-4.0f,-4.3f);
		gl2.glVertex3f(5f,-4.0f,-2.1f);
		gl2.glVertex3f(5.4f,-4.0f,0.7f);
		
		
		gl2.glEnd();
		break;
		
	}

				
	}
	


	//-------------------- Draw the Scene  -------------------------

	/**
	 * The display method is called when the panel needs to be drawn.
	 * It's called when the window opens and it is called by the keyPressed
	 * method when the user hits a key that modifies the scene.
	 */
	public void display(GLAutoDrawable drawable) {    

		GL2 gl2 = drawable.getGL().getGL2(); // The object that contains all the OpenGL methods.
		
		
		if (useAnaglyph) {
			gl2.glDisable(GL2.GL_COLOR_MATERIAL); // in anaglyph mode, everything is drawn in white
			gl2.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, new float[]{1,1,1,1}, 0);
		}
		else { 
			gl2.glEnable(GL2.GL_COLOR_MATERIAL);  // in non-anaglyph mode, glColor* is respected
		}        	
		gl2.glNormal3f(0,0,1); // (Make sure normal vector is correct for object 1.)

		gl2.glClearColor( 0, 0, 0, 1 ); // Background color (black).
		gl2.glClear( GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );


		if (useAnaglyph == false) {
			gl2.glLoadIdentity(); // Make sure we start with no transformation!
			gl2.glTranslated(0,0,-15);  // Move object away from viewer (at (0,0,0)).
			draw(gl2);
		}
		else {
			gl2.glLoadIdentity(); // Make sure we start with no transformation!
			gl2.glColorMask(true, false, false, true);
			gl2.glRotatef(4,0,1,0);
			gl2.glTranslated(1,0,-15); 
			draw(gl2);  // draw the current object!
			gl2.glColorMask(true, false, false, true);
			gl2.glClear(GL2.GL_DEPTH_BUFFER_BIT);
			gl2.glLoadIdentity();
			gl2.glRotatef(-4,0,1,0);
			gl2.glTranslated(-1,0,-15); 
			gl2.glColorMask(false, true, true, true);
			draw(gl2);
			gl2.glColorMask(true, true, true, true);
		}
		
		
		

	} // end display()


	public void init(GLAutoDrawable drawable) {
		GL2 gl2 = drawable.getGL().getGL2();
		gl2.glMatrixMode(GL2.GL_PROJECTION);
		gl2.glFrustum(-3.5, 3.5, -3.5, 3.5, 5, 25);
		gl2.glMatrixMode(GL2.GL_MODELVIEW);
		gl2.glEnable(GL2.GL_LIGHTING);  
		gl2.glEnable(GL2.GL_LIGHT0);
		gl2.glLightfv(GL2.GL_LIGHT0,GL2.GL_DIFFUSE,new float[] {0.7f,0.7f,0.7f},0);
		gl2.glLightModeli(GL2.GL_LIGHT_MODEL_TWO_SIDE, 1);
		gl2.glEnable(GL2.GL_DEPTH_TEST);
		gl2.glLineWidth(3);  // make wide lines for the stellated dodecahedron.
	}

	public void dispose(GLAutoDrawable drawable) {
		// called when the panel is being disposed
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		// called when user resizes the window
	}

	// ----------------  Methods from the KeyListener interface --------------


	public void keyPressed(KeyEvent evt) {
		int key = evt.getKeyCode();
		boolean repaint = true;
		
		//ZAMIENIA FIGURY W ZALEZNOSCI OD KLAWISZA 1 i 2
		if (key == KeyEvent.VK_1)
			objectNumber = 1;
		else if ( key == KeyEvent.VK_2 )
			objectNumber =2;
		
		if ( key == KeyEvent.VK_LEFT )
			rotateY -= 6;
		else if ( key == KeyEvent.VK_RIGHT )
			rotateY += 6;
		else if ( key == KeyEvent.VK_DOWN)
			rotateX += 6;
		else if ( key == KeyEvent.VK_UP )
			rotateX -= 6;
		else if ( key == KeyEvent.VK_PAGE_UP )
			rotateZ += 6;
		else if ( key == KeyEvent.VK_PAGE_DOWN )
			rotateZ -= 6;
		else if ( key == KeyEvent.VK_HOME )
			rotateX = rotateY = rotateZ = 0;
		else if (key == KeyEvent.VK_1)
			objectNumber = 1;
		else if (key == KeyEvent.VK_2)
			objectNumber = 2;
		else if (key == KeyEvent.VK_3)
			objectNumber = 3;
		else if (key == KeyEvent.VK_4)
			objectNumber = 4;
		else if (key == KeyEvent.VK_5)
			objectNumber = 5;
		else if (key == KeyEvent.VK_6)
			objectNumber = 6;
		else if (key == KeyEvent.VK_SPACE)
			useAnaglyph = ! useAnaglyph;
		else
			repaint = false;
		if (repaint)
			repaint();
	}

	public void keyReleased(KeyEvent evt) {
	}

	public void keyTyped(KeyEvent evt) {
	}

} // end class Lab4

