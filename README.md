# math-lang
Basic Math Language

## Running
download from releases and run `java -jar math-lang.jar`

or

just run `Main` found in `org`
## Features
### Basic Math Functions
```
+ 1 2
=> 3

* 3 4
=> 12

exp 1
=> 2.718281828459045

ln 1
=> 0
```
### Defining Symbols
```
e
=> 2.718281828459045
tau
=> 6.283185307179586

def A 123
=> 123
A
=> 123
+ A 1
=> 124
```
### Higher-Order Functions
```
sum 1 5 identity
=> 15
sum 1 5 ln
=> 4.787491742782046

product 1 5 identity
=> 120
product 1 5 ln
=> 0
```
