Login User Activity to Cassandra
================================

## Build
```
$ sbt package
$ sbt "run 172.31.2.180 1000000"

```



To run locally

    cd vagrant-ansible-cassandra/
    vagrant up
    sbt "runMain com.mdinsider.LoggingApp dev 127.0.0.1 2551"
    
To run staging

    sbt "runMain com.mdinsider.LoggingApp staging 127.0.0.1 2551"
    
    
