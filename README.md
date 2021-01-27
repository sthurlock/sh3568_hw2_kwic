# Hurlock_HW1_KWIC for SE-575

# Purpose
This program implements a Key Word in Context (KWIC) generator that takes a list of text strings from a file or console 
and creates a alphabetically sorted list with a line for each word in the input so the user can quickly find any keyword in the file.

# Download from github
git clone https://github.com/sthurlock/Hurlock_HW1_KWIC.git

# Input Options
The program expects 2 arguments as input:  
 1. source of input - filename string with the filename or path and filename.
                    - if this argument is null, the program will read input from the console.  
                      it expects a single input line with multiple lines separated by pipe delimiter "|"
                      for example: What is gooder than god,|more evil than the devil,|the rich need it,|the poor have it,|and if you eat it you will die?
 2. output          - filename string with the filename or path and filename to write the output.
                    - if this argument is null, the program will write the output to the console.
                    
# Running the code
Use IntelliJ to open the directory that was created.  
You can run the testcases which test each module and then run the test with the input and expected output provided in the home work.
Another option is set the input parameters by right clicking on src/main/java/MasterControl, selecting More Run/Debug --> Modify Run Config and entering:
   test/resources/input.txt out1.txt is the text box for command line arguments to run the input file provided.

To enter the input file from the console:
** update **

# Background
the design of this program is based on the proposed modular solution presented in the paper 

