SPARQL Introductory Tutorial

    by Dr Noureddin Sadawi
    629 videos

-----------------
Applied Semanic Web.org
Emanuele Della Valle

http://www.slideshare.net/emanueledellavalle/03-sparql

RDF Schema (or) ontologies are again defined in-terms of Schema. 
They are also stored as collections of triples, relationship in schema defined as collections of Triples, we can use Sparql to query them. 

-----------------

https://code.google.com/p/void-impl/wiki/SPARQLQueriesForStatistics

-----------------


Download 
http://mirror.nus.edu.sg/apache/jena/binaries/apache-jena-2.13.0.zip
http://mirror.nus.edu.sg/apache/jena/binaries/apache-jena-fuseki-2.0.0.zip

export PATH=/cygdrive/c/Mohan/apps/apache-jena-2.13.0/bin/:$PATH
export PATH=/cygdrive/c/Mohan/apps/apache-jena-fuseki-2.0.0/bin/:$PATH
export PATH=/cygdrive/c/Mohan/apps/apache-jena-fuseki-2.0.0/:$PATH

cd /cygdrive/c/Mohan/apps/apache-jena-fuseki-2.0.0/
fuseki-server --update --mem /ds

http://localhost:3030/

Modified file from the book examples are checked in inside resources/delta project
--------------
Sparql

Ensure keywords are in CAPS (convention)
SELECT
FROM
WHERE
LIMIT
PREFIX
ORDER BY
OFFSET
NAMED
OPTIONAL
FILTER
UNION
NOT EXISTS
; -- consider SQL equivalent of AND (In Prolog it is equivalent to OR or Next)
MAX, MIN, SUM, AVG, COUNT



--------------

cd /cygdrive/c/Mohan/
wget -N http://www.learningsparql.com/2ndeditionexamples/LearningSPARQLExamples.zip

cd /cygdrive/c/Mohan/LearningSPARQLExamples/ 
arq --data ex002.ttl --query ex003.rq
arq --data ex012.ttl --query ex008.rq
arq --data ex012.ttl --query ex017.rq
arq --data ex054.ttl --query ex055.rq
arq --data ex054.ttl --query ex057.rq (Using OPTIONAL)
arq --data ex054.ttl --query ex061.rq (Using Multiple OPTIONAL)
arq --data ex054.ttl --query ex067.rq (Using NOT Exists)
arq --data ex054.ttl --query ex068.rq (Using MINUS)
arq --data ex072.ttl --data ex073.ttl  --data ex368.ttl  --query ex070.rq (Joining multiple dataset)
arq --data ex069.ttl --query ex094.rq (Using DISTINCT)
arq --data ex072.ttl --data ex073.ttl  --data ex368.ttl  --query ex090.rq (UNION)
arq --data ex145.ttl --query ex116.rq (rdfs:label)
arq --data ex145.ttl --query ex146.rq (ASCENDING DESCENDING ORDER BY)
arq --data ex145.ttl --query ex153.rq (MAX function)
arq --data ex145.ttl --query ex160.rq (GROUP by aggregation function)
arq --data ex145.ttl --query ex162.rq (GROUP by predicate counts)
arq --data ex145.ttl --query ex162.rq (GROUP by SUM, and FILTER HAVING > 20)
arq --data ex145.ttl --query ex141.rq  (Sub String functon)  
arq --data ex145.ttl --query ex141.rq  (Sub String functon)  
arq --data ex145.ttl --query ex144.rq  (Bind variable)
  
arq --data ex040.ttl --data ex041.ttl (Compare 40 and 41 for differences for blank-nodes)
arq --data ex041.ttl --query ex088.rq (No query, sample blank node, grouping attributes)
arq  --query ex088_mine.rq	(Querying remote Dataset, where ttl is located in some URL)

arq  --data ex040.ttl --query ex167.rq (Qeurying remote Sparql EndPoint)

arq  --data ex069.ttl --data ex122.ttl --query ex123.rq (Qeurying remote Sparql EndPoint)
arq  --query ex123.rq (Qeurying from 'default graph', or multiple dataset combined into default graph internally)
 
arq  --query ex126.rq	(Named query graph)
arq  --query m_ex126.rq	(Multiple Named Graph)
arq  --query m_ex127.rq	(Multiple Named Graph, and Default graph in single query)

 
--------------
Using Fuseki server

Use UI for uploading data (TTL) and query

--------------
http://dbpedia.org/sparql
http://dbpedia.org/snorql/

http://dbpedia.org/snorql/?query=

