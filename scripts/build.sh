#!/bin/bash

APP_DIR="springbootUnittest"
COMPONENT_DIR="springbootUnittest/scripts"
PAYLOAD_DIR="/home/ec2-user"
#Build application package
 ./gradlew clean assemble

echo "Moving application artefact to payload directory"
#Setup payload for upload
PAYLOAD_APP_DIR="${PAYLOAD_DIR}/app"
mkdir -p $PAYLOAD_APP_DIR
chown -R ec2-user:ec2-user $PAYLOAD_APP_DIR
cp -r build/libs/*.jar "${PAYLOAD_APP_DIR}"
chmod 777 "${PAYLOAD_APP_DIR}"/*
echo "Moving application scripts to payload directory"
cp -r "${COMPONENT_DIR}/"* "${PAYLOAD_DIR}"
