statusListener(OnConsoleStatusListener)

def appenderList = ["ROLLING"]
def WEBAPP_DIR = "."
def APP = ""
def ENVIRONMENT = ""
def HOSTNAME = hostname

appenderList.add("CONSOLE")

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{yyyy-MM-dd'T'HH:mm:ss.SSS}|%-5level|${HOSTNAME}|${APP}|%logger{36}|%X{requestId}|%msg%n"
    }
}

appender("ROLLING", RollingFileAppender) {
    file = "${WEBAPP_DIR}/logs/${APP}-${ENVIRONMENT}.log"
    encoder(PatternLayoutEncoder) {
        Pattern = "%d{yyyy-MM-dd'T'HH:mm:ss.SSS}|%-5level|${HOSTNAME}|${APP}|%logger{36}|%X{requestId}|%msg%n"
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        maxHistory = 30
        fileNamePattern = "${WEBAPP_DIR}/logs/${APP}-${ENVIRONMENT}.%d{yyyy-MM-dd}.log"
    }
}

root(INFO, appenderList)