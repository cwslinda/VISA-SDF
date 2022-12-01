#!/usr/bin/env bash
CLASSPATH="./target/client-1.0-SNAPSHOT.jar"

java -cp $CLASSPATH vttp2022.sdf.assessment.task02.client.StressMain $*
