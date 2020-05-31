# quarkus-performance

This project compares different flavors of Quarkus, the Supersonic Subatomic Java Framework:

* [Regular Quarkus application](https://quarkus.io/guides/getting-started), with RESTEasy and no reactive.
* [Reactive Quarkus application](https://quarkus.io/guides/getting-started-reactive), with RESTEasy and Mutiny.
* [Reactive Routes Quarkus application](https://quarkus.io/guides/reactive-routes), with Vert.x and Mutiny.
* [Asynchronous Messaging Quarkus application](https://quarkus.io/guides/reactive-messaging), with Vert.x.

## Environment info

MacBook Pro with

## Test definition

10 threads with 10,000 concurrent users by 60 seconds.

## Results
