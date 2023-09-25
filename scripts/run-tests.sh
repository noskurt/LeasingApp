#! /bin/bash

set -eo pipefail

printHelp() {
    echo "This script is for running all tests in Spring Application."
    echo ""
    echo "Options:"
    echo "  -h    Print this"
}

while :; do
    case $1 in
    -h | --help)
        printHelp
        exit 0
        ;;
    -?*)
        echo "Error: Unknown option: $1"
        printHelp
        exit 0
        ;;
    *)
        break
        ;;
    esac
    shift
done

cd ../docker && docker-compose up postgres -d
cd ../ && ./gradlew test
