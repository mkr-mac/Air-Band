CFLAGS = `pkg-config opencv --cflags`
LIBS = `pkg-config opencv --libs`
CC = g++
STD = -std=c++11
SRC = abcv.cpp
DBG = -ggdb
OUT = -o abcv.out

abcv: $(SRC)
	$(CC) $(SRC) $(OUT) $(CFLAGS) $(LIBS) $(STD) $(DBG)
