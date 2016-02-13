#include <iostream>
#include <cstdio>
#include <opencv2/videoio.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>
#include <opencv2/core.hpp>

using namespace cv;
using namespace std;

int main()
{
	VideoCapture cap(0);if(!cap.isOpened()) exit(-1);
	Mat frame;
	/*int hl,hu,sl,su,ll,lu;
	hl = 0; hu = 179;
	sl = 0; su = 255;
	ll = 0; lu = 255;
	namedWindow("param", WINDOW_AUTOSIZE);
	createTrackbar("hl","param",&hl,179);
	createTrackbar("hu","param",&hu,179);
	createTrackbar("sl","param",&sl,255);
	createTrackbar("su","param",&su,255);
	createTrackbar("ll","param",&ll,255);
	createTrackbar("lu","param",&lu,255);
	int hl2,hu2,sl2,su2,ll2,lu2;
	hl2 = 0; hu2 = 179;
	sl2 = 0; su2 = 255;
	ll2 = 0; lu2 = 255;
	namedWindow("param2", WINDOW_AUTOSIZE);
	createTrackbar("hl","param2",&hl2,179);
	createTrackbar("hu","param2",&hu2,179);
	createTrackbar("sl","param2",&sl2,255);
	createTrackbar("su","param2",&su2,255);
	createTrackbar("ll","param2",&ll2,255);
	createTrackbar("lu","param2",&lu2,255);
	cap.read(frame);
	
	createTrackbar("sz","param",&sz,20);
	
	createTrackbar("nz","param",&nz,100);*/
	int sz = 20;
	int nz = 100;
	int x,y,lx=0,ly=0,x1,y1,lx1= 0,ly1= 0,x2,y2,lx2=0,ly2=0;
	Mat frameln = Mat::zeros(frame.size(),CV_8UC3);
	/*namedWindow("demo",WINDOW_AUTOSIZE);
	
	namedWindow("threshold2",WINDOW_AUTOSIZE);*/
	namedWindow("threshold", WINDOW_AUTOSIZE);
	while(1)
	{
		if(!cap.read(frame)) exit(-1);
		Mat framehls;
		cvtColor(frame,framehls,COLOR_BGR2HLS);		
		Mat frameth,frameth2,frameth3;
		//Green
		inRange(framehls,Scalar(21,52,51),Scalar(78, 135, 108),frameth);
		erode(frameth,frameth,getStructuringElement(MORPH_ELLIPSE,Size(sz,sz)));
		dilate(frameth,frameth,getStructuringElement(MORPH_ELLIPSE,Size(sz,sz)));
		dilate(frameth,frameth,getStructuringElement(MORPH_ELLIPSE,Size(sz,sz)));
		erode(frameth,frameth,getStructuringElement(MORPH_ELLIPSE,Size(sz,sz)));
		//Blue
		inRange(framehls,Scalar(78,0, 102),Scalar(130,139, 255),frameth2);
		erode(frameth2,frameth2,getStructuringElement(MORPH_ELLIPSE,Size(sz,sz)));
		dilate(frameth2,frameth2,getStructuringElement(MORPH_ELLIPSE,Size(sz,sz)));
		dilate(frameth2,frameth2,getStructuringElement(MORPH_ELLIPSE,Size(sz,sz)));
		erode(frameth2,frameth2,getStructuringElement(MORPH_ELLIPSE,Size(sz,sz)));
		//Pink
		inRange(framehls, Scalar(160, 100, 100), Scalar(179, 255, 255), frameth3);
		erode(frameth2, frameth2, getStructuringElement(MORPH_ELLIPSE, Size(sz, sz)));
		dilate(frameth2, frameth2, getStructuringElement(MORPH_ELLIPSE, Size(sz, sz)));
		dilate(frameth2, frameth2, getStructuringElement(MORPH_ELLIPSE, Size(sz, sz)));
		erode(frameth2, frameth2, getStructuringElement(MORPH_ELLIPSE, Size(sz, sz)));


		Moments mt = moments(frameth);
		if (mt.m00 > 100*nz)
		{
			x1 = mt.m10/mt.m00;
			y1 = mt.m01/mt.m00;
			/*if (lx1*ly1*x1*y1>0) line(frameln,Point(lx1,ly1),Point(x1,y1),Scalar(0,0,255),3);
			lx1 = x1;
			ly1 = y1;*/
		}
		else
		{
			cout << "noise" << endl;
		}
		mt = moments(frameth2);
		if (mt.m00 > 100*nz)
		{
			x = mt.m10/mt.m00;
			y = mt.m01/mt.m00;
			/*if (lx*ly*x*y>0) line(frameln,Point(lx,ly),Point(x,y),Scalar(255,0,0),3);
			lx = x;
			ly = y;*/
		}
		else
		{
			cout << "noise" << endl;
		}
		mt = moments(frameth3);
		if (mt.m00 > 100 * nz)
		{
			x2 = mt.m10 / mt.m00;
			y2 = mt.m01 / mt.m00;
			/*if (lx2*ly2*x2*y2>0) line(frameln, Point(lx2, ly2), Point(x2, y2), Scalar(0, 255, 0), 3);
			lx2 = x2;
			ly2 = y2;*/
		}
		else
		{
			cout << "noise" << endl;
		}
		//imshow("demo",frame+frameln);
		imshow("threshold", frameth2);//+frameth2+frameth3);
		fprintf(stdout,"%d %d %d %d %d %d\n",x1,y1,x,y,x2,y2);
		fflush(stdout);
		if (waitKey(1)!=-1) break;
	}
}	
		
