#include "pch.h"
#include <stdio.h>
#include <stdlib.h>
#include <GL/glut.h>

int x[2], y[2];
int i = 0;
int width = 300, height = 300;

GLint state;

void reshape(int width, int height);
void mdisplay();
void mouse(int button, int state, int x, int y);
void drawLine(int xs, int ys, int xe, int ye);
void bresenham2(int xs, int ys, int xe, int ye);
void bresenham3(int xs, int ys, int xe, int ye);


int main(int argc, char **argv) {
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE);
	glutInitWindowSize(width, height);
	glutInitWindowPosition(0, 0);

	glutCreateWindow("Crtanje linija");

	glutDisplayFunc(mdisplay);
	glutReshapeFunc(reshape);
	glutMouseFunc(mouse);

	printf("Zadajte tocke lijevim klikom misa\n");

	glutMainLoop();

	return 0;
}

void reshape(int width, int height) {
	glDisable(GL_DEPTH_TEST);
	glViewport(0, 0, (GLsizei)width, (GLsizei)height);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(0, width - 1, height - 1, 0, 0, 1);
	glMatrixMode(GL_MODELVIEW);
}

void mdisplay() {
	glClearColor(1.0f, 0.0f, 1.0f, 1.0f);
	//glClear(GL_COLOR_BUFFER_BIT);
	glLoadIdentity();
	glColor3f(0.0f, 0.0f, 0.0f); //boja linije
	glFlush();
}


void drawLine(int xs, int ys, int xe, int ye) {
	
	//glPointSize(5);

	glBegin(GL_LINES);
	{
		glVertex2i(xs+50, ys+20);			//	crtanje gotove linije
		glVertex2i(xe+50, ye+20);
	}
	glEnd();
	//glLoadIdentity();
	glColor3f(1.0f, 0.0f, 0.0f);
	if (xs <= xe) {
		if (ys <= ye) {
			bresenham2(xs, ys, xe, ye); 
		}
		else {
			bresenham3(xs, ys, xe, ye);
		}
	}
	else {
		if (ys >= ye) {
			bresenham2(xe, ye, xs, ys);
		}
		else {
			bresenham3(xe, ye, xs, ys);
		}
	}
}

//kutevi od 0 do 90
void bresenham2(int xs, int ys, int xe, int ye){
	int x, yc, korekcija;
	int a, yf;

	if (ye - ys <= xe - xs) {
		a = 2 * (ye - ys);
		yc = ys; yf = -(xe - xs); korekcija = -2 * (xe - xs);
		
		glBegin(GL_POINTS);
		for (x = xs; x <= xe; x++) {
			glVertex2i(x, yc);
			yf += a;
			if (yf >= 0) {
				yf += korekcija;
				yc++;
			}
		}
		glEnd();
	}
	else {
		x = xe; xe = ye; ye = x;
		x = xs; xs = ys; ys = x;
		a = 2 * (ye - ys);
		yc = ys; yf = -(xe - xs); korekcija = -2 * (xe - xs);
		glBegin(GL_POINTS);
		for (x = xs; x <= xe; x++) {
			glVertex2i(yc, x);
			yf += a;
			if (yf >= 0) {
				yf += korekcija;
				yc++;
			}
		}
		glEnd();
	}
}

//kutevi od 0 do -90
void bresenham3(int xs, int ys, int xe, int ye) {
	int x, yc, korekcija;
	int a, yf;

	if (-(ye - ys) <= xe - xs) {
		a = 2 * (ye - ys);
		yc = ys; yf = (xe - xs); korekcija = 2 * (xe - xs);
		
		glBegin(GL_POINTS);
		for (x = xs; x <= xe; x++) {
			glVertex2i(x, yc);
			yf += a;
			if (yf <= 0) {
				yf += korekcija;
				yc--;
			}
		}
		glEnd();
	}
	else {
		x = xe; xe = ys; ys = x;
		x = xs; xs = ye; ye = x;
		a = 2 * (ye - ys);
		yc = ys; yf = (xe - xs); korekcija = 2 * (xe - xs);
		glBegin(GL_POINTS);
		for (x = xs; x <= xe; x++) {
			glVertex2i(yc, x);
			yf += a;
			if (yf <= 0) {
				yf += korekcija;
				yc--;
			}
		}
		glEnd();
	}
}

void mouse(int button, int state, int xM, int yM) {

	if (button == GLUT_LEFT_BUTTON && state == GLUT_DOWN) {
		x[i] = xM;
		y[i] = yM;
		i = i ^ 1;

		if (i == 0) {
			drawLine(x[i], y[i], x[i+1], y[i+1]);
		}
		else {

			glVertex2i(x[i], y[i]);
		}

		printf("Koordinate tocke %d: %d %d \n", i ^ 1, xM, yM);
		glFlush();
	}
}







