@echo OFF

set CONFIG_FILE=config.properties
set CONFIG_LOG_FILE = ""

set CLASSPATH=.\activemq-stresser-1.0-ALPHA.jar;.\lib\*;.\conf;.\user-lib\*

echo **************************************************
echo * configFile: %CONFIG_FILE%
echo * classpath: %CLASSPATH%
echo **************************************************
echo.
echo.

java -DconfigFile=%CONFIG_FILE% -cp %CLASSPATH% andresdlrg.activemq.stresser.main.AppLauncher