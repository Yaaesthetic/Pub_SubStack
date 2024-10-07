# ChatApp Project

This project is a Spring Boot-based application that integrates with Apache Kafka for message production and consumption. It utilizes Docker and Docker Compose for easy service orchestration. The application programmatically creates a Kafka topic and communicates with Kafka using Kafka producer and consumer services.

## Project Structure

```
├── src/
│   └── main/
│       └── java/
│           └── ma/
│               └── chatapp/
│                   ├── component/
│                   │   └── KafkaCommandLineRunner.java
│                   ├── config/
│                   │   ├── KafkaConfig.java
│                   │   ├── KafkaConsumerConfig.java
│                   │   ├── KafkaProducerConfig.java
│                   │   └── KafkaTopicConfig.java
│                   ├── service/
│                   │   ├── KafkaConsumer.java
│                   │   └── KafkaProducer.java
│                   └── ChatappApplication.java
├── Dockerfile
├── compose.yaml
├── .dockerignore
└── .gitignore
```

### Key Components

1. **KafkaCommandLineRunner.java**: A `CommandLineRunner` that sends a test message to the Kafka topic `"chat-room"` when the Spring Boot application starts.

2. **KafkaConfig.java**: General configuration for Kafka, setting up Kafka templates and message converters.

3. **KafkaConsumerConfig.java**: Defines Kafka consumer properties, such as the consumer group and how the application listens to Kafka topics.

4. **KafkaProducerConfig.java**: Defines Kafka producer properties, configuring the producer's behavior for sending messages to Kafka.

5. **KafkaTopicConfig.java**: Configures Kafka topics programmatically using Spring's `KafkaAdmin` and `NewTopic`. The application creates a topic named `"chat-room"` with one partition and one replica.

6. **KafkaConsumer.java**: Listens to messages from the Kafka topic `"chat-room"`. Whenever a message is produced to the topic, it logs and prints the received message to the console.

7. **KafkaProducer.java**: Sends messages to the Kafka topic `"chat-room"`. You can use this service to send custom messages to Kafka.

## Kafka Topic Creation

The topic `"chat-room"` is created programmatically using the `KafkaTopicConfig.java` class. It uses Spring's `KafkaAdmin` to configure Kafka and `TopicBuilder` to define the topic's properties:
- **Name**: `chat-room`
- **Partitions**: 1
- **Replicas**: 1

## Docker Compose Configuration

The `compose.yaml` file orchestrates the services required for running the ChatApp:

- **chat_app**: The Spring Boot application that communicates with Kafka.
- **zookeeper**: Zookeeper service to manage Kafka brokers.
- **kafka**: Kafka broker service where messages are produced and consumed.

### Key Docker Compose Services

- **chat_app**:
  - **Build context**: Uses the Dockerfile in the root directory to build the Spring Boot app.
  - **Ports**: Exposes the Spring Boot application on `8080`.
  - **Environment variables**:
    - `SPRING_KAFKA_BOOTSTRAP_SERVERS`: Points to the Kafka container (`kafka:9093`).
    - `SPRING_KAFKA_CONSUMER_GROUP_ID`: Specifies the Kafka consumer group as `chat-group`.

- **kafka**:
  - **Image**: Uses the latest Kafka image.
  - **Ports**: Kafka broker is exposed on `9092` for external connections and `9093` for internal services.
  - **Environment**:
    - Configures the Kafka listeners and Zookeeper connection.

## Getting Started

### Prerequisites

- Docker
- Docker Compose

### Build and Run

1. Clone the repository:
    ```bash
    git clone <repository-url>
    cd <repository-directory>
    ```

2. Build and start the services using Docker Compose:
    ```bash
    docker-compose up --build
    ```

3. Once the containers are running, the Spring Boot app will start and send a message `"Hello Kafka!"` to the Kafka topic `"chat-room"`.

4. The KafkaConsumer will listen to the `"chat-room"` topic and print any messages received to the console.

### Access the Application

- **Spring Boot Application**: `http://localhost:8080`
- **Kafka Broker**: `localhost:9092`

### Send and Receive Messages

- Use the `KafkaProducer` service to send custom messages to the Kafka topic `chat-room`. The `KafkaConsumer` will receive and log the messages.

### Stop the Services

To stop and remove the containers, run:
```bash
docker-compose down
```

## Customizing the Kafka Setup

You can modify the topic, partitions, or replication factor in the `KafkaTopicConfig.java` file.

- To change the topic name, update the following:
  - `KafkaTopicConfig.java`: Update the `chat-room` topic name.
  - `KafkaCommandLineRunner.java`: Modify the topic name in the `run` method.
  - `KafkaProducer.java` and `KafkaConsumer.java`: Update the topic name in the producer and listener methods.

## Useful Docker Commands

- List running containers:
    ```bash
    docker ps
    ```
- View logs for a specific service:
    ```bash
    docker logs -f <container_name>
    ```
