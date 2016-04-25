# NewsAnalytics
News analytics refers to making analysis of the daily news to categorize into some insightful data. Being able to express news stories as numbers permits the manipulation of everyday information in a statistical  way that allows computers not only to make decisions once made only by humans, but to do so more  efficiently. 

Key Milestones in the project

Step 1: Research on finding out news patterns

Step 1: Develop WebCrawler 

Step 2: Filtering data generated 

Step 3:  Separating data into words, text and url

Step 4: Saving in a text file with filtered – relevant, distinct, stopwords removed

Step 5: Displaying news to the user allowing him to select his interested news

Step 6: Finding out the word generated from the interested news and showing the user news urls with the word contained in it

Step 7: Developing Test Cases


How was data filtered:

Filter out URL which contain 2016 
Split the URL based based on word, text and URL where
for eg: Consider the following URL http://www.slate.com/articles/health_and_science/future_tense/2016/04/biomedicine_facing_a_worse_replication_crisis_than_the_one_plaguing_psychology.html

Word: Biomedicine, facing, worse, replication, plaguing, psychology

Text: biomedicine_facing_a_worse_replication_crisis_than_the_one_plaguing_psychology

URL: http://www.slate.com/articles/health_and_science/future_tense/2016/04/biomedicine_facing_a_worse_replication_crisis_than_the_one_plaguing_psychology.html


Scala Tests

Total No of Scala Tests written: 11

Scala tests written on: Webcrawler, FilterNewsURL and LinkCleaner



Note:

In order to run this project you need:

Spark Installed
IntelliJ or Eclipse or any other IDE
Need to run the project on sbt or the IDE console

References:
Build on a simple crawler made by https://github.com/rynmrtn/scrawler/tree/master/src/main/scala

Credits:
Professor Robin Hillyard

TA: :Laksh Lumba



