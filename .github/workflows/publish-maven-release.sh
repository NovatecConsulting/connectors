name: Publish Maven SNAPSHOT to GitHub Packages

on:
  push:
    tags:
      - '[0-9]+.[0-9]+.[0-9]+'

jobs:
  publish:
    runs-on: ubuntu-latest 
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          distribution: 'temurin'
          java-version: '11'
          cache: 'maven'
      - name: Build with Maven
        run: mvn --batch-mode install
      - name: Publish package
        run: mvn --batch-mode -DskipTests deploy
        env:
          GITHUB_TOKEN: ${{ secrets.DEPLOY_GITHUB_TOKEN }}
