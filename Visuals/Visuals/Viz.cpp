#include <opencv2/highgui.hpp>
#include <iostream>
#include <opencv2/imgproc.hpp>
#include <opencv2\imgcodecs.hpp>
#include <opencv2\core.hpp>
#include <vector>
#include <thread>

using namespace std;
using namespace cv;

int main()
{
	namedWindow("visual", WINDOW_AUTOSIZE);
	int x, y;
	x = rand() % 1920; y = rand() % 1080;
	Mat frame = imread("D:/Programming/github/ab/Visuals/x64/Debug/aes.jpg",CV_LOAD_IMAGE_COLOR);
	if (!frame.data) { exit(-1); }
	imshow("visual", frame);
//	vector<thread> th;
	Mat framehsv;
	cvtColor(frame, framehsv, CV_RGB2HSV);
	while (1)
	{
		for (int i = 0; i < 1920; i++)
			for (int j = 0; j < 1080; j++)
			{
				Vec3b col = framehsv.at<Vec3b>(Point(i, j));
				col[0] = (col[0] + 2) % 180;
				framehsv.at<Vec3b>(Point(i, j)) = col;
			}
		cvtColor(framehsv, frame,COLOR_HSV2BGR);
		imshow("visual", frame);
		if (waitKey(30)==27) break;
	}
}