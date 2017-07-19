#!/bin/sh

# You can set your JAVA_HOME
# JAVA_HOME=

#You can change configuration file for other
CONFIG_FILE=config.properties

dir=$(pwd)
ACTIVEMQ_STRESSER_HOME="$(dirname "$dir")"

CLASSPATH=$ACTIVEMQ_STRESSER_HOME/bin/*
CLASSPATH=$CLASSPATH:$ACTIVEMQ_STRESSER_HOME/lib/*
CLASSPATH=$CLASSPATH:$ACTIVEMQ_STRESSER_HOME/conf
CLASSPATH=$CLASSPATH:$ACTIVEMQ_STRESSER_HOME/user-lib/*

echo "**************************************************"
echo "* configFile: $CONFIG_FILE"
echo "* classpath: $CLASSPATH"
echo "* JAVA_HOME: $JAVA_HOME"
echo "**************************************************"
echo 
echo

java -DconfigFile=$CONFIG_FILE -cp $CLASSPATH andresdlrg.activemq.stresser.main.AppLauncher