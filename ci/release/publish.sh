#!/usr/bin/env bash
# shellcheck shell=bash

set -euo pipefail

#publish to local maven
#./gradlew publishToMavenLocal -P

#publish to Sonatype maven
gradle wrapper
./gradlew clean publishToSonatype closeSonatypeStagingRepository -PSONATYPE_USER=$SONATYPE_USER -PSONATYPE_PASSWORD=$SONATYPE_PASSWORD -PSIGNING_KEY_ID=$SIGNING_KEY_ID -PSIGNING_PASSWORD=$SIGNING_PASSWORD -PSIGNING_KEY=$SIGNING_KEY
