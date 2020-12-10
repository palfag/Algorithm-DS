JFLAGS = -Xlint
JC = javac
JVM = java


JUnit_PATH = ../Resources/Java/JUnit/junit-4.12.jar:../Resources/Java/JUnit/hamcrest-core-1.3.jar:$(BUILD)
BUILD = out
Es3 = ../es_3/src/
Es4 = src/

all: graphTest kruskal

kruskal: kruskal_build
	$(MAKE) kruskal_run

graphTest: graphTest_build
	$(MAKE) graphTest_run

#Kruskal Compile
kruskal_build: graph unionFind
	$(JC) $(JFLAGS) -cp $(BUILD) $(Es4)code/Kruskal.java -d "$(BUILD)"

#Kruskal Run
kruskal_run:
	$(JVM) -cp "$(BUILD)" code.Kruskal


#Compile UnionFindSet
unionFind:
	$(JC) $(JFLAGS) $(Es3)code/Node.java $(Es3)code/UnionFindSet.java -d "$(BUILD)"


#Compile Graph
graph:
	$(JC) $(JFLAGS) $(Es4)code/Edge.java  $(Es4)code/Graph.java -d "$(BUILD)"


graphTest_build: graph
	$(JC) $(JFLAGS) -cp $(JUnit_PATH) $(Es4)test/*.java -d "$(BUILD)"


graphTest_run:
	$(JVM) -cp "$(JUnit_PATH)" test.GraphTest


.PHONY: clean

clean:
	$(RM) -r $(BUILD)/*