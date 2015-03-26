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


Download 
http://mirror.nus.edu.sg/apache/jena/binaries/apache-jena-2.13.0.zip
http://mirror.nus.edu.sg/apache/jena/binaries/apache-jena-fuseki-2.0.0.zip

export PATH=/cygdrive/c/Mohan/apps/apache-jena-2.13.0/bin/:$PATH
export PATH=/cygdrive/c/Mohan/apps/apache-jena-fuseki-2.0.0/bin/:$PATH
export PATH=/cygdrive/c/Mohan/apps/apache-jena-fuseki-2.0.0/:$PATH

fuseki-server --update --mem /ds

http://localhost:3030/

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
arq --data ex072.ttl --data ex073.ttl  --data ex368.ttl  --query ex070.rq (Using MINUS)
 
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

Using multiple datasets

ex072.ttl
ex073.ttl
ex368.ttl

ex070.rq


--------


