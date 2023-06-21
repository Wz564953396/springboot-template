# 一、maxwell选型
从v1.30.0版本开始，需要jdk11才能运行


# 二、部署

### 1.下载解压安装包
```shell
cd /usr/local
```
```shell
mkdir maxwell
```
```shell
cd maxwell
```
```shell
wget https://github.com/zendesk/maxwell/releases/download/v1.29.2/maxwell-1.29.2.tar.gz
```
```shell
tar zxvf maxwell-1.29.2.tar.gz
```
```shell
cd cd maxwell-1.29.2/
```

### 2.修改配置文件
```shell
cp config.properties.example config.properties
```
```shell
vim config.properties
```
```properties
# tl;dr config

# mysql login info

#     *** general ***
# choose where to produce data to. stdout|file|kafka|kinesis|pubsub|sqs|rabbitmq|redis|bigquery
producer=kafka

# set the log level.  note that you can configure things further in log4j2.xml
# [DEBUG, INFO, WARN, ERROR]
log_level=INFO

# if set, maxwell will look up the scoped environment variables, strip off the prefix and inject the configs
#env_config_prefix=MAXWELL_

#     *** mysql ***

# mysql host to connect to
host=10.1.10.43

# mysql port to connect to
port=43306

# mysql user to connect as.  This user must have REPLICATION SLAVE permissions,
# as well as full access to the `maxwell` (or schema_database) database
user=root

# mysql password
password=6%3aogdP6YhAG3B

#       *** kafka ***

# list of kafka brokers
kafka.bootstrap.servers=10.1.10.43:19092

# kafka topic to write to
# this can be static, e.g. 'maxwell', or dynamic, e.g. namespace_%{database}_%{table}
# in the latter case 'database' and 'table' will be replaced with the values for the row being processed
kafka_topic=maxwell

# alternative kafka topic to write DDL (alter/create/drop) to.  Defaults to kafka_topic
ddl_kafka_topic=maxwell_ddl

# a few defaults.
# These are 0.11-specific. They may or may not work with other versions.
kafka_version=2.11-2.0.0
kafka.compression.type=snappy
kafka.retries=0
kafka.acks=1
#kafka.batch.size=16384

#          *** filtering ***

# filter rows out of Maxwell's output.  Command separated list of filter-rules, evaluated in sequence.
# A filter rule is:
#  <type> ":" <db> "." <tbl> [ "." <col> "=" <col_val> ]
#  type    ::= [ "include" | "exclude" | "blacklist" ]
#  db      ::= [ "/regexp/" | "string" | "`string`" | "*" ]
#  tbl     ::= [ "/regexp/" | "string" | "`string`" | "*" ]
#  col_val ::= "column_name"
#  tbl     ::= [ "/regexp/" | "string" | "`string`" | "*" ]
#
# See http://maxwells-daemon.io/filtering for more details
#
#filter= exclude: *.*, include: foo.*, include: bar.baz, include: foo.bar.col_eg = "value_to_match"
filter=exclude:*.*, include:ehaclink.*,include:ehcore.*,include:ehcorebase.*,include:ehcustomsp.*,include:ehecommerce.*,include:ehparking.*,include:ehpromotion.*,include:ehpropertymgr.*,include:ehrealty.*,include:ehuser.*

```

### 3.启动
```shell
./bin/maxwell --config config.properties --daemon
```

