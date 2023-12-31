#! /bin/bash

set -eo pipefail

printHelp() {
    echo "This script is for running all tests in Spring Application with Docker container."
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

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" &>/dev/null && pwd)"

cd "$SCRIPT_DIR"
cd ../docker && PROFILE="$PROFILE" docker-compose up postgres -d
cd ../ && ./gradlew test
