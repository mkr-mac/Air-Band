#include "Visual.h"
#include <iostream>
#include <vector>
#define GLEW_STATIC

#include <GL/glew.h>
#include <GLFW/glfw3.h>
#include <thread>

using namespace std;

Visual::Visual()
{
}


Visual::~Visual()
{
}

const int RESH = 800;
const int RESW = 600;
const float RADI = 40.0f/(RESH/2);

void drawinst(int inst)
{
	auto t_st = chrono::high_resolution_clock::now();
	auto t_now = t_st;
	float x = (rand() % 201) / 100.0f - 1; float y = (rand() % 201) / 100.0f - 1;
	float velX = (rand() % 100) / 100.0f; float velY = (rand() % 100) / 100.0f;
	while (chrono::duration_cast<chrono::duration<float>>((t_now = chrono::high_resolution_clock::now()) - t_st).count() < 1200)
	{
		GLfloat pi = 3.1415926535897932f;
		glClear(GL_COLOR_BUFFER_BIT);
		glColor3f(1.0f / inst, 1.0f / inst, 1.0f / inst);
		glBegin(GL_TRIANGLE_FAN);
		{
			glVertex2f(x, y);
			for (int i = 0; i < 50; i++)
			{
				glVertex2f(x + RADI*cos(i * 2 * pi / 50), y + RADI*sin(i * 2 * pi / 50));
			}
		}
		glEnd();
		if ((x += velX) > 1 || x < -1 || (y += velY) > 1 || y < -1)
		{
			return;
		}
	}
}

void decayinst(int inst)
{

}

void process(int inst)
{
	drawinst(inst);
	decayinst(inst);
}

int man()
{
	glewExperimental = GL_TRUE;
	glfwInit();
	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
	glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

	glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
	GLFWwindow* window = glfwCreateWindow(RESH, RESW, "OpenGL", nullptr, nullptr);
	glfwMakeContextCurrent(window);
	glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	vector<thread> prcs;
	/*while (!glfwWindowShouldClose(window))
	{
		glfwSwapBuffers(window);
		glfwPollEvents();
		int inst; char ctrl;
		cin >> inst >> ctrl;
		prcs.push_back(thread(process, inst));
		if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS)	glfwSetWindowShouldClose(window, GL_TRUE);
	}
	for (auto &t : prcs)
	{
		t.join();
	}*/
	int inst = 3;
	auto t_st = chrono::high_resolution_clock::now();
	auto t_now = t_st;
	float x = (rand() % 201) / 100.0f - 1; float y = (rand() % 201) / 100.0f - 1;
	float velX = (rand() % 100) / 100.0f; float velY = (rand() % 100) / 100.0f;
	while (chrono::duration_cast<chrono::duration<float>>((t_now = chrono::high_resolution_clock::now()) - t_st).count() < 1000000)
	{
		GLfloat pi = 3.1415926535897932f;
		glClear(GL_COLOR_BUFFER_BIT);
		glColor3f(0.0f,0.0f,0.0f);
		glBegin(GL_TRIANGLE_FAN);
		
			glVertex2f(x, y);
			for (int i = 0; i < 50; i++)
			{
				glVertex2f(x + RADI*cos(i * 2 * pi / 50), y + RADI*sin(i * 2 * pi / 50));
			}
		
		glEnd();
		glFlush();
		if ((x += velX) > 1 || x < -1 || (y += velY) > 1 || y < -1)
		{
			break;
		}
	}
	glfwTerminate();
	return 0;
}
