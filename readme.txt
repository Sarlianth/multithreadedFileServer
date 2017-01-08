Multithreaded File Server
Adrian Sypos – G00309646

	This project is a multi-threaded file server and logging application that allows a client application to download files using a set of options presented in a terminal user interface.
	I am using java.util.Scanner object to read in console input from the user, using a while loop to keep the client application alive between interactions. Option (1) opens a socket connection to the server, option (2) queries the server and display the list of files that are available for download, option (3) prompts the user to specify a file from this list and then download the file from the server. Option (4) causes the while loop to exit.
	When the client application is started, it parses an XML file using the Document Object Model (DOM) and extract from it the set of values required to interact with the remote server.
	Every request received by the server is being added to a blocking queue by the thread and logged in a text file.
	I am unsure about the oop.jar if I have compiled it the right way (I have used the command provided by you) it worked once or twice, then I kept getting errors and I couldn’t figure out if it’s my computer or I messed it up.

•	I had the readme file along with the UML diagram saved on the laptop in which the hard drive got faulty, so I had to do it again, just before the submission.. Sorry for the low quality of the both readme and the diagram..
