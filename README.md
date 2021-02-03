# sh3568_hw2_kwic - Steve Hurlock for SE-575 

# Purpose
This program implements a Key Word in Context (KWIC) generator that takes a list of text strings from a file or console 
and creates a alphabetically sorted list with a line for each word in the input so the user can quickly find any keyword in the file.

# Download from github
git clone https://github.com/sthurlock/sh3568_hw2_kwic.git

# Input Options
The program expects 2 arguments as input:  
 1. Input  - filename string with the filename or path and filename.
           - if this argument is null or "null", the program will read input from the console.  
             it expects a single input line with multiple lines separated by pipe delimiter "|"
             for example: What is gooder than god,|more evil than the devil,|the rich need it,|the poor have it,|and if you eat it you will die?
 2. Output - filename string with the filename or path and filename to write the output.
           - if this argument is null or "null", the program will write the output to the console.
                    
# Running the code
Use IntelliJ to open the directory that was created.  Then in the terminal, run "mvn clean install" to build the project.

You can run the testcase **testMasterControlWithFileInputAndOutput** to run the test with the input and expected output provided in the home work.

The input file is test/resources/input.txt and the expected output is in test/resources/expected_output.txt


Another option is set the input parameters by right clicking on src/main/java/MasterControl, selecting More Run/Debug --> Modify Run Config and entering:
   test/resources/input.txt output.txt in the text box for command line arguments to run the input file provided.

To enter the input file from the console:

   Edit the command line parameters and substitue null where the console should be used, for example:
   * test/resources/input.txt null    # read from text file, write to console
   * null output.txt                  # read from console, write to output.txt
   * null null                        # read from console, write output to console

# Comments
The design of this program is based on the proposed modular solution presented in the paper *On the Criteria To Be Used in Decomposing Systems into Modules* by D. L. Parnas.  I created a class for each of the modules that he described.  I decided to instantiate each object in the MastControl main method.  I created 3 LineStorage objects to hold the results of the input, circular shift, and alphabetizer functions.  I ended up not having state in any of the funtions except for LineStorage which creates internal arrays once the number of lines needed is known.  I don't think this is a great design.  I think I would have a single class that has the linestorage arrays as properites and have a method for each function that operates on that state.  

I also wrote it like this to make it easy to test.  I wrote unit tests that created the input LineStorage object and an empty Linestorage object for the result to be verified.  I attempted to write a test to mock the console input, but was not able to get that fully working with mockito so that is commented out for now.  The test that reads input from the console and outputs to a file is also commented out so the tests run without needed input to be entered.  This was my first time working with java unit tests.  The tests are in src/test/java/Test_MasterControl.java
