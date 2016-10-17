web: java -Dserver.port=$PORT $JAVA_OPTS -jar cct-web/target/*.jar -javaagent:/app/newrelic/newrelic.jar
worker: java -Dserver.port=$PORT $JAVA_OPTS -jar cct-worker/target/*.jar 