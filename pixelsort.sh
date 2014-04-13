#!/bin/bash

date >> logs.log
echo "Invoking PixelSorter to upload post to tumblr..." >> logs.log
java -jar ./target/pixelate-1.0-SNAPSHOT-jar-with-dependencies.jar >> logs.log 2>&1
echo "All done!" >> logs.log
