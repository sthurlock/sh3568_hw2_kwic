# sh3568_hw2_kwic - Steve Hurlock for SE-575 

# Purpose
This program implements a Key Word in Context (KWIC) generator that takes a list of text strings from a file or console 
and creates a alphabetically sorted list with a line for each word in the input so the user can quickly find any keyword in the file.

This latest homework requires a number of options to be read from a configuration file and change the behavior using the Strategy and Decorator patterns.  

# Accessing the code/project 
unzip the zipfile provided from github or use the following command to pull from github:
git clone https://github.com/sthurlock/sh3568_hw2_kwic.git

# Input Options
The program expects 3 arguments as input:  
The program expects the following arguments:
  * input filename
  * output filename 
  * properties file (see below) to specify options.  It will use the filenames provided if file input and/or output is configured.
  If console input is configured, it should be on a single input line with multiple lines separated by pipe delimiter "|"
     for example: What is gooder than god,|more evil than the devil,|the rich need it,|the poor have it,|and if you eat it you will die?
                    
# Running the code
Use IntelliJ to open the directory that was created from the zip or git clone command.  Then in the terminal, run "mvn clean install" to build the project.

**Note:** to be able to read from the console in Intelli-J, make sure that the following option is set in the 
   goto Help/Edit Custom VM Options...
   add -Deditable.java.test.console=true

You can run the testcases provided in  **Test_MasterControl.java** to run the various options that can be configured.  There are several paramter files provided in the test/java/se575/kwic directory that are used by the testcases.

There is a test called testMasterControlConsole provided for testing the console input that has the @Test commented out.  This test can be used to test the console input and output.  It uses the console_test.properties file to get input.  

# Specifying Options in the properties file:

These 3 parameters configuring required parameters are select the correct behaviors, the rest are optional behavior modifications
```
# Required configurations:
input=se575.kwic.FileInput
output=se575.kwic.FileHeaderFooterOutput
sort=se575.kwic.AlphabetizerCaseInsensitive
```

You can also use these option behaviors or any combination:
```
# Required configurations:
input=se575.kwic.ConsoleInput
output=se575.kwic.ConsoleHeaderFooterOutput
sort=se575.kwic.AlphabetizerCaseSensitive
```

The options that can be specified are: 
```
# Options:
stopWords="and,is,the"              # if this parameter is present, the list of words will not appear in the first word of the output
addLineCountBefore=true             # if this parameter is present (regardless of value currently), a count of Lines will be added before the list
addLineCountAfter=true              # if present, Line count added after the list.  If both before and after are present, the before behavior will be used.
header="This is the header"         # if either header or footer or both are present, these strings will be added to the FILE output.
footer="Here is the footer"
```

# Notes on the Design and Testing
The design of this program has a top level object called LineStorage that is used read the input, create the Key Word In Context (KWIC) list, then write to the output destination specified.  It uses the strategy pattern to select the correct object for the behavior specified like reading/writing from the console or a file, and selecting how to do the circular shift (ignore stop words or not).  It also uses the Decorator pattern to add in functionality link adding a Line Count before or after the main list, adding a header and footer.


The selection of the behavior based on the Context for the strategy and decorator patterns is done in the LineStorage.configureOptions(parameterFile) method.  The selection uses the ObjectLoader code provided for two the strategy selections to load the specified class names.  These strategy objects are stored in the LineStorage instance *lines*.  There was a case where this wasn't working correctly and I'm using an if/then statement in the meantime.  The decoratoration is done with if/then statements based on the parameter file.  


A note on testing.  I still don't have the console interaction mocked yet.  I spent a bit of time trying to get classloader functionality working before the code was provided in the homework updated.  I took an initial approach of creating the parameter file as part of the test.  There was some timing issue with this approach where the test failed on the first run, but passed on the second.  I moved to using pre-configured parameter files.  I'd rather take the first approach and need to spend more time on debugging that.  In general, I feel that I need to add more testcases.  I tested the console input/output and options using the one console test that now has @Test commented so the other tests will run automatically. The tests are in src/test/java/Test_MasterControl.java

Thanks! Steve
