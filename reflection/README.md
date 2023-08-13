# Small Utility Projects

Kind of Utlilty project

## Reflection

- It is useful whenever we want to modify any member or methods at runtime.
- It breaks all the rules of Java.
- Setting accessibility to true is important if we want to access private members and methods of a
  class.

````
setAccessible(true);
````

- We can pass 'null' or objectName when invoking a static method

````
method.invoke(null);
or
method.invoke(objName);
````

## Precautions:

- We use variable and method name as String in reflection so **modifying a method name or variable
  name** can lead to unexpected behavior.
