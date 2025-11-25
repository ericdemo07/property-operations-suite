Design Patterns Implemented in user-service

1. Builder Pattern

* Description: A creational design pattern that allows for the step-by-step construction of complex objects. The final object is
  retrieved from the builder at the last step.
* How it helps in `user-service` (`User` class):
    * Readability & Maintainability: The User object has many fields. Using a constructor with many parameters would be error-prone
      and hard to read (telescoping constructor anti-pattern). The Builder pattern makes object creation clear and expressive by
      allowing you to set properties one by one with descriptive method names (firstName(), email(), etc.).
    * Immutability: The User object itself is immutable (final fields), which is a good practice for thread safety and predictable
      state. The Builder allows constructing this immutable object incrementally before it's finalized.
    * Optional Parameters: It elegantly handles optional parameters, allowing users to set only the fields they need without
      requiring multiple constructors.

2. Strategy Pattern

* Description: A behavioral design pattern that defines a family of algorithms, encapsulates each one, and makes them
  interchangeable. Strategy lets the algorithm vary independently from clients that use it.
* How it helps in `user-service` (`UserCreationStrategy`, `DefaultUserCreationStrategy`, etc.):
    * Flexibility & Extensibility (Open/Closed Principle): The UserService (or UserResource) no longer needs to know the exact logic
      for how a user is created. It delegates this responsibility to a UserCreationStrategy. If new user creation methods are needed
      (e.g., social login, admin-privileged creation), you can add new concrete strategy classes without modifying the core
      UserService code.
    * Separation of Concerns (Single Responsibility Principle): Each strategy focuses solely on its specific user creation logic,
      keeping the UserService clean and focused on orchestrating business operations rather than knowing the intricate details of
      each creation variant.

3. Repository Pattern

* Description: An architectural pattern that mediates between the domain and data mapping layers, acting like an in-memory collection
  of domain objects. It encapsulates the set of objects and the operations performed over them, providing a more object-oriented view
  of the persistence layer.
* How it helps in `user-service` (`UserRepository`, `InMemoryUserRepository`):
    * Abstraction of Data Access: The UserService interacts with the UserRepository interface, completely unaware of how the data is
      actually stored (e.g., in-memory, database, file system). This significantly decouples the business logic from the persistence
      implementation.
    * Testability: Because the UserService depends on an interface (UserRepository), it's easy to mock or substitute different
      repository implementations for testing purposes without needing a real database connection.
    * Flexibility for Storage Changes: If you decide to switch from in-memory storage to a relational database (like PostgreSQL) or a
      NoSQL database, you only need to create a new UserRepository implementation (e.g., JdbcUserRepository) without altering the
      UserService.

4. Decorator Pattern

* Description: A structural design pattern that allows behavior to be added to an individual object, either statically or
  dynamically, without affecting the behavior of other objects from the same class. It wraps an object and provides the same
  interface to add functionality.
* How it helps in `user-service` (`LoggingUserRepositoryDecorator`):
    * Adding Responsibilities Dynamically (Open/Closed Principle): We were able to add logging capabilities to the UserRepository
      (specifically InMemoryUserRepository) without modifying the original InMemoryUserRepository code. This adheres to the
      Open/Closed Principle: InMemoryUserRepository is closed for modification but open for extension.
    * Separation of Concerns: Cross-cutting concerns like logging are kept separate from the core data access logic. The
      InMemoryUserRepository remains focused solely on storing and retrieving users, while the LoggingUserRepositoryDecorator handles
      logging.
    * Composability: Multiple decorators can be stacked to add various behaviors (e.g., caching, security, transaction management) in
      a flexible and modular way.

5. Command Pattern

* Description: A behavioral design pattern that encapsulates a request as an object, thereby letting you parameterize clients with
  different requests, queue or log requests, and support undoable operations.
* How it helps in `user-service` (`UserCommand`, `CreateUserCommand`):
    * Decoupling Invoker from Receiver: The UserResource (the invoker) no longer directly calls methods on the UserService (the
      receiver). Instead, it creates a CreateUserCommand object and calls its execute() method. This reduces coupling and makes
      UserResource more generic.
    * Abstraction of Actions: Each action (like creating a user) becomes a first-class object. This can be beneficial for scenarios
      like maintaining a history of operations, implementing undo/redo functionality, or queuing commands for asynchronous processing
      (though not fully implemented in our example, the foundation is there).
    * Extensibility: Adding new actions (e.g., UpdateUserCommand, DeleteUserCommand) involves creating new UserCommand
      implementations, without changing the UserResource or the UserCommand interface.

6. Factory Method Pattern

* Description: A creational design pattern that defines an interface for creating an object, but lets subclasses decide which class
  to instantiate. The Factory Method lets a class defer instantiation to subclasses.
* How it helps in `user-service` (`UserStrategyFactory`, `DefaultUserStrategyFactory`):
    * Decoupling Client from Concrete Implementations: UserApplicationRunner (the client) now depends on the UserStrategyFactory
      interface to obtain a UserCreationStrategy. It no longer directly instantiates DefaultUserCreationStrategy. This means if you
      introduce a new type of UserCreationStrategy or need to switch strategies based on some configuration, only the factory
      implementation needs to change, not the client code.
    * Centralized Creation Logic: If the process of creating a UserCreationStrategy becomes more complex (e.g., needing to inject
      dependencies into the strategy itself), the factory encapsulates this complexity in one place.
    * Configurability: You could have different factory implementations (e.g., an AdminStrategyFactory or a SocialStrategyFactory)
      that produce different concrete strategies, which can be configured at runtime or compile time, making the system more
      adaptable.
