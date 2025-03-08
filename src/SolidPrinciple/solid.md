# SOLID Principle

**S - Single Responsibility Principle**  
A class should have only one reason to change, meaning it should have only one job or responsibility.

**O - Open/Closed Principle**  
Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification. This means that the behavior of a module can be extended without modifying its source code.

**L - Liskov Substitution Principle**  
If class B is a subtype of class A, then we should be able to replace an object of class A with an object of class B without breaking the behaviour of the program. Subclass should extend the capability of parent class not narrow it down.

**I - Interface Segregation Principle**  
No client should be forced to depend on methods it does not use. Instead of one large interface, it's better to have several smaller, more specific interfaces.

**D - Dependency Inversion Principle**  
High-level modules should not depend on low-level modules. Both should depend on abstractions. Additionally, abstractions should not depend on details. Details should depend on abstractions. Class should depend on interfaces rather than concrete class.