SELECT *  WHERE 
{
?a ?b ?c .
} LIMIT 20 

--

PREFIX dbpedia-owl. <http://www.dbpedia.org/ontology>

SELECT ?s 
{	?s a dbpedia-owl:Drug .}
 
--
select distinct ?Concept 
where {[] a ?Concept} 
LIMIT 100

--

PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
PREFIX dbpedia-owl: <http://www.dbpedia.org/ontology>

SELECT ?drug ?category
WHERE {?drug a dbpedia-owl:Drug ;
		skos:subject ?category . }
		
--


# filename: ex017.rq

PREFIX ab: <http://learningsparql.com/ns/addressbook#> 

SELECT ?first ?last
WHERE
{
  ?person ab:homeTel "(229) 276-5135" . 
  ?person ab:firstName ?first . 
  ?person ab:lastName  ?last . 
}


# filename: ex017.rq

PREFIX ab: <http://learningsparql.com/ns/addressbook#> 

SELECT ?first ?last
WHERE
{
  ?person ab:homeTel "(229) 276-5135" ; 
  		  ab:firstName ?first ; 
  		  ab:lastName  ?last . 
}


--------

NOT EXISTS { ?s ab:workTel ?workNum }
FILTER(!bound(?workNum))

--------

Query distinct predicate (column ) of subject

PREFIX ab: <http://learningsparql.com/ns/addressbook#>

SELECT DISTINCT ?p 
WHERE
{
  ?s ?p ?o.
}

--------


# filename: ex070.rq

PREFIX ab: <http://learningsparql.com/ns/addressbook#> 

SELECT ?last ?first ?courseName 
WHERE
{
  { ?s       ab:firstName ?first ;
      		 ab:lastName ?last ;
             ab:takingCouse ?course .
    ?course  ab:courseTitle "Using SPARQL with non-RDF Data" .
  }
}

--------

LIMIT 1 OFFSET 0
LIMIT 10 OFFSET 10

--------

PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

SELECT ?label
WHERE
{ ?s rdfs:label ?label . }
LIMIT 2

----------

Find mealName that is lowest in price


PREFIX e: <http://learningsparql.com/ns/expenses#>

SELECT DISTINCT ?mealName ?date ?amount
WHERE
{ 
  ?s e:description ?mealName ;
     e:date ?date ;
     e:amount ?amount .
}
ORDER by ASC(?amount)
LIMIT 1

----------

PREFIX e: <http://learningsparql.com/ns/expenses#>

SELECT (MAX(?amount) as ?maxAmount)
WHERE
{ 
  ?s e:amount ?amount .
}

---

PREFIX e: <http://learningsparql.com/ns/expenses#>

SELECT (SUM(?amount) as ?totalAmount) (AVG(?amount) as ?averageAmount)
WHERE
{ 
  ?s e:amount ?amount .
}

----------

PREFIX e: <http://learningsparql.com/ns/expenses#>

SELECT COUNT(*)
WHERE
{ 
  ?s e:description ?mealName ;
     e:date ?date ;
     e:amount ?amount .
}


----------


PREFIX e: <http://learningsparql.com/ns/expenses#>

SELECT (sum(?amount) as ?amountSum)
WHERE
{ 
  ?s e:description ?mealName ;
     e:date ?date ;
     e:amount ?amount .
}

GROUP BY ?date 
-------------

PREFIX e: <http://learningsparql.com/ns/expenses#>

SELECT ?date (sum(?amount) as ?amountSum)
WHERE
{ 
  ?s e:description ?mealName ;
     e:date ?date ;
     e:amount ?amount .
}
GROUP BY ?date 
HAVING (SUM(?amount) > 40)

or

HAVING (?amountSum > 40)

--------------
Subqueries
--------------


PREFIX d: <http://learningsparql.com/ns/data#>
PREFIX ab: <http://learningsparql.com/ns/addressbook#>

SELECT ?first ?last ?courseTitle2
WHERE
{ 
  ?s ab:firstName ?first ;
     ab:lastName ?last ;
     ab:takingCourse ?cn .
 ?cn ab:courseTitle ?courseTitle .

 ?c ab:courseTitle ?courseTitle2 .

}

---

PREFIX d: <http://learningsparql.com/ns/data#>
PREFIX ab: <http://learningsparql.com/ns/addressbook#>

SELECT ?first ?courseTitle
WHERE
{ 
  ?s ab:firstName ?first .
  ?c ab:courseTitle ?courseTitle .

}




