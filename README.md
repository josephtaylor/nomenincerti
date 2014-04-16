nomen incerti
============


Description
------------
This is an auto-generated tumblr blog.
It searches google images for a random noun from a dictionary text file of nouns,
then it applies a pixel-sorting processing sketch to the image and saves it,
and finally, it uploads the picture with the noun as the caption to Tumblr.

Building the Project
---------------------
Unfortunately, this isn't the easiest thing to build.
I installed the processing core jar to my maven repo manually,
if you download processing and navigate to `$PROCESSING_HOME/core/library`
you can install the jar by running the following:
    
    mvn install:install-file -Dfile=core.jar -DgroupId=processing -DartifactId=core -Dversion=2.0.1 -Dpackaging=jar

I'm also making use of Tumblr's jumblr client which can be found
[Here](https://github.com/tumblr/jumblr).
It can then be installed in your local maven repo by just running `mvn clean install`
  
Once you have those two libraries in your local maven repo you should be ready to rock n roll
To create a runnable jar with all the dependencies do:
 
   `mvn clean compile assembly:single`

How it is automated
-------------------

I put the project on a free-tier AWS EC2 instance.
I added the `pixelsort.sh` script as a cron job by adding a line to the crontab.
It runs every hour.
 
Future plans
---------------

This thing is not very robust, currently. It fails to complete successfully about 25% of the time.
I plan to add in some failovers so that it is more successful.
Also, I plan to add multiple processing sketches to it and have it choose the sorting algorithm at random.
