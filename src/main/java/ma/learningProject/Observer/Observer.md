# Observer Design Pattern Example

## Introduction

The Observer design pattern is a behavioral design pattern used to create a one-to-many dependency between objects. When one object (the subject) changes its state, all its dependent objects (observers) are notified and updated automatically. This pattern is particularly useful when you need to ensure consistency across multiple objects without tightly coupling them.

## Key Components

1. **Subject**: The object that maintains a list of its dependents (observers) and notifies them of any state changes.
2. **Observer**: The object that gets updated whenever the subject's state changes.
3. **ConcreteSubject**: The implementation of the subject, which includes methods for adding, removing, and notifying observers.
4. **ConcreteObserver**: The implementation of the observer, which defines how it reacts to state changes.

## Example Overview

In this example, the **Shop Owner** acts as the **Subject**, and **Clients** act as **Observers**. A server facilitates the connection between shop owners and clients but is not part of the Observer pattern. The shop owner adds products, and all subscribed clients are notified of the new products in real-time.

### Components in the Example

- **Shop Owner**: The subject who adds products and notifies clients.
- **Client**: Represents a client that subscribes to product notifications.
- **NotifyClient**: An interface for managing subscriptions and notifying observers.
- **NotifyClientsImpl**: The concrete implementation of the notification logic.
- **Server**: Manages connections but is not part of the Observer pattern.
- **ProductService**: Manages product creation.

## How It Works

1. **Client Subscription**:
   - Clients connect to the server and create accounts.
   - The server subscribes the client to the notification service.
   - Clients are added to the list of observers.

2. **Shop Owner Adds Products**:
   - The shop owner logs in and adds new products.
   - The notification service notifies all subscribed clients of the new product.

3. **Notification**:
   - When a new product is added, the `notifyClients` method is invoked, and each client's `update` method is called to deliver the notification.
