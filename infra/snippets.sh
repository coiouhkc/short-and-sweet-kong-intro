#!/bin/bash

# main-service + route
curl -i -X POST --url http://localhost:8001/services --data 'name=main-service' --data 'url=http://main-service:8080'
curl -i -X POST --url http://localhost:8001/services/main-service/routes --data 'hosts[]=main.svc'

curl -i -X GET --url http://localhost:8001/services/main-service | jq '.'

# test service+route
curl -i -X GET --url 'http://localhost:8000/hello/complex' --header 'Host: main.svc'

# test rate-limit
for i in {1..10} ; do curl -X GET --url 'http://localhost:8000/hello/complex' --header 'Host: main.svc' ; done

curl -X POST http://localhost:8001/services/main-service/plugins --data "name=rate-limiting" --data "config.second=5"

for i in {1..10} ; do curl -X GET --url 'http://localhost:8000/hello/complex' --header 'Host: main.svc' ; done

# test basic-auth
curl -d "username=openvalue" http://localhost:8001/consumers/

curl -X POST http://localhost:8001/consumers/openvalue/basic-auth \
  --data "username=Aladdin" \
  --data "password=OpenSesame"


curl -X POST http://localhost:8001/services/main-service/plugins \
    --data "name=basic-auth"  \
    --data "config.hide_credentials=false"


curl -i -X GET --url 'http://localhost:8000/hello/complex' \
  --header 'Host: main.svc' \
  --header 'Authorization: Basic QWxhZGRpbjpPcGVuU2VzYW1l'