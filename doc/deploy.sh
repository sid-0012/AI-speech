#!/bin/bash
echo "publish nls----------"

process_id=`ps -ef | grep nls.jar | grep -v grep |awk '{print $2}'`
if [ $process_id ] ; then
sudo kill -9 $process_id
fi

source /etc/profile
nohup java -jar -Dspring.profiles.active=prod ~/nls.jar > /dev/null 2>&1 &

echo "end publish"
