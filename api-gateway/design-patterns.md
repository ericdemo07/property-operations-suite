1. Chain of Responsibility Pattern

* Description: A behavioral design pattern that allows you to pass requests along a chain of handlers. Each handler
  decides either to
  process the request or to pass it to the next handler in the chain.
* How it helps in `api-gateway` (`RequestProcessor` interface, `LoggingRequestProcessor`,
  `AuthenticationRequestProcessor`):
    * Decoupling Senders from Receivers: The ApiGatewayTestRunner (acting as a client/sender of requests) doesn't need
      to know which
      specific processor will handle the request, or even the full sequence of processors. It simply initiates the chain
      with the
      first handler.
    * Flexible Request Processing (Open/Closed Principle): As an API Gateway, incoming requests often require multiple
      steps like
      logging, authentication, authorization, rate limiting, etc. This pattern allows you to add, remove, or reorder
      these processing
      steps (handlers) dynamically without modifying the core gateway or existing handlers. You can extend the
      processing behavior
      without altering existing code.
    * Separation of Concerns (Single Responsibility Principle): Each RequestProcessor implementation (e.g.,
      LoggingRequestProcessor,
      AuthenticationRequestProcessor) has a single, well-defined responsibility. This makes the code cleaner, easier to
      understand,
      test, and maintain.

2. Factory Method Pattern (for RequestProcessors)

* Description: A creational design pattern that defines an interface for creating an object, but lets subclasses decide
  which class
  to instantiate.
* How it helps in `api-gateway` (`RequestProcessorFactory`, `DefaultRequestProcessorFactory`):
    * Decoupling Object Creation from Usage (Dependency Inversion Principle): The ApiGatewayTestRunner now depends on
      the
      RequestProcessorFactory interface to get RequestProcessor instances, rather than directly instantiating
      LoggingRequestProcessor
      or AuthenticationRequestProcessor. This reduces coupling between the client and the concrete processor
      implementations.
    * Centralized and Configurable Creation: If the creation logic for processors becomes complex (e.g., needing to
      inject specific
      configurations into each processor), the factory encapsulates this logic in one place. You can also imagine
      different factory
      implementations providing different sets or configurations of processors based on environment or feature flags.
    * Extensibility: Adding new types of RequestProcessors (e.g., for rate limiting) can be done by extending the
      factory interface
      and its implementations, without modifying the client code that uses the factory.

3. Strategy Pattern (for Target URL Resolution)

* Description: A behavioral design pattern that defines a family of algorithms, encapsulates each one, and makes them
  interchangeable. Strategy lets the algorithm vary independently from clients that use it.
* How it helps in `api-gateway` (`TargetUrlResolverStrategy`, `EurekaUrlResolverStrategy`):
    * Decoupling Service Discovery Logic: An API Gateway's core function is to route requests. The method by which it
      discovers the
      target service's actual URL can vary (e.g., Eureka, static configuration, DNS lookup, Consul). The Strategy
      pattern abstracts
      this variability behind the TargetUrlResolverStrategy interface. The routing component (simulated in
      ApiGatewayTestRunner)
      depends on this abstraction, not a concrete discovery mechanism.
    * Flexibility and Extensibility (Open/Closed Principle): If the service discovery mechanism changes (e.g., migrating
      from Eureka
      to a different system), you can introduce a new TargetUrlResolverStrategy implementation without altering the core
      routing
      logic.
    * Improved Testability: During testing, you can easily inject different strategy implementations (e.g., a mock
      strategy that
      always returns a fixed URL) to isolate and test the routing behavior without needing a live service discovery
      server.

4. Decorator Pattern (for Caching Target URL Resolution)

* Description: A structural design pattern that allows behavior to be added to an individual object, either statically
  or
  dynamically, without affecting the behavior of other objects from the same class. It wraps an object and provides the
  same
  interface to add functionality.
* How it helps in `api-gateway` (`CachedUrlResolverDecorator`):
    * Adding Functionality Transparently (Open/Closed Principle): We added caching capabilities to
      TargetUrlResolverStrategy
      (specifically EurekaUrlResolverStrategy) without modifying the original strategy's code. This means
      EurekaUrlResolverStrategy
      remains focused purely on Eureka interaction, while caching is handled externally.
    * Separation of Concerns: The responsibility of resolving a URL from Eureka is separate from the responsibility of
      caching that
      resolution. This leads to cleaner, more modular code.
    * Composability: Decorators can be chained. You can imagine adding other decorators like
      RateLimitingUrlResolverDecorator or
      LoggingUrlResolverDecorator to further enhance the URL resolution process without bloating the core
      EurekaUrlResolverStrategy.
      This allows for highly flexible and dynamic composition of features.
