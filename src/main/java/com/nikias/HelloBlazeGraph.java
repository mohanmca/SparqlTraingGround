package com.nikias;

import java.util.Properties;

import org.openrdf.OpenRDFException;
import org.openrdf.model.impl.LiteralImpl;
import org.openrdf.model.impl.StatementImpl;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

import com.bigdata.journal.Options;
import com.bigdata.rdf.sail.BigdataSail;
import com.bigdata.rdf.sail.BigdataSailRepository;

public class HelloBlazeGraph {

	public static void main(String[] args) throws OpenRDFException {
		Repository repo = initializeRepository();
		URIImpl subject = new URIImpl("http://blazegraph.com/Blazegraph");
		URIImpl predicate = new URIImpl("http://blazegraph.com/says");
		LiteralImpl literal = new LiteralImpl("hello");
		org.openrdf.model.Statement stmt = new StatementImpl(subject,
				predicate, literal);

		RepositoryConnection conn = repo.getConnection();
		try {
			conn.begin();
			conn.add(stmt);
			conn.commit();
		} catch (Exception exception) {
			conn.rollback();
			throw exception;
		}
		if (repo instanceof BigdataSailRepository) {
			conn = ((BigdataSailRepository) repo).getReadOnlyConnection();
		} else {
			conn = repo.getConnection();
		}
		query(conn);
	}

	private static void query(RepositoryConnection conn)
			throws OpenRDFException {
		try {

			final TupleQuery tupleQuery = conn
					.prepareTupleQuery(QueryLanguage.SPARQL,
							"select ?p ?o where { <http://blazegraph.com/Blazegraph> ?p ?o . }");
			TupleQueryResult result = tupleQuery.evaluate();
			try {
				while (result.hasNext()) {
					BindingSet bindingSet = result.next();
					System.err.println(bindingSet);
				}
			} finally {
				result.close();
			}

		} finally {
			conn.close();
		}
	}

	private static Repository initializeRepository() throws RepositoryException {
		final Properties props = new Properties();
		// persistent file system located journal
		props.put(Options.BUFFER_MODE, "DiskRW");
		props.put(Options.FILE, "/tmp/journal.jnl");
		final BigdataSail sail = new BigdataSail(props);
		final Repository repo = new BigdataSailRepository(sail);
		repo.initialize();
		return repo;
	}
}
