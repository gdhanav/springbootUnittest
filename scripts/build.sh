#!/bin/bash

APP_DIR="springbootUnittest"
COMPONENT_DIR="springbootUnittest/scripts"
PAYLOAD_DIR="/home/ec2-user"
#Build application package
cd "${APP_DIR}" && ./gradlew clean assemble && cd ..

echo "Moving application artefact to payload directory"
#Setup payload for upload
PAYLOAD_APP_DIR="${PAYLOAD_DIR}/app"
mkdir -p $PAYLOAD_APP_DIR
cp -r "${APP_DIR}/"build/libs/*.jar "${PAYLOAD_APP_DIR}"

echo "Moving application scripts to payload directory"
cp -r "${COMPONENT_DIR}/scripts/"* "${PAYLOAD_DIR}"

echo "Listing payload contents"
ls -R "${PAYLOAD_DIR}"