JFLAGS = -Xlint
JC = javac
JVM = java

BUILD = out
JUnit_PATH = ../Resources/Java/JUnit/junit-4.12.jar:../Resources/Java/JUnit/hamcrest-core-1.3.jar:$(BUILD)

EDIT = src/code/EditDistance.java
RECTIFY = src/code/Rectify.java

all: editDistanceTest rectify

editDistanceTest: editDistanceTest_build
	$(MAKE) editDistanceTest_run

rectify: rectify_build
	$(MAKE) rectify_run


#Compile TestEditDistance.java
editDistanceTest_build: editDistance_build
	$(JC) $(JFLAGS) -cp $(JUnit_PATH) src/test/*.java -d "$(BUILD)"

#Compiles EditDistance.java
editDistance_build:
	$(JC) $(JFLAGS) $(EDIT) -d "$(BUILD)"

#Run Test for EditDistance
editDistanceTest_run:
	$(JVM) -cp "$(JUnit_PATH)" test.TestRunner


#Compiles Rectify.java
rectify_build: editDistance_build
	$(JC) $(JFLAGS) -cp $(BUILD) $(RECTIFY) -d "$(BUILD)"

#Run Rectify
rectify_run:
	$(JVM) -cp $(BUILD) code.Rectify


.PHONY: clean

clean:
	$(RM) -r $(BUILD)/*