#!/bin/sh

# You can set your JAVA_HOME
# JAVA_HOME=

# You can change configuration file for other
CONFIG_FILE=config.properties
# You should change LOG_FILE if using multiple instances of the program at the same time
LOG_FILE=trace

dir=$(pwd)
ACTIVEMQ_STRESSER_HOME="$(dirname "$dir")"

CLASSPATH=$ACTIVEMQ_STRESSER_HOME/bin/*
CLASSPATH=$CLASSPATH:$ACTIVEMQ_STRESSER_HOME/lib/*
CLASSPATH=$CLASSPATH:$ACTIVEMQ_STRESSER_HOME/conf
CLASSPATH=$CLASSPATH:$ACTIVEMQ_STRESSER_HOME/user-lib/*

echo "**************************************************"
echo "* configFile: $CONFIG_FILE"
echo "* logFile: $LOG_FILE"
echo "* classpath: $CLASSPATH"
echo "* JAVA_HOME: $JAVA_HOME"
echo "**************************************************"
echo 
echo

java -DconfigFile=$CONFIG_FILE -DlogFile=$LOG_FILE -cp $CLASSPATH andresdlrg.activemq.stresser.main.AppLauncher