# Fact_Checker2019-2020

Develop a fact-checking engine, which reads facts from a file (.tsv), check the veracity of these facts and returns a conﬁdence value between -1 (fact is false) and +1 (fact is true) for each fact in a result file (.ttl).

## Team
Team name: Oumama Msellek

Members: Oumama Msellek

### Instructions for project execution

The application is a maven project. The needed library (jsoup 1.12.1) and the input files are already included in the application.

For a successful execution of the project, follow the steps:

1. Download the project or clone the URL from the git 

2. Import and open the maven project in your IDE: In case you have downloaded the project, than choose to import from an existing     
   project. The other way is to import directly from the version control (git) and clone the git URL. The import process is related to   
   the IDE you choose to use https://eclipsesource.com/blogs/tutorials/egit-tutorial/ or https://www.jetbrains.com/help/idea/import-   
   project-or-module-wizard.html

3. Run the application (Checker.java).

4. Check the result file under the path "src\results". 

### Approach

The app starts at first with reading the input file (.tsv). It processes each line of this file, separates the Ids and their corresponding facts and stores them as a hash map and a list.
Each fact extracted from the previous step is treated separately. Using the regular expression concept, we delete at first any symbols or non needed alphabets, after that with the "statement" or the "predicate" of the fact ex.: died, honour, born.., we extract the "subject" and the "object" of the fact using the index and length of the fact.
e.g.:
```
Fact: "Naples is Alfonso IV of Aragon's nascence place." will be changed to "Naples is Alfonso IV of Aragon nascence place"
Subject: Alfonso IV of Aragon
Object: Naples
Statement: nascence place
```
Unfortunately some of the "statement" ex.:honour, stars, generator.. used in the facts are not similar to the ones used in Wikipedia info Box which makes the validation of the subject againts the object using the statement problematic. For this reason, we assigned for each fact a new predicated called "wiki statement" inspired from the wiki page.
e.g.:
```
"statement": nascence place --> "wiki statement": Born
"statement": better half --> "wiki statement": Spouse(s)
```
After we creat a new java object called "Fact" with the parameters: subject, object and wiki statement. For time gain, it is sometimes enough to search for the accuracy of a "Fact" only in the info Box of Wikipedia, for this reason we create the "level" parameter to determine the level of search for each "Fact". 

In the search for veracity step, we fetch the wiki page using the subject, processing each "Fact", we search for the veracity in this page given its "wiki statement" and "level". 

Finally, we write the results in a file (.ttl) respecting the result format described in the mini-project presentation and count the elapsed time taken to process all the facts of the input file.


## Non working fact types:

Facts treating the Career History of the player:
```
"Anthony Davis team is Los Angeles Lakers"
```
Facts with name separated by a "'s" or "'" are likely to be non working example:
```
"Clerks subsidiary is Brian O'Halloran"
```
Facts which subject could not be found on Wikipedia:
```
"Impetus Technologies' foundation place is Portsmouth" 
```
Facts which subject to be searched has many Wikipedia pages:
```
"Justin James team is Sacramento Kings" 
```
Facts which meaning is ambiguous are considered a false positive:
```
"Nelson Rockefeller spouse is New York City"

```

More information can be found in the pdf file.
