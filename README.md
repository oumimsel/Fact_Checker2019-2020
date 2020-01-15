# Fact_Checker2019-2020

Develop a fact-checking engine, which reads facts from a file (.tsv), check the veracity of these facts and returns a conﬁdence value between -1 (fact is false) and +1 (fact is true) for each fact in a result file (.ttl).

## Team
Team name: Oumama Msellek

Members: Oumama Msellek

### Approach

approach
### Build and run the project

Say what the step will be

## Example of Non working facts
More information can be found in the pdf file:

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

