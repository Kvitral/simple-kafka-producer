# simple-kafka-producer

Simple infinite stream passing random ints to kafka as downstream

## Install Kafka
You need to setup and start Kafka [using quickstart guide]("https://kafka.apache.org/quickstart")
Don\`t forget to add `advertised.host.name= %your_vm_ip%` to `../config/server-properties` inside your virtual machine if you will run kafka from it 

## How to run it

Simply run sbt with command
```
sbt assembly
```

##How to run it

You can compiled jar like any other jar
`java -Dkafka.bootstrap-servers=localhost:9092 -jar kafka-producer.jar`

Full list of posibile parameters can be found in `../main/resources/application.conf` in `kafka` section
