# Food Delivery System - Backend PoC

This is an enterprise-grade backend Proof of Concept (PoC) for a high-traffic Food Delivery application. It demonstrates a polyglot persistence architecture, in-memory caching, and event-driven choreography to handle massive scale and performance.

## 🏗️ Tech Stack

*   **Java 17+ & Spring Boot:** Core application framework.
*   **PostgreSQL:** Handles strict, ACID-compliant order transactions.
*   **MongoDB:** Manages flexible, deeply-nested restaurant menus.
*   **Redis:** High-speed cache for read-heavy menu queries.
*   **Apache Kafka:** Event broker for decoupled, asynchronous order processing.
*   **Docker Compose:** Local infrastructure provisioning.

## 🚀 Architecture Overview

1.  **Polyglot Persistence:** The system bridges two database paradigms. `OrderService` (PostgreSQL) validates incoming requests against the `RestaurantService` (MongoDB) to lock in accurate pricing.
2.  **High-Performance Caching:** Restaurant and menu data lookups bypass MongoDB and are served in sub-milliseconds from Redis RAM.
3.  **Event-Driven Choreography:** To prevent long user wait times, successful checkouts are saved as `PENDING`, and an event is published to Kafka. Dedicated parallel consumer threads pick up the event to update the order to `CONFIRMED`, send emails, and notify the kitchen.

## 🛠️ Prerequisites

*   Java 17 or higher
*   Maven
*   Docker & Docker Compose

## ⚙️ How to Run Locally

1.  **Start the Infrastructure:**
    Navigate to the root directory and start the Kafka and Redis containers in the background:
    ```bash
    docker-compose up -d
    ```
2.  **Run the Spring Boot Application:**
    ```bash
    mvn spring-boot:run
    ```

## 📂 Project Structure
*   `/config` - Global infrastructure configurations (e.g., `KafkaConfig.java`).
*   `/order` - Order domain (Controllers, Services, PostgreSQL Repositories, Kafka Consumers).
*   `/restaurant` - Restaurant domain (Controllers, Services, MongoDB Repositories).
*   `docker-compose.yml` - Infrastructure definitions (Kafka KRaft mode, Redis).

## 📅 Future Roadmap
*   Expanding Kafka consumers for decoupled Email Notifications and Restaurant Kitchen systems.
*   Adding Dead Letter Queues (DLQ) for message failure resilience.
