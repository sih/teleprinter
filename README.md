# Teleprinter
## Overview
This toy project takes football results from [football-data.co.uk] and outputs them via a fake Teleprinter (see [this Wikipedia article on the Vidiprinter for those too young to remember Grandstand](https://en.wikipedia.org/wiki/Vidiprinter)).

It is one of the inputs to the toy Apache Flink [in this same github project](https://github.com/sih/flinkball).

## Running

### Marking time
Run the Simulator class to iterate over all the data files (from 1993 to the current season, 2017/18). Note that the data files contain more data later on but even the original files have enough key info to use as output.

### Producing a full output file
Run the SimFilePublisher to produce a single CSV file containing all the results. All means from the beginning of the Premier League to a mid-season point in 2017/18 (when this was developed). 

This creates an output file at ````"./output/results.csv"```` which is used by all of the Flinkball examples.

## Classes

### SimFilePublisher
Iterates over all of the files and produces an ordered single, jagged file of results. 

_Ordered_ as the results are selected by year (although not necessarily by division). 

_Jagged_ as there is gradually more information in each of the files as the years progress. So initially there is the date, teams, full time score, and division. This moves to having half time scores and finally lot of additional info (e.g. refs, betting odds, etc.)

### Simulator
Use this to look at the results (tres interessant :-/) to get a feel for the data.

### Teleprinter
Use this to publish results (to a stream) between two dates. This can be used to simulate a season, a week, etc. Only limited validation of the dates is done so be sensible.

### SimSocketPublisher
Outputs the results of the simulator to a socket. Not really used currently but could be used to test out socket streams in Flink.

