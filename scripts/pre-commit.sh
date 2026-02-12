#!/bin/sh

# Pre-commit hook to run CI checks locally

echo "Running pre-commit checks..."

# Run ktlint
echo "1. Running ktlintCheck..."
./gradlew ktlintCheck
if [ $? -ne 0 ]; then
    echo "ktlintCheck failed. Please fix formatting issues before committing."
    exit 1
fi

# Run unit tests
echo "2. Running unit tests..."
./gradlew :composeApp:testDebugUnitTest
if [ $? -ne 0 ]; then
    echo "Unit tests failed. Please fix test failures before committing."
    exit 1
fi

# Run Roborazzi verification
echo "3. Running Roborazzi verification..."
./gradlew :composeApp:verifyRoborazziDebug
if [ $? -ne 0 ]; then
    echo "Roborazzi verification failed. Please check screenshots before committing."
    exit 1
fi

echo "All checks passed! Committing..."
exit 0