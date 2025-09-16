# My Personal Project: Personal Investment Statistics Tracker

## Project Description

My application will track past investment logs, while including statistics for the trader based off of previous and current investments.

Those who will use my app are primarily investors of various levels interested in improving their trading skills, especially those who would love to see various statistics of their previous and current investments such as their overall ROI, money earned/lost, and other statistics. 

This project is of interest to me because of my interest in statistics and finance, where implementing an application that enables me to utilize both of these interests enables me to advance my knowledge in finance and statistics while showing the skills learned in CPSC210.

## User Stories

*4 Completed user stories:*

* As a user, I want to be able to add any number of new logs at one time to the table of current logs where I input the date, how much money I initially invested, the current value of my investment, and if I still have any money invested in a certain company alongside their general sector (e.g. energy, technology, health, etc.).

* As a user, I want to be able to filter the logs I see based on whether an investment is current or not. 

* As a user, I want to be able to delete any investment logs from the table of current logs. 

* As a user, I want to see a table of all the logs I added.

*Additional user stories:*

* As a user, I want to be able to see a summary of my investments, featuring some important basic statistics.

*For phase 2:*

As a user, I want to be able to load my table of investments logs.

As a user, I want to save any changes I have made to my table of investment logs.

## Instructions for End User

* You can add any an arbitrary number of new logs by clicking on *"Add new log"* and filling out any necessary information.

* You can generate the table of current investments by clicking on *"View current investments"*

* You can generate the table of total investments by clicking on *"View all investments"*

* You can locate my visual component by generating the table of current investments or the table of total investments.

* You can save the state of my application by clicking on *"Save log tracker to file"*

* You can reload the state of my application by clicking on *"Load log tracker to file"*

## Phase 4: Task 2

*Sample events that occur:* \
Wed Mar 26 22:50:25 PDT 2025 \
Created a new log for Amazon \
Wed Mar 26 22:50:25 PDT 2025 \
Added Amazon to Logs list \
Wed Mar 26 22:50:49 PDT 2025 \
Created a new log for EA SPORTS \
Wed Mar 26 22:50:49 PDT 2025 \
Added EA SPORTS to Logs list \
Wed Mar 26 22:50:52 PDT 2025 \
Removed Amazon from Logs list \
Wed Mar 26 22:51:07 PDT 2025 \
Saved EA SPORTS to Logs list \
Wed Mar 26 22:51:07 PDT 2025 \
Saved new logs to JSON file!

## Phase 4: Task 3

One refactoring I would do is to have `CurrentInvestmentLogTable` and `TotalInvestmentLogTable` extend a new superclass or interface named `InvestmentLogTable` that contains the methods used in both classes where their implementations are similar to one another. This is to make my code less redundant in those two classes, as most of the code in those classes are relatively similar to each other. Hence, it would increase readability, making it easier for other people to understand my code. It would also be easier to maintain the code, as there is less redundancy in my code, making it easier to catch bugs. 

This refactoring would also make it easier for me to enhance my code without worrying about properly formatting my table or whatnot, since I will have a basic table interface or superclass already, and I would only need to worry about the implementation of a new table class that differentiates from the other table classes that I have. This would mean that all of my table subclasses follow the same method signatures and design patterns, reducing inconsistencies when presenting the data of logs.