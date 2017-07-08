--- Create Tables ---

USE activity;

DROP TABLE IF EXISTS rawlog;

CREATE TABLE activity.rawlog (
    customer_id text,
    period timeuuid,
    agent text,
    doctor text,
    language text,
    os text,
    request_type text,
    search_term text,
    session_id text,
    specialty text,
    success text,
    url text,
    user_id text,
    user_ip text,
    user_lat text,
    user_long text,
    PRIMARY KEY (customer_id, period)
) WITH CLUSTERING ORDER BY (period DESC);


) WITH CLUSTERING ORDER BY ( period DESC)
;
