#!/bin/bash
# process name
readonly PROC_NAME="tour-boot"
# jar file full path
readonly DAEMON="/app/api/tour-0.1.0.jar"
# pid path and name
readonly PID_PATH="/app/api/kioskApi/"
readonly PROC_PID="${PID_PATH}${PROC_NAME}.pid"

# start
start()
{
   echo "Starting ${PROC_NAME}..."
    local PID=$(get_status)
    if [ -n "${PID}" ]; then
        echo "${PROC_NAME} is already running"
        exit 0
    fi
    nohup java -jar -Dspring.profiles.active=prod -XX:MaxPermSize=128m -Xms512m -Xmx2048m "${DAEMON}" > /dev/null 2>&1 &

    local PID=${!}

    if [ -n ${PID} ]; then
        echo " - Starting..."
        echo " - Created Process ID in ${PROC_PID}"
        echo ${PID} > ${PROC_PID}
    else
        echo " - failed to start."
    fi
}
# stop
stop()
{
    echo "Stopping ${PROC_NAME}..."
    local DAEMON_PID=`cat "${PROC_PID}"`

    if [ "$DAEMON_PID" -lt 3 ]; then
        echo "${PROC_NAME} was not running."
    else
        kill $DAEMON_PID
        rm -f $PROC_PID
        echo " - Shutdown ...."
    fi
}
# status
status()
{
    local PID=$(get_status)
    if [ -n "${PID}" ]; then
        echo "${PROC_NAME} is running"
    else
        echo "${PROC_NAME} is stopped"
        # start daemon
        #nohup java -jar "${DAEMON}" > /dev/null 2>&1 &
    fi
}

get_status()
{
    ps ux | grep ${PROC_NAME} | grep -v grep | awk '{print $2}'
}

case "$1" in
start)
start
sleep 7
;;
stop)
stop
sleep 5
;;
status)
status "${PROC_NAME}"
    ;;
    *)
    echo "Usage: $0 {start | stop | status }"
esac
exit 0