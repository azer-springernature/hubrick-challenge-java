#!/bin/sh

./mvnw -q -Dmaven.test.skip=true clean install exec:java -Dexec.mainClass=HubrickStatisticsApplication -Dexec.args="$1"
