--- Create Keyspaces ---

DROP KEYSPACE IF EXISTS activity;

CREATE KEYSPACE activity WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 3 };

DESCRIBE KEYSPACES;
