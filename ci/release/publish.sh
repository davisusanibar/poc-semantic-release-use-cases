#!/usr/bin/env bash
# shellcheck shell=bash

set -euo pipefail

#publish to local maven
#./gradlew publishToMavenLocal -P

#publish to Sonatype maven
echo $PWD
ls -latr ~/
./gradlew clean publishToSonatype closeSonatypeStagingRepository
