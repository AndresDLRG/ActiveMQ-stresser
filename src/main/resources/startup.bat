@echo OFF

REM You can set your JAVA_HOME
REM set JAVA_HOME=

REM You can change configuration file for other 
set CONFIG_FILE=config.properties

set current=%cd%
pushd ..
set ACTIVEMQ_STRESSER_HOME=%cd%
popd

set CLASSPATH=%ACTIVEMQ_STRESSER_HOME%\bin\*
set CLASSPATH=%CLASSPATH%;%ACTIVEMQ_STRESSER_HOME%\lib\*
set CLASSPATH=%CLASSPATH%;%ACTIVEMQ_STRESSER_HOME%\conf
set CLASSPATH=%CLASSPATH%;%ACTIVEMQ_STRESSER_HOME%\user-lib\*

echo **************************************************
echo * configFile: %CONFIG_FILE%
echo * classpath: %CLASSPATH%
echo * JAVA_HOME: %JAVA_HOME%
echo **************************************************
echo.
echo.

java -DconfigFile=%CONFIG_FILE% -cp %CLASSPATH% andresdlrg.activemq.stresser.main.AppLauncher