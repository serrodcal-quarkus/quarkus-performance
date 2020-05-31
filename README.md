# quarkus-performance

![](/img/quarkus.png)

This project compares different flavors of Quarkus, the Supersonic Subatomic Java Framework:

* [Quarkus application](https://quarkus.io/guides/getting-started), with RESTEasy and no reactive.
* [Quarkus Reactive application](https://quarkus.io/guides/getting-started-reactive), with RESTEasy and Mutiny.
* [Quarkus Reactive Routes application](https://quarkus.io/guides/reactive-routes), with Vert.x and Mutiny.
* [Quarkus Asynchronous Messaging application](https://quarkus.io/guides/reactive-messaging), with Vert.x.

## Environment info

MacBook Pro with Mojave (10.14.6), 2,6 GHz Intel Core i7  and 32 GB 2400 MHz DDR4.

Running on JVM with Java 11.

## Test definition

1. 10 threads with 10,000 concurrent users by 60 seconds with WRK.
2. Using all cores available with Vegeta.

## Results

* Quarkus application:

  ![](/img/quarkus-app.png)

  ```
  $ wrk -t10 -c10000 -d60s http://localhost:8080/greeting/Sergio
  Running 1m test @ http://localhost:8080/greeting/Sergio
    10 threads and 10000 connections
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency    10.51ms    5.18ms 229.85ms   79.13%
      Req/Sec     2.31k     1.90k    9.09k    75.32%
    1370619 requests in 1.00m, 116.33MB read
    Socket errors: connect 9759, read 110, write 0, timeout 0
  Requests/sec:  22809.07
  Transfer/sec:      1.94MB
  ```

  ```
  $ echo "GET http://localhost:8080/greeting/Sergio" | vegeta attack -duration=60s  | tee results.bin | vegeta report
  Requests      [total, rate, throughput]         3000, 50.01, 50.01
  Duration      [total, attack, wait]             59.983s, 59.983s, 437.108µs
  Latencies     [min, mean, 50, 90, 95, 99, max]  279.611µs, 803.354µs, 910.966µs, 1.014ms, 1.052ms, 1.154ms, 6.669ms
  Bytes In      [total, mean]                     30000, 10.00
  Bytes Out     [total, mean]                     0, 0.00
  Success       [ratio]                           100.00%
  Status Codes  [code:count]                      200:3000  
  Error Set:
  ```

* Quarkus Reactive application:

  ![](/img/quarkus-reactive.png)

  ```
  $ wrk -t10 -c10000 -d60s http://localhost:8080/greeting/Sergio
  Running 1m test @ http://localhost:8080/greeting/Sergio
    10 threads and 10000 connections
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency    10.92ms    5.69ms 291.10ms   82.44%
      Req/Sec     2.22k     2.09k    8.82k    80.90%
    1319815 requests in 1.00m, 112.02MB read
    Socket errors: connect 9759, read 79, write 0, timeout 0
  Requests/sec:  21964.23
  Transfer/sec:      1.86MB
  ```

  ```
  $ echo "GET http://localhost:8080/greeting/Sergio" | vegeta attack -duration=60s  | tee results.bin | vegeta report
  Requests      [total, rate, throughput]         3000, 50.01, 50.01
  Duration      [total, attack, wait]             59.985s, 59.984s, 903.105µs
  Latencies     [min, mean, 50, 90, 95, 99, max]  257.081µs, 617.067µs, 546.573µs, 985.425µs, 1.045ms, 1.131ms, 4.913ms
  Bytes In      [total, mean]                     30000, 10.00
  Bytes Out     [total, mean]                     0, 0.00
  Success       [ratio]                           100.00%
  Status Codes  [code:count]                      200:3000  
  Error Set:
  ```

* Quarkus Reactive Routes application:

  ![](/img/quarkus-reactive-routes.png)

  ```
  $ wrk -t10 -c10000 -d60s http://localhost:8080/greeting/Sergio
  Running 1m test @ http://localhost:8080/greeting/Sergio
    10 threads and 10000 connections
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency     8.98ms    4.63ms 178.64ms   78.56%
      Req/Sec     2.70k     2.31k    9.57k    59.12%
    1604857 requests in 1.00m, 114.79MB read
    Socket errors: connect 9759, read 76, write 0, timeout 0
  Requests/sec:  26712.57
  Transfer/sec:      1.91MB
  ```

  ```
  $ echo "GET http://localhost:8080/greeting/Sergio" | vegeta attack -duration=60s  | tee results.bin | vegeta report
  Requests      [total, rate, throughput]         3000, 50.02, 50.02
  Duration      [total, attack, wait]             59.977s, 59.977s, 626.641µs
  Latencies     [min, mean, 50, 90, 95, 99, max]  186.052µs, 544.216µs, 613.667µs, 700.246µs, 728.119µs, 813.4µs, 4.426ms
  Bytes In      [total, mean]                     30000, 10.00
  Bytes Out     [total, mean]                     0, 0.00
  Success       [ratio]                           100.00%
  Status Codes  [code:count]                      200:3000  
  Error Set:
  ```

* Quarkus Asynchronous Messaging application:

  ![](/img/quarkus-async-messaging.png)

  ```
  $ wrk -t10 -c10000 -d60s http://localhost:8080/greeting/Sergio
  Running 1m test @ http://localhost:8080/greeting/Sergio
    10 threads and 10000 connections
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency    11.78ms    6.22ms 253.20ms   90.44%
      Req/Sec     2.06k     1.94k    9.11k    81.71%
    1223825 requests in 1.00m, 103.87MB read
    Socket errors: connect 9759, read 75, write 0, timeout 0
  Requests/sec:  20363.03
  Transfer/sec:      1.73MB
  ```

  ```
  $ echo "GET http://localhost:8080/greeting/Sergio" | vegeta attack -duration=60s  | tee results.bin | vegeta report
  Requests      [total, rate, throughput]         3000, 50.02, 50.02
  Duration      [total, attack, wait]             59.98s, 59.979s, 481.444µs
  Latencies     [min, mean, 50, 90, 95, 99, max]  354.26µs, 887.959µs, 1.017ms, 1.136ms, 1.167ms, 1.247ms, 5.489ms
  Bytes In      [total, mean]                     30000, 10.00
  Bytes Out     [total, mean]                     0, 0.00
  Success       [ratio]                           100.00%
  Status Codes  [code:count]                      200:3000  
  Error Set:
  ```
