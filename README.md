# QBasic-Interpreter
Simple QBasic Interpreter using JavaCC

Note: the interpreter has some issues like:
- if you are reading the code from a text file and the last line of the code has no new line after it 
  then the interpreter will give you an error that is not expected token <EOF>, 
  but this can be handled by just adding new line after the last line of your code.