PREFIX d: <http://learningsparql.com/ns/data#>
PREFIX ab: <http://learningsparql.com/ns/addressbook#>

SELECT ?first ?courseTitle
WHERE
{ 
  {	SELECT ?first  WHERE { ?s ab:firstName ?first . }	}
  {	SELECT ?courseTitle  WHERE { ?c ab:courseTitle ?courseTitle . }	}
}


---

PREFIX e: <http://learningsparql.com/ns/expenses#>

SELECT ?date ?amount ((?amount * 0.2) as ?tip) ((?amount + ?tip) as ?total)
WHERE
{ 
  ?s e:description ?mealName ;
     e:date ?date ;
     e:amount ?amount .
}

PREFIX e: <http://learningsparql.com/ns/expenses#>

SELECT (UCASE(SUBSTR(?mealName,1,3)) as ?mealCode) ?amount
WHERE
{ 
  ?s e:description ?mealName ;
     e:date ?date ;
     e:amount ?amount .
}
------------

PREFIX  dc:  <http://purl.org/dc/elements/1.1/>
SELECT  ?title
WHERE   { ?x dc:title ?title
          FILTER regex(?title, "^SPARQL") 
        }
        
Query result
<<title>>
"SPARQL Tutorial"

------------

# Querying remote dataset
PREFIX ab: <http://learningsparql.com/ns/addressbook#> 

SELECT ?firstName ?lastName ?streetAddress ?city ?region ?postalCode
FROM <http://learningsparql.com/2ndeditionexamples/ex041.ttl>
WHERE 
{ 
  ?s ab:firstName ?firstName ;
     ab:lastName ?lastName ;
     ab:address ?address . 

  ?address ab:postalCode ?postalCode ;
           ab:city ?city ;
           ab:streetAddress ?streetAddress ;
           ab:region ?region . 
}


OR

From describes dataset, could be relative path to ttl, could be absolute path or URL to ttl file.

FROM <http://learningsparql.com/2ndeditionexamples/ex041.ttl>
FROM <ex041.ttl>	-- Current directory
FROM </cygdrive/c/Mohan/LearningSPARQLExamples/ex041.ttl>	-- Current directory

------------
***GOTCHA***

Dataset not specified in query nor provided on command line.

Above error appears when query remote sparql endpoint. It could be bug in ARQ tool.
We could overcome by specifying local ttl file as --data argument, that ARQ would use, but still queries remote endpoint

------------
Named graphs (multiple graphs, opposite of default graph) are conveient mechanism to "partioning" a RDF triple data store
(Storing many independent but possibly related graphs in it)

Named graphs are idea to handle multiple RDF graph in single document/repoistory
and naming them with URI provides useful additional functionality.

A set of Named grphas is collection of RDF graphs, Each one is named with a URI REF

It is also used to get source or provenance of the data.
---


@prefix foaf: <http://xmlns.com/foaf/0.1/> .
 
<http://example.org/joe#me> a foaf:Person .
<http://example.org/joe#me> foaf:homepage <http://example.org/joe/index.html> .
<http://example.org/joe#me> foaf:mbox <mailto:joe@example.org> .
<http://example.org/joe#me> foaf:name "Joe Lambda" .

---

PREFIX foaf: <http://xmlns.com/foaf/0.1/>
 
SELECT ?homepage
 
FROM NAMED <http://example.org/joe>
 
WHERE {
	GRAPH <http://example.org/joe> {
		?person foaf:homepage ?homepage .
		?person foaf:mbox <mailto:joe@example.org> .
	}
}

or

GRAPHY ?g (for any graph, chooses all the grpah)

---------------

SELET ?lname ?courseName
FROM <ex069.ttll>
FROM NAMED <ex125.ttl>
FROM NAMED <ex122.ttl>
WHERE {
	{?student ab:lastName  ?lname  }
	UNION
	{ GRAPH <ex125.ttl> 
		{
		?course ab:courseTitle ?courseName .
		}
	}	
}

--
#filename m_ex126.rq

PREFIX ab: <http://learningsparql.com/ns/addressbook#> 

SELET ?lname ?courseName
FROM <ex069.ttll>
FROM NAMED <http://learningsparql.com/2ndeditionexamples/ex125.ttl>
FROM NAMED <ex122.ttl>
WHERE {
	{?student ab:lastName  ?lname  }
	UNION
	{ GRAPH <http://learningsparql.com/2ndeditionexamples/ex125.ttl> 
		{
		?course ab:courseTitle ?courseName .
		}
	}	
}



---------------



