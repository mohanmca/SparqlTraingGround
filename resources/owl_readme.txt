Ref:	http://slideplayer.com/slide/2433699/

rdf:type ~~~ a  (We can use them interchangebly)

RDF Schema and OWL standards for storing vocabularies and ontologies

ex042.ttl - Few of the triples from RDF Schema vocabulary description 
of the Dublic Core Vocabulary. 

ex042.ttl - Defining Musician.

 
What is a resource - https://classes.soe.ucsc.edu/cmps183/Winter04/lectures/uri-url-urn.pdf

RDF Schema gives following properties.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
rdf:List
rdf:Property
rdf:type

RDF Schema gives following properties.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
rdfs:Class
rdfs:comment
rdfs:Datatype
rdfs:domain
rdfs:isDefinedBy
rdfs:label
rdfs:Literal
rdfs:range
rdfs:Resource
rdfs:seeAlso
rdfs:subClassOf
rdfs:subPropertyOf



---------------------
Example of new schema
---------------------

# filename: ex044.ttl

@prefix ab:   <http://learningsparql.com/ns/addressbook#> .
@prefix rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .

ab:playsInstrument
      rdf:type rdf:Property ;
      rdfs:comment "Identifies the instrument that someone plays" ;
      rdfs:label "plays instrument" ;
      rdfs:domain ab:Musician ;
      rdfs:range ab:MusicalInstrument .  

---------------

rdfs:domain dictates the subject *class* of the property

In Object oriented terms, Musician object should have property of playsInstrument.
in RDF, OWL, it is not like that, rather it is similar to Scala Traits.
			 An object that has palyInstrument propery is Musician object.

---------------
