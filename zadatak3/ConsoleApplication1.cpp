#include "pch.h"
#include <stdio.h>
#include <stdlib.h>
#include <GL/glut.h>

int x[4], y[4]; // koordinate poligona
int Ax, Ay; //proizvoljna ispitna tocka
int xmax, xmin, ymax, ymin; 
int xall[4], yall[4]; // x koordinate i y koordinate
int a[4], b[4], c[4]; //koeficijenti bridova


int i = 0; // kolko vrhiva ima polygon
int polyDone = 0, tockaZadana=0;
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
	glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
	glLoadIdentity();
	glColor3f(0.0f, 0.0f, 0.0f); //boja linije
	glFlush();
}


void izracunajKoeficijente() {
	for (int j = 0; j < i - 1; j++) {
		a[j] = y[j] - y[j + 1];
		b[j] = -x[j] + x[j + 1];
		c[j] = x[j] * y[j + 1] - x[j + 1] * y[j];
	}
	//i=3
	a[i - 1] = y[i - 1] - y[0];
	b[i - 1] = -x[i - 1] + x[0];
	c[i - 1] = x[i - 1] * y[0] - x[0] * y[i - 1];

	for (int j = 0; j < i; j++) {
		printf("Koeficijenti brida %d: a = %d, b=%d, c=%d\n", j + 1, a[j], b[j], c[j]);
	}
	return;
}

void provjeriGdjeJe() {
	for ( int j = 0; j < i; j++) {
		if ((Ax*a[j] + Ay * b[j] + c[j]) < 0) {
			printf("Tocka je van poligona\n");
			return;
		}
	}
	printf("Tocka je unutar poligona\n");
	return;
}

void bojanjePoligona() {
	int l, d;
	printf("Bojam poligon...\n");
	for (ymin; ymin < ymax+1; ymin++) { //dal treba ymax+1??
		l = xmin; d = xmax;
		for (int j = 0; j < i; j++) {

			int k = j % i;//normalizacija indexa
			if (a[k] == 0) {
				break;
			}
			int xS = (-b[k] * ymin - c[k]) / a[k];
			if (y[k] >= y[k + 1]) {
				if (xS > l)
					l = xS;
			}
			else if (y[k] < y[k + 1]) {
				if (xS <= d)
					d = xS;
			}
		}
		if (l < d) {
			glBegin(GL_LINES);
			glVertex2i(l, ymin);
			glVertex2i(d, ymin);
			glEnd();
		}
	}
	return;
}

void mouse(int button, int state, int xM, int yM) {

	if (button == GLUT_LEFT_BUTTON && state == GLUT_DOWN) {
		if (polyDone == 0) {
			x[i] = xM;
			y[i] = yM;
			i++;
		}
		else {
			Ax = xM;
			Ay = yM;
			tockaZadana = 1;
		}

		if (i == 4 && polyDone==0) {
			glBegin(GL_LINE_LOOP);
			glVertex2i(x[0], y[0]);
			glVertex2i(x[1], y[1]);
			glVertex2i(x[2], y[2]);
			glVertex2i(x[3], y[3]);
			glEnd();
			for (int j = 0; j < i; j++) {
				xall[j] = x[j];
				yall[j] = y[j];
			}
			xmax = xall[0];
			ymax = yall[0];
			xmin = xall[0];
			ymin = yall[0];
			for (int j = 0; j < i; j++) {
				if (xmax < xall[j])
					xmax = xall[j];
				if (ymax < yall[j])
					ymax = yall[j];
				if (xmin > xall[j])
					xmin = xall[j];
				if (ymin > yall[j])
					ymin = yall[j];
			}
			polyDone = 1;
		}
		else {
			glBegin(GL_POINTS);
			glVertex2i(Ax, Ay);
			glEnd();
		}
		
		printf("Koordinate tocke %d: %d %d \n", i, xM, yM);
		if (polyDone && !tockaZadana) {
			printf("xmax: %d, xmin: %d, ymax: %d, ymin: %d\n", xmax, xmin, ymax, ymin);
			izracunajKoeficijente();
		}
		if (tockaZadana) {
			provjeriGdjeJe();
			bojanjePoligona();
		}
		//bojanjePoligona();
		glFlush();
	}
}