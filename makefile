JFLAGS = -g
JC = javac
JVM = java
FILE =
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Main.java \
	Menu.java \
	Product.java \
	InventoryManager.java

MAIN = Main

default: classes

classes: $(CLASSES:.java=.class)

run: classes
	$(JVM) $(MAIN)

clean:
	$(RM) *.class
