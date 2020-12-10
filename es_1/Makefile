CC = gcc
CFLAGS = -Wall

BUILD = out/
UNITY = ../Resources/C/Unity/unity.c

all: linked array

linked: $(BUILD)linkedList linked_run clean

array: $(BUILD)arrayList array_run clean


$(BUILD)linkedList: 
	gcc test/test.c src/LinkedList.c $(UNITY) -o out/testLinked

$(BUILD)arrayList:
	gcc test/test.c src/ArrayList.c $(UNITY) -o out/testArray

linked_run:
	./$(BUILD)testLinked
	

array_run:
	./$(BUILD)testArray
	

clean:
	$(RM) -r $(BUILD)/*