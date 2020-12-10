JFLAGS = -Xlint
JC = javac
JVM = java

BUILD = out
JUnit_PATH = ../Resources/Java/JUnit/junit-4.12.jar:../Resources/Java/JUnit/hamcrest-core-1.3.jar:$(BUILD)

NODE = src/code/Node.java
UNIONFINDSET = src/code/UnionFindSet.java
FILES = $(NODE) $(UNIONFINDSET)

all: unionFindTest

unionFindTest: unionFind_build
	$(MAKE) unionFind_run

unionFind_build:
	$(JC) $(JFLAGS) $(FILES) -d "$(BUILD)"
	$(JC) $(JFLAGS) -cp $(JUnit_PATH) src/test/*.java -d "$(BUILD)"

unionFind_run:
	$(JVM) -cp "$(JUnit_PATH)" test.TestRunner

.PHONY: clean

clean:
	$(RM) -r $(BUILD)/*