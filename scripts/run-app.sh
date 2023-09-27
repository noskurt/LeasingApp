#! /bin/bash

set -eo pipefail

printHelp() {
    echo "This script is for running the Spring Application in the Docker container."
    echo ""
    echo "Options:"
    echo "  -p    It's for the Spring Active Profile. If you want initial data, you need to pass 'load-data'"
    echo "  -h    Print this"
}

PROFILE="default"

while :; do
    case $1 in
    -p | --profile)
        PROFILE=$2
        shift
        ;;
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
cd .. && ./gradlew bootBuildImage --imageName=leasing/app
cd ./docker && PROFILE="$PROFILE" docker-compose up
